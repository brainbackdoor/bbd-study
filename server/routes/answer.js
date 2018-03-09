import express from 'express';
import Question from '../models/question';
import Answer from '../models/answer';
import mongoose from 'mongoose';

const router = express.Router();
/*
    READ ANSWER: GET /api/answer
*/
router.get('/', (req, res) => {
    Answer.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, answers) => {
        if(err) throw err;
        res.json(answers);
    })
});

/*
    WRITE ANSWER: POST /api/answer/:questionId
    BODY SAMPLE: 
    { 
        "content": "댓글컨텐츠",
    }
    ERROR CODES
        1: NOT LOGGED IN
        2: EMPTY CONTENTS
*/
router.post('/:questionId', (req, res) => {
    // check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json({
            error: "NOT LOGGED IN",
            code: 1
        });
    }

    // check data valid
    if((typeof req.body.content !== 'string') || (req.body.content === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    Question.findById(req.params.questionId, (err, question) => {
        if(err) throw err;
        if(!question) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 2
            });
        }
        // CREATE NEW ANSWER
        let answer = new Answer({
            accountId: req.session.loginInfo.loginId,
            accountName: req.session.loginInfo.name,
            questionerId: question.accountId,
            questionId: question._id,
            content: req.body.content
        });

        // save in db
        answer.save( err => {
            if(err) throw err;
            return res.json({ success: true });
        })
    });
});

/*
    DELETE ANSWER: DELETE /api/answer/:id
    ERROR CODES
        1: INVALID ID
        2: NOT LOGGED IN
        3: NO RESOURCE
        4: PERMISSION FAILURE
*/
router.delete('/:id', (req, res) => {

    // check answer in validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)){
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }

    // check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json({
            error: "NOT LOGGED IN",
            code: 2
        });
    }

    // find answer and check for writer
    Answer.findById(req.params.id, (err, answer) => {
        if(err) throw err;

        if(!answer) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(answer.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }
        // REMOVE THE ANSWER
        answer.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});

/*
    MODIFY ANSWER: PUT /api/answer/:id
    BODY SAMPLE: 
    { 
        "content": "댓글컨텐츠"
    }
    ERROR CODES
        1: INVALID ID,
        2: EMPTY CONTENTS,
        3: NOT LOGGED IN
        4: NO RESOURCE
        5: PERMISSION FAILURE
*/
router.put('/:id', (req, res) => {

    // check answer id validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }

    // check data valid
    if((typeof req.body.content !== 'string') || (req.body.content === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }

    //check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json ({
            error: "NOT LOGGED IN",
            code: 3
        });
    }

    // find answer
    Answer.findById(req.params.id, (err, answer) => {
        if(err) throw err;

        // if answer does not exist
        if(!answer) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(answer.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }

        // MODIFY AND SAVE IN DB
        answer.content =  req.body.content;   
        answer.date.edited = new Date();
        answer.is_edited = true;

        answer.save((err, answer) => {
            if(err) throw err;

            return res.json({
                success: true,
                answer
            });
        });
    });
});

export default router;