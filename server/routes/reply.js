import express from 'express';
import Question from '../models/question';
import Reply from '../models/reply';
import mongoose from 'mongoose';

const router = express.Router();
/*
    READ REPLY: GET /api/reply
*/
router.get('/', (req, res) => {
    Reply.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, replies) => {
        if(err) throw err;
        res.json(replies);
    })
});

/*
    WRITE REPLY: POST /api/reply
    BODY SAMPLE: 
    { 
        "content": "댓글컨텐츠",
    }
    ERROR CODES
        1: NOT LOGGED IN
        2: EMPTY CONTENTS
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
    if((typeof req.body.content !== 'string') || (req.body.content === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    // CREATE NEW REPLY
    let reply = new Reply({
        accountId: req.session.loginInfo.loginId,
        content: req.body.content
    });

    // save in db
    reply.save( err => {
        if(err) throw err;
        return res.json({ success: true });
    })
});

/*
    DELETE REPLY: DELETE /api/reply/:id
    ERROR CODES
        1: INVALID ID
        2: NOT LOGGED IN
        3: NO RESOURCE
        4: PERMISSION FAILURE
*/
router.delete('/:id', (req, res) => {

    // check reply in validity
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

    // find reply and check for writer
    Reply.findById(req.params.id, (err, reply) => {
        if(err) throw err;

        if(!reply) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(reply.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }
        // REMOVE THE REPLY
        reply.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});

/*
    MODIFY REPLY: PUT /api/reply/:id
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

    // check reply id validity
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

    // find reply
    Reply.findById(req.params.id, (err, reply) => {
        if(err) throw err;

        // if reply does not exist
        if(!reply) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(reply.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }

        // MODIFY AND SAVE IN DB
        reply.content =  req.body.content;   
        reply.date.edited = new Date();
        reply.is_edited = true;

        reply.save((err, reply) => {
            if(err) throw err;

            return res.json({
                success: true,
                reply
            });
        });
    });
});

export default router;