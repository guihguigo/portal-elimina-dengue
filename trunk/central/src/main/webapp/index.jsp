<%-- 
    Document   : index
    Created on : 20/01/2015, 22:00:29
    Author     : T2S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bar Chart</title>
        <script src="scripts/Chart.min.js"></script>
        <script src="scripts/jquery-1.11.2.min.js"></script>
    </head>
    <body>
        <div style="width: 50%">
            <canvas id="canvas" height="450" width="600"></canvas>
        </div>
         
        <script>
            var randomScalingFactor = function () {
                return Math.round(Math.random() * 100)
            };
            
            var getPercentualPrevencoes = function () {
                $.getJSON("http://localhost:8083/central/webresources/prevencao/percentualPrevencoesPorMes"
                    , function (result) {
                        $.each(result, function (i, obj) {
                            window.myBar.addData([obj.percentualEfetuada, obj.percentualAtrasada]);
                        });
                    });
            };
            
            var barChartData = {
                labels: [],
                datasets: [
                    {
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,0.8)",
                        highlightFill: "rgba(220,220,220,0.75)",
                        highlightStroke: "rgba(220,220,220,1)",
                        
                    },
                    {
                        fillColor: "rgba(151,187,205,0.5)",
                        strokeColor: "rgba(151,187,205,0.8)",
                        highlightFill: "rgba(151,187,205,0.75)",
                        highlightStroke: "rgba(151,187,205,1)",
//                        data: [randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor(), randomScalingFactor()]
                    }
                ]
            }
            window.onload = function () {
                var ctx = document.getElementById("canvas").getContext("2d");
                window.myBar = new Chart(ctx).Bar(barChartData, {
                    responsive: true
                });
                getPercentualPrevencoes();
                
               
               
            }
        </script>
    </body>
</html>
