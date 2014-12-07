'use strict';

angular.module('albumApp')
  .controller('SonglistCtrl', function ($scope, $routeParams, $location, AlbumsService) {
    
    $scope.songList = [];
    init();
    
    function init() {
      AlbumsService.getSongs($routeParams.albumId).then(function(result){
        $scope.songList = result.data.result;
      });
    }
    
    $scope.showAlbumList = function() {
      $location.path('/albums')
    }
    
  });
