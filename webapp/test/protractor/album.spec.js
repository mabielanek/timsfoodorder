describe('album homepage', function() {
  
  
  var prot = protractor.getInstance();

  var mockHttp = function() {
    angular.module('mockHttpBackend', ['albumApp','ngMockE2E'])
      .run(function ($httpBackend) {
        var albums = {
            result: [{
              id: 1,
              title: 'aaa',
              artist: 'artist AAA',
              releaseYear: 1212
            },
            {
              id: 2,
              title: 'bbb',
              artist: 'artist BBB',
              releaseYear: 4433,
              comment: 'Sample comment for album bbb.'
            },
            {
              id: 3,
              title: 'ccc',
              artist: 'artist CCC',
              releaseYear: 2012
            }]
        };
    
        $httpBackend.whenGET('/api/albums').respond(function(method, url, data, headers) {
          return [200, albums, {}];
        });
    
        $httpBackend.whenGET(/.*/).passThrough();
      });
    };
  
  
  beforeEach(function() {
    prot.addMockModule('mockHttpBackend', mockHttp);
  });

  function loadMainPage() {
    browser.get('http://localhost:3000');
  }
  
  
  it('should open album homepage', function() {
    loadMainPage();

    var title = element(by.tagName('p'));

    expect(title.getText()).toEqual('This is the albums view.');
  });
  
  it('should contains correct number of albums', function() {
    loadMainPage();
    albumList = element.all(by.repeater('album in albums'));
    // we have 2 rows for every album
    expect(albumList.count()).toEqual(2 * 3);
  });
  
  it('should contains three action links in row', function() {
    loadMainPage();
    
    var all =
      element.all(by.css('tr:nth-child(1) td a'));
    all.then(function(arr) {
      expect(arr.length).toEqual(3);
      expect(arr[0].getText()).toEqual('Expand');
      expect(arr[1].getText()).toEqual('Songs');
      expect(arr[2].getText()).toEqual('Edit');
    });
  });
  
  
  it('should mark row as selected on click', function() {
    loadMainPage();
    
    var selectedClass = 'alert-info'

    // nothing "selected" on load
    element(by.id('idAlbums:0')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeTruthy();
    });
    element(by.id('idAlbums:1')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeTruthy();
    });

    // click on first row, check if selected
    element(by.id('idAlbums:0')).click();
    element(by.id('idAlbums:0')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeFalsy();
    });
    element(by.id('idAlbums:1')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeTruthy();
    });

    // click on second row, check if selected
    element(by.id('idAlbums:1')).click();
    element(by.id('idAlbums:0')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeTruthy();
    });
    element(by.id('idAlbums:1')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeFalsy();
    });

    // click on second row, check if second (and only second) is still selected 
    element(by.id('idAlbums:1')).click();
    element(by.id('idAlbums:0')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeTruthy();
    });
    element(by.id('idAlbums:1')).getAttribute("class").then(function(attrValue) {
      var styles = attrValue.split(" ");
      expect(styles.indexOf(selectedClass) < 0).toBeFalsy();
    });

  });

  it('should handle Expand link', function() {
    loadMainPage();

    element(by.id('idAlbumsComment:1')).isDisplayed().then(function(disp) {
      expect(disp).toEqual(false);
    });
    element(by.id('idAlbums:1')).element(by.css('td a:nth-child(1)')).click();
    element(by.id('idAlbumsComment:1')).isDisplayed().then(function(disp) {
      expect(disp).toEqual(true);
    });
    element(by.id('idAlbums:1')).element(by.css('td a:nth-child(1)')).click();
    
    element(by.id('idAlbumsComment:1')).isDisplayed().then(function(disp) {
      expect(disp).toEqual(false);
    });
    
  });

  it('should load sons page when click on link', function() {
    loadMainPage();
    element(by.css('tr:nth-child(1) td a:nth-child(2)')).click();
    expect(element(by.tagName('p')).getText()).toEqual('This is the songList view.');
  });

});
