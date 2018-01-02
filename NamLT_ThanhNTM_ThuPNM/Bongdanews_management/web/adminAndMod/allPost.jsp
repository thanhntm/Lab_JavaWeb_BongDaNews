<%-- 
    Document   : addNewPost
    Created on : Oct 15, 2017, 10:20:57 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All posts</title>
        <link href="src/plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">

    </head>
    <body>
        <%@include file="../elements/header.jsp" %>
        <!-- Jquery DataTable Plugin Js -->
        <script src="src/plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="src/plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.min.js"></script>
        <!-- Custom Js -->
        <script src="src/js/PostBussiness/getPostFunction.js"></script>
        <!--Needed sources for comment functions-->
        <link href="src/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet">
        <link href="src/css/CommentStyles.css" type="text/css" rel="stylesheet">
        <script src="src/js/PostBussiness/CommentFunctions/CommentFunctions.js"></script>
        <script>
            $("#page_name").text("ALL POST");
        </script>
        <section class="content">
            <div class="container-fluid">
                
                <div class="col-sm-12">
                    <div class="card">
                        <div class="body" style="background: #FFF">
                            <div class="table-responsive" id="dataHolder">
                                <script>$(getAllPost)</script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
