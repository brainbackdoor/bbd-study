var express         = require('express');
var app             = express();
var bodyParser      = require('body-parser');
var mongoose        = require('mongoose');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var port = process.env.PORT || 8080;

var server = app.listen(port, function(){
    console.log("Express server has started on port "+ port)
});

// Connect to MongoDB Server
var db = mongoose.connection;
db.on('error',console.error);
db.once('open',function(){
    console.log("Connected to mongod server");
});

mongoose.connect('mongodb://localhost:27017/mongodb_tutorial');

// Define Model
var Book = require('./models/book');

var router = require('./routes')(app, Book);