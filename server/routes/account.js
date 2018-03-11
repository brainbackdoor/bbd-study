import express from 'express';
import Account from '../models/account';
import Academy from '../models/academy';
const router = express.Router();
 
/**
 * @api {post} /api/account/signup Post Signup Information
 * @apiVersion 0.1.0
 * @apiName PostAccountAndAcademy
 * @apiGroup Account
 * 
 * @apiParam {String} requestType 유저 타입 { parents | corporate }
 * @apiParam {Boolean} marketingInfo 마케팅 수신 동의여부
 * @apiParam {String} loginId 유저 ID 
 * @apiParam {String} password 비밀번호
 * @apiParam {String} memberAddress 주소 [학부모일 경우만 기입]
 * @apiParam {String} nickname 별명 [학부모일 경우만 기입]
 * @apiParam {String} originalName 가입자이름 [학원일 경우만 기입]
 * @apiParam {String} phoneNo 가입자번호 [학원일 경우만 기입]
 * @apiParam {Academy} academy 학원정보 [학원일 경우만 기입]
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 201 OK
 * {
 *      "success": true
 * }
 * 
 * @apiError BAD REQUEST TYPE
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 BAD REQUEST TYPE
 *     {
 *       "error": "BAD REQUEST TYPE",
 *       "code" : 1
 *     }
 * 
 * @apiError BAD PASSWORD
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 BAD PASSWORD
 *     {
 *       "error": "BAD PASSWORD",
 *       "code" : 2
 *     }
 * 
 * @apiError LOGIN ID EXISTS
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 409 LOGIN ID EXISTS
 *     {
 *       "error": "LOGIN ID EXISTS",
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
 * 
 * @apiError NICKNAME EXISTS
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 NICKNAME EXISTS
 *     {
 *       "error": "NICKNAME EXISTS",
 *       "code" : 5
 *     }
 * 
 * @apiError EMPTY CONTENTS
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 400 EMPTY CONTENTS
 *     {
 *       "error": "EMPTY CONTENTS",
 *       "code" : 6
 *     }
 * 
 * @apiError ACADEMY EXISTS
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 409 ACADEMY EXISTS
 *     {
 *       "error": "ACADEMY EXISTS",
 *       "code" : 7
 *     }
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
                    } else {
                        // CREATE ACCOUNT
                        let account = new Account({
                            requestType: req.body.requestType,
                            marketingInfo: req.body.marketingInfo,
                            loginId: req.body.loginId,
                            password: req.body.password,
                            memberAddress: req.body.memberAddress,
                            nickname: req.body.nickname,          
                        });
                        account.password = account.generateHash(account.password);
                        account.save( err => {
                            if(err) throw err;
                            return res.json({ success: true });
                        })                          
                    }                    
                });
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
                    } else {
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
                        // save in the db
                        account.save( err => {
                            if(err) throw err;
                        });         

                        // CREATE NEW ACADEMY
                        let academy = new Academy({
                            accountId: account._id,
                            academyName: req.body.academy.academyName,
                            ownerName: req.body.academy.ownerName,
                            address: req.body.academy.address,
                            academyPhoneNumber: req.body.academy.academyPhoneNumber,
                            carAvailable: req.body.carAvailable
                        });        
                        // save in db
                        academy.save( err => {
                            if(err) throw err;
                            return res.status(201).json({ success: true });
                        });        
                    }
                });
            }
        }
    });  
});
/**
 * @api {post} /api/account/signin Post Signin information
 * @apiVersion 0.1.0
 * @apiName Login
 * @apiGroup Account
 * 
 * @apiParam {String} loginId 유저 ID
 * @apiParam {String} password 비밀번호
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * {
 *      "success": true
 * }
 * 
 * @apiError LOGIN FAILED
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 401 LOGIN FAILED
 *     {
 *       "error": "LOGIN FAILED",
 *       "code" : 1
 *     }
 * 
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

        if(account.requestType ==='parents'){
            // alter session
            let session = req.session;
            session.loginInfo = {
                _id: account._id,
                loginId: account.loginId,
                name: account.nickname,
                type: account.requestType
            };
            // return success
            return res.json({
                success: true
            });            
        } else {
            Academy.findOne({accountId:account._id},(err, academy) => {
                if(err) throw err;
                // alter session
                let session = req.session;
                session.loginInfo = {
                    _id: account._id,
                    loginId: account.loginId,
                    academyId: academy._id,
                    name: academy.academyName,
                    type: account.requestType
                }; 
                // return success
                return res.json({
                    success: true
                });                   
            });
           
        } 
    });

});
/**
 * @api {get} /api/account/getInfo Get Session Information
 * @apiVersion 0.1.0
 * @apiName GetLoginInfo
 * @apiGroup Account
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * {
 *    "info": {
 *        "_id": "5aa2ea5ce7d46c44ccf24589",
 *        "loginId": "bbd@modoohakwon.com",
 *        "name": "bbd",
 *        "type": "parents"
 *     }
 * }
 * 
 * @apiError LOGIN FAILED
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 401 LOGIN FAILED
 *     {
 *       "code" : 1
 *     }
 * 
 */

router.get('/getinfo', (req,res) => {
    if(typeof req.session.loginInfo ==="undefined "){
        return res.status(401).json({
            error: 1
        });
    }
    res.json({ info: req.session.loginInfo });
});
/**
 * @api {post} /api/account/logout Post Logout Request
 * @apiVersion 0.1.0
 * @apiName Logout
 * @apiGroup Account
 * 
 * 
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 * {
 *      "success": true
 * }
 *
 */

router.post('/logout', (req, res) => {
    req.session.destroy(err => { if(err) throw err; });
    return res.json({ success: true });
});

export default router;