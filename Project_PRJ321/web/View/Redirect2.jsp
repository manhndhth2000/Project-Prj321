<%-- 
    Document   : Redirect
    Created on : Jul 11, 2021, 8:49:12 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var seconds = 2; // seconds for HTML
            var foo; // variable for clearInterval() function

            function redirect() {
                document.location.href = 'DetailController?title=${blog.title}&timePost=${blog.timePost}';
            }

            function updateSecs() {
                document.getElementById("seconds").innerHTML = seconds;
                seconds--;
                if (seconds == -1) {
                    clearInterval(foo);
                    redirect();
                }
            }

            function countdownTimer() {
                foo = setInterval(function () {
                    updateSecs()
                }, 1000);
            }

            countdownTimer();
        </script>
    </head>
    <body>
        <p>
            Processing! Redirect after <strong style="font-size: 200%"><span id="seconds">2</span></strong> seconds.
        </p>
    </body>
</html>
