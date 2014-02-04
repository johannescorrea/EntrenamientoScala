/*
 * Angular JS
 * 
 */

var angularIG = angular.module('angularIG', [ 'ngRoute', 'ngResource' ]);

angularIG.constant('RestServiceUrl', 'http://localhost:8088');

angularIG.factory('RestService',['$resource','RestServiceUrl',function($resource,RestServiceUrl){
	var RestService = {};
	var restServiceUrl = RestServiceUrl; //'http://localhost:8088';
	RestService.getUsuario = function () {
		return $resource(restServiceUrl+'/getUsuario/:Id', {Id:123},{ get : { method : 'GET' }, save : { method : 'POST' } });
	};
	RestService.addUsuario = function (){
		return $resource(restServiceUrl+'/addUsuario', {}, { save : { method : 'POST' } });
	};
	return RestService;
}]);

angularIG.directive("libro.detail", function() {
    return {
      restrict: "E",
      templateUrl:'views/libro-detail.html'
    };
});

angularIG.config(function($routeProvider, $locationProvider, $httpProvider) {

	$routeProvider.when('/libros', {
		templateUrl : 'views/libros-list.html',
		controller : FrontController.list
	});

	$routeProvider.when('/contact', {
		templateUrl : 'views/contact.html',
		controller : ContactController
	});

	$routeProvider.when('/libro/:libroId', {
		templateUrl : 'views/libro.html',
		controller : FrontController.detail
	});

	$routeProvider.otherwise({
		redirectTo : '/libros'
	});

	// $locationProvider.html5Mode(true);
	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

angularIG.factory('usuarioRest', [ '$resource', function($resource) {
	return $resource('http://localhost:8088/getUsuario/:Id', {Id:123},{ get : { method : 'GET' }, save : { method : 'POST' } });
} ]);

angularIG.factory('enviarRest', [ '$resource', function($resource) {
	return $resource('http://localhost:8088/addUsuario', {}, { save : { method : 'POST' } });
} ]);

angularIG.controller('mostrarContactos', [ '$scope', '$routeParams',
		'RestService', function($scope, $routeParams, RestService) {
			$scope.link = "contactos";
			$scope.suma = function() {
				RestService.getUsuario().get({
					Id : 123
				}, function(data) {
					alert("get " + data.email + "-->" + data.password);
				});
				/*
				enviarRest.save({
					email : "nf@gmail.com",
					password : "123abc"
				}, function(data) {
					alert("post " + data.email + "-->" + data.password);
				});
				*/
			};
		} ]);