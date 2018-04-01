import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;
const ObjectId = mongoose.Types.ObjectId;
const Reply = new Schema({
    replyId: {type: Schema.Types.ObjectId, default: new ObjectId()},
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