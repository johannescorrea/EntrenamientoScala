var FrontController = {
	list : function($scope, $http) {
		$scope.titulo = "Libros";
		$http.get('data/libros.json').success(function(data) {
			$scope.libros = data;
		});
		/*
		 * selecciona el desplegable y ordena automaticamente, variable definida en la vista con
		 * ng-model
		 */
		$scope.orderField = "titulo";
		$scope.orderReverse = "true";
	},
	detail : function($scope, $http, $routeParams) {
		$scope.id = $routeParams.libroId;
		//'data/libro' + $scope.id + '.json'
		$http.get('restservice/json/get').success(function(data) {
			$scope.libro = data;
		});
	}
};




var uid = 1;
function ContactController($scope) {
    
    $scope.contacts = [
        {id:0, 'name': 'nelson fernando', 'email':'nefeper@gmail.com', 'phone': '123-2343-44'}
    ];
    
    $scope.save = function() {
        
        if($scope.contact.id == null) {
             $scope.contact.id = uid++;
             $scope.contacts.push($scope.contact);
        } else {
            
             for(i in $scope.contacts) {
                    if($scope.contacts[i].id == $scope.contact.id) {
                        $scope.contacts[i] = $scope.contact;
                    }
             }                
        }
        $scope.contact = {};
    };

    
    $scope.remove = function(id) {
        
        for(i in $scope.contacts) {
            if($scope.contacts[i].id == id) {
                $scope.contacts.splice(i,1);
                $scope.contact = {};
            }
        }
        
    };
    
    
    $scope.edit = function(id) {
        for(i in $scope.contacts) {
            if($scope.contacts[i].id == id) {
                $scope.contact = angular.copy($scope.contacts[i]);
            }
        }
    };
    
}