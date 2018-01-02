<%-- 
    Document   : header
    Created on : Nov 1, 2017, 10:24:12 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bóng đá News</title>
<!-- Goole Fonts -->
<link href="https://fonts.googleapis.com/css?family=Oswald:400,700|Roboto:400,500" rel="stylesheet">

<!-- Bootstrap -->
<link href="src/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome -->
<link href="src/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- Owl carousel -->
<link href="src/css/owl.carousel.css" rel="stylesheet">
<link href="src/css/owl.theme.default.min.css" rel="stylesheet">

<!-- Off Canvas Menu -->
<link href="src/css/offcanvas.min.css" rel="stylesheet">

<!--Theme CSS -->
<link href="src/css/style.css" rel="stylesheet">
<!--Jquery-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<style>
    .dropdown:hover>ul{
        display: block;
    }
</style>

<div class="divhinhnen"> </div>
<div id="main-wrapper"></div>

<!-- Header -->
<header style="position:relative">
    <div class="container">
        <div class="top_ber">
            <div class="row">
                <div class="col-md-6">
                </div>
                <!--col-md-6-->
                <div class="col-md-6">
                    <div class="top_ber_right">
                    </div>
                    <!--top_ber_left-->
                </div>
                <!--col-md-6-->
            </div>
            <!--row-->
        </div>
        <!--top_ber-->

        <div class="header-section">
            <div class="row">
                <div class="col-md-3">
                    <div class="logo">
                        <a href="Home"><img class="img-responsive" src="src/img/logo.png" alt=""></a>
                    </div>
                    <!--logo-->
                </div>
                <!--col-md-3-->
                <div class="col-md-6">
                </div>
                <!--col-md-6-->

            </div>
            <!--row-->
        </div>
        <!--header-section-->
    </div>
    <!-- /.container -->

    <nav class="navbar main-menu navbar-inverse navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed pull-left" data-toggle="offcanvas">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div id="navbar" class="collapse navbar-collapse sidebar-offcanvas">
                <div class="pull-right">
                    <form class="navbar-form" role="search" action="Tag">
                        <div class="input-group">
                            <input class="form-control" placeholder="Search" name="searchtxt" type="text" value="${requestScope.SEARCH}">
                            <button class="btn btn-default" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </nav>
    <!-- .navbar -->
</header>
<script src="src/js/jquery.min.js"></script>
<script src="src/js/MenuCategoryFunctions.js"></script>
<script>
    $(getCategoryMenu);
</script>
<div id="modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content" id="modal-content">
        </div>
    </div>
</div>
