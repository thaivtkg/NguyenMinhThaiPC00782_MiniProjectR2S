app.controller("account-ctrl",function($scope,$http){
	$scope.items=[]
	$scope.form={}
	$scope.error={}
	$scope.initialize=function(){
		$http.get("/rest/accounts").then(resp=>{
			$scope.items=resp.data;
		}).catch(error=>{
			console.log(error);
		})
	}
	$scope.initialize();
	$scope.reset=function(){
		$scope.form={};
	}
	$scope.reset();
	$scope.edit=function(item){	
		$scope.form=angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show');
	}
	$scope.create=function(){		
		var confirmpass=$( "input[name=confirmpass]" ).val();
		if($scope.form.password==confirmpass){
			var item=angular.copy($scope.form);
			$http.post(`/rest/accounts`,item).then(resp=>{
				$scope.items.push(resp.data);
				$scope.reset();
				Swal.fire({
				  	icon: 'success',
				  	title: 'Thành công',
				  	text: 'Tạo tài khoản thành công',			  
				  	width:'500px',
				  	heightAuto:true
				})
			}).catch(error=>{
				$scope.error=error.data;		
				console.log(error)
				Swal.fire({
				  	icon: 'error',
				  	title: 'Thất bại',
				  	text: 'Tạo tài khoản thất bại',			  
				  	width:'500px',
				  	heightAuto:true
				})			
			})		
		}
			
			
			
	}
	$scope.update=function(){
		var item=angular.copy($scope.form);
		var confirmpass=$( "input[name=confirmpass]" ).val();
		if($scope.form.password==confirmpass){
			$http.put(`/rest/accounts/${item.username}`,item).then(resp=>{
				var index=$scope.items.findIndex(p=> p.username==item.username);
				$scope.items[index]=item
				Swal.fire({
				  	icon: 'success',
				  	title: 'Thành công',
				  	text: 'Cập nhật thành công',			  
				 	width:'500px',
				 	heightAuto:true
				})
			}).catch(error=>{
				$scope.error=error.data;
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Cập nhật tài khoản thất bại',			  
				  width:'500px',
				  heightAuto:true
				})
				console.log("Error",error);
			})
		}
		
	}
	$scope.delete=function(id){
		Swal.fire({
			  title: 'Are you sure?',
			  text: `Bạn có muốn xóa ${id}`,
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
			  if (result.isConfirmed) {
				  var item=angular.copy($scope.form);
					$http.delete(`/rest/accounts/${id}`).then(resp=>{
						var index=$scope.items.findIndex(p=> p.username==id);
						$scope.items.splice(index,1);
						$scope.reset();
						Swal.fire({
							  icon: 'success',
							  title: 'Thành công',
							  text: 'Xóa thành công',			  
							  width:'500px',
							  heightAuto:true
							})
					}).catch(error=>{
						console.log(error)									
					})
			  }
			})
		
	}
	$scope.pager={
			page:0,
			size:6,
			get items(){
				var start=this.page*this.size;
				return $scope.items.slice(start,start+this.size);
			},
			get count(){
				return Math.ceil(1.0*$scope.items.length/this.size);
			},
			first(){
				this.page=0;
			},
			prev(){
				this.page--;
				if(this.page<0){
					this.last();
				}
			},
			next(){
				this.page++;
				if(this.page>=this.count){
					this.first();
				}
			},
			last(){
				this.page=this.count-1;
			}		
	}
})