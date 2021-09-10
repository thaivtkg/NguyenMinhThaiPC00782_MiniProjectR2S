app.controller("report-ctrl", function($scope, $http) {
	$scope.productRevenue = []
	$scope.brandRevenue = []
	$scope.topSeller=[]

	$scope.init=function(){
		$http.get(`/rest/orders/ProductRevenue`).then(resp=>{
			$scope.productRevenue=resp.data
		}).catch(error=>{
			console.log(error)
		})
		$http.get(`/rest/orders/BrandRevenue`).then(resp=>{
			$scope.brandRevenue=resp.data
		}).catch(error=>{
			console.log(error)
		})
		$http.get(`/rest/orders/TopSeller`).then(resp=>{
			$scope.topSeller=resp.data
		}).catch(error=>{
			console.log(error)
		})
	}
	$scope.init();
})