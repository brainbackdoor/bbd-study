import express from 'express';
import Academy from '../models/academy';
import HashTag from '../models/hashTag';
import mongoose from 'mongoose';

const router = express.Router();

/**
 * @api {post} /api/hashTag Post HashTag information
 * @apiVersion 0.1.0
 * @apiName PostHashTag
 * @apiGroup HashTag
 * 
 * @apiParam {String} title 해쉬태그
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

    Academy.findOne({accountId:req.session.loginInfo.accountId}, (err, academy)=> {
        // CREATE NEW HashTag
        let hashTag = new HashTag({
            title: req.body.title
        });
        academy.hashTags.push(hashTag);
        // save in db
        academy.save( err => {
            if(err) throw err;
            return res.json({ success: true });
        })        
    });
});
/**
 * @api {delete} /api/hashTag/:id Delete HashTag Information
 * @apiVersion 0.1.0
 * @apiName DeleteHashTagInformation
 * @apiGroup HashTag
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

    // check hashTag in validity
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

    // find hashTag and check for writer
    Academy.findOne({accountId:req.session.loginInfo.accountId}, (err, academy)=> {
        if(academy.accountId != req.session.loginInfo.accountId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }        
        for(var i = 0;i < academy.hashTags.length; i ++) {
            if(academy.hashTags[i].hashTagId == req.params.id){
                academy.hashTags.splice(i,1);
                academy.save( err => {
                    if(err) throw err;
                    return res.json({ success: true });
                })     
            } 
        }
    });    
});
/**
 * @api {put} /api/hashTag/:id Put HashTag Information [Dev]
 * @apiVersion 0.1.0
 * @apiName PutHashTag
 * @apiGroup HashTag
 * 
 * @apiParam {String} title 해쉬태그
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

    // check hashTag id validity
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

    //check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json ({
            error: "NOT LOGGED IN",
            code: 3
        });
    }
    Academy.findOne({accountId:req.session.loginInfo.accountId}, (err, academy)=> {
        if(academy.accountId != req.session.loginInfo.accountId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }        
        for(var i = 0;i < academy.hashTags.length; i ++) {
           
            if(academy.hashTags[i].hashTagId == req.params.id){
                // MODIFY AND SAVE IN DB
                academy.hashTags[i].title =  req.body.title;    
                academy.hashTags[i].date.edited = new Date();
                academy.hashTags[i].is_edited = true;          
                
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