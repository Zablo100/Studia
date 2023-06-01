<?php session_start();?>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<HEAD>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</HEAD>
<BODY>
<?php
 $user = htmlentities ($_POST['user'], ENT_QUOTES, "UTF-8"); // rozbrojenie potencjalnej bomby w zmiennej $user
 $pass = htmlentities ($_POST['pass'], ENT_QUOTES, "UTF-8"); // rozbrojenie potencjalnej bomby w zmiennej $pass
 $pass2 = htmlentities ($_POST['pass2'], ENT_QUOTES, "UTF-8"); // rozbrojenie potencjalnej bomby w zmiennej $pass2
 $link = mysqli_connect("localhost", "id19668897_buk", "I1A3ahT~98PU\zFB", "id19668897_z1");
 if(!$link) { echo"Błąd: ". mysqli_connect_errno()." ".mysqli_connect_error(); } // obsługa błędu połączenia z BD
 mysqli_query($link, "SET NAMES 'utf8'"); // ustawienie polskich znaków
 $result = mysqli_query($link, "SELECT * FROM users5 WHERE username='$user'"); // wiersza, w którym login=login z formularza
 $rekord = mysqli_fetch_array($result); // wiersza z BD, struktura zmiennej jak w BD 
 if(!$rekord) //Jeśli brak, to nie ma użytkownika o podanym loginie, więc możemy go rejestrować
 {
     if($pass == $pass2){
        $sql = "INSERT INTO users (username, password) VALUES ('$user', '$pass')";
        if (mysqli_query($link , $sql)){
            mysqli_close($link); // zamknięcie połączenia z BD
            $_SESSION ['loggedin5'] = true;
            include('index4.php');
        }
     }else{
         echo "Hasła nie są takie same";
     }
 }
 else
 { // jeśli $rekord istnieje
    echo "Nazwa użytkownika zajęta";
 }
?>
</BODY>
</HTML>