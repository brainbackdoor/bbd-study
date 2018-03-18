import express from 'express';
// import Account from '../models/account';
import Question from '../models/question';
import Answer from '../models/answer';
import Reply from '../models/reply';
import SearchInquiry from '../dto/searchInquiry';
import mongoose from 'mongoose';

const router = express.Router();

/**
 * @api {get} /api/question Get Question Information [Dev]
 * @apiVersion 0.1.0
 * @apiName GetQuestions
 * @apiGroup Question
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *[
 *    {
 *        "date": {
 *            "created": "2018-03-09T16:54:54.481Z",
 *            "edited": "2018-03-09T16:54:54.481Z"
 *        },
 *        "receivers": [
 *            {
 *                "receiverId": "bbd@educhoice.com",
 *                "_id": "5aa2bc5eb4b817f1759f0cc1"
 *            },
 *            {
 *                "receiverId": "bbd2@educhoice.com",
 *                "_id": "5aa2bc5eb4b817f1759f0cc0"
 *            }
 *        ],
 *        "is_edited": false,
 *        "_id": "5aa2bc5eb4b817f1759f0cc2",
 *        "accountId": "brainbackdoor@modoohakwon.com",
 *        "questionTitle": "문의타이틀",
 *        "questionContent": "문의컨텐츠",
 *        "__v": 0
 *    }
 *]
 * 
 * 
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
/**
 * @api {get} /api/question/node/:questionId Get Question Information
 * @apiVersion 0.1.0
 * @apiName GetQuestionInformation
 * @apiGroup Question
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *{
 *    "accountId": "bbd@educhoice.com",
 *    "accountName": "모두의학원",
 *    "answerContent": "댓글 컨텐츠",
 *    "created": "2018-03-09T18:53:46.131Z",
 *    "reply": [
 *        {
 *            "_id": "5aa2d8549981b98349922da1",
 *            "role": "corporate",
 *            "accountName": "모두의학원",
 *            "content": "댓글 컨텐츠"
 *        }
 *    ],
 *    "_id": "5aa3804b9679db694a27b96e"
 *}
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
 * @apiError NO RESOURCE 
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 NO RESOURCE 
 *     {
 *       "error": "NO RESOURCE",
 *       "code" : 2
 *     } 
 * 
 */

router.get('/node/:questionId', (req, res) => {
    // check question in validity
    if(!mongoose.Types.ObjectId.isValid(req.params.questionId)){
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }
    //check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json ({
            error: "NOT LOGGED IN",
            code: 2
        });
    }    
    // find question and check for writer    
    Answer.findOne({'questionId':req.params.questionId}, (err, answer) => {
        if(err) throw err;
        // if exists, check writer
        if(answer.accountId !== req.session.loginInfo.loginId && answer.questionerId !== req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 3
            });
        }
        if(!answer) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        Reply.find({'answerId': answer._id},(err, reply)=> {
           let result = new SearchInquiry({
                accountId: answer.accountId,
                accountName: answer.accountName,
                answerContent: answer.content,
                created: answer.date.created,
                reply: reply
           });
           res.json(result); 
        });
    });
});

/**
 * @api {get} /api/question/list Get Question List
 * @apiVersion 0.1.0
 * @apiName GetQuestionList
 * @apiGroup Question
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *[
 *   {
 *       "date": {
 *           "created": "2018-03-09T16:54:54.481Z",
 *           "edited": "2018-03-09T16:54:54.481Z"
 *       },
 *       "receivers": [
 *           {
 *               "receiverId": "bbd@educhoice.com",
 *               "_id": "5aa2bc5eb4b817f1759f0cc1"
 *           },
 *           {
 *               "receiverId": "bbd2@educhoice.com",
 *               "_id": "5aa2bc5eb4b817f1759f0cc0"
 *           }
 *       ],
 *       "is_edited": false,
 *       "_id": "5aa2bc5eb4b817f1759f0cc2",
 *       "accountId": "brainbackdoor@modoohakwon.com",
 *       "questionTitle": "문의타이틀",
 *       "questionContent": "문의컨텐츠",
 *       "__v": 0
 *   }
 *]
 * 
 * @apiError NO RESOURCE 
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 NO RESOURCE 
 *     {
 *       "error": "NO RESOURCE",
 *       "code" : 1
 *     } 
 * 
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
/**
 * @api {post} /api/question Post Question information
 * @apiVersion 0.1.0
 * @apiName PostQuestionInformation
 * @apiGroup Question
 * 
 * @apiParam {Array} receivers 학원 ID
 * @apiParam {String} questionTitle 문의 제목
 * @apiParam {String} questionContent 문의 내용 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
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
 * @apiError NOT PARENTS
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 403 NOT PARENTS
 *     {
 *       "error": "NOT PARENTS",
 *       "code" : 3
 *     } 
 *
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
            code: 1
        });
    }
    // check sender type
    if(req.session.loginInfo.type !== 'parents') {
        return res.status(403).json({
            error: "NOT PARENTS",
            code: 3
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
/**
 * @api {delete} /api/question/:id Delete Question Information [Dev]
 * @apiVersion 0.1.0
 * @apiName DeleteQuestionInformation
 * @apiGroup Question
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
/**
 * @api {put} /api/question/:id Put Question Information [Dev]
 * @apiVersion 0.1.0
 * @apiName PutQuestion
 * @apiGroup Question
 * 
 * @apiParam {String} questionTitle 문의 제목
 * @apiParam {String} questionContent 문의 내용 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *{
 *   "success": true,
 *   "question": {
 *       "date": {
 *           "created": "2018-03-10T07:14:16.226Z",
 *           "edited": "2018-03-10T07:22:51.906Z"
 *       },
 *       "receivers": [
 *           {
 *               "receiverId": "bbd@educhoice.com",
 *               "_id": "5aa385c815958aece4131e61"
 *           },
 *           {
 *               "receiverId": "bbd2@educhoice.com",
 *               "_id": "5aa385c815958aece4131e60"
 *           }
 *       ],
 *       "is_edited": true,
 *       "_id": "5aa385c815958aece4131e62",
 *       "accountId": "hohsso@modoohakwon.com",
 *       "questionTitle": "문의타이틀수정",
 *       "questionContent": "문의컨텐츠수정",
 *       "__v": 0
 *   }
 *}
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