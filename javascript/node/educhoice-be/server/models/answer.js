import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;
const ObjectId = mongoose.Types.ObjectId;
const Answer = new Schema({
    answerId: {type: Schema.Types.ObjectId, default: new ObjectId()},
    accountId: String,
    accountName: String,
    questionId: String,
    questionerId: String,
    content: String,
    date: {
        created: { type: Date, default: Date.now },
        edited: { type: Date, default: Date.now }
    }, 
    is_edited: { type: Boolean, default: false }
});
export default mongoose.model('answer', Answer);