import express from 'express';
import Academy from '../models/academy';
import Course from '../models/course';
import mongoose from 'mongoose';
import jsonQuery from 'json-query';

const router = express.Router();

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
    Academy.findOne({accountId:req.session.loginInfo.accountId}, (err, academy)=> {
        // CREATE NEW COURSE
        let course = new Course({
            courseType: req.body.courseType,
            coursesClassification: req.body.coursesClassification,
            subjectClassification: req.body.subjectClassification,
            courseName: req.body.courseName,
            grade: req.body.grade,
            tuition: req.body.tuition,
            dayOfWeek: req.body.dayOfWeek,
            duration: req.body.duration
        });

        academy.courses.push(course);
        if(!academy.grades.includes(req.body.grade)) academy.grades.push(req.body.grade);
        if(!academy.coursesClassification.includes(req.body.coursesClassification)) academy.subjects.push(req.body.coursesClassification);
  
        // save in db
        academy.save( err => {
            if(err) throw err;
            return res.json({ success: true });
        })        
    });



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
    Academy.findOne({accountId:req.session.loginInfo.accountId}, (err, academy)=> {
        if(academy.accountId != req.session.loginInfo.accountId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }        
        for(var i = 0;i < academy.courses.length; i ++) {
            if(academy.courses[i].courseId == req.params.id){
                academy.courses.splice(i,1);
                academy.subjects.splice(i,1);
                academy.grades.splice(i,1);
                academy.save( err => {
                    if(err) throw err;
                    return res.json({ success: true });
                })     
            }
        }
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


    Academy.findOne({accountId:req.session.loginInfo.accountId}, (err, academy)=> {
        if(academy.accountId != req.session.loginInfo.accountId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }        
        for(var i = 0;i < academy.courses.length; i ++) {
           
            if(academy.courses[i].courseId == req.params.id){

                // MODIFY AND SAVE IN DB
                academy.courses[i].courseType = req.body.courseType;
                academy.courses[i].coursesClassification = req.body.coursesClassification;
                academy.courses[i].subjectClassification = req.body.subjectClassification;
                academy.courses[i].courseName = req.body.courseName;
                academy.courses[i].grade = req.body.grade;
                academy.courses[i].tuition = req.body.tuition;
                academy.courses[i].dayOfWeek = req.body.dayOfWeek;
                academy.courses[i].duration = req.body.duration;        
                academy.courses[i].date.edited = new Date();
                academy.courses[i].is_edited = true;

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