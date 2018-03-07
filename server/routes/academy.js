import express from 'express';
import Academy from '../models/academy';
import mongoose from 'mongoose';

const router = express.Router();
/*
    READ ACADEMY: GET /api/academy
*/
router.get('/', (req, res) => {
    Academy.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, academies) => {
        if(err) throw err;
        res.json(academies);
    })
});

/*
    WRITE ACADEMY: POST /api/academy
    BODY SAMPLE: 
    { 
        "academyName": "codesquad",
        "address": {
            "sido": "서울시",
            "sigungu": "노원구",
            "dong": "상계동",
            "latitude": 127.06697859544342,
            "longitude": 37.66444002512082
    },
        "carAvailable": true,
        "inquiryResponseRate": 0,
        "introduction": "소개글",
        "hashTags": {"title":"태그"},    
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

    // check academyName valid
    if(typeof req.body.academyName !== 'string') {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }

    if(req.body.academyName === "") {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }

    // CREATE NEW ACADEMY
    let academy = new Academy({
        accountId: req.session.loginInfo._id,
        academyName: req.body.academyName,
        address: req.body.address,
        carAvailable: false,
        inquiryResponseRate: 0,
        introduction: req.body.introduction,
        hashTags: req.body.hashTags
    });

    // save in db
    academy.save( err => {
        if(err) throw err;
        return res.json({ success: true });
    })
});

/*
    DELETE ACADEMY: DELETE /api/academy/:id
    ERROR CODES
        1: INVALID ID
        2: NOT LOGGED IN
        3: NO RESOURCE
        4: PERMISSION FAILURE
*/
router.delete('/:id', (req, res) => {

    // check academy in validity
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

    // find academy and check for writer
    Academy.findById(req.params.id, (err, academy) => {
        if(err) throw err;

        if(!academy) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(academy.accountId != req.session.loginInfo._id){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }

        // REMOVE THE ACADEMY
        Academy.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});

/*
    MODIFY ACADEMY: PUT /api/academy/:id
    BODY SAMPLE: 
    { 
        "academyName": "codesquad",
        "address": {
            "sido": "서울시",
            "sigungu": "노원구",
            "dong": "상계동",
            "latitude": 127.06697859544342,
            "longitude": 37.66444002512082
    },
        "carAvailable": true,
        "inquiryResponseRate": 0,
        "introduction": "소개글",
        "hashTags": {"title":"태그"},    
    }    
    ERROR CODES
        1: INVALID ID,
        2: EMPTY CONTENTS,
        3: NOT LOGGED IN
        4: NO RESOURCE
        5: PERMISSION FAILURE
*/
router.put('/:id', (req, res) => {

    // check academy id validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }

    // check contents valid
    if(typeof req.body.academyName !== 'string'){
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if(req.body.academyName === "") {
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

    // find academy
    Academy.findById(req.params.id, (err, academy) => {
        if(err) throw err;

        // if academy does not exist
        if(!academy) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(academy.accountId != req.session.loginInfo._id){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }
        

        // MODIFY AND SAVE IN DB
        academy.academyName = req.body.academyName;
        academy.address = req.body.address;
        academy.carAvailable = req.body.carAvailable;
        academy.introduction = req.body.introduction;
        academy.hashTags = req.body.hashTags;
        academy.save((err, academy) => {
            if(err) throw err;

            return res.json({
                success: true,
                academy
            });
        });
    });
});

export default router;