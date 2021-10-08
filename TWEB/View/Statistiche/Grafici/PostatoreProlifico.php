<script type="text/javascript">

  // Load the Visualization API and the piechart package.
  google.load('visualization', '1.0', {'packages':['corechart']});

  // Set a callback to run when the Google Visualization API is loaded.
  google.setOnLoadCallback(drawChart);


  // Callback that creates and populates a data table, 
  // instantiates the pie chart, passes in the data and
  // draws it.
  function drawChart() {

    var jsonData = $.ajax({
        type: "POST",
        url: "",
        data: {"ajaxRequest" : "prolificPoster" },
        async: false
      }).responseText;
      
    var array = [];
    var res= JSON.parse(jsonData);
    for(i=0; i < res.length ; i+=2) {
        array.push([res[i] , Number(res[i+1])]);
    }

    
      // Create the data table.
var data = new google.visualization.DataTable();
data.addColumn('string', 'Topping');
data.addColumn('number', 'Slices');
data.addRows(array);

var options = {title:'Classifica maggior numero di post',
                  is3D: true,
                 'width':500,
                 'height':300};

  // Instantiate and draw our chart, passing in some options.
  var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
  chart.draw(data, options);
}
</script>

<div id="chart_div" style="width:400; height:300"></div>
<div id="success_div"></div>