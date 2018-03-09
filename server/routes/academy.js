import express from 'express';
import Academy from '../models/academy';
import Course from '../models/course';
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
    READ ACADEMY: GET /api/academy/:id
    ERROR CODES
        1: INVALID ID
        2: NO RESOURCE  
*/
router.get('/:id', (req, res) => {
    // check academy in validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)){
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }

    // find academy and check for writer
    Academy.findById(req.params.id, (err, academy) => {
        if(err) throw err;

        if(!academy) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 2
            });
        }
        res.json(academy);
    });
});

/*
    MODIFY ACADEMY: PUT /api/academy/:id
    BODY SAMPLE: 
    { 
        "academyName": "codesquad",
        "address": {
            "address":"서울 노원구 상계동 455 백산빌딩",
            "jibunaddress": "서울 노원구 상계동 455",
            "roadAddress": "서울 노원구 한글비석로 460",
            "zoneCode": "139820",
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
        if(academy.accountId != req.session.loginInfo.loginId){
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
        if(academy.accountId != req.session.loginInfo.loginId){
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



export default router;