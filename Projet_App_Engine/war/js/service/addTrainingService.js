angular. module('addTrainingService', []).service('add',addFnc);
addFnc.$inject=['$http','$q'];
function addFnc($http,$q) {
	
	var fncContainer={
		localAdd:localAdd
	};
	function localAdd(title,description,heure, minute, num){
		

	    var deferred = $q.defer();
		$http({
			method: 'POST',
	        url: '/taskqueue',
	        data: {title: title, description: description, heure: heure, minute:minute, num: num}
			}).then(function successCallback(data) {
			    // this callback will be called asynchronously
			    // when the response is available
				
				alert(data.data);
				if(data){
		
					deferred.resolve({"title": data.data.title, "description":data.data.description});
				}
				else{
					 var msg = "METHOD GET FAILED FOR ADDING Training!";
			         deferred.reject({"add":{}, msg: msg});
				}
			  }, function errorCallback(data) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
				  console.log("Erreur requête get ! ");
				  
			  });
	return deferred.promise;
	}

	return fncContainer;
}