import express from 'express';

const router = express.Router();

router.post('/signup', (req, res) => {
    res.json({ success: true });
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