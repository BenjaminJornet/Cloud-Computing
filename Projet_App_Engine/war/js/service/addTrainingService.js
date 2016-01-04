angular. module('addTrainingService', []).service('add',addFnc);
addFnc.$inject=['$http','$q'];
function addFnc($http,$q) {
	
	var fncContainer={
		localAdd:localAdd
	};
	function localAdd(title, description, domain, ex, heure, minute, num){
		

	    var deferred = $q.defer();
		$http({
			method: 'POST',
	        url: '/taskqueue',
	        data: {title: title, description: description, domain: domain, ex: ex, heure: heure, minute:minute, num: num}
			}).then(function successCallback(data) {
			    // this callback will be called asynchronously
			    // when the response is available
				//, "domain": data.data.domain, "ex": data.data.ex, "heure": data.data.heure, "minute":data.data.minute, "num": data.data.num
				
				if(data){
					var msg = "Training plan added!";
					deferred.resolve({"add":{"title": data.data.title, "description": data.data.description}, msg: msg});
				}
				else{
					 var msg = "METHOD GET FAILED FOR ADDING Training!";
			         deferred.reject({"add":{}, msg: msg});
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