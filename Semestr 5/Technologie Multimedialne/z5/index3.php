<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
    <title>Zabłocki</title>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<BODY>
Formularz logowania
<form method="post" action="weryfikuj3.php">
 Login:<input type="text" name="user" maxlength="20" size="20" autocomplete="off"><br>
 Hasło:<input type="password" name="pass" maxlength="20" size="20"><br>
 <input id="klik" type="submit" value="Send"/>
</form>
<a href="rejestracja.php">Rejestracja</a> <br>
<a href="index.php">Powrót na stronę główną</a><br>
<script>
    $(document).ready(function(){
        $("#klik").click(function(){
        let res = screen.width + " x " + screen.height;
        let cscreen = window.innerWidth + " x " + window.innerHeight;
        let colors = screen.colorDepth;
        let cookies = navigator.cookieEnabled;
        let java = navigator.javaEnabled();
        let lang = navigator.language;
                  $.ajax({
                    url:'test.php',
                    method:'POST',
                    data:{
                        res:res,
                        cscreen:cscreen,
                        colors:colors,
                        cookies:cookies,
                        java:java,
                        lang:lang
                    },
                  success:function(data){
                      //alert(data);
                  }
                });
            });
    });
</script>
</BODY>
</HTML>