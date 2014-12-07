'use strict';

describe('Controller: AlbumCtrl', function () {

  // load the controller's module
  beforeEach(module('albumApp'));

  var AlbumCtrl,
    scope, $q, mockAlbumsService = {};
  var initAlbumData = {data: {result: []}};
  var savedAlbumData = {data: {result: [{id: 1, title: 'aaa'}]}};
  var routeParams = {albumId: 1}; 

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $q, AlbumsService) {
    scope = $rootScope.$new();
    mockAlbumsService = AlbumsService;
    
    spyOn(mockAlbumsService, "getAlbum").andCallFake(function(){
      return $q.when(initAlbumData);
    });
    spyOn(mockAlbumsService, "saveAlbum").andCallFake(function() {
      return $q.when(savedAlbumData);
    });
    
    AlbumCtrl = $controller('AlbumCtrl', {
      $scope: scope,
      $routeParams : routeParams,
      AlbumsService : mockAlbumsService
    });
  }));
  
  it('should redirect user to album list ', inject(function($location) {
    spyOn($location, 'path');
    
    scope.showAlbumList();
    expect($location.path).toHaveBeenCalledWith('/albums');
  }));
  
  it('should call fetch data from service on load', function() { 
    expect(mockAlbumsService.getAlbum).toHaveBeenCalledWith(routeParams.albumId);
  });
  
  it('should init album data on load', function() { 
    scope.$digest();
    expect(scope.album).toEqual(initAlbumData.data.result);
    expect(scope.origAlbum).toEqual(initAlbumData.data.result);
  });
  
  it('should not allow to save after load, before any changes', function() {
    scope.$digest();
    expect(scope.canSave()).toBeFalsy();
  });
  
  it('should allow to save when album info changed', function() {
    scope.album.artist = 'xxx';
    expect(scope.canSave()).toBeTruthy();
  });

  it('should not allow to save, when album info changed, and reset', function() {
    scope.album.artist = 'xxx';
    scope.resetEdit();
    expect(scope.canSave()).toBeFalsy();
  });
  
  it('should have oryginal album data after reset', function () {
    var origValue = angular.copy(scope.album);
    scope.album.artist = 'xxx';
    // album should be changed now
    expect(scope.album).not.toEqual(origValue);
    scope.resetEdit();
    // album should have original value now
    expect(scope.album).toEqual(origValue);
  });

  it('should call save album from service on save', function() {
    var albumToSave = {id: 2, artist: 'Ala'};
    scope.album = albumToSave;
    scope.saveAlbum();
    
    scope.$digest();
    expect(mockAlbumsService.saveAlbum).toHaveBeenCalledWith(albumToSave);
    expect(scope.album).toEqual(savedAlbumData.data.result);
  });
  
  
});
