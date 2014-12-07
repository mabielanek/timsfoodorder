'use strict';

var album = require('./album');

/**
 * Application routes
 */
module.exports = function(app) {

  // Server API Routes
  app.get('/api/albums', album.getAlbums);
  app.get('/api/album/:id', album.getAlbumById);
  app.get('/api/album/:id/songs', album.getSongsByAlbumId);
  app.put('/api/album', album.saveAlbum);
  

};