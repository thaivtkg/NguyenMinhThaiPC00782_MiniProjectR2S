app.controller("product-ctrl",function($scope,$http){
	$scope.items=[]
	$scope.brands=[]
	$scope.form={}
	$scope.error={}
	$scope.initialize=function(){
		$http.get("/rest/products").then(resp=>{
			$scope.items=resp.data;
			$scope.items.forEach(item=>{
				item.createDate=new Date(item.createDate)
			})
		}).catch(error=>{
			console.log(error);
		})
		$http.get("/rest/brands").then(resp=>{
			$scope.brands=resp.data;
		}).catch(error=>{
			console.log(error);
		})
	}
	$scope.initialize();
	$scope.reset=function(){
		$scope.form={
				createDate:new Date(),
				image:'cloud-upload.jpg',
				available:true,			
		};
	}
	$scope.reset();
	$scope.edit=function(item){	
		$scope.form=angular.copy(item);
		$(".nav-tabs a:eq(0)").tab('show');
	}
	$scope.create=function(){
		var item=angular.copy($scope.form);
		$http.post(`/rest/products`,item).then(resp=>{
			resp.data.createDate=new Date(resp.data.createDate)
			$scope.items.push(resp.data);
			$scope.reset();
			Swal.fire({
				  icon: 'success',
				  title: 'Thành công',
				  text: 'Thêm mới thành công',			  
				  width:'500px',
				  heightAuto:true
				})
		}).catch(error=>{
			$scope.error=error.data;		
			console.log(error)
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Lỗi thêm mới sản phẩm',			  
				  width:'500px',
				  heightAuto:true
				})
				
		})
		
	}
	$scope.update=function(){
		var item=angular.copy($scope.form);
		$http.put(`/rest/products/${item.id}`,item).then(resp=>{
			var index=$scope.items.findIndex(p=> p.id==item.id);
			$scope.items[index]=item
			$scope.error={}
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
				  text: 'Cập nhật thất bại',			  
				  width:'500px',
				  heightAuto:true
				})
				console.log("Error",error);
		})
	}
	$scope.delete=function(id){
		Swal.fire({
			  title: 'Are you sure?',
			  text: "You won't be able to revert this!",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, delete it!'
			}).then((result) => {
			  if (result.isConfirmed) {
				  var item=angular.copy($scope.form);
					$http.delete(`/rest/products/${id}`).then(resp=>{
						var index=$scope.items.findIndex(p=> p.id==id);
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
						if(error.status==405){
							Swal.fire({
								  icon: 'error',
								  title: 'Thất bại',
								  text: 'Sản phẩm đang được bán',			  
								  width:'500px',
								  heightAuto:true
								})	
						}											
					})
			  }
			})
		
	}
	$scope.imageChanged=function(files){
		var data=new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/img',data,{
			transformRequest:angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp=>{
			$scope.form.image=resp.data.name;
		}).catch(error=>{
			alert("Lỗi upload ảnh")
			console.log("Error",error)
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