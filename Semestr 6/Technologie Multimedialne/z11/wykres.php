<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}
require_once 'phplot.php';
$plot = new PHPlot();
$data = array(array('14:30',0,5), array('14:35',1,2), array('14:40',2,7), array('14:45',3,3), array('14:50',4,1));
$plot -> SetDataValues($data);
$plot -> SetDataType('data-data');
$plot -> SetTitle('Przyklad wykresu liniowego'); // opcjonalny tytuł wykresu
$plot -> DrawGraph();
?>