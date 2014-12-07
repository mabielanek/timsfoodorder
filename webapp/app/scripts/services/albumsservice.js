'use strict';

angular.module('albumApp')
  .service('AlbumsService', function Albumsservice($http) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    
    return {
      getAlbumList: function() {
        return $http.get('/api/albums');
      },

      getSongs: function(albumId) {
        return $http.get('/api/album/' + albumId + '/songs');
      },

      getAlbum: function(albumId) {
        return $http.get('/api/album/' + albumId);
      },

      saveAlbum: function(album) {
        return $http.put('/api/album', album);
      }
    
    };
  });
