<%-- 
    Document   : index
    Created on : 20/01/2015, 22:00:29
    Author     : T2S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Simple Sidebar - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
    <body>

        <div id="wrapper">
            <jsp:include page="menu.jsp"></jsp:include>
            
            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div style="width: 75%">
                        <canvas id="canvas" height="300" width="450"></canvas>
                        
                    </div>
                </div>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="scripts/jquery-1.11.2.min.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="scripts/bootstrap.min.js"></script>
        <!-- Chart core -->
        <script src="scripts/Chart.min.js"></script>
        <!-- Mustache core -->
        <script src="scripts/mustache.min.js"></script>

        <script src="scripts/graficos.js"></script>

        <!-- Menu Toggle Script -->
        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
        </script>
    </body>
</html>
