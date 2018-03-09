import express from 'express';
import Course from '../models/course';
import mongoose from 'mongoose';

const router = express.Router();
/*
    READ COURSE: GET /api/course
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

/*
    WRITE COURSE: POST /api/course
    BODY SAMPLE: 
    { 
        "courseType": {"normal" | "special"},
        "coursesClassification": "과학",
        "subjectClassification": "과학",
        "courseName": "물리(과탐)-고2",
        "grade": "고등2",
        "tuition": 400000,
        "dayOfWeek": [
            {
                "startTime": "18:00",
                "endTime": "20:00",
                "day": "월"
            },
            {
                "startTime": "18:00",
                "endTime": "20:00",
                "day": "화"
            }
        ],
        "duration":"2018.01.01 ~ 2018.01.02"
    }
    ERROR CODES
        1: NOT LOGGED IN
        2: EMPTY CONTENTS
        3: BAD REQUEST TYPE
        4: BAD REQUEST DATA
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

/*
    DELETE COURSE: DELETE /api/course/:id
    ERROR CODES
        1: INVALID ID
        2: NOT LOGGED IN
        3: NO RESOURCE
        4: PERMISSION FAILURE
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

/*
    MODIFY COURSE: PUT /api/course/:id
    BODY SAMPLE: 
    { 
        "coursesClassification": "과학",
        "subjectClassification": "과학",
        "courseName": "물리(과탐)-고2",
        "grade": "고등2",
        "tuition": 400000,
        "dayOfWeek": [
            {
                "startTime": "18:00",
                "endTime": "20:00",
                "day": "월"
            },
            {
                "startTime": "18:00",
                "endTime": "20:00",
                "day": "화"
            }
        ]
    }
    ERROR CODES
        1: INVALID ID,
        2: EMPTY CONTENTS,
        3: NOT LOGGED IN
        4: NO RESOURCE
        5: PERMISSION FAILURE
        6: BAD REQUEST TYPE
        7: BAD REQUEST DATA
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