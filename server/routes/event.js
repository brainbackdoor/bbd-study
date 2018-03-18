import express from 'express';
import Academy from '../models/academy';
import Event from '../models/event';
import mongoose from 'mongoose';

const router = express.Router();

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

    Academy.findOne({accountId:req.session.loginInfo._id}, (err, academy)=> {
        // CREATE NEW EVENT
        let event = new Event({
            title: req.body.title,
            content: req.body.content
        });
        academy.events.push(event);
        // save in db
        academy.save( err => {
            if(err) throw err;
            return res.json({ success: true });
        })        
    });
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
    Academy.findOne({accountId:req.session.loginInfo._id}, (err, academy)=> {
        if(academy.accountId != req.session.loginInfo._id){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }        
        for(var i = 0;i < academy.events.length; i ++) {
            if(academy.events[i]._id == req.params.id){
                academy.events.splice(i,1);
                academy.save( err => {
                    if(err) throw err;
                    return res.json({ success: true });
                })     
            } 
        }
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

    Academy.findOne({accountId:req.session.loginInfo._id}, (err, academy)=> {
        if(academy.accountId != req.session.loginInfo._id){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }        
        for(var i = 0;i < academy.events.length; i ++) {
           
            if(academy.events[i]._id == req.params.id){
                // MODIFY AND SAVE IN DB
                academy.events[i].title =  req.body.title;
                academy.events[i].content = req.body.content;      
                academy.events[i].date.edited = new Date();
                academy.events[i].is_edited = true;          
                
                academy.save((err, academy) => {
                    if(err) throw err;
        
                    return res.json({
                        success: true,
                        academy
                    });
                });                 
            }
        }
    });
});

export default router;