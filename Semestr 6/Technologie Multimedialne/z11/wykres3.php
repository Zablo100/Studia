<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
} 
$dbhost="localhost"; $dbuser="id19668897_buk"; $dbpassword="I1A3ahT~98PU\zFB"; $dbname="id19668897_z1";
$polaczenie = mysqli_connect($dbhost, $dbuser, $dbpassword, $dbname);
$rezultat = mysqli_query($polaczenie, "SELECT * FROM pomiary2 ORDER BY id DESC LIMIT 5"); //ORDER BY id DESC LIMIT 5
$test = [];
$test2 = [];
$test3 = [];
$test4 = [];
$test5 = [];
while ($wiersz = mysqli_fetch_array ($rezultat)) 
{
$id = $wiersz[0];
$x1 = $wiersz[1];
$x2 = $wiersz[2];
$x3 = $wiersz[3];
$x4 = $wiersz[4];
$x5 = $wiersz[5];
$datetime = $wiersz[6];
array_push($test, $x1);
array_push($test2, $x2);
array_push($test3, $x3);
array_push($test4, $x4);
array_push($test5, $x5);
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
$plot->DrawGraph();
?>