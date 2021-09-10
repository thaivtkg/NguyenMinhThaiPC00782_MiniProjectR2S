const app = angular.module("sign-up-app", []);
app.controller("sign-up-ctrl", function($scope, $http) {
	$scope.account={};
	$scope.confirmpass="";
	$scope.edit={};
	$scope.signup=function(){
		if($scope.account.password==$scope.confirmpass){
			var account=angular.copy($scope.account);
			$http.post(`/rest/accounts`,account).then(resp	=>{
				Swal.fire(
						  'Thành công',
						  'Tạo tài khoản thành công',
						  'success'
				)				
			}).catch(error=>{
				console.log(error)
			})			
		}
	}
})