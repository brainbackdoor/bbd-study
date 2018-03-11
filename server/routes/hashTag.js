import express from 'express';
import HashTag from '../models/hashTag';
import mongoose from 'mongoose';

const router = express.Router();

/**
 * @api {get} /api/hashTag Get HashTag Information [Dev]
 * @apiVersion 0.1.0
 * @apiName GetHashTags
 * @apiGroup HashTag
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 *[
 *   {
 *       "date": {
 *           "created": "2018-03-09T07:48:13.814Z",
 *           "edited": "2018-03-09T07:48:13.815Z"
 *       },
 *       "is_edited": false,
 *       "_id": "5aa23c3d6d0e51514fb618e8",
 *       "accountId": "bbd@educhoice.com",
 *       "title": "태그",
 *       "__v": 0
 *   }
 *]
 * 
 */
router.get('/', (req, res) => {
    HashTag.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, hashTags) => {
        if(err) throw err;
        res.json(hashTags);
    })
});
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
    // CREATE NEW HashTag
    let hashTag = new HashTag({
        accountId: req.session.loginInfo.loginId,
        title: req.body.title
    });

    // save in db
    hashTag.save( err => {
        if(err) throw err;
        return res.json({ success: true });
    })
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
    HashTag.findById(req.params.id, (err, hashTag) => {
        if(err) throw err;

        if(!hashTag) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(hashTag.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }
        // REMOVE THE HASHTAG
        HashTag.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
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

    // find hashTag
    HashTag.findById(req.params.id, (err, hashTag) => {
        if(err) throw err;

        // if hashTag does not exist
        if(!hashTag) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(hashTag.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }

        // MODIFY AND SAVE IN DB
        hashTag.title =  req.body.title;   
        hashTag.date.edited = new Date();
        hashTag.is_edited = true;

        hashTag.save((err, hashTag) => {
            if(err) throw err;

            return res.json({
                success: true,
                hashTag
            });
        });
    });
});

export default router;