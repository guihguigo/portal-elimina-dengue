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

    <!-- UI-Widget CSS -->
    <link href="css/jquery-ui.min.css" rel="stylesheet">

    <link href="css/jquery-ui.structure.min.css" rel="stylesheet">

    <link href="css/padrao.css" rel="stylesheet">
    <body>

        <div id="wrapper">
            <jsp:include page="menu.jsp"></jsp:include>

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4">
                            <select class="form-control " id="lista-focos">
                                <option value="0">Geral</option>
                            </select>
                        </div>
                        <div class="col-md-5">
                            <div class="input-group stylish-input-group ">
                                <input id="filtro-regiao" type="text" class="form-control "  placeholder="Filtrar Região" >
                                <span class="input-group-addon">
                                    <button id="filtrar">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>  
                                </span>
                            </div>

                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div style="width: 65%">
                            <canvas id="canvas" height="300" width="450"></canvas>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="scripts/jquery-1.11.2.min.js"></script>

    <!--JQuery UI-->
    <script src="scripts/jquery-ui.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="scripts/bootstrap.min.js"></script>
    <!-- Chart core -->
    <script src="scripts/Chart.min.js"></script>
    <!-- Mustache core -->
    <script src="scripts/mustache.min.js"></script>

    <script src="scripts/graficos.js"></script>

    <script src="scripts/auto-complete.js"></script>

    <!-- Menu Toggle Script -->
    <script>
        $("#menu-toggle").click(function (e) {
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");
        });
    </script>
</body>
</html>
