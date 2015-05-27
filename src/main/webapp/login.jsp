<%-- 
    Document   : login
    Created on : 26/05/2015, 22:18:49
    Author     : T2S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!-- Custom css-->
        <link type="text/css" rel="stylesheet" href="css/custom.css">
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

        <title>Login</title>
    </head>
    <body style="background-color: #eeeded">
        <div class="navbar-fixed">
            <nav>
                <div class="nav-wrapper blue darken-1">

                    <a href="index.jsp" class="brand-logo">Aedes</a>
                    <ul class="right hide-on-med-and-down">                        
                        <li><a href="infografico.jsp">Consultar infograficos?</a></li>
                        <li><a href="login.jsp">Entrar</a></li>
                    </ul>

                </div>
            </nav>
        </div>

        <div class="container">

            
            <div class="row center logar">
                <div class="col offset-m4 s12 m4">
                    <div class="card-panel">
                        <div class="circle center-align">
                            <i class="large mdi-action-account-circle blue-text"></i>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="login" type="text" class="validate">
                                <label for="login">Usuário</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input id="password" type="password" class="validate">
                                <label for="password">Senha</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m12 center-align">
                                 <a class="waves-effect waves-teal btn-flat">Entrar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
