import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;

const Academy = new Schema({
    accountId: String,
    academyName: String,
    ownerName: String,
    academyPhoneNumber: String,
    address: {
        address: String,
        jibunaddress: String,
        roadAddress: String,
        zonecode: Number,
        sido: String,
        sigungu: String,
        dong: String,
        latitude: Number,
        longitude: Number
    },
    carAvailable: Boolean,
    inquiryResponseRate: Number,
    introduction: String,
    hashTags: {
        title: String,    
    },
    created: { type: Date, default: Date.now }
});
export default mongoose.model('academy', Academy);