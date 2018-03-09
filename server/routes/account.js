import express from 'express';
import Account from '../models/account';
import Academy from '../models/academy';
const router = express.Router();
/*
    ACCOUNT SIGNUP: POST /api/account/signup
    BODY SAMPLE: 
    {
        // Common Variables
            "requestType": "{"parents" | "corporate"}",
            "marketingInfo": true,
            "loginId": "bbd@educhoice.com",
            "password": "test",
        // Parent Variables
            "memberAddress": "경기 부천시 소사구 송내동",
            "nickname": "bbd",
        // Corporate Variables
            "originalName": "이동규",
            "phoneNo": "010-1234-5678"
            "academy": {
                "academyName": "codesquad",
                "ownerName":"이준",
                "academyPhoneNumber":"(830) 533-3036",
                "address": {
                    "address":"서울 노원구 상계동 455 백산빌딩",
                    "jibunaddress": "서울 노원구 상계동 455",
                    "roadAddress": "서울 노원구 한글비석로 460",
                    "zoneCode": "139820",
                    "sido": "서울시",
                    "sigungu": "노원구",
                    "dong": "상계동",
                    "latitude": 37.66444002512082,
                    "longitude": 127.06697859544342
                }    

            }
    }
    ERROR CODES:
        1: BAD REQUEST TYPE
        2: BAD PASSWORD
        3: LOGIN ID EXISTS
        4: BAD REQUEST DATA
        5: NICKNAME EXISTS
        6: EMPTY CONTENTS
        7: ACADEMY EXISTS
*/

router.post('/signup', (req, res) => {
    // check request type
    if(typeof req.body.requestType !== 'string' || req.body.requestType === ""){
        return res.status(400).json({
            error: "BAD REQUEST TYPE",
            code: 1
        });
    }
    if(!req.body.requestType.match('parents') && !req.body.requestType.match('corporate')) {
        return res.status(400).json({
            error: "BAD REQUEST TYPE",
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
    // check login ID existance
    Account.findOne({ loginId: req.body.loginId }, (err, exists) => {
        if(err) throw err;
        if(exists) {
            return res.status(409).json({
                error: "LOGIN ID EXISTS",
                code: 3
            });
        } else {
            if(req.body.requestType.match('parents')){
                // check request data
                if(!req.body.memberAddress || !req.body.nickname || req.body.originalName || req.body.phoneNo ){
                    return res.status(400).json({
                        error: "BAD REQUEST DATA",
                        code: 4
                    });            
                }
                // check nickname existance
                Account.findOne({ nickname: req.body.nickname }, (err, exists) => {
                    if(err) throw err;
                    if(exists) {
                        return res.status(400).json({
                            error: "NICKNAME EXISTS",
                            code: 5
                        });
                    }
                });
                // CREATE ACCOUNT
                let account = new Account({
                    requestType: req.body.requestType,
                    marketingInfo: req.body.marketingInfo,
                    loginId: req.body.loginId,
                    password: req.body.password,
                    memberAddress: req.body.memberAddress,
                    nickname: req.body.nickname,          
                });
                account.save( err => {
                    if(err) throw err;
                    return res.json({ success: true });
                })  
            }
        
            if (req.body.requestType.match('corporate')) {
                // check request data
               if (!req.body.originalName || !req.body.phoneNo || req.body.memberAddress|| req.body.nickname){
                    return res.status(400).json({
                        error: "BAD REQUEST DATA",
                        code: 4
                    }); 
                }
                    // check academyName valid
                if(typeof req.body.academy.academyName !== 'string') {
                    return res.status(400).json({
                        error: "EMPTY CONTENTS",
                        code: 6
                    });
                }
        
                if(req.body.academy.academyName === "") {
                    return res.status(400).json({
                        error: "EMPTY CONTENTS",
                        code: 6
                    });
                }
                // check login ID existance
                Academy.findOne({ loginId: req.body.loginId }, (err, exists) => {
                    if(err) throw err;
                    if(exists) {
                        return res.status(409).json({
                            error: "ACADEMY EXISTS",
                            code: 7
                        });
                    }
                });
                // CREATE ACCOUNT
                let account = new Account({
                    requestType: req.body.requestType,
                    marketingInfo: req.body.marketingInfo,
                    loginId: req.body.loginId,
                    password: req.body.password,
                    originalName: req.body.originalName,
                    phoneNo: req.body.phoneNo            
                });
                account.password = account.generateHash(account.password);
        
                // CREATE NEW ACADEMY
                let academy = new Academy({
                    accountId: req.body.loginId,
                    academyName: req.body.academy.academyName,
                    address: req.body.academy.address
                });        
                // save in the db
                account.save( err => {
                    if(err) throw err;
                    // save in db
                    academy.save( err => {
                        if(err) throw err;
                        return res.json({ success: true });
                    })        
                });
            }
        }
    });  
});

/*
    ACCOUNT SIGNIN: POST /api/account/signin
    BODY SAMPLE: { "loginId": "test", "password": "test" }
    ERROR CODES:
        1: LOGIN FAILED
*/
router.post('/signin',(req, res) => {
    if(typeof req.body.password !== "string"){
        return res.status(401).json({
            error: "LOGIN FAILED",
            code: 1
        });
    }

    // find the user by username
    Account.findOne({ loginId: req.body.loginId }, (err, account) => {
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
            loginId: account.loginId
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