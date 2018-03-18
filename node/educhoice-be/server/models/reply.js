import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;

const Reply = new Schema({
    accountId: String,
    role: String,
    accountName: String,
    questionId: String,
    answerId: String,
    content: String,
    date: {
        created: { type: Date, default: Date.now },
        edited: { type: Date, default: Date.now }
    }, 
    is_edited: { type: Boolean, default: false }
});
export default mongoose.model('reply', Reply);