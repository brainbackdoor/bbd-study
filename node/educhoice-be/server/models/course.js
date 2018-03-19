import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;
const ObjectId = mongoose.Types.ObjectId;
const Course = new Schema({
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
});
export default mongoose.model('course', Course);