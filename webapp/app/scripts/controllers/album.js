'use strict';

angular.module('albumApp')
  .controller('AlbumCtrl', function ($scope, $routeParams, $location, AlbumsService) {
    
    $scope.origAlbum = {};
    $scope.album = {};
    init();
    
    function init() {
      AlbumsService.getAlbum($routeParams.albumId).then(function(result) {
        $scope.origAlbum = result.data.result;
        $scope.album = angular.copy($scope.origAlbum);
      });
    }
    
    $scope.showAlbumList = function() {
      $location.path("/albums");
    }
    
    $scope.saveAlbum = function () {
      AlbumsService.saveAlbum($scope.album).then(function(result) {
        $scope.origAlbum = result.data.result;
        $scope.album = angular.copy($scope.origAlbum);
      });
    }
    
    $scope.resetEdit = function () {
      $scope.album = angular.copy($scope.origAlbum);
    }
    
    $scope.canSave = function() {
      return !angular.equals($scope.album, $scope.origAlbum);
    }
  });
