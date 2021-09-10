app.controller("authority-ctrl",function($scope,$http,$location){
	$scope.roles=[];
	$scope.admins=[];
	$scope.authorities=[];
	
	$scope.initialize=function(){
		$http.get("/rest/roles").then(resp=>{
			$scope.roles=resp.data;
		}).catch(error=>{
			console.log(error);
		})
		$http.get("/rest/accounts?admin=true").then(resp=>{
			$scope.admins=resp.data;
		}).catch(error=>{
			console.log(error)
		})
		$http.get("/rest/authorities").then(resp=>{
			$scope.authorities=resp.data;
			console.log(resp.data)
		}).catch(error=>{
			$location.path("/unauthorized")
		})
		
		$scope.authority_of=function(acc,role){
			if($scope.authorities){
				return $scope.authorities.find(ur=>ur.account.username==acc.username&&ur.role.id==role.id)
			}
		}
		$scope.authority_changed=function(acc,role){
			var	authority=$scope.authority_of(acc,role);		
			if(authority){
				$scope.revoke_authority(authority);
			}else{
				authority={account:acc,role:role};
				$scope.grant_authority(authority);
			}
		}
		$scope.grant_authority=function(authority){
				$http.post(`/rest/authorities`,authority).then(resp=>{
					$scope.authorities.push(resp.data)
					Swal.fire(
							'Thành công',
							'Cấp quyền thành công',
							'success'
					)
				}).catch(error=>{
					Swal.fire(
							'Thất bại',
							'Cấp quyền thất bại',
							'error'
					)
					console.log("Error",error)
				})			
			}			
		$scope.revoke_authority=function(authority){
			if(authority.role.id!='DIRE'){			
				$http.delete(`/rest/authorities/${authority.id}`).then(resp=>{
					var index=$scope.authorities.findIndex(a=>a.id==authority.id);
					$scope.authorities.splice(index,1);
					Swal.fire(
							'Thành công',
							'Thu hồi quyền thành công',
							'success'
					)
				}).catch(error=>{
					Swal.fire(
							'Thất bại',
							'Thu hồi quyền thất bại',
							'error'
					)
					console.log("Error",error)
				})	
			}else{
				Swal.fire(
						'Thất bại',
						'Không thể loại bỏ quyền Admin',
						'error'
				)
			}					
		}
	}
	$scope.initialize();
})