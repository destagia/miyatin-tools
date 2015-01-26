

$(function(){

    $("#add_line").click(function(){
        appendLine();
    });

});

var appendLine = (function(){
    var index = 0;
    return function() {
        $("#dijkstra_table").append(
            "<tr>" + 
            "<td>" + index + "</td>" + 
            "</tr>"
            );
        index += 1;
    };
})();