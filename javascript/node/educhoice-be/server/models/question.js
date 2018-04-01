import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;
const ObjectId = mongoose.Types.ObjectId;
const Question = new Schema({
    questionId: {type: Schema.Types.ObjectId, default: new ObjectId()},
    accountId: String,
    accountName: String,
    receivers:[{
        receiverId: String,
        receiverName: String
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