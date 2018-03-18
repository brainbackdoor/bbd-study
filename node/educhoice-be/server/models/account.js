import mongoose from 'mongoose';
import bcrypt from 'bcryptjs';

const Schema = mongoose.Schema;

const Account = new Schema({
    // Common Variables
    requestType: String,
    marketingInfo: Boolean,
    loginId: String,
    password: String,
    // Parent Variables
    memberAddress: String,
    nickname: String,
    // Corporate Variables
    originalName: String,
    phoneNo: String,
    created: { type: Date, default: Date.now }
});

// generates hash 
Account.methods.generateHash = function(password) {
    return bcrypt.hashSync(password, 8);
};

// compares the password
Account.methods.validateHash = function(password) {
    return bcrypt.compareSync(password, this.password);
};

export default mongoose.model('account', Account);