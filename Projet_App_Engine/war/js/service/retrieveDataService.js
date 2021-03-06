angular. module('retrieveService', []).service('retrieve',retrieveFnc);
retrieveFnc.$inject=['$http','$q'];

function retrieveFnc($http, $q){
	    var fncContainer={
	    	localRetrieve:localRetrieve,
	    	localData:localData
		};
		function localRetrieve(){
			
		    var deferred = $q.defer();
			$http({
				method: 'GET',
		        url: '/retrieveData'
				}).then(function successCallback(data1,status,header,config) {
				    // this callback will be called asynchronously
				    // when the response is available
					//, "domain": data.data.domain, "ex": data.data.ex, "heure": data.data.heure, "minute":data.data.minute, "num": data.data.num
					
					if(data1){
						var msg = "Search request success !";
						deferred.resolve({"plans": data1.data.plan, "exs": data1.data.ex, "msg": msg});
					}
					else{
						 var msg = "METHOD GET FAILED FOR SEARCHING!";
				         deferred.reject({"search":{}, msg: msg});
					}
				  }, function errorCallback(data) {
				    // called asynchronously if an error occurs
				    // or server returns response with an error status.
					  console.log("Erreur requête post ! ");
					  
				  });
		return deferred.promise;
		}
		
		
		function localData(){
			
		    var deferred = $q.defer();
			$http({
				method: 'GET',
		        url: '/searchDataStore2'
				}).then(function successCallback(data1,status,header,config) {
				    // this callback will be called asynchronously
				    // when the response is available
					//, "domain": data.data.domain, "ex": data.data.ex, "heure": data.data.heure, "minute":data.data.minute, "num": data.data.num
					
					if(data1){
						var msg = "Search request success !";
						deferred.resolve({"exos": data1.data.ex, "mesg": msg}); //"title": data1.data.title, "description": data1.data.description, "heure": data1.data.heure, "minute": data1.data.minute});
					}
					else{
						 var msg = "METHOD GET FAILED FOR SEARCHING!";
				         deferred.reject({"search":{}, msg: msg});
					}
				  }, function errorCallback(data) {
				    // called asynchronously if an error occurs
				    // or server returns response with an error status.
					  console.log("Erreur requête post ! ");
					  
				  });
		return deferred.promise;
		}

		return fncContainer;
}

