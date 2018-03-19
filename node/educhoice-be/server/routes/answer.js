import express from 'express';
import Question from '../models/question';
import Answer from '../models/answer';
import mongoose from 'mongoose';

const router = express.Router();

/**
 * @api {get} /api/answer Get Answer Information [Dev]
 * @apiVersion 0.1.0
 * @apiName GetAnswers
 * @apiGroup Answer
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *    {
 *        "date": {
 *            "created": "2018-03-09T18:53:46.131Z",
 *            "edited": "2018-03-09T18:53:46.131Z"
 *        },
 *        "is_edited": false,
 *        "_id": "5aa2d83a9981b98349922da0",
 *        "accountId": "bbd@educhoice.com",
 *        "accountName": "모두의학원",
 *        "questionerId": "brainbackdoor@modoohakwon.com",
 *        "questionId": "5aa2bc5eb4b817f1759f0cc2",
 *        "content": "댓글 컨텐츠",
 *      "__v": 0
 *   }
 * 
 * 
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
/**
 * @api {post} /api/answer/:questionId Post Answer Information
 * @apiVersion 0.1.0
 * @apiName PostAnswerInformation
 * @apiGroup Answer
 * 
 * @apiParam {String} content 답글 컨텐츠
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
 * @apiError NO RESOURCE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 NO RESOURCE
 *     {
 *       "error": "NO RESOURCE",
 *       "code" : 3
 *     } 
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
                code: 3
            });
        }
        // CREATE NEW ANSWER
        let answer = new Answer({
            accountId: req.session.loginInfo.accountId,
            accountName: req.session.loginInfo.name,
            questionerId: question.accountId,
            questionId: question.accountId,
            content: req.body.content
        });

        // save in db
        answer.save( err => {
            if(err) throw err;
            return res.status(201).json({ success: true });
        })
    });
});
/**
 * @api {delete} /api/answer/:questionId Delete Answer Information [Dev]
 * @apiVersion 0.1.0
 * @apiName DeleteAnswerInformation
 * @apiGroup Answer
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
        answer.remove({ answerId: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});

export default router;