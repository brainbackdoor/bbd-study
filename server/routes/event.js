import express from 'express';
import Event from '../models/event';
import mongoose from 'mongoose';

const router = express.Router();
/*
    READ EVENT: GET /api/event
*/
router.get('/', (req, res) => {
    Event.find()
    .sort({"_id": -1})
    .limit(6)
    .exec((err, events) => {
        if(err) throw err;
        res.json(events);
    })
});

/*
    WRITE EVENT: POST /api/event
    BODY SAMPLE: 
    { 
        "title": "이벤트타이틀",
        "content": "이벤트컨텐츠"
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
    if((typeof req.body.content !== 'string') || (req.body.content === "")) {
        return res.status(400).json({
            error: "EMPTY CONTENTS",
            code: 2
        });
    }
    // CREATE NEW EVENT
    let event = new Event({
        accountId: req.session.loginInfo.loginId,
        title: req.body.title,
        content: req.body.content
    });

    // save in db
    event.save( err => {
        if(err) throw err;
        return res.json({ success: true });
    })
});

/*
    DELETE EVENT: DELETE /api/event/:id
    ERROR CODES
        1: INVALID ID
        2: NOT LOGGED IN
        3: NO RESOURCE
        4: PERMISSION FAILURE
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
    Event.findById(req.params.id, (err, event) => {
        if(err) throw err;

        if(!event) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 3
            });
        }
        if(event.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 4
            });
        }
        // REMOVE THE EVENT
        Event.remove({ _id: req.params.id }, err => {
            if(err) throw err;
            res.json({ success: true });
        });
    });
});

/*
    MODIFY EVENT: PUT /api/event/:id
    BODY SAMPLE: 
    { 
        "title": "이벤트타이틀",
        "content": "이벤트컨텐츠"
    }
    ERROR CODES
        1: INVALID ID,
        2: EMPTY CONTENTS,
        3: NOT LOGGED IN
        4: NO RESOURCE
        5: PERMISSION FAILURE
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

    // find event
    Event.findById(req.params.id, (err, event) => {
        if(err) throw err;

        // if event does not exist
        if(!event) {
            return res.status(404).json({
                error: "NO RESOURCE",
                code: 4
            });
        }

        // if exists, check writer
        if(event.accountId != req.session.loginInfo.loginId){
            return res.status(403).json({
                error: "PERMISSION FAILURE",
                code: 5
            });
        }

        // MODIFY AND SAVE IN DB
        event.title =  req.body.title;
        event.content = req.body.content;      
        event.date.edited = new Date();
        event.is_edited = true;

        event.save((err, event) => {
            if(err) throw err;

            return res.json({
                success: true,
                event
            });
        });
    });
});

export default router;