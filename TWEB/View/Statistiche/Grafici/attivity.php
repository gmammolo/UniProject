   <script type="text/javascript">
      google.load("visualization", "1.1", {packages:["calendar"]});
      google.setOnLoadCallback(drawChart);

   function drawChart() {
        var jsonData = $.ajax({
            type: "POST",
            url: "",
            data: {"ajaxRequest" : "attivity" },
            async: false
          }).responseText;
      
        var array = [];
        var res= JSON.parse(jsonData);
        for(i=0; i < res.length ; i++) {
            array.push([new Date(res[i]) , Number(1)]);
        }

       
       
       var dataTable = new google.visualization.DataTable();
       dataTable.addColumn({ type: 'date', id: 'Date' });
       dataTable.addColumn({ type: 'number', id: 'Won/Loss' });
       dataTable.addRows(array);

       var chart = new google.visualization.Calendar(document.getElementById('calendar_basic'));

       var options = {
         title: "Attivita",
         height: 350,
         colorAxis: {
             minValue : 0
         }
       };

       chart.draw(dataTable, options);
   }
    </script>
    
    
     <div id="calendar_basic" style="width: 1000px; height: 350px;"></div>