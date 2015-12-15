<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <title>Form Sample</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
<!--     <link href="navbar-fixed-top.css" rel="stylesheet"> -->

  </head>

  <body>

    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Form Sample</a>
        </div>
        
        <form class="navbar-form navbar-left"  style="width:100%" role="search">
              <button type="submit" class="btn btn-primary g-signin2" data-onsuccess="onSignIn" data-theme="dark">G</button>
              <button type="submit" class="btn btn-success">Y</button>
              <button type="submit" class="btn btn-warning">O</button>
          
        </form>
                  <script>
      function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        var profile = googleUser.getBasicProfile();
        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        console.log("Name: " + profile.getName());
        console.log("Image URL: " + profile.getImageUrl());
        console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        console.log("ID Token: " + id_token);
      };
    </script>
      </div>
    </div>

    <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Personnal trainer</h1>
      </div>
    </div> 

<div class="container" method="get">

  <div class="row">
      <div class=" col-md-1 col-sm-0 col-xs-0 " ></div>
      <div class=" col-md-6 col-sm-12 col-xs-12 ">
<!--           <p id=msg>Ideoque fertur neminem aliquando ob haec vel similia poenae addictum oblato de more elogio revocari iussisse, quod inexorabiles quoque principes factitarunt. et exitiale hoc vitium, quod in aliis non numquam intepescit, in illo aetatis progressu effervescebat, obstinatum eius propositum accendente adulatorum cohorte.</p> -->
<jsp:useBean id="message" class="model.message"></jsp:useBean>      
<jsp:getProperty property="text" name="message"/>
      </div>
      <div class=" col-md-3 col-sm-12 col-xs-12 ">
          <div class=" col-md-12 col-sm-12 col-xs-12 ">
              <div id="flipcountdownbox1">                            
              </div>
          </div>
      </div>
  </div>

</div> 


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <script src="js/jquery-1.11.1.min.js"></script>
     <script>
     $.get("/addWelcome?msg= Bienvenue sur notre site d'eCoaching!",null);
     </script>

    <script src="js/bootstrap.min.js"></script>
  </body>
</html>