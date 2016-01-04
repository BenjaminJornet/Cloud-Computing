angular.module('addTrainingApp').controller('addTrainingCtrl',addTrainingFnt);

addTrainingFnt.$inject=['$scope','$log', '$window', 'add' ];

function addTrainingFnt($scope, $log, $window, add){
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
	
	
	$scope.addTraining=function(){
		$scope.nbex = $scope.nbex + 1;
	var exToAdd ={
		title:$scope.ex.title,
		description:$scope.ex.description,
		heure:$scope.ex.heure,
		minute:$scope.ex.minute,
		num: $scope.nbex
	};
	
	$scope.trainingPlan.ex.push(exToAdd);
	
	}
	
	$scope.remove=function(exo_remove){
		
		var i =$scope.trainingPlan.ex.indexOf(exo_remove);
		$scope.trainingPlan.ex.splice(i,1);
		$scope.nbex = $scope.nbex - 1;
		
	}
	
	$scope.sommeTot=function(){
		
		var somme_h=0;
		var somme_m=0;
		
		angular.forEach($scope.trainingPlan.ex, function(ex, key) {
		somme_m = somme_m + parseInt(ex.minute);
		somme_h = somme_h + parseInt(ex.heure);
		
		});
		
		$scope.trainingPlan.h_dure = somme_h;
		$scope.trainingPlan.m_dure = somme_m;
		
		if($scope.trainingPlan.m_dure>60){
			var nb_m = $scope.trainingPlan.m_dure%60;
			var nb_h = ($scope.trainingPlan.m_dure - nb_m)/60;
			$scope.trainingPlan.h_dure = $scope.trainingPlan.h_dure +nb_h;
			$scope.trainingPlan.m_dure = nb_m;
		}
		
	}
	$scope.savePlanToDb=function(trainingPlan){
		
		console.log("trying to add to task queue");
		
		var add_response = add.localAdd(trainingPlan.title, trainingPlan.description, trainingPlan.domain, trainingPlan.ex, trainingPlan.h_dure, trainingPlan.m_dure);
	    add_response.then(
	        function(payload){
	        	if(add_response){
	        		   $log.info("add success: " + JSON.stringify(payload));

	        	}

	        },
	        function(err_payload){
	            $log.warn("Error add training plan: " + err_payload.msg);
	        }
	    );
		
	}
	
	

}


