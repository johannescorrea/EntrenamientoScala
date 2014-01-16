/*
 * Angular JS
 * 
 */
var IG = angular.module('IG',[]);

IG.config(['$routeProvider', function($routes) {  
$routes.
      when('/libros',{
		  templateUrl: 'views/libros-list.html',
		  controller: FrontController.list
		  }).
	  when('/contact',{
			  templateUrl: 'views/contact.html',
			  controller: ContactController
			  }).	  
	  when('/libro/:libroId',{  //mediante dos puntos (:) definimos un parámetro
		  templateUrl: 'views/libro.html',
		  controller: FrontController.detail
		  }).
	  otherwise({redirectTo: '/libros'}); //cualquier ruta no definida
}]);


IG.controller('mostrarContactos', function($scope) {
	$scope.link = "contactos";
	$scope.suma = function(){
		alert("hola");
	};
});

