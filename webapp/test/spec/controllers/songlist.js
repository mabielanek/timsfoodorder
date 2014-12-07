'use strict';

describe('Controller: SonglistCtrl', function () {

  // load the controller's module
  beforeEach(module('albumApp'));

  var SonglistCtrl,
    scope, $q, mockAlbumsService = {};
    var initSongsData = {data: {result: ['aaa']}};
    var routeParams = {albumId: 1}; ;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $q, AlbumsService) {
    scope = $rootScope.$new();
    mockAlbumsService = AlbumsService;
    spyOn(mockAlbumsService, "getSongs").andCallFake(function(){
      return $q.when(initSongsData);
    });

    SonglistCtrl = $controller('SonglistCtrl', {
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
    expect(mockAlbumsService.getSongs).toHaveBeenCalledWith(routeParams.albumId);
  });
  
  it('should init songs data on load', function() { 
    scope.$digest();
    expect(scope.songList).toEqual(initSongsData.data.result);
  });

});
