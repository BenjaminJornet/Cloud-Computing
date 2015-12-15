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
	console.log("trying to add to task queue");
	var add_response = add.localAdd(exToAdd.title, exToAdd.description, exToAdd.heure, exToAdd.minute, exToAdd.num);
    add_response.then(
        function(payload){
        	if(add_response){
        		   $log.info("add success: " + JSON.stringify(payload));

        	}

        },
        function(err_payload){
//            $scope.errMsg = "Wrong login";
            $log.warn("wrong login: " + err_payload.msg);
        }
    );
	}
	
	$scope.remove=function(exo_remove){
//		   var res =document.getElementsByClassName('btn btn-danger btn-sm');
//		   var td = angular.element(res).parent();
//		   var tr = angular.element(td[0]).parent();
//		   tr[0].remove();
//		   alert(tr);
		
		var i =$scope.trainingPlan.ex.indexOf(exo_remove);
		$scope.trainingPlan.ex.splice(i,1);
		
	}
	
	$scope.sommeTot=function(){
		
//		var table = angular.element(document.getElementById('table_add')).children();
//		var max_it = table[0].childElementCount
//		
//		for(var i=0;i<max_it;i++){
//			var tr =table[0].children[i].children;
//			var time =tr[3].innerText;
//			var heure_str = time.split("h et");
//			var lenh = heure_str.length;
//			if(lenh == 1){
//				var min = parseInt(heure_str[0].split(" min")[0]);
//				var heure = 0;
//			}
//			else{
//				var min = parseInt(heure_str[1].split(" min")[0]);
//				var heure=parseInt(heure_str[0]);
//			}
//			$scope.ex.somme.h = $scope.ex.somme.h + heure;
//			$scope.ex.somme.m = $scope.ex.somme.m + min;
//			
//			alert("heure:" + $scope.ex.somme.h);
//			alert("minutes:"+ $scope.ex.somme.m);
//			
//		}
//		
//		if(min>60){
//			var nb_h = min%60;
//			$scope.ex.somme.h = $scope.ex.somme.h +nb_h;
//			$scope.ex.somme.m = min - nb_h*60;
//		}
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
	
	
	

}


