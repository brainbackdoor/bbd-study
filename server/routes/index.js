import express from 'express';
import account from './account';
import academy from './academy';
import course from './course';

const router = express.Router();
router.use('/account', account);
router.use('/academy',academy);
router.use('/course',course);

export default router;