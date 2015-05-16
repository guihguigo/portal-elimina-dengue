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

        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/material.min.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">

        <link href="css/roboto.min.css" rel="stylesheet">
    </head>
    <body>

        <!-- Fixed navbar -->
        <div class="navbar navbar-default navbar-fixed-top navbar-material-blue">
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
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <div id="headerwrap" class="">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <h1>A prevenção é a única arma contra a dengue<br/>
                            Baixe o aplicativo</h1>
                        <p class="lead">
                            <!--<a href="https://play.google.com/store?hl=pt_BR" class="btn ">Baixar</a>-->
                            <button id="btn-baixar" class="btn btn-default"><i class="mdi-file-file-download"></i>Baixar</button>
                        </p>				
                    </div><!-- /col-lg-6 -->
                    <div class="col-lg-6">
                        <img class="img-responsive" src="images/ipad-hand.png" alt="">
                    </div><!-- /col-lg-6 -->

                </div><!-- /row -->
            </div><!-- /container -->
        </div><!-- /headerwrap -->


        <div class="container">
            <div class="row mt centered">
                <div class="col-lg-6 col-lg-offset-3">
                    <h1>Mantenha-se longe da dengue <br/>em três passos!</h1>
                    <h3>Agende suas prevenções e deixe-nos lhe ajudar nesta tarefa.</h3>
                </div>
            </div><!-- /row -->

            <div class="row mt centered">
                <div class="col-lg-4">
                    <img src="images/checklist.png" width="180" alt="">
                    <h4>1º Agende suas prevenções</h4>
                    <p>Faça o checklist dos possíveis focos de dengue em sua residência para que as prevenções sejam agendadas.</p>
                </div><!--/col-lg-4 -->

                <div class="col-lg-4">
                    <img src="images/notificacao.png" width="180" alt="">
                    <h4>2º É hora da prevenção</h4>
                    <p>Receba uma notificação no momento de efetuar a prevenção. .</p>

                </div><!--/col-lg-4 -->

                <div class="col-lg-4">
                    <img src="images/reagendar.png" width="180" alt="">
                    <h4>3º Agora é só reangedar</h4>
                    <p>Após efetuada, reagende e continue a manter sua residência livre desta ameaça.</p>

                </div><!--/col-lg-4 -->
            </div><!-- /row -->
        </div><!-- /container -->



        <div class="container">

            <div class="row mt centered">
                <div class="col-lg-6 col-lg-offset-3">
                    <h1>O Aplicativo...</h1>
                    <h3></h3>
                </div>
            </div><!-- /row -->

            <! -- CAROUSEL -->
            <div class="row mt centered">
                <div class="col-lg-6 col-lg-offset-3">
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                        <div class="carousel-inner">
                            <div class="item active">
                                <img src="images/p01.png" alt="">
                            </div>
                            <div class="item">
                                <img src="images/p02.png" alt="">
                            </div>
                            <div class="item">
                                <img src="images/p03.png" alt="">
                            </div>
                        </div>
                    </div>			
                </div><!-- /col-lg-8 -->
            </div><!-- /row -->
        </div><! --/container -->


        <div class="container">

            <div class="row mt centered">
                <div class="col-lg-6 col-lg-offset-3">
                    <h1>Consulte os infográficos <br</h1>
                    <h3>Verifique o quão protegida sua região se encontra.</h3>
                </div>
            </div><!-- /row -->


            <div class="container">
                <hr>
                <p class="centered">Criado by Pimpolhos - 2015</p>
            </div><!-- /container -->

            <script src="scripts/jquery-1.11.2.min.js"></script>
            <script src="scripts/bootstrap.min.js"></script>
    </body>
</html>
