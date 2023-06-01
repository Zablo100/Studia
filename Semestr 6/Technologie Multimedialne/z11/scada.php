<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}
$dbhost="localhost"; $dbuser="id19668897_buk"; $dbpassword="I1A3ahT~98PU\zFB"; $dbname="id19668897_z1";
$polaczenie = mysqli_connect($dbhost, $dbuser, $dbpassword, $dbname);
$rezultat = mysqli_query($polaczenie, "SELECT * FROM scada ORDER BY id DESC LIMIT 5"); //ORDER BY id DESC LIMIT 5
$test = [];
$test2 = [];
$test3 = [];
$test4 = [];
$test5 = [];

$f = [];
$w = [];
$k = [];
$z = [];
$co = [];
while ($wiersz = mysqli_fetch_array ($rezultat)) 
{
$id = $wiersz[0];
$x1 = $wiersz[1];
$x2 = $wiersz[2];
$x3 = $wiersz[3];
$x4 = $wiersz[4];
$x5 = $wiersz[5];
$x6 = $wiersz[7];
$x7 = $wiersz[8];
$x8 = $wiersz[9];
$x9 = $wiersz[10];
$x10 = $wiersz[11];

$datetime = $wiersz[6];
array_push($test, $x1);
array_push($test2, $x2);
array_push($test3, $x3);
array_push($test4, $x4);
array_push($test5, $x5);

array_push($f, $x6);
array_push($w, $x7);
array_push($k, $x8);
array_push($z, $x9);
array_push($co, $x10);
}
mysqli_close($polaczenie);
require_once 'phplot.php';
$plot = new PHPlot(800,600);

//Set titles
$plot->SetTitle("Wykres x1,x2,x3,x4,x5");
$plot->SetXTitle('X Data');
$plot->SetYTitle('Y Data');

$legend_text = array('x1','x2','x3','x4','x5');
//Define some data
$example_data = array(
    array('1',$test[0], $test2[0], $test3[0], $test4[0], $test5[0]),
    array('2',$test[1],$test2[1],$test3[1],$test4[1],$test5[1]),
    array('3',$test[2],$test2[2],$test3[2],$test4[2],$test5[2]),
    array('4',$test[3],$test2[3],$test3[3],$test4[3],$test5[3]),
    array('5',$test[4],$test2[4],$test3[4],$test4[4],$test5[4]),
);
$plot->SetDataValues($example_data);

//Turn off X axis ticks and labels because they get in the way:
$plot->SetXTickLabelPos('none');
$plot->SetXTickPos('none');
$plot->SetLegend($legend_text);
//Draw it
$plot->SetPrintImage(False); // No automatic output
$plot->DrawGraph();

$sr = ($test[0] + $test2[0] + $test3[0])/3;
?>

<style>
    .content{
        display: flex;
    }
    
    #x1{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 5em;
        top: 4.5em;
        
        z-index: 1;
    }
    
    #x2{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 13em;
        top: 6em;
        
        z-index: 1;
    }
    #x3{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 16em;
        top: 12em;
        
        z-index: 1;
    }
    
    #x4{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 26em;
        top: 6em;
        
        z-index: 1;
    }
    
    #x5{
        background-color: yellow;
        position: absolute;
        font-size: 2em;
        left: 26em;
        top: 12em;
    }
    
    #temp1{
        position: absolute;
        height: 15em;
        width: 12em;

        
        opacity: 0.6;
        
        left: 4em;
        top: 4em;
    }
    
    #temp2{
        position: absolute;
        height: 15em;
        width: 20em;

        
        opacity: 0.6;
        
        left: 20em;
        top: 4em;
    }
    
    #temp3{
        position: absolute;
        height: 10em;
        width: 15em;

        
        opacity: 0.6;
        
        left: 26em;
        top: 21em;
    }
</style>
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
   <script type="text/javascript">
      google.charts.load('current', {'packages':['gauge']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['', <?php print($sr);?>],
        ]);

        var options = {
          min: -30, max: 50,
          width: 400, height: 150,
          redFrom: 30, redTo: 50,
          yellowFrom:25, yellowTo: 30,
          minorTicks: 5
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));

        chart.draw(data, options);
        
      }
    </script>
    
<div class="content">
            <div id="obrazek">
                <img src="mieszkanie.jpg">
                <span id="x1"><?php print $test[0];?></span>
                <span id="x2"><?php print $test2[0];?></span>
                <span id="x3"><?php print $test3[0];?></span>
                <div id="temp1"></div>
                <div id="temp2"></div>
                <div id="temp3"></div>
                </div>
        <div id="koc">
            <img src="Koc.png">
            <span id="x4"><?php print $test4[0];?></span>
            <span id="x5"><?php print $test5[0];?></span>
        </div>
        <div id="wykres">
            <img src="<?php echo $plot->EncodeImage();?>">
        </div>

</div>
<div class="content">
    <div>
       <span id="opis">Średnia Temperatura w domu</span>
        <div id="chart_div" style="width: 300px; height: 120px;"></div> 
    </div>
    <img id="fire" src="icons/falarm.png" width="80px" height="150px">
    <img id="r" src="icons/ralarm.jpg" width="150px" height="150px">
    <img id="co" src="icons/coalarm.png" width="150px" height="150px">
    <img id="pipe">
    <img id="klima">
</div>

    <script defer>
        const x1 = <?php print($test[0]);?>;
        const x2 = <?php print($test2[0]);?>;
        const x3 = <?php print($test3[0]);?>;
        const fire = <?php print($f[0]);?>;
        const r = <?php print($w[0]);?>;
        const pipe = <?php print($z[0]);?>;
        const klima = <?php print($k[0]);?>;
        const co = <?php print($co[0]);?>;
        
        if(x1 < 10){
            document.getElementById("temp1").style.backgroundColor = "blue";
        }else if(x1 >= 10 & x1 < 20){
            document.getElementById("temp1").style.backgroundColor = "green";
        }else if(x1 >= 20 & x1 < 25){
            
        }else if(x1 >= 25 & x1 < 30){
            document.getElementById("temp1").style.backgroundColor = "orange";
        }else if( x1 >= 30){
            document.getElementById("temp1").style.backgroundColor = "red";
        }
        
        
        if(x2 < 10){
            document.getElementById("temp2").style.backgroundColor = "blue";
        }else if(x2 >= 10 & x2 < 20){
            document.getElementById("temp2").style.backgroundColor = "green";
        }else if(x2 >= 20 & x2 < 25){
            
        }else if(x2 >= 25 & x2 < 30){
            document.getElementById("temp2").style.backgroundColor = "orange";
        }else if( x2 >= 30){
            document.getElementById("temp2").style.backgroundColor = "red";
        }
        
        if(x3 < 10){
            document.getElementById("temp3").style.backgroundColor = "blue";
        }else if(x3 >= 10 & x3 < 20){
            document.getElementById("temp3").style.backgroundColor = "green";
        }else if(x3 >= 20 & x3 < 25){
            
        }else if(x3 >= 25 & x3 < 30){
            document.getElementById("temp3").style.backgroundColor = "orange";
        }else if( x3 >= 30){
            document.getElementById("temp3").style.backgroundColor = "red";
        }
        

        if(fire){
            document.getElementById("fire").src="icons/fire.jpg";
        }
        
        if(r){
            document.getElementById("r").src="icons/r.png";
        }
        
        if(pipe){
            document.getElementById("pipe").src="icons/pipe.png";
            document.getElementById("pipe").style.height = '100px';
            document.getElementById("pipe").style.width = '200px';
        }
        
        if(klima){
            document.getElementById("klima").src="icons/klima.png";
            document.getElementById("klima").style.height = '100px';
            document.getElementById("klima").style.width = '200px';
        }
        
        if(co){
            document.getElementById("co").src="icons/cod.png";
        }
    </script>