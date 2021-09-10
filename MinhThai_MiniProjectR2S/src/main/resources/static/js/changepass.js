const app2 = angular.module("change-pass-app", []);
app2.controller("change-pass-ctrl", function($scope, $http) {
	$scope.account={};
	$scope.confirmnewpass="";
	$scope.newpass="";
	$http.get("/rest/accounts/user").then(resp=>{
		$scope.account=resp.data;
		console.log($scope.account)
	}).catch(error=>{
		console.log(error);
	})
	$scope.changepass=function(){
		if($scope.newpass==$scope.confirmnewpass){
			$scope.account.password=$scope.newpass
			$http.put(`/rest/accounts/changepass`,$scope.account).then(resp=>{
				Swal.fire({
				  icon: 'success',
				  title: 'Thành công',
				  text: 'Đổi mật khẩu thành công',			  
				  width:'500px',
				  heightAuto:true
				})
				location.href="/index";
			}).catch(error=>{
				console.log(error)
			})
		}
	}
})