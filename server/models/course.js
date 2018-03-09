import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;

const Course = new Schema({
    courseType: String,
    accountId: String,
    academyId: String,
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
});
export default mongoose.model('course', Course);