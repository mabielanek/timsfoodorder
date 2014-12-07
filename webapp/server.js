'use strict';

var express = require('express');
var path = require('path');

/**
 * Main application file
 */

var app = express();

app.use(require('connect-livereload')());

// Disable caching of scripts for easier testing
app.use(function noCache(req, res, next) {
  if (req.url.indexOf('/scripts/') === 0) {
    res.header('Cache-Control', 'no-cache, no-store, must-revalidate');
    res.header('Pragma', 'no-cache');
    res.header('Expires', 0);
  }
  next();
});

app.use(express.static(path.join(__dirname, '.tmp')));
app.use(express.static(path.join(__dirname, 'app')));
console.log(path.join(__dirname, 'app'));
app.use(express.errorHandler());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
// Router needs to be last
app.use(app.router);

// Express settings
//require('./lib/config/express')(app);

// Routing
require('./server_side/routes')(app);

// Start server
app.listen(3000, function () {
  console.log('Express server listening on port 3000');
});

// Expose app
exports = module.exports = app;
