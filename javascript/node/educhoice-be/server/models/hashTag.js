import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;
const ObjectId = mongoose.Types.ObjectId;
const HashTag = new Schema({
    hashTagId: {type: Schema.Types.ObjectId, default: new ObjectId()},
    title: String,
    date: {
        created: { type: Date, default: Date.now },
        edited: { type: Date, default: Date.now }
    }, 
    is_edited: { type: Boolean, default: false }
});
export default mongoose.model('hashTag', HashTag);