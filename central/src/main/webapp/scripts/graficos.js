$(function () {
    var percentualTemplate = '[{{percentualEfetuada}}, {{percentualAtrasada}}], {{nomeMes}}';
    constroiBarChart();
    getPercentualPorMes('', '', '', '');
});

function getPercentualPorMes(foco, estado, cidade, bairro) {
    var url = 'http://localhost:8083/central/webresources/prevencao/percentaulPorFocoNoMes/';


    var data = {idFoco: foco, estado: estado, cidade: cidade, bairro: bairro};

    $.getJSON(url, data
            ,
            function (result) {
                $.each(result, function (i, obj) {
                    window.myBar.addData([obj.percentualEfetuada.toFixed(2), obj.percentualAtrasada.toFixed(2)], obj.nomeMes);
                });
            });
}

function constroiBarChart() {
    var barChartData = {
        labels: [],
        datasets: [
            {
                highlightFill: "rgba(220,220,220,0.75)",
                highlightStroke: "rgba(220,220,220,1)",
                fillColor: "rgba(151,187,205,0.5)",
                strokeColor: "rgba(151,187,205,0.8)",
            },
            {
                fillColor: "rgba(255,0, 0, 0.5)",
                strokeColor: "rgba(178, 34, 34, 0.8)",
                highlightFill: "rgba(151,187,205,0.75)",
                highlightStroke: "rgba(151,187,205,1)",
            }
        ]
    };

    var options = {
        responsive: true,
        legendTemplate: '<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };
    var ctx = document.getElementById("canvas").getContext("2d");

    window.myBar = new Chart(ctx).Bar(barChartData, options);
}

function atualizaBarChart(foco, estado, cidade, bairro) {
    var canvas = document.getElementById("canvas");
    var ctx = document.getElementById("canvas").getContext("2d");
    ctx.beginPath();
    ctx.save();
    ctx.setTransform(1, 0, 0, 1, 0, 0);
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.restore();
    constroiBarChart();
    getPercentualPorMes(foco, estado, cidade, bairro);
}


