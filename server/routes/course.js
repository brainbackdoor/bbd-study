import express from 'express';
import Course from '../models/course';
import mongoose from 'mongoose';

const router = express.Router();

/**
 * @api {get} /api/course Get Course Information [Dev]
 * @apiVersion 0.1.0
 * @apiName GetCourses
 * @apiGroup Course
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * 
 *[
 *    {
 *        "date": {
 *            "created": "2018-03-09T05:57:08.473Z",
 *            "edited": "2018-03-09T06:06:34.220Z"
 *        },
 *        "dayOfWeek": [
 *            {
 *                "startTime": "18:00",
 *                "endTime": "20:00",
 *                "day": "월",
 *                "_id": "5aa2246a3e230146f31adc00"
 *            },
 *            {
 *                "startTime": "18:00",
 *                "endTime": "20:00",
 *                "day": "화",
 *                "_id": "5aa2246a3e230146f31adbff"
 *            }
 *        ],
 *        "is_edited": true,
 *        "_id": "5aa222342ae1501ddf695bf7",
 *        "courseType": "special",
 *        "accountId": "bbd@educhoice.com",
 *        "coursesClassification": "물리",
 *        "subjectClassification": "물리",
 *        "courseName": "물리(과탐)-고2",
 *        "grade": "고등2",
 *        "tuition": 400000,
 *        "duration": "2018.01.01 ~ 2018.01.03",
 *        "__v": 1
 *    }
 *]
 * 
 */
router.get('/', (req, res) => {
    Course.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, courses) => {
        if(err) throw err;
        res.json(courses);
    })
});
/**
 * @api {post} /api/course Post Course information
 * @apiVersion 0.1.0
 * @apiName PostCourse
 * @apiGroup Course
 * 
 * @apiParam {String} courseType 강좌 유형 { normal | special }
 * @apiParam {String} coursesClassification 과목 대분류
 * @apiParam {String} subjectClassification 과목 소분류
 * @apiParam {String} courseName 과목 이름
 * @apiParam {Number} tuition 수업료
 * @apiParam {String} grade 학년
 * @apiParam {Array} dayOfWeek 수업일자
 * @apiParam {String} duration 수업기간 (특강(courseType:special)에 한함)
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
 * @apiError BAD REQUEST TYPE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 BAD REQUEST TYPE
 *     {
 *       "error": "BAD REQUEST TYPE",
 *       "code" : 3
 *     }
 * 
 * @apiError BAD REQUEST DATA
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 BAD REQUEST DATA
 *     {
 *       "error": "BAD REQUEST DATA",
 *       "code" : 4
 *     }
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
    if((typeof req.body.coursesClassification !== 'string') || (req.body.coursesClassification === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.subjectClassification !== 'string') || (req.body.subjectClassification === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.courseName !== 'string') || (req.body.courseName === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.grade !== 'string') || (req.body.grade === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.tuition !== 'number') || (req.body.tuition === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if( (req.body.dayOfWeek === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.courseType !== 'string') || (req.body.courseType === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if(!req.body.courseType.match('normal') && !req.body.courseType.match('special')) {
        return res.status(400).json({
            error: "BAD REQUEST TYPE",
            code: 3
        });
    } 
    if(req.body.courseType.match('special')){
        // check request data
        if(!req.body.duration ){
            return res.status(400).json({
                error: "BAD REQUEST DATA",
                code: 4
            });            
        }
    } else {
        if(req.body.duration ){
            return res.status(400).json({
                error: "BAD REQUEST DATA",
                code: 4
            });            
        }       
    }
    // CREATE NEW COURSE
    let course = new Course({
        courseType: req.body.courseType,
        accountId: req.session.loginInfo.loginId,
        coursesClassification: req.body.coursesClassification,
        subjectClassification: req.body.subjectClassification,
        courseName: req.body.courseName,
        grade: req.body.grade,
        tuition: req.body.tuition,
        dayOfWeek: req.body.dayOfWeek,
        duration: req.body.duration
    });

    // save in db
    course.save( err => {
        if(err) throw err;
        return res.json({ success: true });
    })
});
/**
 * @api {delete} /api/course/:id Delete Course Information
 * @apiVersion 0.1.0
 * @apiName DeleteCourseInformation
 * @apiGroup Course
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

    // check course in validity
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

    // find course and check for writer
    Course.findById(req.params.id, (err, course) => {
        if(err) throw err;

        if(!course) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(course.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }

        // REMOVE THE COURSE
        Course.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});
/**
 * @api {put} /api/course/:id Put Course Information
 * @apiVersion 0.1.0
 * @apiName PutCourseInformation
 * @apiGroup Course
 * 
 * @apiParam {String} courseType 강좌 유형 { normal | special }
 * @apiParam {String} coursesClassification 과목 대분류
 * @apiParam {String} subjectClassification 과목 소분류
 * @apiParam {String} couorseName 과목 이름
 * @apiParam {String} grade 학년
 * @apiParam {Array} dayOfWeek 수업일자
 * @apiParam {String} duration 수업기간 (특강(courseType:special)에 한함)
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
 * @apiError BAD REQUEST TYPE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 BAD REQUEST TYPE
 *     {
 *       "error": "BAD REQUEST TYPE",
 *       "code" : 6
 *     } 
 * 
 * @apiError BAD REQUEST DATA
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 BAD REQUEST DATA
 *     {
 *       "error": "BAD REQUEST DATA",
 *       "code" : 7
 *     } 
 * 
 */

router.put('/:id', (req, res) => {

    // check course id validity
    if(!mongoose.Types.ObjectId.isValid(req.params.id)) {
        return res.status(400).json({
            error: "INVALID ID",
            code: 1
        });
    }

    // check data valid
    if((typeof req.body.coursesClassification !== 'string') || (req.body.coursesClassification === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.subjectClassification !== 'string') || (req.body.subjectClassification === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.courseName !== 'string') || (req.body.courseName === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.grade !== 'string') || (req.body.grade === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.tuition !== 'number') || (req.body.tuition === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if( (req.body.dayOfWeek === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if((typeof req.body.courseType !== 'string') || (req.body.courseType === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    if(!req.body.courseType.match('normal') && !req.body.courseType.match('special')) {
        return res.status(400).json({
            error: "BAD REQUEST TYPE",
            code: 6
        });
    } 
    if(req.body.courseType.match('special')){
        // check request data
        if(!req.body.duration ){
            return res.status(400).json({
                error: "BAD REQUEST DATA",
                code: 7
            });            
        }
    } else {
        if(req.body.duration ){
            return res.status(400).json({
                error: "BAD REQUEST DATA",
                code: 7
            });            
        }       
    }

    //check login status
    if(typeof req.session.loginInfo === 'undefined') {
        return res.status(403).json ({
            error: "NOT LOGGED IN",
            code: 3
        });
    }

    // find course
    Course.findById(req.params.id, (err, course) => {
        if(err) throw err;

        // if couorse does not exist
        if(!course) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(course.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }

        // MODIFY AND SAVE IN DB
        course.courseType = req.body.courseType;
        course.coursesClassification = req.body.coursesClassification;
        course.subjectClassification = req.body.subjectClassification;
        course.courseName = req.body.courseName;
        course.grade = req.body.grade;
        course.tuition = req.body.tuition;
        course.dayOfWeek = req.body.dayOfWeek;
        course.duration = req.body.duration;        
        course.date.edited = new Date();
        course.is_edited = true;

        course.save((err, course) => {
            if(err) throw err;

            return res.json({
                success: true,
                course
            });
        });
    });
});

export default router;