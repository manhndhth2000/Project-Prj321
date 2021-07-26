<%-- 
    Document   : ForgetPassword
    Created on : Jun 13, 2021, 8:58:33 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forget Password Page</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/blog-home.css" rel="stylesheet">
        <style>
            body{
                background-image: linear-gradient(45deg,rgba(35, 43, 38, 0.8), 60%,rgba(51, 59, 56, 0.85)),url(../image/anh-bia-dong-ho-18.jpg);
            }
            form {
                background-color: white;
                box-sizing: border-box;
                width: 360px;
                margin: 100px auto 0;
                box-shadow: 2px 2px 5px 3px rgba(0, 0, 0, 0.3);
                padding-bottom: 40px;
                border-radius: 5px;
            }
            form h1 {
                text-align: center;
                box-sizing: border-box;
                padding: 20px;
                color: #1abc9c;
            }
            form p a{
                text-decoration: none;
            }
            input[type="text"],input[type="password"],input[type="email"] {
                margin: 40px 25px;
                width: 90%;
                display: block;
                border: none;
                padding: 10px 0;
                border-bottom: solid 1px #1abc9c;
                transition: all 0.3s cubic-bezier(0.64, 0.09, 0.08, 1);
                background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 96%, #1abc9c 4%);
                background-position: -200px 0;
                background-size: 200px 100%;
                background-repeat: no-repeat;
                color: #0e6252;
            }
            input:focus, input:valid {
                box-shadow: none;
                outline: none;
                background-position: 0 0;
            }
            input:focus::-webkit-input-placeholder, input:valid::-webkit-input-placeholder {
                color: #1abc9c;
                font-size: 11px;
                transform: translateY(-20px);
                visibility: visible !important;
            }

            input[type="submit"] {
                border: none;
                background: #1abc9c;
                cursor: pointer;
                border-radius: 3px;
                padding: 6px;
                width: 200px;
                color: white;
                margin-left: 80px;
                box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.2);
            }
            input[type="submit"]:hover {
                transform: translateY(-3px);
                box-shadow: 0 6px 6px 0 rgba(0, 0, 0, 0.2);
            }
            .login{
                font-family:arial,helvetica,sans-serif;
                margin-left: 20px;
                float: left;
            }
            .register{
                font-family:arial,helvetica,sans-serif;
                float: left;
                margin-left: 30px;
            }
        </style>
        <base href="${pageContext.servletContext.contextPath}/">
    </head>
    <body>
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="View/Home.jsp">Home</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="View/About.jsp">About</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="body">
            <span style="font-size:12px;">
                <span style="font-family:arial,helvetica,sans-serif;">
                    <form>
                        <h1 style="font-size:250%">RESET PASSWORD</h1>
                        <input placeholder="Username" type="text" required="">
                        <input placeholder="Email" type="email" required="">
                        <input placeholder="New-password" type="password" required="">
                        <input placeholder="Re-password" type="password" required="">
                        <p class="login">Have account?<a href="View/Login.jsp">Login here</a></p>
                        <p class="register">New user?<a href="View/Register.jsp">Register here</a></p>
                        <br><br><input type="submit" name="CONFIRM" value="CONFIRM">
                    </form>
                </span>
            </span>
        </div>
    </body>
</html>
