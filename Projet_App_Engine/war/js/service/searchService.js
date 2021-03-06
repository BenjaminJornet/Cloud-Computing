angular. module('searchService', []).service('search',searchFnc);
searchFnc.$inject=['$http','$q'];

function searchFnc($http, $q){

	var fncContainer={
		localSearch:localSearch
	};
	function localSearch(content){
		

	    var deferred = $q.defer();
		$http({
			method: 'POST',
	        url: '/searchDataStore',
	        data: {q: content}
			}).then(function successCallback(data1,status,header,config) {
			    // this callback will be called asynchronously
			    // when the response is available
				//, "domain": data.data.domain, "ex": data.data.ex, "heure": data.data.heure, "minute":data.data.minute, "num": data.data.num
				
				if(data1){
					var msg = "Search request success !";
					deferred.resolve({"result":{"plans": data1.data.title_plan_found, "exs": data1.data.title_exo_found}, msg: msg});
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