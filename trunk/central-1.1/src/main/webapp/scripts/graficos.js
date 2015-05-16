$(function () {
    $.material.init();
    construirGrafico();
});
var host = 'http://localhost:8083/central/webresources/prevencao/';

function construirGrafico(estado, cidade, bairro) {
    var focoSelecionado;
    var tipo; 
    
    if ($('.level2').find('.active').length > 0) {
        focoSelecionado = $('.sidebar-nav .active').parent().attr('value');
        tipo = $('.level1').attr('value');
    } else {
        focoSelecionado = '0';
        tipo = $('.sidebar-nav .active').parent().attr('value');
    }
    
    if (tipo === 'PercentualPrevencoesPorMes') {
        if (focoSelecionado === '0') {
            construirBarChart();
            popularPercentualPorMes(estado, cidade, bairro);
        } else {
            construirLineChart();
            popularPercentualDoFocoPorMes(focoSelecionado, estado, cidade, bairro);
        }
    } else if (tipo === 'PercentualPrevencoesTopEfetuadas') {
        construirPieChart();
        popularPercentualTopEmDia(estado, cidade, bairro);
    } else if (tipo === 'PercentualPrevencoesTopAtrasadas') {
        construirPieChart();
        popularPercentualTopAtrasadas(estado, cidade, bairro);
    }



}

function popularPercentualTopEmDia(estado, cidade, bairro) {
    var url = host + 'percentualTopEmDia';
    var randomColorFactor = function () {
        return Math.round(Math.random() * 255);
    };
    
    var color = ['rgb(0,0,128)', 'rgb(0,0,255)', 'rgb(100,149,237)', 'rgb(30,144,255)', 'rgb(135,206,235)'];
    var data = {estado: estado, cidade: cidade, bairro: bairro};

    $.getJSON(url, data
            ,
            function (result) {
                $.each(result, function (i, obj) {
                    if (i === 5) {
                        return false;
                    }

                    if (obj.percentualEmDia !== 0) {
                        window.grafico.addData({
                            value: obj.percentualEmDia.toFixed(2),
                            color: 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',.7)',
//                            highlight: 'rgba(224,255,255, 0.5)',
                            
                            label: obj.nomeFoco

                        });
                    }
                });

                var legend = window.grafico.generateLegend();
                $('#legend').html(legend);
            });
}

function popularPercentualTopAtrasadas(estado, cidade, bairro) {
    var url = host + 'percentualTopAtrasadas';
    var randomColorFactor = function () {
        return Math.round(Math.random() * 255);
    };

    var data = {estado: estado, cidade: cidade, bairro: bairro};

    $.getJSON(url, data
            ,
            function (result) {
                $.each(result, function (i, obj) {
                    if (i === 5) {
                        return false;
                    }

                    if (obj.percentualAtrasada !== 0) {
                        window.grafico.addData({
                            value: obj.percentualAtrasada.toFixed(2),
                            color: 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',.7)',
//                            highlight: "#C69CBE",
                            label: obj.nomeFoco

                        });
                    }

                });
                
                var legend = window.grafico.generateLegend();
                $('#legend').html(legend);

            });
}

function popularPercentualPorMes(estado, cidade, bairro) {
    var url = host + 'percentualPorMes';


    var data = {estado: estado, cidade: cidade, bairro: bairro};

    $.getJSON(url, data
            ,
            function (result) {
                $.each(result, function (i, obj) {
                    window.grafico.addData([obj.percentualEmDia.toFixed(2), obj.percentualAtrasada.toFixed(2)], obj.nomeMes);

                });

            });
}

function popularPercentualDoFocoPorMes(foco, estado, cidade, bairro) {
    var url = host + 'percentualDoFocoPorMes';


    var data = {foco: foco, estado: estado, cidade: cidade, bairro: bairro};

    $.getJSON(url, data
            ,
            function (result) {
                $.each(result, function (i, obj) {
                    window.grafico.addData([obj.percentualEmDia.toFixed(2), obj.percentualAtrasada.toFixed(2)], obj.nomeMes);

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
                strokeColor: "rgba(151, 187, 205, 0.8)",
                label: 'Em dia (%)'
            },
            {
                fillColor: "rgba(255,0, 0, 0.5)",
                strokeColor: "rgba(178, 34, 34, 0.8)",
                highlightFill: "rgba(151,187,205,0.75)",
                highlightStroke: "rgba(151,187,205,1)",
                label: 'Atrasadas (%)'
            }
        ]
    };

    var opcoes = {
        responsive: true,
        legendTemplate: "<ul style=\"list-style-type: none;\" class=\"<%=name.toLowerCase()%>-legend\">" +
                "<% for (var i=0; i<datasets.length; i++){%><li style=\"margin: 0 0 6px 0\" >" +
                "<div style=\"background-color:<%=datasets[i].fillColor%>;border:1px solid <%=datasets[i].strokeColor%>;" +
                "display: inline-block; width: 15px; height: 15px; margin: 0 10px 0 0; border-radius: 2px; vertical-align: middle\">" +
                "</div><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
    };

    var ctx = getContext();
    window.grafico = new Chart(ctx).Bar(barChartData, opcoes);
    var legend = window.grafico.generateLegend();
    $('#legend').html(legend);
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
                pointHighlightStroke: "rgba(220,220,220,1)",
                label: 'Em dia (%)'

            },
            {
                fillColor: "rgba(255,0, 0, 0.2)",
                strokeColor: "rgba(178, 34, 34, 0.8)",
                pointColor: "rgba(178, 34, 34, 0.8)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                label: 'Atrasadas (%)'
            }
        ]
    };

    var opcoes = {
        responsive: true,
        legendTemplate: "<ul style=\"list-style-type: none;\" class=\"<%=name.toLowerCase()%>-legend\">" +
                "<% for (var i=0; i<datasets.length; i++){%><li style=\"margin: 0 0 6px 0\" >" +
                "<div style=\"background-color:<%=datasets[i].fillColor%>;border:1px solid <%=datasets[i].strokeColor%>;" +
                "display: inline-block; width: 15px; height: 15px; margin: 0 10px 0 0; border-radius: 2px; vertical-align: middle\">" +
                "</div><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>"
    };

    var ctx = getContext();
    window.grafico = new Chart(ctx).Line(lineChartData, opcoes);
    var legend = window.grafico.generateLegend();
    $('#legend').html(legend);

}

function construirPieChart() {
    var data = [];

    var opcoes = {
        animateRotate: true,
        animateScale: true,
        animationEasing: "easeOutQuart",
        segmentShowStroke: false,
        responsive: true,
        legendTemplate: "<ul style=\"list-style-type: none;\" class=\"<%=name.toLowerCase()%>-legend\">" +
                "<% for (var i=0; i<window.grafico.segments.length; i++){%><li style=\"margin: 0 0 6px 0\" >" +
                "<div style=\"background-color:<%=window.grafico.segments[i].fillColor%>;" +
                "display: inline-block; width: 15px; height: 15px; margin: 0 10px 0 0; border-radius: 2px; vertical-align: middle\">" +
                "</div><%if(window.grafico.segments[i].label){%><%=window.grafico.segments[i].label + ' ' + '(%' + window.grafico.segments[i].value + ')'%><%}%></li><%}%></ul>"
    };

    var ctx = getContext();
    window.grafico = new Chart(ctx).Pie(data, opcoes);
    

}

function getContext() {
    var elemento = $('#canvas');
    var canvas = elemento.get(0);
    var ctx = canvas.getContext('2d');

    return ctx;
}

function atualizarGrafico(estado, cidade, bairro) {
    limparGrafico();
    construirGrafico(estado, cidade, bairro);
}

function limparGrafico() {
    var elemento = $('#canvas');
    var canvas = elemento.get(0);
    var ctx = canvas.getContext('2d');


    window.grafico.destroy();
//    window.grafico.datasets = [];
}


