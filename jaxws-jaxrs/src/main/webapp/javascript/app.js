/*
 * Angular JS
 * 
 */

var angularIG = angular.module('angularIG', [ 'ngRoute', 'ngResource' ]);

// angularIG.constant('RestService', 'http://localhost:8088');

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
	return $resource('http://localhost:8088/enviar', {}, { save : { method : 'POST' } });
} ]);

angularIG.controller('mostrarContactos', [ '$scope', '$routeParams',
		'usuarioRest', 'enviarRest', function($scope, $routeParams, usuarioRest, enviarRest) {
			$scope.link = "contactos";
			$scope.suma = function() {
				usuarioRest.get({
					Id : 123
				}, function(data) {
					alert("get " + data.email + "-->" + data.password);
				});
				enviarRest.save({
					email : "nf@gmail.com",
					password : "123abc"
				}, function(data) {
					alert("post " + data.email + "-->" + data.password);
				});
			};
		} ]);