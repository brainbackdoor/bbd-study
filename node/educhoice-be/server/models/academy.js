import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;
const ObjectId = mongoose.Types.ObjectId;
const Academy = new Schema({
    academyId: {type: Schema.Types.ObjectId, default: new ObjectId()},
    accountId: String,
    searchImpression: String,
    academyName: String,
    ownerName: String,
    phoneNo: String,
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
    subjects:[
        String
    ],
    grades:[
        String
    ],
    courses: [
        {
            courseId: {type: Schema.Types.ObjectId, default: new ObjectId()},
            courseType: String,
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
            duration: String,
            date: {
                created: { type: Date, default: Date.now },
                edited: { type: Date, default: Date.now }
            }, 
            is_edited: { type: Boolean, default: false }  
        }
    ],
    events:[
        {
            eventId: {type: Schema.Types.ObjectId, default: new ObjectId()},
            title: String,
            content: String,
            date: {
                created: { type: Date, default: Date.now },
                edited: { type: Date, default: Date.now }
            }, 
            is_edited: { type: Boolean, default: false }
        }
    ],
    hashTags:[
        {
            hashTagId: {type: Schema.Types.ObjectId, default: new ObjectId()},
            title: String,
            date: {
                created: { type: Date, default: Date.now },
                edited: { type: Date, default: Date.now }
            }, 
            is_edited: { type: Boolean, default: false }
        }
    ],
    imageUrl: [
        String
    ],
    created: { type: Date, default: Date.now }
});
export default mongoose.model('academy', Academy);