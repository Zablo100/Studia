<?php
session_start();
if (!isset($_SESSION['loggedin']))
{
header('Location: index3.php');
exit();
}
$connection = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
if (!$connection)
{
	echo " MySQL Connection error." . PHP_EOL;
	echo "Errno: " . mysqli_connect_errno() . PHP_EOL;
	echo "Error: " . mysqli_connect_error() . PHP_EOL;
	exit;
}

$userName = $_SESSION['user'];
echo "Zalogowano jako $userName";
?>
<br>
<a href="index4.php">Historia wizyt</a><br>

<form method='POST' action='nMsg.php'><br>
<label for='post' class='form-label'>Wiadomość:</label>
<input type='text' name='post' maxlength='90' size='90'><br>
<label for='recipient' class='form-label'>Odbiorca:</label>
<select class='form-control' name='recipient' id='recipient'>";

<?php 
$result = mysqli_query($connection, "Select username from users3 Where username!='$userName'") or die ("DB erro");

while($row = mysqli_fetch_array ($result)){
    print "<option value='$row[0]'>$row[0]</option>";
}?>

</select><br>
<input type='submit' value='Send'/>
</form>

<form method="POST" action="plik.php">
<input type='submit' value='Send file'/>
</form><br>

<?php
print "<section id='pull'></section>";
mysqli_close($connection);
?>

<script>
            var lastPull = "";
            function getPull(){ 
                var http = new XMLHttpRequest();
                http.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        if(lastPull!=this.responseText){
                            lastPull = this.responseText;
                            document.getElementById("pull").innerHTML = this.responseText;
                        }
                    }
                }
                http.open("GET", "pull.php", true);
                http.send();
            }
            getPull();
            setInterval(getPull,1000);
</script>


<a href ="logout.php">Wyloguj</a><br />