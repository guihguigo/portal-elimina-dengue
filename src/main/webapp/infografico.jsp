<%-- 
    Document   : infografico.jsp
    Created on : 16/05/2015, 15:37:04
    Author     : T2S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!-- Custom css-->
        <link type="text/css" rel="stylesheet" href="css/custom.css">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>



        <title>Consultar inforgraficos</title>
    </head>
    <body style="background-color: #eeeded">
        <div class="wrap-container">          
            <nav>
                <div class="nav-wrapper">
                    <form>
                        <div class="input-field">
                            <input id="search" type="search" required>
                            <label for="search"><i class="mdi-action-search"></i></label>
                            <i class="mdi-navigation-close"></i>
                        </div>
                    </form>
                </div>
            </nav>

            <div class="navbar-fixed">
                <nav>
                    <div class="nav-wrapper blue darken-1">

                        <a href="#!" class="brand-logo">Aedes</a>
                        <ul class="right hide-on-med-and-down">                        
                            <li><a href="Inforgrafico.jsp">Consultar infograficos?</a></li>
                        </ul>

                    </div>
                </nav>
            </div>


            <div class="graficos container">
                <h3>Percentual dos últimos 6 mêses</h3>
                <div class="row">
                    <div class="col s12 m5">
                        <div class="card-panel white">
                            <span>I am a very simple card. I am good at containing small bits of information.
                                I am convenient because I require little markup to use effectively. I am similar to what is called a panel in other frameworks.
                            </span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
