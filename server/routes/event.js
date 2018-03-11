import express from 'express';
import Event from '../models/event';
import mongoose from 'mongoose';

const router = express.Router();

/**
 * @api {get} /api/event Get Event Information [Dev]
 * @apiVersion 0.1.0
 * @apiName GetEventInfo
 * @apiGroup Event
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 *[
 *   {
 *       "date": {
 *           "created": "2018-03-09T06:28:40.004Z",
 *           "edited": "2018-03-09T06:29:23.035Z"
 *       },
 *       "is_edited": true,
 *       "_id": "5aa22998ec1be63d3c76bb19",
 *       "accountId": "bbd@educhoice.com",
 *       "title": "이벤트타이틀",
 *       "content": "컨텐츠수",
 *       "__v": 0
 *   }
 *]
 * 
 */
router.get('/', (req, res) => {
    Event.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, events) => {
        if(err) throw err;
        res.json(events);
    })
});
/**
 * @api {post} /api/event Post Event information
 * @apiVersion 0.1.0
 * @apiName PostEvent
 * @apiGroup Event
 * 
 * @apiParam {String} title 이벤트제목
 * @apiParam {String} content 이벤트 컨텐츠
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

router.post('/', (req, res) => {
    // check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json({
            error: "NOT LOGGED IN",
            code: 1
        });
    }

    // check data valid
    if((typeof req.body.title !== 'string') || (req.body.title === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.content !== 'string') || (req.body.content === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    // CREATE NEW EVENT
    let event = new Event({
        accountId: req.session.loginInfo.loginId,
        title: req.body.title,
        content: req.body.content
    });

    // save in db
    event.save( err => {
        if(err) throw err;
        return res.json({ success: true });
    })
});
/**
 * @api {delete} /api/event/:id Delete Event Information
 * @apiVersion 0.1.0
 * @apiName DeleteEventInformation
 * @apiGroup Event
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

    // check event in validity
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

    // find event and check for writer
    Event.findById(req.params.id, (err, event) => {
        if(err) throw err;

        if(!event) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(event.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }
        // REMOVE THE EVENT
        Event.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});
/**
 * @api {put} /api/event/:id Put Event Information
 * @apiVersion 0.1.0
 * @apiName PutEvent
 * @apiGroup Event
 * 
 * @apiParam {String} title 이벤트제목
 * @apiParam {String} content 이벤트 컨텐츠
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

    // check event id validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }

    // check data valid
    if((typeof req.body.title !== 'string') || (req.body.title === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
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

    // find event
    Event.findById(req.params.id, (err, event) => {
        if(err) throw err;

        // if event does not exist
        if(!event) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(event.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }

        // MODIFY AND SAVE IN DB
        event.title =  req.body.title;
        event.content = req.body.content;      
        event.date.edited = new Date();
        event.is_edited = true;

        event.save((err, event) => {
            if(err) throw err;

            return res.json({
                success: true,
                event
            });
        });
    });
});

export default router;