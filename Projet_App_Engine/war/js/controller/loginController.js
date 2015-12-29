angular.module('addTrainingApp').controller('loginCtrl',loginFnt);

loginFnt.$inject=['$scope','$log', '$window'];

function loginFnt($scope, $log, $window){
       
	$scope.userCarac = {};
	$scope.userCarac.client_id = "";
	$scope.userCarac.id = "";
	$scope.userCarac.name = "";
	$scope.userCarac.image_url = "";
	$scope.userCarac.email = "";
	$scope.userCarac.connected = false;
	
	$scope.onSignIn=function(googleUser){
		console.log("OnSignIn called");
	    // Useful data for your client-side scripts:
		
		//var goUser = GoogleAuth.currentUser.get();

		if(googleUser == undefined){
			if(GoogleAuth == undefined){
				var GoogleAuth = gapi.auth2.getAuthInstance();
			}
			googleUser = GoogleAuth.currentUser.get();
		}
	    var profile = googleUser.getBasicProfile();
	    
	    console.log("ID: ", profile.getId()); // Don't send this directly to your server!
	    $scope.userCarac.client_id = profile.getId();
	    
	    console.log("Name: ", profile.getName());
	    $scope.userCarac.name =  profile.getName();
	    
	    console.log("Image URL: ", profile.getImageUrl());
	    $scope.userCarac.image_url = profile.getImageUrl();
	    
	    console.log("Email: ", profile.getEmail());
	    $scope.userCarac.email = profile.getEmail();

	    // The ID token you need to pass to your backend:
	    var idToken = googleUser.getAuthResponse().id_token;
	    console.log("ID Token: ", idToken);
	    $scope.userCarac.client_id = idToken;
	    $scope.userCarac.connected = false;
	    
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', '/validateToken');  // On invoque la méthode post de la servlet
	    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    xhr.onload = function() {
	      console.log('Signed in as: ', xhr.responseText);
	    };
	    xhr.send('idtoken=' + idToken);
	    
	    console.log("*********************************");
	    $scope.userCarac.connected = true;
	    console.log("Connected set to", $scope.userCarac.connected);
	}
	
	  /**
	   * Handler for the signin callback triggered after the user selects an account.
	   */
	  $scope.onSignInCallback=function(resp){
	    gapi.client.load('plus', 'v1', apiClientLoaded);
	  }
	
	  /**
	   * Sets up an API call after the Google API client loads.
	   */
	  $scope.apiClientLoaded=function(){
	    gapi.client.plus.people.get({userId: 'me'}).execute(handleEmailResponse);
	  }
	
	  /**
	   * Response callback for when the API client receives a response.
	   *
	   * @param resp The API response object with the user email and profile information.
	   */
	  $scope.handleEmailResponse=function(resp){
	    var primaryEmail;
	    for (var i=0; i < resp.emails.length; i++) {
	      if (resp.emails[i].type === 'account') primaryEmail = resp.emails[i].value;
	    }
	    document.getElementById('responseContainer').value = 'Primary email: ' +
	        primaryEmail + '\n\nFull Response:\n' + JSON.stringify(resp);
	  }
	  
	  $scope.signOut=function(){
		    var auth2 = gapi.auth2.getAuthInstance();
		    auth2.signOut().then(function () {
		      console.log('User signed out.');
		    });
		    console.log("*********************************");
		    $scope.userCarac.connected = false;
		    console.log("Connected set to", $scope.userCarac.connected);
	  }
}