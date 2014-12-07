'use strict';

angular.module('albumApp')
  .filter('trackTime', function () {
    return function (trackTime, format) {
      if(trackTime) {
        var useFormat = 'short'
        switch(format) {
          case('long') :
            useFormat = 'long';
            break;
          default:
            break;
        }
        
        if(useFormat === 'long') {
          return trackTime.minutes + "min " + trackTime.seconds + "sec";
        }
        
        var pad = "00";
        pad = pad.substring(0, pad.length - trackTime.seconds.toString().length) + trackTime.seconds.toString();
        return trackTime.minutes + ":" + pad;

      }
      
      
      
      return 'No time specified';
    };
  });
