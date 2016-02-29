<%@ page language="java" pageEncoding="UTF-8"%>
<script>
    $(document).ready( function() {
        var d = new Date();
        var dia = d.getDate();
        var mes = d.getMonth() + 1;
        var ano = d.getFullYear();

        var fecha = dia + '/' + mes + '/' + ano;
        $('footer')
        .html('Fecha del sistema: ' + fecha)
        .attr('class','text-center')
        .css({
            'backgroundColor': '#343434',
            'color': '#cecece',
            'padding': '1em',
            'margin-top': '1em'
        });
    });
</script>
<footer>
</footer>
