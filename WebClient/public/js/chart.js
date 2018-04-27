
var ctx = document.getElementById("chart-canvas").getContext("2d");

var drawChart = function(data) {
    var myChart = new Chart(ctx, {
        type : 'line',
        data: data,
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
    });
    return myChart;
}

