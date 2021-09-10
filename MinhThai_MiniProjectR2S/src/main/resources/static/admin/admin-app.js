app=angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/product",{
		templateUrl: "/admin/product/index.html",
		controller:"product-ctrl"
	})
	.when("/authority",{
		templateUrl: "/admin/authority/index.html",
		controller:"authority-ctrl"
	})
	.when("/unauthorized",{
		templateUrl: "/admin/authority/unauthorized.html",
		controller:"authority-ctrl"
	})
	.when("/report",{
		templateUrl: "/admin/report/index.html",
		controller:"report-ctrl"
	})
	.when("/account",{
		templateUrl: "/admin/account/index.html",
		controller:"account-ctrl"
	})
	.otherwise({
		template:"<h1 class='text-center'>FPT Polytechnic Administration</h1>"
	})
})