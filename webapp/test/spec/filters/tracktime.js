'use strict';

describe('Filter: trackTime', function () {

  // load the filter's module
  beforeEach(module('albumApp'));

  // initialize a new instance of the filter before each test
  var trackTime;
  beforeEach(inject(function ($filter) {
    trackTime = $filter('trackTime');
  }));

  it('should return "No time specified" for undefined', function() {
    expect(trackTime(undefined)).toEqual('No time specified')
  });

  it('should return "No time specified" for null', function() {
    expect(trackTime(null)).toEqual('No time specified')
  });
  
  it('should return short text of time track', function () {
    var songTime = {minutes: 4, seconds: 12};
    expect(trackTime(songTime)).toEqual('4:12');
  });
  
  it('should return short text of time track with zero aligned seconds', function() {
    var songTime = {minutes: 2, seconds: 4};
    expect(trackTime(songTime)).toEqual('2:04');
  });
  
  it('should return long text of time track', function() {
    var songTime = {minutes: 2, seconds: 23};
    expect(trackTime(songTime, 'long')).toEqual('2min 23sec');
  });

  it('should return long text of time track, no zero aligned for seconds', function() {
    var songTime = {minutes: 3, seconds: 1};
    expect(trackTime(songTime, 'long')).toEqual('3min 1sec');
  });

  it('should return short text of time when wrong format specified', function() {
    var songTime = {minutes: 2, seconds: 23};
    expect(trackTime(songTime, 'ala')).toEqual('2:23');
  });

});
