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
                        <li><a href="login.jsp">Sair</a></li>
                    </ul>

                </div>
            </nav>
        </div>

        <div class="container">


            <div class="row center">

                <div class="col offset-m4 s12 m5">
                    <h4>Aedes</h4>
                    <p>Cadastro de empresa</p>

                    <div class="card-panel center">
                        <form id="empresa-controller" method="POST" action="empresacontroller.do">
                            <div class="row">

                            <div class="input-field col s12 m12">
                                <input name="nome-empresa" id="nome-empresa" type="text"  class="validate" required length="60">
                                <label for="nome-empresa">Nome</label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input name="login" id="login" type="text" class="validate" length="10">
                                <label for="login">Login</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input name="password" id="password" type="password" class="validate" length="12">
                                <label for="password">Senha</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <input name="repite-password" id="repite-password" type="password" class="validate" length="12">
                                <label for="repite-password">Repita a senha</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s6 m6 center-align">
                                <a class="waves-effect waves-red btn-flat">Cancelar</a>
                            </div>
                            <div class="input-field col s6 m6 center-align">
                                <a onclick="$('#empresa-controller').submit()" class="waves-effect waves-teal btn-flat">Cadastrar</a>
                            </div>
                        </div>
                        </form>
                        
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
    </body>
</html>
