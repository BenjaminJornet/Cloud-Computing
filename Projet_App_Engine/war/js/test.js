$( document ).ready(function() {
	$.get("/addWelcome?msg=Bonjour et bienvenue sur notre site d'eCoaching!",function(data){
		console.log( "ready!" + data);
		$('#mess').text(data);
		
	});
    
});