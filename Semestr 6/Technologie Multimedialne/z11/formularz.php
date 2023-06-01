<?php
session_start();
if (!isset($_SESSION['loggedin11'])) {
    header('Location: logowanie/index3.php');
    exit();
}
?>
<form method="POST" action="add.php"><br>
 x1:<input type="number" name="x1" min="-50" max="100"><br>
 x2:<input type="number" name="x2" min="-50" max="100"><br>
 x3:<input type="number" name="x3" min="-50" max="100"><br>
 x4:<input type="number" name="x4" min="-50" max="100"><br>
 x5:<input type="number" name="x5" min="-50" max="100"><br>
 <input type="submit" value="Send"/>
</form>