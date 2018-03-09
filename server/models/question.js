import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;

const Question = new Schema({
    accountId: String,
    receivers:[{
        receiverId: String
    }],
    questionTitle: String,
    questionContent: String,
    date: {
        created: { type: Date, default: Date.now },
        edited: { type: Date, default: Date.now }
    }, 
    is_edited: { type: Boolean, default: false }
});
export default mongoose.model('question', Question);