<%-- 
    Document   : addNewPost
    Created on : Oct 15, 2017, 10:20:57 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Category</title>
        <link href="src/plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@include file="../elements/header.jsp" %>
        <!-- Jquery DataTable Plugin Js -->
        <script src="src/plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="src/plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.min.js"></script>
        <!-- Custom Js -->
        <script src="src/js/CategoryBussiness/CRUDCategoryFunctions.js"></script>
        <script>
            $("#page_name").text("CATEGORIES");
        </script>
        <section class="content">
            <div class="container-fluid">
                <c:if test="${sessionScope.INFO.role == 0}">
                    <div class="col-xs-12 col-sm-2" style="padding: 0px;">
                        <div class="card">
                            <div class="header"><h4>Add New</h4></div>
                            <div class="body">
                                <form action="CreateCategory" method="POST" id="createCategoryForm">
                                    <span>*Name:</span> <br/>
                                    <input class="form-control" type="text" name="name" required="true"/> <br/>
                                    <span>Description:</span> <br/>
                                    <textarea class="form-control" name="description"></textarea><br/>
                                    <button type="button" class="btn btn-success" id="AddNewCategoryButton">Add</button>
                                    <script>$(function () {
                                            $("#AddNewCategoryButton").click(function () {
                                                createNewCategory("createCategoryForm");
                                            });
                                        })</script>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>
                <div class="col-xs-12 <c:if test="${sessionScope.INFO.role == 0}">col-sm-10</c:if>">
                    <div class="card">
                        <div class="header">
                            <div class="row clearfix">
                                <div class="col-xs-12 col-sm-6">
                                    <h4>All Category in System</h4>
                                </div>
                            </div>
                            <ul class="header-dropdown m-r--5">
                                <li class="dropdown">
                                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                                        <i class="material-icons">more_vert</i>
                                    </a>
                                    <ul class="dropdown-menu pull-right">
                                        <li><a href="javascript:void(0);" class=" waves-effect waves-block" onclick="refreshCategoryData()">Refresh</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="body" style="background: white;">
                            <div class="table-responsive" id="dataHolder">
                                <script>$(getAllCategory)</script>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>

    </body>
</html>
