<%-- 
    Document   : header
    Created on : Oct 15, 2017, 9:17:06 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="thanhntm" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <link href="src/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="src/js/jquery.js"></script>
    <script src="src/bootstrap/js/bootstrap.min.js"></script>

    <link href="src/plugins/materialize-css/css/materialize.min.css"/>
    <!-- Waves Effect Css -->
    <link href="src/plugins/node-waves/waves.css" rel="stylesheet" type="text/css"/>

    <!-- Animation Css -->
    <link href="src/plugins/animate-css/animate.css" rel="stylesheet" type="text/css"/>

    <!-- Custom Css -->
    <link href="src/css/style.css" rel="stylesheet" type="text/css">

    <!-- AdminBSB Themes. You can choose a theme from css/themes instead of get all themes -->
    <link href="src/css/themes/all-themes.css" rel="stylesheet" />

    <!-- Slimscroll Plugin Js -->
    <script src="src/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

    <!-- Waves Effect Plugin Js -->
    <script src="src/plugins/node-waves/waves.js"></script>

    <!-- Jquery CountTo Plugin Js -->
    <script src="src/plugins/jquery-countto/jquery.countTo.js"></script>

    <!-- Custom Js -->
    <script src="src/js/admin.js"></script>
    <script src="src/js/pages/index.js"></script>
    <script src="src/js/PostBussiness/createPost.js"></script>

    <!-- Demo Js -->
    <script src="src/js/demo.js"></script>

</head>

<body class="theme-red">
    <!-- Page Loader -->
    <div class="page-loader-wrapper">
        <div class="loader">
            <div class="preloader">
                <div class="spinner-layer pl-red">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
            <p>Please wait...</p>
        </div>
    </div>
    <!-- #END# Page Loader -->
    <!-- Overlay For Sidebars -->
    <div class="overlay"></div>
    <!-- #END# Overlay For Sidebars -->
    <!-- Top Bar -->
    <nav class="navbar">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
                <a href="javascript:void(0);" class="bars"></a>
                <a class="navbar-brand" href="#" id="page_name">MANAGE PAGE</a>
            </div>
        </div>
    </nav>
    <!-- #Top Bar -->
    <section>
        <!-- Left Sidebar -->
        <aside id="leftsidebar" class="sidebar">
            <!-- User Info -->
            <div class="user-info">
                <div class="image">
                    <img src="src/images/baby-groot-figurine-640x533.jpg" width="48" height="48" alt="User" />
                </div>
                <div class="info-container">
                    <div class="name" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ${sessionScope.INFO.name}
                    </div>
                    <div class="email">
                        ${sessionScope.INFO.mail}
                    </div>
                    <div class="btn-group user-helper-dropdown">
                        <i class="material-icons" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">keyboard_arrow_down</i>
                        <ul class="dropdown-menu pull-right">
                            <li>
                                <thanhntm:url var="ProfileLink" value="ShowProfileController">
                                    <thanhntm:param name="action" value="ShowProfile"/>
                                </thanhntm:url>
                                <a href="${ProfileLink}"><i class="material-icons">person</i>Profile</a>
                            </li>
                            <li role="seperator" class="divider"></li>
                            <li>
                                <thanhntm:url var="LogoutLink" value="LogoutController">
                                    <thanhntm:param name="action" value="Logout"/>
                                </thanhntm:url>
                                <a href="${LogoutLink}" id="Logout"><i class="material-icons">input</i>Sign Out</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- #User Info -->
            <!-- Menu -->
            <div class="menu">
                <ul class="list">
                    <li class="header">MAIN NAVIGATION</li>

                    <li>
                        <a href="javascript:void(0);" class="menu-toggle">
                            <i class="material-icons">content_copy</i>
                            <span>Posts</span>
                        </a>
                        <ul class="ml-menu">
                            <thanhntm:if test="${sessionScope.INFO.role == 0 || sessionScope.INFO.role == 1}">
                                <li>
                                    <a href="AllPost">All posts</a>
                                </li>
                            </thanhntm:if>
                            <li>
                                <a href="MyPost">My posts</a>
                            </li>
                            <li>
                                <a id="addNewPost">Add new post</a>
                                <script>$(function () {
                                        $("#addNewPost").click(function () {
                                            confirmCreate();
                                        });
                                    });
                                </script>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:void(0);" class="menu-toggle">
                            <i class="material-icons">assignment</i>
                            <span>Category</span>
                        </a>
                        <ul class="ml-menu">
                            <thanhntm:if test="${sessionScope.INFO.role == 0 || sessionScope.INFO.role == 1}">
                                <li>
                                    <a href="Categories">Categories</a>
                                </li>
                            </thanhntm:if>
                            <thanhntm:if test="${sessionScope.INFO.role == 0}">
                                <li>
                                    <a href="MenuCategories">Category Order</a>
                                </li>
                            </thanhntm:if>
                        </ul>
                    </li>


                    <thanhntm:if test="${sessionScope.INFO.role == 0 || sessionScope.INFO.role == 1}">
                        <li>
                            <a href="AllMedia">
                                <i class="material-icons">perm_media</i>
                                <span>Media</span>
                            </a>
                        </li>
                    </thanhntm:if>

                    <thanhntm:if test="${sessionScope.INFO.role == 0}">
                        <li>
                            <a href="javascript:void(0);" class="menu-toggle">
                                <i class="material-icons">widgets</i>
                                <span>Manage users</span>
                            </a>
                            <ul class="ml-menu">
                                <li>
                                    <a href="AllUser">All users</a>
                                </li>
                                <li>
                                    <a href="AddNewUser">Add new user</a>
                                </li>
                            </ul>
                        </li>
                    </thanhntm:if>

                    <li>
                        <a href="javascript:void(0);" class="menu-toggle">
                            <i class="material-icons">widgets</i>
                            <span>My Account</span>
                        </a>
                        <ul class="ml-menu">
                            <li>
                                <a href="UpdatePersonalAccount">Update personal info</a>
                            </li>
                            <li>
                                <a href="ChangePassword">Change password</a>
                            </li>
                        </ul>
                    </li>
                    <li class="active" style="display: none;">

                    </li>
                </ul>
            </div>
            <!-- #Menu -->
            <!-- Footer -->
            <div class="legal">

            </div>
            <!-- #Footer -->
        </aside>
        <!-- #END# Left Sidebar -->
        <!-- Right Sidebar -->

        <!-- #END# Right Sidebar -->
    </section>
    <div id="modal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content" id="modalContent">
            </div>

        </div>
    </div>
</body>
