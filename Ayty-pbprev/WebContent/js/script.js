
var app = angular.module('autenticacaoPBPrevApp', []);

app.controller('autenticacaoController', function ($scope, $http){
	
	$scope.autenticar = function(){
		var jsonObj = {Login: $scope.login, Senha: $scope.senha};
		var res = $http.post('http://localhost:8080/Ayty-pbprev/rest/valida/validar-usuario', jsonObj);
		res.success(function(data) {
			alert(data);
		});
		res.error(function(data) {
			alert(data);
		});	
		
	}
	
	$scope.cadastrar = function(){
		var jsonObj = {Login: $scope.loginCadastrado, Senha: $scope.senhaCadastrada};
		var res = $http.post('http://localhost:8080/Ayty-pbprev/rest/valida/cadastrar-usuario', jsonObj);
		res.success(function(data) {
			alert(data);
		});
		res.error(function(data) {
			alert(data);
		});	
		
	}
	
});


