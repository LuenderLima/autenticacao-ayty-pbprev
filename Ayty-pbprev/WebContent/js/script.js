
var app = angular.module('autenticacaoPBPrevApp', []);

app.controller('autenticacaoController', function ($scope, $http){
	
	$scope.autenticar = function(){
		var response = $http.post('http://localhost:8080/Ayty-pbprev/rest/valida/validar-usuario', {Login: $scope.login, Senha: $scope.senha});
		response.success(function(data) {
			alert(data);
		});
		response.error(function(data) {
			alert(data);
		});	
		
	}
	
	$scope.cadastrar = function(){
		var response = $http.post('http://localhost:8080/Ayty-pbprev/rest/valida/cadastrar-usuario', {Login: $scope.loginCadastrado, Senha: $scope.senhaCadastrada});
		response.success(function(data) {
			alert(data);
		});
		response.error(function(data) {
			alert(data);
		});	
		
	}
	
});


