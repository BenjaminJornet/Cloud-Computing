angular.module('retrieveApp').controller('retrieveCtrl',retrieveFnt);

retrieveFnt.$inject=['$scope','$log', '$window', 'retrieve' ];

function retrieveFnt($scope,retrieve,$log,$window){
$scope.result={
		ex:[],
		plan:[]
};
$scope.retrieve = function(){
	resp_search.then(function(payload){
    	if(resp_search){
    		
    	   $scope.result.ex.push.apply($scope.result.ex, payload.result.exs);
    	   $scope.result.plan.push.apply($scope.result.plan, payload.result.plans);
    	   
 		   $log.info("research result: " + JSON.stringify(payload));
 		   
 		   $window.location.href="/ha-result-screen.html";
    	}
	},
	function(err_payload){
			$log.warn("Error research: " + err_payload.msg);
 
	});
}
}