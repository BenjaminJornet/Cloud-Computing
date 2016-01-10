angular.module('retrieveApp').controller('retrieveCtrl',retrieveFnt);

retrieveFnt.$inject=['$scope','$log', '$window', 'retrieve', 'show' ];

function retrieveFnt($scope,$log,$window,retrieve,show){
$scope.result={
		ex:[],
		plan:[]
};

$scope.nbex =0;

$scope.trainingPlan={
		title:"",
		description:"",
		domain:"",
		ex:[],
		h_dure: "",
		m_dure:""
	}

$scope.ex={
		title:"",
		description:"",
		heure:"",
		minute:"",
		num:""
	}
console.log("$scope.result = ");
console.log($scope.result);

// HEURE MINUTE TITRE DESCRIPTION
$scope.sendStat=function(titre){
	
	var result = show.local(titre);
	console.log("Resultat : ");
	console.log(result);
};

$scope.detail = function(){
	var resp_search = retrieve.localData();
	resp_search.then(function(payload){
    	if(resp_search){
    		
  		   $log.info("research result: " + JSON.stringify(payload));
 		   var size = $scope.trainingPlan.ex.length;
 		   
   		   if(angular.isArray(payload.exos)){
	    	   angular.forEach(payload.exos, function(value,key){
					//$scope.result.ex.push(value);
		 		   $scope.trainingPlan.ex[size].title = value.title;
		 		   $scope.trainingPlan.ex[size].description = value.description;
		 		   $scope.trainingPlan.ex[size].heure = value.heure;
		 		   $scope.trainingPlan.ex[size].minute = value.minute;
		 		   size++;
	    	   });
   		   }
 		   console.log("$scope.result = ");
 		   console.log(JSON.stringify(payload));
 		   console.log($scope.trainingPlan);
    	}
	},
	function(err_payload){
			$log.warn("Error research: " + err_payload.msg);
 
	});
}

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
    			
    	   console.log("*******************************");
    	   console.log(JSON.stringify(payload));
 		   $log.info("research result: " + JSON.stringify(payload));
 		   console.log("$scope.result = ");
 		   console.log($scope.result);
    	}
	},
	function(err_payload){
			$log.warn("Error research: " + err_payload.msg);
 
	});
}
}