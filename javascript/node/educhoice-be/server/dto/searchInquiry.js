import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;

const SearchInquiry = new Schema({
    accountId: String,
    accountName: String,
    answerContent: String,
    created: String,
    reply:[
        {
            role: String,
            accountName: String,
            content: String,
        }
    ],
    created: { type: Date, default: Date.now }
});
export default mongoose.model('searchInquiry', SearchInquiry);