import express from 'express';
import account from './account';
import academy from './academy';
import course from './course';
import event from './event';
import hashTag from './hashTag';

const router = express.Router();
router.use('/account', account);
router.use('/academy',academy);
router.use('/course',course);
router.use('/event',event);
router.use('/hashTag',hashTag);

export default router;