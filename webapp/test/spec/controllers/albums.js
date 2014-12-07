'use strict';

describe('Controller: AlbumsCtrl', function () {

  // load the controller's module
  beforeEach(module('albumApp'));

  var AlbumsCtrl,
    scope, $q, mockAlbumsService = {};
    var initAlbumsData = {data: {result: ['aaa', 'bbb']}};;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $q, AlbumsService) {
    scope = $rootScope.$new();
    mockAlbumsService = AlbumsService;
    spyOn(mockAlbumsService, "getAlbumList").andCallFake(function(){
      return $q.when(initAlbumsData);
    });
    
    AlbumsCtrl = $controller('AlbumsCtrl', {
      $scope: scope
    });
  }));
  
  
  it('should call fetch data from service on load', function() { 
    expect(mockAlbumsService.getAlbumList).toHaveBeenCalled();
  });
  
  it('should init songs data on load', function() { 
    scope.$digest();
    expect(scope.albums).toEqual(initAlbumsData.data.result);
  });
  
  it('should mark album as selected'), function() {
    expect(scope.selectedAlbum).toBeUndefined();
    scope.selectAlbum(2);
    expect(scope.selectedAlbum).toEqual(2);
  }

  it('should mark album as selected, when selected two times'), function() {
    expect(scope.selectedAlbum).toBeUndefined();
    scope.selectAlbum(2);
    scope.selectAlbum(2);
    expect(scope.selectedAlbum).toEqual(2);
  }

  it('should change selected album on second select'), function() {
    expect(scope.selectedAlbum).toBeUndefined();
    scope.selectAlbum(2);
    expect(scope.selectedAlbum).toEqual(2);
    scope.selectAlbum(4);
    expect(scope.selectedAlbum).toEqual(4);
  }
  
  it('should mark row as expanded', function() {
    expect(scope.expandedAlbumId).toBeUndefined();
    scope.expandDetail(2);
    expect(scope.expandedAlbumId).toEqual(2);
  });

  it('should mark row as unexpanded, on second expand', function() {
    expect(scope.expandedAlbumId).toBeUndefined();
    scope.expandDetail(2);
    expect(scope.expandedAlbumId).toEqual(2);
    scope.expandDetail(2);
    expect(scope.expandedAlbumId).toBeUndefined();
  });
  
  it('should change expanded album on second expand'), function() {
    expect(scope.expandedAlbumId).toBeUndefined();
    scope.expandDetail(2);
    expect(scope.expandedAlbumId).toEqual(2);
    scope.expandDetail(4);
    expect(scope.expandedAlbumId).toEqual(4);
  }
  


});
