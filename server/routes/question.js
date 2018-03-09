import express from 'express';
// import Account from '../models/account';
import Question from '../models/question';
import mongoose from 'mongoose';

const router = express.Router();
/*
    READ QUESTION: GET /api/question
*/
router.get('/', (req, res) => {
    Question.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, questions) => {
        if(err) throw err;
        res.json(questions);
    })
});
/*
    READ QUESTION: GET /api/question/list
    ERROR CODES
        1: NO RESOURCE  
*/
router.get('/list', (req, res) => {
    if(req.session.loginInfo.type ==='parents'){
        // find question and check for writer    
        Question.find({"accountId":req.session.loginInfo.loginId}, (err, question) => {
            if(err) throw err;
            if(!question) {
                return res.status(404).json({
                    error: "NO RESOURCE",
                    code: 1
                });
            }
        }).sort({"date.edited": -1})
        .exec((err, questions) => {
            if(err) throw err;
            res.json(questions);
        });
    } else {
        // find question and check for writer    
        Question.find({"receivers.receiverId":req.session.loginInfo.loginId}, (err, question) => {
            if(err) throw err;
            if(!question) {
                return res.status(404).json({
                    error: "NO RESOURCE",
                    code: 1
                });
            }
        }).sort({"date.edited": -1})
        .exec((err, questions) => {
            if(err) throw err;
            res.json(questions);
        });
    }

});
/*
    READ QUESTION: GET /api/question/:id
    ERROR CODES
        1: INVALID ID
        2: NO RESOURCE  
*/
router.get('/:id', (req, res) => {
    // check question in validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)){
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }
    // find question and check for writer    
    Question.findById(req.params.id, (err, question) => {
        if(err) throw err;
        if(!question) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 2
            });
        }
        if(question.accountId !== req.session.loginInfo.loginId){
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 2
            }); 
        }
        res.json(question);
    });
});
/*
    WRITE QUESTION: POST /api/question
    BODY SAMPLE: 
    { 
        "receivers":[
            {
                "receiverId":"bbd@educhoice.com"
            }
        ],
        "questionTitle": "문의타이틀",
        "questionContent": "문의컨텐츠"
    }
    ERROR CODES
        1: NOT LOGGED IN
        2: EMPTY CONTENTS
        3: NOT LOGGED IN
        4: NOT PARENTS
        5: NON-EXISTENCE ACCOUNT
*/
router.post('/', (req, res) => {
    // check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json({
            error: "NOT LOGGED IN",
            code: 1
        });
    }

    // check data valid
    
    if(typeof req.body.receivers === 'undefined') {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    
    // req.body.receivers.forEach(function(receiver){
        // Account.find({'loginId':receiver.receiverId}, (err, account) => {
        //     if(err) throw err;
        //     if(account !==''){
        //         return res.status(404).json({
        //             error: "NO RESOURCE",
        //             code: 2
        //         }); 
        //     }
        // });        
    // })
    if((typeof req.body.questionTitle !== 'string') || (req.body.questionTitle === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.questionContent !== 'string') || (req.body.questionContent === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    // check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json({
            error: "NOT LOGGED IN",
            code: 3
        });
    }
    // check sender type
    if(req.session.loginInfo.type !== 'parents') {
        return res.status(403).json({
            error: "NOT PARENTS",
            code: 4
        });
    }    
    // CREATE NEW QUESTION
    let question = new Question({
        accountId: req.session.loginInfo.loginId,
        receivers: req.body.receivers,
        questionTitle: req.body.questionTitle,
        questionContent: req.body.questionContent
    });

    // save in db
    question.save( err => {
        if(err) throw err;
        return res.json({ success: true });
    })
});
/*
    DELETE Question: DELETE /api/question/:id
    ERROR CODES
        1: INVALID ID
        2: NOT LOGGED IN
        3: NO RESOURCE
        4: PERMISSION FAILURE
*/
router.delete('/:id', (req, res) => {

    // check question in validity
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

    // find question and check for writer
    Question.findById(req.params.id, (err, question) => {
        if(err) throw err;

        if(!question) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(question.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }
        // REMOVE THE QUESTION
        Question.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});

/*
    MODIFY QUESTION: PUT /api/question/:id
    BODY SAMPLE: 
    { 
        "questionTitle": "문의타이틀",
        "questionContent": "문의컨텐츠"
    }
    ERROR CODES
        1: INVALID ID,
        2: EMPTY CONTENTS,
        3: NOT LOGGED IN
        4: NO RESOURCE
        5: PERMISSION FAILURE
*/
router.put('/:id', (req, res) => {

    // check question id validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }

    // check data valid
    if((typeof req.body.questionTitle !== 'string') || (req.body.questionTitle === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.questionContent !== 'string') || (req.body.questionContent === "")) {
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

    // find question
    Question.findById(req.params.id, (err, question) => {
        if(err) throw err;

        // if question does not exist
        if(!question) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(question.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }

        // MODIFY AND SAVE IN DB
        question.questionTitle =  req.body.questionTitle;
        question.questionContent = req.body.questionContent;      
        question.date.edited = new Date();
        question.is_edited = true;

        question.save((err, question) => {
            if(err) throw err;

            return res.json({
                success: true,
                question
            });
        });
    });
});

export default router;