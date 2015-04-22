$(function () {
    var host = 'http://54.94.249.193:8083/central/webresources/';
    $.ajax({
        url: host + 'foco',
        success: function (result) {
            var options;

            $.each(result, function (i, obj) {
                options = options + "<option value='" + obj.codigo + "'> " + obj.nome + "</option>";
            });

            $('#lista-focos').append(options);
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

    $('.sidebar-nav li').click(function () {
        $('.sidebar-nav li.active').removeClass('active');
        $(this).addClass('active');
    });

    function filtrar() {
        var regiao = $('#filtro-regiao').val();
        var endereco = obtemRegiao(regiao);
        var foco = $('#lista-focos').val();

        atualizarGrafico(foco, endereco[0], endereco[1], endereco[2])
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

});



