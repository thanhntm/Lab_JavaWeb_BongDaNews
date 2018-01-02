
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Media</title>
        <link href="src/plugins/light-gallery/css/lightgallery.min.css" rel="stylesheet">
        <link href="src/plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="src/font-awesome/css/font-awesome.css" type="text/css" rel="stylesheet">
        <style>
            @media (min-width: 768px){
                /*sm*/
                .image_cover_responsive{
                    height: 400px;}
            }
            @media (min-width: 992px){
                /*md*/
                .image_cover_responsive{
                    height: 250px;}
            }
            @media (min-width: 1200px){
                /*lg*/
                .image_cover_responsive{
                    height: 200px;
                }
            }
            .img-responsive{
                width: 100%;
                height: 100% !important;
                object-fit: cover;
            }
            .image_cover_responsive{
                overflow: hidden;
                box-sizing: border-box;
            }
            .folderTable{
                
            }
        </style>
    </head>
    <body>
        <%@include file="../elements/header.jsp" %>
        <!--Gallery Plugins-->
        <script src="src/plugins/light-gallery/js/lightgallery-all.min.js"></script>
        <!-- Jquery DataTable Plugin Js -->
        <script src="src/plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="src/plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.min.js"></script>
        <!--Custom Js-->
        <script src="src/js/MediaBussiness/getMediaFunction.js"></script>
        <script>
            $("#page_name").text("IMAGE GALLERY");
        </script>
        <section class="content">
            <div class="container-fluid">

                <!--File gallery-->
                <div class="card" id="folderCard">
                    <div class="header">
                        <h2>Folders in system</h2>
                    </div>
                    <div class="body" style="background: #FFF">
                        <div class="table-responsive" id="folderHolder">
                            <script>$(getAllFolder)</script>
                        </div>
                    </div>
                </div>

                <!-- Image Gallery -->
                <div class="row" id="imageCard" style="display: none;">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <div class="row">
                                    <div class="col-xs-2 col-sm-1">
                                        <i class="fa fa-arrow-left fa-2" style="cursor: pointer" aria-hidden="true" onclick="showFolderCard();"></i>
                                    </div>
                                    <div class="col-xs-10 col-sm-6">
                                        <h2 id="folderTitle"></h2>
                                    </div>
                                </div>
                            </div>
                            <div class="body">
                                <div id="imageHolder" class="list-unstyled row clearfix">
                                    <script>
                                        <c:if test="${not empty param.p}">
                                        $(function () {
                                            loadImage(<c:if test="${not empty param.p}">${param.p}</c:if>);
                                        });
                                        </c:if>
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
