import express from 'express';
import path from 'path';

/* Development */
import webpack from 'webpack';

/* Middleware - log */
import morgan from 'morgan'; // HTTP Request Logger
import bodyParser from 'body-parser'; // Parse HTML Body

/* Middleware - mongodb */
import mongoose from 'mongoose';

import session from 'express-session';

/* setup routers & static directory */
import api from './routes';
import cors from 'cors';


const app = express();
const port = 5000;

// CORS 설정
app.use(cors());

app.listen(port, () => {
    console.log('Express is listening on port',port);
});

/* Middleware */

app.use(morgan('dev'));
app.use(bodyParser.json());

/* Middleware - mongodb */
/* mongodb connection */

const db = mongoose.connection;
db.on('error', console.error);
db.once('open', () => { console.log('Connected to mongodb server'); });

// mongoose.connect('mongodb://username:password@host:port/database=');
mongoose.connect('mongodb://127.0.0.1:27017/educhoice-be');


/* use session */
app.use(session({
    secret: 'bbd$1$234',
    resave: false,
    saveUnintialized: true
}))

/* setup routers & static directory */
app.use('/', express.static(path.join(__dirname, './../public')));
app.use('/api', api);

/* handle error */
app.use(function(err, req, res, next){
    console.error(err.stack);
    res.status(500).send('Something broke !');
});
