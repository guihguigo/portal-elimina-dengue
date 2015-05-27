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


            <div class="graficos container">
                <h5>Índice regional</h5>
                <div class="row">
                    <div class="col s12 m12">
                        <div class="card-panel white">
                            <div class="row">
                                <div class="col s12">
                                    <div class="horizontal_link_menu right-align">
                                        <span class="horizontal_link_menu_first">
                                            <a href="#" class="disabledAnchor" onclick="constroiRegionGeoChart()">Região</a>
                                        </span>
                                        <span class="horizontal_link_menu_item">
                                            <a href="#"  onclick="constroiCityGeoChart()">Cidade</a>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s6">
                                    <div id="chart_regional"></div>    
                                </div>
                                <div class="col s4 right">
                                    <table class="responsive-table bordered">
                                        <thead>
                                            <tr>
                                                <th data-field="id">
                                                    Cidades mais afestas
                                                </th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <tr>
                                                <td><span class="index">1</span>Santos</td>
                                                <td><div>%0.87</div> 
                                                    <div class="progress">
                                                        <div class="determinate" style="width: 70%"></div>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><span class="index">2</span>São Vicente</td>
                                                <td>%3.76</td>
                                            </tr>
                                            <tr>
                                                <td><span class="index">3</span>Mongaguá</td>
                                                <td>%3.76</td>
                                            </tr>
                                            <tr>
                                                <td><span class="index">4</span>Cubatão</td>
                                                <td>%0.87</td>
                                            </tr>
                                            <tr>
                                                <td><span class="index">5</span>Peruíbe</td>
                                                <td>%3.76</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <h5>Índice mensal por região e por foco</h5>
                <div class="row">
                    <div class="col s12 m12">
                        <div class="card-panel white ">
                            <div class="row">
                                <div class="input-field col s6 right-align">
                                    <input id="filtro-regiao" type="text" class="validate">
                                    <label for="filtro_regiao">Filtrar Região</label>
                                </div>

                                <div class="input-field col s6 right-align">
                                    <select size="3" id="select_menu_focos">
                                        <option value="" selected>Focos</option>
                                        <option value="1">Bebedouros de Animais</option>
                                        <option value="2">Bromélias (Planta)</option>
                                        <option value="3">Caixa de Ar Condicionado</option>
                                        <option value="4">Caixa dágua</option>
                                        <option value="5">Calhas</option>
                                        <option value="6">Depressões de Terrenos</option>
                                        <option value="7">Garagens e Subsolos</option>
                                        <option value="8">Geladeiras</option>
                                        <option value="9">Piscinas</option>
                                        <option value="10">Pneus Velhos</option>
                                        <option value="11">Ralos</option>
                                        <option value="12">Recipientes de Água</option>
                                        <option value="13">Recipientes Descartáveis</option>
                                        <option value="14">Sacos de Lixo</option>
                                        <option value="15">Vasos (Flores e Plantas)</option>
                                    </select>
                                </div>
                            </div>


                            <div id="chart_percentuais_mensais"></div>
                        </div>

                    </div>
                </div>

                <!--                <h5>Índice mensal por prevenção</h5>
                                <div class="row">
                                    <div class="col s12 m12">
                                        <div class="card-panel white">
                                            <div id="chart_percentuais_mensais_por_foco" style="width: 800px; height:300px;"></div>
                                        </div>
                                    </div>
                                </div>-->

                <h5>Top em dia e atrasadas</h5>
                <div class="row">
                    <div class="col s12 m6">
                        <div class="card-panel white">                            
                            <!--<div id="chart_top_em_dia" style="width: 400px; height:250px;"></div>-->
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="filtro-regiao-top-em-dia" type="text">
                                    <label for="filtro-regiao-top-em-dia">Filtrar região</label>
                                </div>
                            </div>
                            <table  id="table-top-em-dia" class="responsive-table bordered">
                                <thead>
                                    <tr>
                                        <th data-field="id">
                                            Top 5 prevenções em dia
                                        </th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <td><span class="index">1</span>Caixa D'água</td>
                                        <td><div class="center-align">%0.87</div> 
                                            <div class="progress center-align">
                                                <div class="determinate " style="width: 70%"></div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">2</span>Calhas</td>
                                        <td>%3.76</td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">3</span>Ralos</td>
                                        <td>%3.76</td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">4</span>Recipientes Descartáveis</td>
                                        <td>%0.87</td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">5</span>Lixos</td>
                                        <td>%3.76</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="col s12 m6">
                        <div class="card-panel white">

                            <!--<div id="chart_top_atrasadas" style="width: 400px; height:250px;"></div>-->
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="filtro-regiao-top-atrasadas" type="text">
                                    <label for="filtro-regiao-top-atrasadas">Filtrar região</label>
                                </div>
                            </div>
                            <table id="table-top-atrasadas" class="responsive-table bordered">
                                <thead>
                                    <tr>
                                        <th data-field="id">
                                            Top 5 prevenções atrasadas
                                        </th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <td><span class="index">1</span>Caixa D'água</td>
                                        <td><div class="center-align">%0.87</div> 
                                            <div class="progress center-align">
                                                <div class="determinate" style="width: 70%"></div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">2</span>Calhas</td>
                                        <td>%3.76</td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">3</span>Ralos</td>
                                        <td>%3.76</td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">4</span>Recipientes Descartáveis</td>
                                        <td>%0.87</td>
                                    </tr>
                                    <tr>
                                        <td><span class="index">5</span>Lixos</td>
                                        <td>%3.76</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="js/materialize.min.js" type="text/javascript"></script>
    <!--Load the AJAX API-->
    <script src="https://www.google.com/jsapi" type="text/javascript"></script>
    <script src="js/custom-google-charts.js"  type="text/javascript"></script>
    <script src="js/auto-complete.js" type="text/javascript"></script>


    <script type="text/javascript">
                                                init();
//                                        constroiBarChart();
//                                        constroiLineChart();
//                                        constroiPieChartEmDia();
//                                        constroiPieChartAtrasadas();
//                                        constroiRegionGeoChart();


    </script>
</body>
</html>
