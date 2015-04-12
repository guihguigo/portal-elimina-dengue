$(function () {
    construirGrafico();
});

function construirGrafico(foco, estado, cidade, bairro) {
    var focoSelecionado = $('#lista-focos').val();

    if (focoSelecionado === '0')
        construirBarChart();
    else
        construirLineChart();

    popularPercentualPorMes(foco, estado, cidade, bairro);
}

function popularPercentualPorMes(foco, estado, cidade, bairro) {
    var url = 'http://localhost:8083/central/webresources/prevencao/percentaulPorFocoNoMes/';


    var data = {idFoco: foco, estado: estado, cidade: cidade, bairro: bairro};

    $.getJSON(url, data
            ,
            function (result) {
                $.each(result, function (i, obj) {
                    window.grafico.addData([obj.percentualEfetuada.toFixed(2), obj.percentualAtrasada.toFixed(2)], obj.nomeMes);
                });
            });
}

function construirBarChart() {
    var barChartData = {
        labels: [],
        datasets: [
            {
                highlightFill: "rgba(220, 220, 220, 0.75)",
                highlightStroke: "rgba(220, 220, 220, 1)",
                fillColor: "rgba(151, 187, 205, 0.5)",
                strokeColor: "rgba(151, 187, 205, 0.8)"
            },
            {
                fillColor: "rgba(255,0, 0, 0.5)",
                strokeColor: "rgba(178, 34, 34, 0.8)",
                highlightFill: "rgba(151,187,205,0.75)",
                highlightStroke: "rgba(151,187,205,1)"
            }
        ]
    };

    var opcoes = {
        responsive: true,
        legendTemplate: '<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };

    var ctx = getContext();
    window.grafico = new Chart(ctx).Bar(barChartData, opcoes);
}

function construirLineChart() {
    var lineChartData = {
        labels: [],
        datasets: [
            {
                fillColor: "rgba(151, 187, 205, 0.2)",
                strokeColor: "rgba(151, 187, 205, 0.8)",
                pointColor: "rgba(151, 187, 205, 0.8)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)"

            },
            {
                fillColor: "rgba(255,0, 0, 0.2)",
                strokeColor: "rgba(178, 34, 34, 0.8)",
                pointColor: "rgba(178, 34, 34, 0.8)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)"

            }
        ]
    };

    var opcoes = {
        responsive: true,
        legendTemplate: '<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>'
    };

    var ctx = getContext();
    window.grafico = new Chart(ctx).Line(lineChartData, opcoes);
}

function getContext() {
    var elemento = $('#canvas');
    var canvas = elemento.get(0);
    var ctx = canvas.getContext('2d');

    return ctx;
}

function atualizarGrafico(foco, estado, cidade, bairro) {
    limparGrafico();
    construirGrafico(foco, estado, cidade, bairro);
}

function limparGrafico() {
    var elemento = $('#canvas');
    var canvas = elemento.get(0);
    var ctx = canvas.getContext('2d');

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.beginPath();

    window.grafico.datasets = [];
}


