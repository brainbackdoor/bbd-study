'use strict';

var _express = require('express');

var _express2 = _interopRequireDefault(_express);

var _path = require('path');

var _path2 = _interopRequireDefault(_path);

var _webpackDevServer = require('webpack-dev-server');

var _webpackDevServer2 = _interopRequireDefault(_webpackDevServer);

var _webpack = require('webpack');

var _webpack2 = _interopRequireDefault(_webpack);

var _morgan = require('morgan');

var _morgan2 = _interopRequireDefault(_morgan);

var _bodyParser = require('body-parser');

var _bodyParser2 = _interopRequireDefault(_bodyParser);

var _mongoose = require('mongoose');

var _mongoose2 = _interopRequireDefault(_mongoose);

var _expressSession = require('express-session');

var _expressSession2 = _interopRequireDefault(_expressSession);

var _routes = require('./routes');

var _routes2 = _interopRequireDefault(_routes);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

// HTTP Request Logger
var app = (0, _express2.default)();

/* setup routers & static directory */
// Parse HTML Body

/* Middleware - mongodb */


/* Middleware - log */


/* Development */

var port = 3000;

app.use('/', _express2.default.static(_path2.default.join(__dirname, './../public')));

app.get('/hello', function (req, res) {
    return res.send('Hello BBD');
});

/* Middleware */
var devPort = 4000;

app.use((0, _morgan2.default)('dev'));
app.use(_bodyParser2.default.json());
console.log(process.env.NODE_ENV);
/* development */
if (process.env.NODE_ENV == 'development') {
    console.log('Server is running on development mode');
    var config = require('../webpack.dev.config');
    var compiler = (0, _webpack2.default)(config);
    var devServer = new _webpackDevServer2.default(compiler, config.devServer);
    devServer.listen(devPort, function () {
        console.log('webpack-dev-server is listening on port', devPort);
    });
} else {
    app.listen(port, function () {
        console.log('Express is listening on port', port);
    });
}

/* Middleware - mongodb */
/* mongodb connection */
var db = _mongoose2.default.connection;
db.on('error', console.error);
db.once('open', function () {
    console.log('Connected to mongodb server');
});

// mongoose.connect('mongodb://username:password@host:port/database=');
_mongoose2.default.connect('mongodb://bbd:codesquad@ds239648.mlab.com:39648/react-codelab-project');

/* use session */
app.use((0, _expressSession2.default)({
    secret: 'bbd$1$234',
    resave: false,
    saveUnintialized: true
}));

/* setup routers & static directory */
app.use('/api', _routes2.default);

app.get('*', function (req, res, next) {
    var regExp = /bundle.js$/;
    if (!regExp.test(req.url)) {
        res.sendFile(_path2.default.resolve(__dirname, './../public/index.html'));
    } else {
        next();
    }
});

/* handle error */
app.use(function (err, req, res, next) {
    console.error(err.stack);
    res.status(500).send('Something broke !');
});