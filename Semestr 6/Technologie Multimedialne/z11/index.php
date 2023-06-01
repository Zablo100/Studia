<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}?>
<head>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
</head>
    <body>
        <a href="formularz.php">Formularz</a> <br>
        <a href="wykres.php">Wykres</a> <br>
        <a href="tabela.php">Tabela</a> <br>
        <a href="wykres2.php">Wykres 2</a> <br>
        <a href="wykres3.php">Wykres 3</a> <br>
        </br>
        <a href="scadaform.php">SCADA Formularz</a> <br>
        <a href="test.php">SCADA</a> <br><br>
        <a href="logowanie/logout.php">Wyloguj</a> <br>
    </body>