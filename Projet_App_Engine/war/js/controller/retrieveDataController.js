angular.module('retrieveApp').controller('retrieveCtrl',retrieveFnt);

retrieveFnt.$inject=['$scope','$log', '$window', 'retrieve' ];

function retrieveFnt($scope,$log,$window,retrieve){
$scope.result={
		ex:[],
		plan:[]
};
$scope.retrieve = function(){
	var resp_search =  retrieve.localRetrieve();
	resp_search.then(function(payload){
    	if(resp_search){
    		if(angular.isArray(payload.exs)){
    			angular.forEach(payload.exs, function(value,key){
    				$scope.result.ex.push(value);
    			});
    		}	
			else{
				$scope.result.ex.push(payload.exs);
			}
    			if(angular.isArray(payload.plans)){
       				angular.forEach(payload.plans, function(value,key){
        				$scope.result.plan.push(value);
        			});
    			}
    			else{
    				$scope.result.plan.push(payload.plans);
    			}
    			

 		   $log.info("research result: " + JSON.stringify(payload));
 		   
    	}
	},
	function(err_payload){
			$log.warn("Error research: " + err_payload.msg);
 
	});
}
}