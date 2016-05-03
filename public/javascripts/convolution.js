var endpoint = '/';
var data = {
    x:{
        size:1
    },
    y:{
        size:1
    },

};

function arrayToString(arr){
    var res = "";
    for(var i=0;i<arr.length;i++){
        if (i==arr.length-1) {
            res += arr[i];
        } else {
            res += arr[i] + ",";
        }
    }
    return res;
}


$(function(){
    ["x","y"].forEach(function(element, index){
        $("#add_" + element).click(function(){
            console.log("click");
            var size;
            if (element == "x"){ size = data.x.size; data.x.size++;}
            else {size = data.y.size; data.y.size++;}

            $("#" + element + "_row").append('<td><input id="' + element + '_' + size + '" type="text"></td>');

        });

        $("#rm_" + element).click(function(){
            var size;
            if (element == "x"){ size = data.x.size; data.x.size--;}
            else {size = data.y.size; data.y.size--;}
            console.log($("#" + element + "_" + (size-1)));
            $("#" + element + "_" + (size-1)).parent().remove();
        });
    });

    $("#calc").click(function(){

        var xarr = [];
        var yarr = [];

        for (var i=0;i<data.x.size;i++) {
            xarr[i] = parseInt($("#x_" + i).val());
        }

        for (j=0;j<data.y.size;j++) {
            yarr[j] = parseInt($("#y_" + j).val());
        }
        console.log(xarr);
        console.log(arrayToString(xarr));
        $.ajax({
            url:endpoint + "convolution/calc",
            data:{
                xn:arrayToString(xarr),
                yn:arrayToString(yarr)
            },
            success:function(json){
                var val = json.reduce(function(x,y){ return x+", "+y ;});
                $("#answer").html("z[n] = [ " + val + " ]");
            }
        });
    });

});

