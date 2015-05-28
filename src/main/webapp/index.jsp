<%-- 
    Document   : index
    Created on : 01/05/2015, 22:56:05
    Author     : T2S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aedes - Elimina Dengue</title>
        <!--Import materialize.css-->
        <link href="css/main.css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <!--<link href="css/bootstrap.min.css" rel="stylesheet">-->


        <link type="text/css" rel="stylesheet" href="css/custom.css">

    </head>
    <body>

        <!--         Fixed navbar 
                <div class="navbar navbar-default navbar-fixed-top blue darken-1">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="index.jsp"><b>Aedes</b></a>
                        </div>
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="infografico.jsp">Consultar infográficos?</a></li>
                            </ul>
                        </div>/.nav-collapse 
                    </div>
                </div>-->

        <!--        <div class="wrap-container">          -->
        <div class="navbar-fixed">
            <nav style="box-shadow: none !important">
                <div class="nav-wrapper blue darken-1" >

                    <a href="index.jsp" class="brand-logo">Aedes</a>
                    <ul class="right hide-on-med-and-down">                        
                        <li><a href="infografico.jsp">Consultar infograficos?</a></li>
                        <li><a href="login.jsp">Entrar</a></li>
                    </ul>

                </div>
            </nav>
        </div>

        <div id="headerwrap" class="blue darken-1">
            <div class="container">
                <div class="row" style="margin-bottom: 0">
                    <div class="col s12 m6">
                        <h3>A prevenção é a única arma contra a dengue<br/>
                            Baixe o aplicativo</h3>
                        <p class="lead">
                            <!--<a href="https://play.google.com/store?hl=pt_BR" class="btn ">Baixar</a>-->
                            <a class="waves-effect waves-light btn-large  blue lighten-2 white-text">Baixar</a>
                        </p>				
                    </div><!-- /col-lg-6 -->
                    <div class="col s12 m6">
                        <img class="img-responsive" src="images/ipad-hand.png" alt="">
                    </div><!-- /col-lg-6 -->

                </div><!-- /row -->
            </div><!-- /container -->
        </div><!-- /headerwrap -->


        <div class="container mt">
            <div class="row centered">
                <div class="col s12 m12 ">
                    <h3>Mantenha-se longe da dengue <br/>em três passos!</h3>
                    <h5>Agende suas prevenções e deixe-nos lhe ajudar nesta tarefa.</h5>
                </div>
            </div><!-- /row -->

            <div class="row mt centered">
                <div class="col s12 m4">
                    <img src="images/checklist.png" width="180" alt="1º Agende suas prevenções">
                    <h5>1º Agende a prevenção</h5>
                    <p>Faça o checklist dos possíveis focos de dengue em sua residência para que as prevenções sejam agendadas.</p>
                </div><!--/col-lg-4 -->

                <div class="col s12 m4">
                    <img src="images/notificacao.png" width="180" alt="2º É hora da prevenção">
                    <h5>2º É hora da prevenção</h5>
                    <p>Receba uma notificação no momento de efetuar a prevenção. .</p>

                </div><!--/col-lg-4 -->

                <div class="col s12 m4">
                    <img src="images/reagendar.png" width="180" alt="3º Agora é só reangedar">
                    <h5>3º Agora é só reagendar</h5>
                    <p>Após efetuada, reagende e continue a manter sua residência livre desta ameaça.</p>

                </div><!--/col-lg-4 -->
            </div><!-- /row -->
        </div><!-- /container -->



        <div class="container">

            <div class="row mt centered">
                <div class="col s12 m6 offset-m3">
                    <h3>O Aplicativo...</h3>
                    <h3></h3>
                </div>
            </div><!-- /row -->

            <! -- CAROUSEL -->

            <div class="row centered">
                <div class="col s12 m6 offset-m3">
                    <div class="slider">
                        <ul class="slides">
                            <li>
                                <img src="images/p01.png"> <!-- random image -->
                                
                            </li>
                            <li>
                                <img src="images/p02.png"> <!-- random image -->
                                
                            </li>
                            <li>
                                <img src="images/p03.png"> <!-- random image -->
                                
                            </li>
                            
                        </ul>
                    </div>			
                </div><!-- /col-lg-8 -->
            </div><!-- /row -->
        </div><! --/container -->


        <div class="container">

            <div class="row mt centered">
                <div class="col-lg-6 col-lg-offset-3">
                    <h3>Consulte os infográficos <br</h3>
                    <h5>Verifique o quão protegida sua região se encontra.</h5>
                </div>
            </div><!-- /row -->


            <div class="container">
                <hr>
                <p class="centered">Criado by Pimpolhos - 2015</p>
            </div><!-- /container -->

            <script src="js/jquery-1.11.2.min.js"></script>
            <script src="js/materialize.min.js"></script>
            <script type="text/javascript">
                $('.slider').slider({full_width: false});
            </script>
    </body>
</html>
