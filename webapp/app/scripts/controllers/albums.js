'use strict';

angular.module('albumApp')
  .controller('AlbumsCtrl', function ($scope, AlbumsService) {
    
    $scope.albums = [];
    $scope.expandedAlbumId = undefined;
    $scope.selectedAlbum = undefined;
    
    init();
    
    function init() {
      AlbumsService.getAlbumList().then(function(result) {
        angular.extend($scope.albums, result.data.result);
      });
    }
    
    $scope.selectAlbum = function(albumId) {
      $scope.selectedAlbum = albumId;
    }
    
    $scope.isSelected = function(albumId) {
      return $scope.selectedAlbum === albumId;
    }
    
    $scope.expandDetail = function (albumId) {
      if($scope.expandedAlbumId === albumId) {
        $scope.expandedAlbumId = undefined;
      } else {
        $scope.expandedAlbumId = albumId;
      }
    }
    
  });
