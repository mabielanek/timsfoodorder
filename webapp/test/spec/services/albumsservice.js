'use strict';

describe('Service: AlbumsService', function () {

  // load the service's module
  beforeEach(module('albumApp'));
  
  var albumId = 32;

  // instantiate service
  var AlbumsService;
  var httpBackend;
  beforeEach(inject(function (_AlbumsService_, _$httpBackend_) {
    AlbumsService = _AlbumsService_;
    httpBackend = _$httpBackend_;
    
  }));
  
  afterEach(function() {
    httpBackend.verifyNoOutstandingExpectation();
    httpBackend.verifyNoOutstandingRequest();
  });
  

  it('should do something', function () {
    expect(!!AlbumsService).toBe(true);
  });
  
  it('shuld call service to fetch albums data', function() {
    httpBackend.expectGET('/api/albums').respond('respondGetAlbums');
    
    var result;
    AlbumsService.getAlbumList().then(function(callResult) {
      result = callResult.data; 
    });
    httpBackend.flush();
    expect(result).toEqual('respondGetAlbums');
  });

  it('shuld call service to fetch songs data', function() {
    var albumId = 43;
    httpBackend.expectGET('/api/album/' + 43 + '/songs').respond('respondGetSongs');
    
    var result;
    AlbumsService.getSongs(albumId).then(function(callResult) {
      result = callResult.data; 
    });
    httpBackend.flush();
    expect(result).toEqual('respondGetSongs');
  });

  it('shuld call service to fetch single album', function() {
    var albumId = 23;
    httpBackend.expectGET('/api/album/' + 23).respond('respondGetSingleAlbum');
    
    var result;
    AlbumsService.getAlbum(albumId).then(function(callResult) {
      result = callResult.data; 
    });
    httpBackend.flush();
    expect(result).toEqual('respondGetSingleAlbum');
  });
  
  it('shuld call service to save album data', function() {
    var albumToSave = {sample: 'aaa'};
    httpBackend.expectPUT('/api/album', albumToSave).respond('respondPutAlbum');
    
    var result;
    AlbumsService.saveAlbum(albumToSave).then(function(callResult) {
      result = callResult.data; 
    });
    httpBackend.flush();
    expect(result).toEqual('respondPutAlbum');
  });


});
