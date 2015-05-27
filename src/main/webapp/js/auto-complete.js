$(function () {
    var host = 'http://localhost:8083/central/webresources/';
    $.ajax({
        url: host + 'foco',
        success: function (result) {
            var options;
            var lis = "";

            $.each(result, function (i, obj) {
//                options = options + "<option value='" + obj.codigo + "'> " + obj.nome + "</option>";
                lis = lis + "<li value='" + obj.codigo + "'><a href='#'>" + obj.nome + "</a></li>";
            });

            $('#lista-focos').append(options);
            $('#focos').append(lis);
        }
    });

    $('#filtro-regiao').keyup(function (e) {
        var regiao = this.value;
        $.ajax({
            url: host + 'prevencao/regioes',
            data: {'regiao': regiao},
            success: function (result) {
                $('#filtro-regiao').autocomplete({
                    source: result,
                    select: function () {
                        filtrar();
                    }
                });
            }
        });
    });

    $('#filtro-regiao').keypress(function (e) {
        if (e.which === 13) {
            filtrar();
        }
    });

    $('#filtrar').click(function () {
        filtrar();
    });

    $('.sidebar-nav').on('click', 'li', function (e) {
        $('.sidebar-nav .active').removeClass('active');
        $(this).find("> a").addClass('active');
        
        if (!($(this).hasClass('level1')))
            e.stopPropagation();

        filtrar();

    });



    function filtrar() {
        var regiao = $('#filtro-regiao').val();
        var endereco = obtemRegiao(regiao);
        var foco = $('#lista-focos').val();

        atualizarGrafico(endereco[0], endereco[1], endereco[2])
    }

    function obtemRegiao(regiao) {
        var endereco = [];
        var tamanho = regiao.length;
        var inicio = regiao.indexOf(" - ");
        var aux;
        var estado;
        var cidade;
        var bairro;

        //jardim quietude, praia grande - Sao paulo
        if (inicio > -1) {
            estado = regiao.substr(inicio + 3, tamanho);

            aux = regiao.indexOf(", ");

            if (aux > -1) {
                tamanho = inicio;
                inicio = aux;
                cidade = regiao.substr(inicio + 2, tamanho - (inicio + 2));
                bairro = regiao.substr(0, inicio);
            } else {
                cidade = regiao.substr(0, inicio);
            }
        } else {
            estado = regiao;
        }

        endereco[0] = estado;
        endereco[1] = cidade;
        endereco[2] = bairro;

        return endereco;
    }
    
    $('#select_menu_focos').change(function() {
        var foco = $(this).val();
        if (foco !== "") {
            constroiLineChart();
        } else {
            constroiBarChart();
        }
    });

});



