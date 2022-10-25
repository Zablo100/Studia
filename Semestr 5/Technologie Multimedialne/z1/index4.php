<?php declare(strict_types=1); // włączenie typowania zmiennych w PHP >=7
session_start();
if (!isset($_SESSION['loggedin']))
{
header('Location: index3.php');
exit();
}
echo "Jestem zalogowany";
?>
<br/>
<a href="logout.php">Wyloguj</a>
