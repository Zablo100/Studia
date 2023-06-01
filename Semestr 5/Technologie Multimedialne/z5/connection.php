<?php
$connection = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
if (!$connection) {
  echo "Error: " . mysqli_connect_errno() . " " . mysqli_connect_error();
} // obsługa błędu połączenia z BD

mysqli_query($connection, "SET NAMES 'utf8'"); // ustawienie polskich znaków