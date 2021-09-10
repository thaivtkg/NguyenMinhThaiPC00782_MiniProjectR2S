const app = angular.module("shopping-cart-app", []);
app.directive('overridePlusKey', ['$window', function ($window) {
    // Linker function
    return function (scope, element, attrs) {
      element.bind('keydown', function (e) {
        var keyCode = e.keyCode || e.which;
        if (keyCode == 96 || keyCode == 109||keyCode==189||keyCode==48)
        {
          e.preventDefault();
          // Your code here to add a row
        }
      });
    };
  }]);
app.controller("shopping-cart-ctrl", function($scope, $http) {
	$scope.addressesBook=[];
	
	$scope.newaddress={
			account:{username:$("#username").text()},
			address:"",
			createAddress(){
				var newaddress=angular.copy(this);
				if(address!=""){
					$http.post(`/rest/address`,newaddress).then(resp=>{
						$scope.addressesBook.push(resp.data.address);
						$('.nav-tabs a[href="#home"]').tab('show');
					}).catch((err) => {
				        console.log(err);
				    });
				}
			}
	};
	
	$scope.changeTab=function(){
		$(".nav-tabs a:eq(1)").tab('show');
	}
	if(location.pathname=="/cart/checkout"){
		$http.get(`/rest/address/`+$("#username").text()).then(resp=>{
			$scope.addressesBook=resp.data		
		}).catch((err) => {
			console.log(error)
    	});
	}
	
    $scope.cart = {
        items: [],
        quantity:1,
        // Thêm sản phẩm vào giỏ hàng
        add(id) {
            var item = this.items.find(item => item.id == id);      
            if (item) {
				item.qty=1;
				this.saveToLocalStorage();
				item.qty+=Number.parseInt(this.quantity);
                this.saveToLocalStorage();              
            } else {
                $http.get(`/rest/products/${id}`).then((resp) => {               	    
                	resp.data.qty = Number.parseInt(this.quantity);           	               
                    this.items.push(resp.data);
                    this.saveToLocalStorage(); 
                }).catch((err) => {
                    console.log(err);
                });
            }
        },
        // Xóa sản phẩm
        remove(id) {
			var item = this.items.find(item => item.id == id);
			if(item.qty>1){
				item.qty--;
			}else{
				 var index = this.items.findIndex(item => item == id);
                this.items.splice(index, 1);
                this.saveToLocalStorage();
			}                
        },
        // xóa giỏ hàng
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        // Tính thành tiền của 1 sản phẩm
        amt_of(item) {
            this.items.price = item.qty * items.price;
        },
        // Tính tổng số lượng các mặt hàng trong giỏ
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        // Tổng thành tiền các mặt hàng trong giỏ
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        // Lưu giỏ hàng vào local storage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));         
            localStorage.setItem("shoppingcart", json);         
        },
        // Đọc giỏ hàng từ local storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("shoppingcart")     
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFromLocalStorage();
    $scope.order = {
        createDate: new Date(),
        address:"",
        account: {username:$("#username").text()},
        get orderDetails(){
        	return $scope.cart.items.map(item=>{
        		return {
        			product:{id:item.id},
        			price:item.price,
        			quantity: item.qty
        		}
        	})    		       	
        },
        purchase(){
        	if(this.address!=""){
        		var order=angular.copy(this);
            	if($scope.cart.items.length==0){
					Swal.fire({
        				  icon: 'error',
        				  title: 'Thất Bại',
        				  text: 'Vui lòng đặt sản phẩm',			  
        				  width:'500px',
        				  heightAuto:true
        				})
				}else{
            	$http.post("/rest/orders",order).then(resp=>{
            		Swal.fire({
        				  icon: 'success',
        				  title: 'Thành công',
        				  text: 'Đặt hàng thành công',			  
        				  width:'500px',
        				  heightAuto:true
        				})
            		$scope.cart.clear();
            		location.href="/order/detail/"+resp.data.id;
            	}).catch(error=>{
            		Swal.fire({
        				  icon: 'error',
        				  title: 'Thất Bại',
        				  text: 'Đặt hàng lỗi',			  
        				  width:'500px',
        				  heightAuto:true
        				})
            		console.log(error)
            	})
				}
        	}else{
        		Swal.fire({
  				  icon: 'error',
  				  title: 'Thất Bại',
  				  text: 'Vui lòng nhập địa chỉ',			  
  				  width:'500px',
  				  heightAuto:true
  				})
        	}     	
        }
    }
})