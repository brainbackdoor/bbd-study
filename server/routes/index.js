import express from 'express';
import account from './account';
import academy from './academy';
import course from './course';
import event from './event';
import hashTag from './hashTag';
import question from './question';
import answer from './answer';
import reply from './reply';

const router = express.Router();
router.use('/account', account);
router.use('/academy',academy);
router.use('/course',course);
router.use('/event',event);
router.use('/hashTag',hashTag);
router.use('/question',question);
router.use('/answer',answer);
router.use('/reply',reply);

export default router;