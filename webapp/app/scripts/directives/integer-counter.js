'use strict';

angular.module('albumApp')
  .directive('integerCounter', function () {
    return {
      restrict: 'A',
      scope: { value: '=value' },
      template: '<div class="input-group"> \
                  <span class="input-group-btn"> \
                    <button class="btn btn-default" type="button" ng-click="minus()">-</button> \
                  </span> \
                  <input type="text" class="form-control" ng-model="value" ng-change="changed()" > \
                  <span class="input-group-btn"> \
                    <button class="btn btn-default" type="button" ng-click="plus()">+</button> \
                  </span> \
                 </div>',
      link: function( scope , element , attributes ) {

        if (angular.isUndefined(scope.value)) {
          throw "Missing the value attribute on the counter directive.";
        }

        var min = angular.isUndefined(attributes.min) ? null : parseInt(attributes.min);
        var max = angular.isUndefined(attributes.max) ? null : parseInt(attributes.max);
        var step = angular.isUndefined(attributes.step) ? 1 : parseInt(attributes.step);

        var setValue = function(val) {

          scope.value = parseInt(val);
        }

        setValue(scope.value);

        scope.minus = function() {

          if (min && (scope.value <= min || scope.value - step <= min) || min === 0 && scope.value < 1) {
            setValue(min);
            return false;
          }
          setValue(scope.value - step);
        };

        scope.plus = function() {

          if (max && (scope.value >= max || scope.value + step >= max)) {
            setValue(max);
            return false;
          }
          setValue(scope.value + step);
        };

        // manually changed
        scope.changed = function() {

          // if empty - set to 0
          if (!scope.value)
            setValue(0);

          // are number only entered
          if (/[0-9]/.test(scope.value)) {
            setValue(scope.value);
          } else {
            setValue(0);
          }

          // check with min
          if (min && (scope.value <= min || scope.value - step <= min)) {
            setValue(min);
            return false;
          }

          // check with max
          if (max && (scope.value >= max || scope.value + step >= max)) {
            setValue(max);
            return false;
          }

          // Re-set the value as an integer.
          setValue(scope.value);
        };
      }
    };
  });
