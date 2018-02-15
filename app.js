// Load The Dependencies
const express       = require('express')
const bodyParser    = require('body-parser')
const morgan        = require('morgan')
const mongoose      = require('mongoose')

// Load The Config
const config        = require('./config')
const port          = process.env.PORT || 3000

// Express Configuration
const app           = express()
console.log(config.mongodbUri)
// parse JSON and url-encoded query
app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json())

// print the request log on console
app.use(morgan('dev'))

// set the secret key variable for jwt
app.set('jwt-secret', config.secret)


// index page, jst for testing
app.get('/', (req, res)=>{
    res.send('Hello JWT')
})

// open the server
app.listen(port, () => {
    console.log(`Express is running on port ${port}`)
})

/*
Connect to MongoDB Server
*/

mongoose.connect(config.mongodbUri)

const db = mongoose.connection
db.on('error', console.error)
db.once('open', ()=>{
    console.log('connected to mongodb server')
})