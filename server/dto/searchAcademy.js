import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;

const SearchAcademy = new Schema({
    accountId: String,
    academyName: String,
    academyPhoneNumber: String,
    address: {
        fullAddress: String,
        detailAddress: String,
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
    courses: [{
        courseId: String,
        courseType: String,
        accountId: String,
        coursesClassification: String,
        subjectClassification: String,
        courseName: String,
        grade: String,
        tuition: Number,
        dayOfWeek: [
            {
                startTime: String,
                endTime: String,
                day: String
            }
        ],
        duration: String
    }],
    events: [{
        eventId: String,
        title: String,
        content: String,
        edited: Date
    }],
    hashTags: [{
        hashTagId: String,
        title: String
    }],
    corporateAccount: {
        phoneNo: String,
        accountName: String,
        accountId: String
    },
    created: { type: Date, default: Date.now }
});
export default mongoose.model('searchAcademy', SearchAcademy);