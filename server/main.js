import express from 'express';
import path from 'path';

/* Development */
import WebpackDevServer from 'webpack-dev-server';
import webpack from 'webpack';

/* Middleware - log */
import morgan from 'morgan'; // HTTP Request Logger
import bodyParser from 'body-parser'; // Parse HTML Body

/* Middleware - mongodb */
import mongoose from 'mongoose';
import session from 'express-session';

/* setup routers & static directory */
import api from './routes';

const app = express();
const port = 3000;

app.use('/', express.static(path.join(__dirname, './../public')));

app.get('/hello', (req,res) => {
    return res.send('Hello BBD');
});

app.listen(port, () => {
    console.log('Express is listening on port',port);
});


/* development */
if (process.env.NODE_ENV == 'development'){
    console.log('Server is running on development mode');
    const config = require('../webpack.dev.config');
    const compiler = webpack(config);
    const devServer = new WebpackDevServer(compiler, config.devServer);
    devServer.listen(
        devPort, () => {
            console.log('webpack-dev-server is listening on port', devPort);
        }
    );
}

/* Middleware */
const devPort = 4000;

app.use(morgan('dev'));
app.use(bodyParser.json());

/* Middleware - mongodb */
/* mongodb connection */
const db = mongoose.connection;
db.on('error', console.error);
db.once('open', () => { console.log('Connected to mongodb server'); });

// mongoose.connect('mongodb://username:password@host:port/database=');
mongoose.connect('mongodb://bbd:codesquad@ds239648.mlab.com:39648/react-codelab-project');

/* use session */
app.use(session({
    secret: 'bbd$1$234',
    resave: false,
    saveUnintialized: true
}))

/* setup routers & static directory */
app.use('/api', api);