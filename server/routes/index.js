import express from 'express';
import account from './account';
import academy from './academy';
import course from './course';
import event from './event';
import hashTag from './hashTag';
import question from './question';

const router = express.Router();
router.use('/account', account);
router.use('/academy',academy);
router.use('/course',course);
router.use('/event',event);
router.use('/hashTag',hashTag);
router.use('/question',question);

export default router;