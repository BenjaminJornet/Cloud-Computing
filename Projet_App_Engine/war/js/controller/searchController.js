angular.module('searchApp').controller('searchCtrl',searchFnt);

searchFnt.$inject=['$scope','$log', '$window', 'search'];

function searchFnt($scope, $log, $window, search){
	$scope.content = "";
	$scope.result = {
			plan:[],
			ex:[]
	}
	
	
	$scope.search=function(content){
//	alert($scope.content);
	 console.log("search in progress ...");
		
	 var resp_search = search.localSearch($scope.content);
	 resp_search.then(function(payload){
        	if(resp_search){
        		
        	   $scope.result.ex.push.apply($scope.result.ex, payload.result.exs);
        	   $scope.result.plan.push.apply($scope.result.plan, payload.result.plans);
        	   
     		   $log.info("research result: " + JSON.stringify(payload));
     		   
     		   $.post("/retrieveData",$scope.result, function(data, err){
     			   alert(data);
     			   $window.location.href="/ha-result-screen.html";
     			   
     		   })
     		   
     		   
        	}
     },
     function(err_payload){
         $log.warn("Error research: " + err_payload.msg);
     
     });
		
	}

}