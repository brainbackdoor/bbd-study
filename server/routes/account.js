import express from 'express';
import Account from '../models/account';

const router = express.Router();

/*
    POST /api/account/signup
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

router.post('/signin',(req, res) => {
    res.json({ success: true });
});

router.get('/getinfo', (req,res) => {
    res.json({ info: null });
});

router.post('/logout', (req, res) => {
    return res.json({ success: true });
});

export default router;