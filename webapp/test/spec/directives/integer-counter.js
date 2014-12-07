'use strict';

describe('Directive: integerCounter', function () {

  // load the directive's module
  beforeEach(module('albumApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope, $compile) {
    scope = $rootScope.$new();
    scope.counterValue = 2;
    element = angular.element('<span integer-counter value="counterValue"/>');
    element = $compile(element)(scope);
  }));

  describe('initialization', function() {

    it('should produce two buttons and edit field', function() {
      expect(element.find('button').length).toEqual(2);
      expect(element.find('input').length).toEqual(1);
    });
    
    it('should set value of input on initialization', function() {
      console.log(element.find('input'));
      scope.$digest();
      expect(element.find('input').val()).toEqual('2');
    });

  });
  
  describe('increment', function() {
    it('should increment model value', function() {
      element.isolateScope().plus();
      scope.$digest();
      expect(scope.counterValue).toEqual(3);
      element.isolateScope().plus();
      scope.$digest();
      expect(scope.counterValue).toEqual(4);
    });
    
    it('should not increment above max', inject(function($compile) {
      element = angular.element('<span integer-counter value="counterValue" max="3" />');
      element = $compile(element)(scope);
      scope.$digest();
      expect(scope.counterValue).toEqual(2);
      element.isolateScope().plus();
      element.isolateScope().plus();
      element.isolateScope().plus();
      scope.$digest();
      expect(scope.counterValue).toEqual(3);
    }));
  });
  

  describe('decrement', function() {
    it('should decrement model value', function() {
      element.isolateScope().minus();
      scope.$digest();
      expect(scope.counterValue).toEqual(1);
      element.isolateScope().minus();
      scope.$digest();
      expect(scope.counterValue).toEqual(0);
    });
    
    it('should not decrement below min', inject(function($compile) {
      element = angular.element('<span integer-counter value="counterValue" min="1" />');
      element = $compile(element)(scope);
      scope.$digest();
      expect(scope.counterValue).toEqual(2);
      element.isolateScope().minus();
      element.isolateScope().minus();
      element.isolateScope().minus();
      scope.$digest();
      expect(scope.counterValue).toEqual(1);
    }));
  });

  describe('value changed', function() {
    beforeEach(inject(function ($compile) {
      element = angular.element('<span integer-counter value="counterValue" min="1" max="10"/>');
      element = $compile(element)(scope);
    }));
    
    it('should set min when change to less then min', function() {
      element.find('input').val(0);
      element.find('input').triggerHandler('change');
      expect(scope.counterValue).toEqual(1);
    });
    
    it('shouild set max when change to greater then max', function() {
      element.find('input').val(123);
      element.find('input').triggerHandler('change');
      expect(scope.counterValue).toEqual(10);
    });
    
    it('should set min when change to empty', function() {
      element.find('input').val('');
      element.find('input').triggerHandler('change');
      expect(scope.counterValue).toEqual(1);
    });

    it('should set min when not number', function() {
      element.find('input').val('ala');
      element.find('input').triggerHandler('change');
      expect(scope.counterValue).toEqual(1);
    });
    
  });
  
});
