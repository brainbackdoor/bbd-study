import express from 'express';
import account from './account';
import academy from './academy';

const router = express.Router();
router.use('/account', account);
router.use('/academy',academy);

export default router;