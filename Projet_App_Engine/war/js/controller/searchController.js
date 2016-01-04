angular.module('searchApp').controller('searchCtrl',searchFnt);

searchFnt.$inject=['$scope','$log', '$window', 'search' ];

function searchFnt($scope, $log, $window,search){
	$scope.content = "";
	
	$scope.search=function(){
//	alert($scope.content);
	 console.log("search in progress ...");
		
	 var resp_search = search.localSearch($scope.content);
	 resp_search.then(function(payload){
        	if(resp_search){
     		   $log.info("research result: " + JSON.stringify(payload));
//     		   $window.location.href="/ha-result-screen.html";
        	}
     },
     function(err_payload){
         $log.warn("Error research: " + err_payload.msg);
     
     });
		
	}

}