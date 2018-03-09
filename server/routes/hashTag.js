import express from 'express';
import HashTag from '../models/hashTag';
import mongoose from 'mongoose';

const router = express.Router();
/*
    READ HASHTAG: GET /api/hashTag
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

/*
    WRITE HASHTAG: POST /api/hashTag
    BODY SAMPLE: 
    { 
        "title": "태그타이틀",
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

/*
    DELETE HASHTAG: DELETE /api/hashTag/:id
    ERROR CODES
        1: INVALID ID
        2: NOT LOGGED IN
        3: NO RESOURCE
        4: PERMISSION FAILURE
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

/*
    MODIFY HASHTAG: PUT /api/hashTag/:id
    BODY SAMPLE: 
    { 
        "title": "태그타이틀"
    }
    ERROR CODES
        1: INVALID ID,
        2: EMPTY CONTENTS,
        3: NOT LOGGED IN
        4: NO RESOURCE
        5: PERMISSION FAILURE
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