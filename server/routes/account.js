import express from 'express';
import Account from '../models/account';

const router = express.Router();

/*
    ACCOUNT SIGNUP: POST /api/account/signup
    BODY SAMPLE: { "username": "test", "password": "test" }
    ERROR CODES:
        1: BAD USERNAME
        2: BAD PASSWORD
        3: USERNAM EXISTS
*/

router.post('/signup', (req, res) => {
    
    // check username format
    let usernameRegex = /^[a-z0-9]+$/;

    if(!usernameRegex.test(req.body.username)) {
        return res.status(400).json({
            error: "BAD USERNAME",
            code: 1
        });
    }
    
    // check pass length
    if(req.body.password.length < 4 || typeof req.body.password !== "string"){
        return res.status(400).json({
            error: "BAD PASSWORD",
            code: 2
        });
    }

    // check user existance
    Account.findOne({ username: req.body.username }, (err, exists) => {
        if(err) throw err;
        if(exists) {
            return res.status(409).json({
                error: "USERNAME EXISTS",
                code: 3
            });
        }

        // CREATE ACCOUNT
        let account = new Account({
            username: req.body.username,
            password: req.body.password
        });

        account.password = account.generateHash(account.password);

        // save in the db
        account.save( err => {
            if(err) throw err;
            return res.json({ success: true });
        });
    });
});

/*
    ACCOUNT SIGNIN: POST /api/account/signin
    BODY SAMPLE: { "username": "test", "password": "test" }
    ERROR CODES:
        1: LOGIN FAILED
*/
router.post('/signin',(req, res) => {
    console.log(req.body);
    if(typeof req.body.password !== "string"){
        return res.status(401).json({
            error: "LOGIN FAILED",
            code: 1
        });
    }

    // find the user by username
    Account.findOne({ username: req.body.username }, (err, account) => {
        if(err) throw err;

        // check account existancy
        if(!account){
            return res.status(401).json({
                error: "LOGIN FAILED",
                code: 1
            });
        }

        // check whether the password is valid
        if(!account.validateHash(req.body.password)) {
            return res.status(401).json({
                error: "LOGIN FAILED",
                code: 1
            });
        }

        // alter session
        let session = req.session;
        session.loginInfo = {
            _id: account._id,
            username: account.username
        };

        // return success
        return res.json({
            success: true
        });
    });
});

/*
    GET CURRENT USER INFO GET /api/account/getInfo
*/
router.get('/getinfo', (req,res) => {
    if(typeof req.session.loginInfo ==="undefined "){
        return res.status(401).json({
            error: 1
        });
    }

    res.json({ info: req.session.loginInfo });
});

/*
    LOGOUT: POS /api/account/logout
*/
router.post('/logout', (req, res) => {
    req.session.destroy(err => { if(err) throw err; });
    return res.json({ success: true });
});

export default router;