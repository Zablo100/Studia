<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}?>
<form method="POST" action="scadaadd.php"><br>
 x1:<input type="number" name="x1" min="-50" max="100">(sypialnia)<br>
 x2:<input type="number" name="x2" min="-50" max="100">(Salon)<br>
 x3:<input type="number" name="x3" min="-50" max="100">(Gabinet)<br>
 x4:<input type="number" name="x4" min="-50" max="100"><br>
 x5:<input type="number" name="x5" min="-50" max="100"><br>
 Pożar: <select name="f">
  <option value="0">Nie</option>
  <option value="1">Tak</option>
</select>
<br>
 Włamanie: <select name="w">
  <option value="0">Nie</option>
  <option value="1">Tak</option>
</select>
<br>
 Zalanie: <select name="z">
  <option value="0">Nie</option>
  <option value="1">Tak</option>
</select>
<br>
 Klimatyzacja: <select name="k">
  <option value="0">Off</option>
  <option value="1">On</option>
</select>
<br>
 Alarm CO: <select name="co">
  <option value="0">Czuwanie</option>
  <option value="1">Zagrożenie</option>
</select>
 <input type="submit" value="Send"/>
</form>
