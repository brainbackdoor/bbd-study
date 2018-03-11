import express from 'express';
import Academy from '../models/academy';
import Account from '../models/account';
import Course from '../models/course';
import Event from '../models/event';
import HashTag from '../models/hashTag';
import SearchAcademy from '../dto/searchAcademy';
import mongoose from 'mongoose';
const router = express.Router();

/**
 * @api {get} /api/academy Get Academy list
 * @apiVersion 0.1.0
 * @apiName GetAcademies
 * @apiGroup Academy
 *
 * @apiSuccess {Address} address Address of the academy.
 * @apiSuccess {String} accountId  ID of the academy.
 * @apiSuccess {String} academyName  Name of the academy.
 * @apiSuccess {String} ownerName  Name of the owner.
 * @apiSuccess {String} academyPhoneNumber  PhoneNumber of the academy.
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *   {
 *       "address": {
 *           "dong": "상계동",
 *           "sido": "서울",
 *           "sigungu": "노원구",
 *           "address": "서울 노원구 상계동 455 백산빌딩",
 *           "roadAddress": "서울 노원구 한글비석로 460",
 *           "latitude": 127.06697859544342,
 *           "longitude": 37.66444002512082
 *       },
 *       "created": "2018-03-09T07:34:39.629Z",
 *       "_id": "5aa2390fa26a0f7a8c5bbabd",
 *       "accountId": "bbd@educhoice.com",
 *       "academyName": "모두의학원",
 *       "ownerName": "이준",
 *       "academyPhoneNumber": "07012345678",
 *       "__v": 0
 *   }
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
/**
 * @api {get} /api/academy/:id Get Academy inpormation
 * @apiVersion 0.1.0
 * @apiName GetAcademy
 * @apiGroup Academy
 *
 * @apiSuccess {Address} address Address of the academy.
 * @apiSuccess {Account} corporateAccount Account of the corporate.
 * @apiSuccess {String} academyName  Name of the academy.
 * @apiSuccess {String} academyPhoneNumber  PhoneNumber of the academy.
 * @apiSuccess {Course} courses  Course of the academy.
 * @apiSuccess {Event} events  Event of the academy.
 * @apiSuccess {HashTag} hashTags  HashTag of the academy.
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * {
 *    "address": {
 *        "dong": "상계동",
 *        "sido": "서울",
 *        "sigungu": "노원구",
 *        "address": "서울 노원구 상계동 455 백산빌딩",
 *        "roadAddress": "서울 노원구 한글비석로 460",
 *        "latitude": 127.06697859544342,
 *        "longitude": 37.66444002512082
 *    },
 *    "corporateAccount": {
 *        "phoneNo": "07012345678",
 *        "accountName": "이준"
 *    },
 *    "academyName": "모두의학원",
 *    "academyPhoneNumber": "07012345678",
 *    "courses": [
 *        {
 *            "dayOfWeek": [
 *                {
 *                    "startTime": "18:00",
 *                    "endTime": "20:00",
 *                    "day": "월",
 *                    "_id": "5aa2246a3e230146f31adc00"
 *                },
 *                {
 *                    "startTime": "18:00",
 *                    "endTime": "20:00",
 *                    "day": "화",
 *                    "_id": "5aa2246a3e230146f31adbff"
 *                }
 *            ],
 *            "_id": "5aa222342ae1501ddf695bf7",
 *            "courseType": "special",
 *            "accountId": "bbd@educhoice.com",
 *            "coursesClassification": "물리",
 *            "subjectClassification": "물리",
 *            "courseName": "물리(과탐)-고2",
 *            "grade": "고등2",
 *            "tuition": 400000,
 *            "duration": "2018.01.01 ~ 2018.01.03"
 *        }
 *    ],
 *    "events": [
 *        {
 *            "_id": "5aa22998ec1be63d3c76bb19",
 *            "title": "이벤트타이틀",
 *            "content": "컨텐츠수"
 *        }
 *    ],
 *    "hashTags": [
 *        {
 *            "_id": "5aa23c3d6d0e51514fb618e8",
 *            "title": "태그"
 *        },
 *        {
 *            "_id": "5aa248f9fd158d2be65387ea",
 *            "title": "태그"
 *        }
 *    ],
 *    "created": "2018-03-09T19:39:20.192Z",
 *    "_id": "5aa2e2e8e7d46c44ccf24588"
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
 * @apiError NO RESOURCE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 NO RESOURCE
 *     {
 *       "error": "NO RESOURCE",
 *       "code" : 2
 *     }
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
        Account.find({ 'loginId': academy.accountId}, (err, account) => {
            if(err) throw err;
            if(!account) {
                return res.status(404).json({
                    error: "NO RESOURCE",
                    code: 2
                });                    
            }        
            Course.find({'accountId':academy.accountId}, (err, course) => {
                Event.find({'accountId':academy.accountId}, (err, event)=> {
                    HashTag.find({'accountId':academy.accountId}, (err, hashTag) => {
                        let result = new SearchAcademy({
                            academyName: academy.academyName,
                            academyPhoneNumber: academy.academyPhoneNumber,
                            address: academy.address,
                            carAvailable: academy.carAvailable,
                            inquiryResponseRate: academy.inquiryResponseRate,
                            introduction: academy.introduction,
                            corporateAccount: {
                                phoneNo: academy.academyPhoneNumber,
                                accountName: academy.ownerName,
                                accountId: account.loginId
                            },
                            courses: course,
                            events: event,
                            hashTags: hashTag
                        });
                        res.json(result); 
                    });
                });
            });
        });
    });
});

/**
 * @api {put} /api/academy/:id PUT Academy inpormation
 * @apiVersion 0.1.0
 * @apiName PutAcademy
 * @apiGroup Academy
 *
 * @apiParam {String} academyName 학원이름
 * @apiParam {Address} address 학원주소
 * @apiParam {Boolean} carAvaiable 차량운행여부 
 * @apiParam {Number} inquiryResponseRate 응답률
 * @apiParam {String} introduction 소개
 * 
 * @apiSuccess {Address} address Address of the academy.
 * @apiSuccess {Account} corporateAccount Account of the corporate.
 * @apiSuccess {String} academyName  Name of the academy.
 * @apiSuccess {String} academyPhoneNumber  PhoneNumber of the academy.
 * @apiSuccess {Course} courses  Course of the academy.
 * @apiSuccess {Event} events  Event of the academy.
 * @apiSuccess {HashTag} hashTags  HashTag of the academy.
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * {
 *    "address": {
 *        "dong": "상계동",
 *        "sido": "서울",
 *        "sigungu": "노원구",
 *        "address": "서울 노원구 상계동 455 백산빌딩",
 *        "roadAddress": "서울 노원구 한글비석로 460",
 *        "latitude": 127.06697859544342,
 *        "longitude": 37.66444002512082
 *    },
 *    "corporateAccount": {
 *        "phoneNo": "07012345678",
 *        "accountName": "이준"
 *    },
 *    "academyName": "모두의학원",
 *    "academyPhoneNumber": "07012345678",
 *    "courses": [
 *        {
 *            "dayOfWeek": [
 *                {
 *                    "startTime": "18:00",
 *                    "endTime": "20:00",
 *                    "day": "월",
 *                    "_id": "5aa2246a3e230146f31adc00"
 *                },
 *                {
 *                    "startTime": "18:00",
 *                    "endTime": "20:00",
 *                    "day": "화",
 *                    "_id": "5aa2246a3e230146f31adbff"
 *                }
 *            ],
 *            "_id": "5aa222342ae1501ddf695bf7",
 *            "courseType": "special",
 *            "accountId": "bbd@educhoice.com",
 *            "coursesClassification": "물리",
 *            "subjectClassification": "물리",
 *            "courseName": "물리(과탐)-고2",
 *            "grade": "고등2",
 *            "tuition": 400000,
 *            "duration": "2018.01.01 ~ 2018.01.03"
 *        }
 *    ],
 *    "events": [
 *        {
 *            "_id": "5aa22998ec1be63d3c76bb19",
 *            "title": "이벤트타이틀",
 *            "content": "컨텐츠수"
 *        }
 *    ],
 *    "hashTags": [
 *        {
 *            "_id": "5aa23c3d6d0e51514fb618e8",
 *            "title": "태그"
 *        },
 *        {
 *            "_id": "5aa248f9fd158d2be65387ea",
 *            "title": "태그"
 *        }
 *    ],
 *    "created": "2018-03-09T19:39:20.192Z",
 *    "_id": "5aa2e2e8e7d46c44ccf24588"
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
 *     HTTP/1.1 400 NO RESOURCE
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
        academy.save((err, academy) => {
            if(err) throw err;

            return res.json({
                success: true,
                academy
            });
        });
    });
});
/**
 * @api {delete} /api/academy/:id Delete Academy
 * @apiVersion 0.1.0
 * @apiName DeleteAcademy
 * @apiGroup Academy
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