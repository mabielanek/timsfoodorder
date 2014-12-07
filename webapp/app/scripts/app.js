'use strict';

angular.module('albumApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute'
])
  .config(function ($routeProvider) {
    $routeProvider
/*      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })*/
      .when('/albums', {
        templateUrl: 'views/albums.html',
        controller: 'AlbumsCtrl'
      })
      .when('/album/:albumId', {
        templateUrl: 'views/album.html',
        controller: 'AlbumCtrl'
      })
      .when('/songList/:albumId', {
        templateUrl: 'views/songlist.html',
        controller: 'SonglistCtrl'
      })
      .otherwise({
        redirectTo: '/albums'
      });
  });




