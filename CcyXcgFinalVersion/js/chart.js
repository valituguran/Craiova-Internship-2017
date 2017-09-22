function myFunction() {
    var x = document.getElementById("select").value;
    return x;
}
function getSparePartsAdditionHorizontalBar( RESTOperation) {
    var HttpClient = function() {
        this.get = function(aUrl, aCallback) {
            var anHttpRequest = new XMLHttpRequest();
            anHttpRequest.onreadystatechange = function() {
                if (anHttpRequest.readyState == 4
                    && anHttpRequest.status == 200)
                    aCallback(anHttpRequest.responseText);
            }

            anHttpRequest.open("GET", aUrl, true);
            anHttpRequest.send(null);
        }
    }
    aClient = new HttpClient();
    var url = "jSonServlet?op="+myFunction();
    aClient.get(url, function(response) {
        var values = eval('(' + response + ')');
        var xAxis = Array();
        var yAxis = Array();
        var seriesArray = [];
        var depDetail = [];
        for ( var key in values) {
            // xAxis.push(values[key]['period']);
            xAxis.push(values[key]['Day']);
            yAxis = values[key]['val'];
        }

        createSparePartsAdditionHorizontalBarGraph(xAxis, yAxis)
    });
}

function createSparePartsAdditionHorizontalBarGraph(xAxis, yAxis) {
    var echartBar = echarts.init(document
        .getElementById('echart_bar_horizontal'), theme);

    echartBar.setOption({
        title : {
            text : 'Currency chart',
        },
        tooltip : {
            show : true,
            showContent: false,
            trigger : 'axis',
        },
        xAxis : [ {
            type : 'value',
           data:xAxis,
        } ],
        yAxis : [ {
            type : 'category',
            data : yAxis
        } ],
    });
}