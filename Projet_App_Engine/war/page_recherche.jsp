<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/js.css" rel="stylesheet">
      

  </head>
  <body>
      
      <!-- ------------------------------------------- HEADER ZONE ------------------------------------------- -->
      <nav class="navbar navbar-inverse" role="navigation">
        <div class=" col-md-10 col-sm-10 col-xs-10 ">
            <form class="navbar-form navbar-left"  style="width:100%" role="search">
                <button type="submit" class="btn btn-default">Search</button>
                <div class="form-group"  style="width:90%">
                    <input type="text"  style="width:100%" class="form-control" placeholder="Search">
                </div>
          </form>
        </div>
        <div id="auth">
        <div class=" col-md-2 col-sm-2 col-xs-2 ">
            <form class="navbar-form navbar-left"  style="width:100%" role="search">
                <button type="submit" class="btn btn-primary">G</button>
                <button type="submit" class="btn btn-success">Y</button>
                <button type="submit" class="btn btn-warning">O</button>
          </form>


		<button type="button" onclick="loadDoc()">Change Content</button>
		</div>
		<script>
		function loadDoc() {
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("auth").innerHTML = xhttp.responseText;
		    }
		  };
		  var message = $.get("/getWelcomeMsg()?msg", null);
		  console.log(message);
		  xhttp.open("GET", "text.html", true);
		  xhttp.send();
		}
		</script>
        </div>
      </nav>
      <!-- ------------------------------------------- HEADER ZONE END ------------------------------------------- -->
      
      <div class=" col-md-12 col-sm-12 col-xs-12 " style="margin-bottom:40px">
          <h3> Your Domain</h3>
      </div>
      
      <div class=" col-md-12 col-sm-12 col-xs-12" style="margin-bottom:80px">
          <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
          </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
       </div>
      
      <div class=" col-md-12 col-sm-12 col-xs-12 " style="margin-bottom:80px">
          <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
          </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
                    <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
       </div>
      
      <div class=" col-md-12 col-sm-12 col-xs-12 ">
          <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
          </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
                    <div class=" col-md-3 col-sm-3 col-xs-3 ">
            <button type="submit" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-tree-deciduous"></span></button> <label> RUN </label>
           </div>
       </div>
      
      
      <!-- ------------------------------------------- FOOTER ZONE ------------------------------------------- -->
      <nav class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
        <div class=" col-md-10 col-sm-10 col-xs-10 ">
            <form class="navbar-form navbar-left"  style="width:100%" role="search">
                <button type="submit" class="btn btn-danger"> <span class="glyphicon glyphicon-plus-sign"></span> </button>
               <label style="color:white">Add a Training Plan</label>
          </form>
        </div>
      </nav>
      <!-- ------------------------------------------- FOOTER ZONE END ------------------------------------------- -->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.11.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>