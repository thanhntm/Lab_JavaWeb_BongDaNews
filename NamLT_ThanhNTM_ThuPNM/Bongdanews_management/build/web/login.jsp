<%-- 
    Document   : login
    Created on : Oct 15, 2017, 10:16:49 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="thanhntm" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Favicon-->
        <link rel="icon" href="src/plugins/favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="src/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="src/plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="src/plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="src/css/style.css" rel="stylesheet">
    </head>
    <body class="login-page">

        <div class="login-box">
            <div class="logo">
                <a href="#">Login Page</a>
            </div>
            <div class="card">
                <div class="body">
                    <form action="LoginController" id="sign_in" method="POST">
                        <div class="msg">Log in to start your session</div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">person</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons">lock</i>
                            </span>
                            <div class="form-line">
                                <input type="password" class="form-control" name="password" placeholder="Password" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 p-t-5">
                            </div>
                            <div class="col-xs-4">
                                <button class="btn btn-block bg-pink waves-effect" type="submit">LOG IN</button>
                            </div>
                        </div>
                    </form>
                    <thanhntm:if test="${param.ERRORLOGIN != null}">
                        <span style="color: red;">${param.ERRORLOGIN}</span>
                    </thanhntm:if>
                </div>
            </div>
        </div>

        <!-- Jquery Core Js -->
        <script src="src/js/jquery.js"></script>

        <!-- Bootstrap Core Js -->
        <script src="src/bootstrap/js/bootstrap.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="src/plugins/node-waves/waves.js"></script>

        <!-- Validation Plugin Js -->
        <script src="src/plugins/jquery-validation/jquery.validate.js"></script>

        <!-- Custom Js -->
        <script src="src/js/admin.js"></script>
        <script src="src/js/pages/examples/sign-in.js"></script>

    </body>
</html>
