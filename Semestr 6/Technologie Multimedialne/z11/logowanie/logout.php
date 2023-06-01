<?php declare(strict_types=1); // włączenie typowania zmiennych w PHP >=7
session_start();
if (!isset($_SESSION['loggedin11']))
{
header('Location: index3.php');
exit();
}
//session_destroy();
unset($_SESSION['loggedin11']);
header('Location: index3.php');
exit();
?>