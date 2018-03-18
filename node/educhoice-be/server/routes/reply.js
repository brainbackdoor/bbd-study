import express from 'express';
import Answer from '../models/answer';
import Reply from '../models/reply';
import mongoose from 'mongoose';

const router = express.Router();

/**
 * @api {get} /api/reply Get Reply Information [Dev]
 * @apiVersion 0.1.0
 * @apiName GetRepies
 * @apiGroup Reply
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 *[
 *   {
 *       "date": {
 *           "created": "2018-03-09T19:05:45.542Z",
 *           "edited": "2018-03-09T19:05:45.542Z"
 *       },
 *       "is_edited": false,
 *       "_id": "5aa2db096e3e895a54d38e9a",
 *       "accountId": "brainbackdoor@modoohakwon.com",
 *       "role": "parents",
 *       "accountName": "bbd",
 *       "questionId": "5aa2bc5eb4b817f1759f0cc2",
 *       "answerId": "5aa2d83a9981b98349922da0",
 *       "content": "댓글 컨텐츠",
 *       "__v": 0
 *   }
 *]
 * 
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
/**
 * @api {post} /api/reply/:answerId Post Reply information
 * @apiVersion 0.1.0
 * @apiName PostReply
 * @apiGroup Reply
 * 
 * @apiParam {String} content 댓글내용
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 201 OK
 * {
 *      "success": true
 * }
 * 
 * @apiError NOT LOGGED IN
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 NOT LOGGED IN
 *     {
 *       "error": "NOT LOGGED IN",
 *       "code" : 1
 *     }
 * 
 * @apiError EMPTY CONTENTS
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 EMPTY CONTENTS
 *     {
 *       "error": "EMPTY CONTENTS",
 *       "code" : 2
 *     }
 * 
 */
router.post('/:answerId', (req, res) => {
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
    Answer.findById(req.params.answerId, (err, answer) => {
        if(err) throw err;
        if(!answer) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 2
            });
        }
        // CREATE NEW REPLY
        let reply = new Reply({
            accountId: req.session.loginInfo._id,
            role: req.session.loginInfo.type,
            accountName: req.session.loginInfo.name,
            questionId: answer.questionId,
            answerId: answer._id,
            content: req.body.content
        });

        // save in db
        reply.save( err => {
            if(err) throw err;
            return res.status(201).json({ success: true });
        })
    });
});
/**
 * @api {delete} /api/reply/:id Delete Reply Information [Dev]
 * @apiVersion 0.1.0
 * @apiName DeleteReply
 * @apiGroup Reply
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * {
 *      "success": true
 * }
 * 
 * @apiError INVALID ID
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 INVALID ID
 *     {
 *       "error": "INVALID ID",
 *       "code" : 1
 *     }
 * 
 * @apiError NOT LOGGED IN
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 NOT LOGGED IN
 *     {
 *       "error": "NOT LOGGED IN",
 *       "code" : 2
 *     } 
 * 
 * @apiError NO RESOURCE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 NO RESOURCE
 *     {
 *       "error": "NO RESOURCE",
 *       "code" : 3
 *     } 
 * 
 * @apiError PERMISSION FAILURE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 PERMISSION FAILURE
 *     {
 *       "error": "PERMISSION FAILURE",
 *       "code" : 4
 *     } 
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
/**
 * @api {put} /api/reply/:id Put Reply Information [Dev]
 * @apiVersion 0.1.0
 * @apiName PutReply
 * @apiGroup Reply
 * 
 * @apiParam {String} content 댓글 내용
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * {
 *      "success": true
 * }
 * 
 * @apiError INVALID ID
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 INVALID ID
 *     {
 *       "error": "INVALID ID",
 *       "code" : 1
 *     }
 * 
 * @apiError EMPTY CONTENTS
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 EMPTY CONTENTS
 *     {
 *       "error": "EMPTY CONTENTS",
 *       "code" : 2
 *     } 
 * 
 * @apiError NOT LOGGED IN
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 NOT LOGGED IN
 *     {
 *       "error": "NOT LOGGED IN",
 *       "code" : 3
 *     } 
 * 
 * @apiError NO RESOURCE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 NO RESOURCE
 *     {
 *       "error": "NO RESOURCE",
 *       "code" : 4
 *     } 
 * 
 * @apiError PERMISSION FAILURE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 PERMISSION FAILURE
 *     {
 *       "error": "PERMISSION FAILURE",
 *       "code" : 5
 *     } 
 * 
 * 
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