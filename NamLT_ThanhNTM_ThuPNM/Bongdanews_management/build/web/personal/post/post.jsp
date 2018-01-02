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
        <title>Post</title>
        <style>
            #form-upload { padding: 10px; background: #A5CCFF; border-radius: 5px;}
            #progress { border: 1px solid #ccc; width: 100%; height: 20px; margin-top: 10px;text-align: center;position: relative;display: none;}
            #bar { background: #F39A3A; height: 20px; width: 0px;}
            #percent { position: absolute; left: 50%; top: 0px;}
            .img-responsive{
                width: 90%;
                height: 100% !important;
                margin-left: 5%;
                object-fit: cover;
            }
            .image_cover_responsive{
                z-index: 2;
                overflow: hidden;
                box-sizing: border-box;
                height: 100%;
            }
            .form-upload{
                z-index: 3;
                margin-left: 5%;
                position: absolute;
            }
            .form-upload:hover{
                display: block;
            }
            .btnRemoveTag{
                cursor: pointer;
                margin: 5px;
            }
            .tag{
                font-size: 14px !important;
                float: left;
                margin: 5px;
            }
            .tagHolder{
                overflow: auto;
                margin: 10px;
            }
            .postSymbolImage{
                
            }
            .postSymbolImage:hover form{
                display: block !important;
            }
        </style>
    </head>
    <body>
        <%@include file="../../elements/header.jsp" %>
        <script src="src/js/PostBussiness/tagFunctions.js"></script>
        <script src="src/js/PostBussiness/savePostFunction.js"></script>
        <script src="src/js/jquery.form.min.js"></script>
        <script>
            $("#page_name").text("POST");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="col-sm-12">
                    <div id="posts" class="card">
                        <!--show symbolic image of this post-->
                        <div class="postSymbolImage" style="background: whitesmoke;alignment-baseline: central">
                            <form id="form-upload" method="post" action='upload_image<c:if test="${param.p != null}">/${param.p}</c:if>' enctype="multipart/form-data">
                                    <input type="file" name="file" id="select-file" style="display: none;"/>
                                    <button class="btn" id="tmpbtn">Choose an Images</button>
                                </form>
                                <div id="progress">
                                    <div id="bar"></div>
                                    <div id="percent">0%</div>
                                </div>
                            </div>
                            <div class="body">
                                <h5><strong>Title</strong></h5>
                                <!--Titlte of this post-->
                                <input id="title" type="text" name="#" class="form-control">
                                <br/>
                                <h5><strong>Content</strong></h5>
                                <!--Place to edit content of this post-->
                            <%@include file="../../elements/editor.jsp" %>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="header">
                                <h4><strong>Detail Information</strong></h4>
                            </div>
                            <div class="body">
                                <!--Infomation of this post-->
                                <div id="info">
                                    <h5>Status: Draft</h5>
                                    <h5>Created day: 05/10/2017</h5>
                                </div>
                                <!--action buttons-->
                                <form action="#" method="post" id="savePostForm">
                                    <input type="hidden" value="${sessionScope.INFO.role}" id="role"/>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="header">
                                <h4><strong>Categories</strong></h4>
                            </div>
                            <div class="body row">
                                <div class="col-sm-6">
                                    <form action="SetCategoryOfPost" class="category-list" method="POST">
                                        <select class="form-control show-tick" name="categoryId" id="categoryId">
                                        </select>
                                        <br/>
                                        <input class="btn btn-success" type="submit" value="Update" name="action" />
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <div class="card">
                            <div class="header">
                                <h4><strong>Tags</strong></h4>
                            </div>
                            <div class="body">
                                <h4>Current tags</h4>
                                <div class="tagHolder" id="tagHolder">
                                </div>
                                <form action="#" method="post">
                                    <input type="text" class="form-control" id="newTag"/> <br/>
                                </form>
                            </div>
                        </div></div>
                </div>
            </div>
        </section>
        <script src="src/js/PostBussiness/uploadImage.js"></script>
        <script>
            <c:if test="${not empty sessionScope.INFO}">
                                        var userId = ${sessionScope.INFO.id};
            </c:if>
            <c:if test="${param.p != null}">
                                        var postId = ${param.p};
            </c:if>
                                        $(function () {
                                            $("#tmpbtn").click(function () {
                                                $("#select-file").click();
                                            });
                                            $("#select-file").change(function () {
                                                $("#form-upload").submit();
                                            });
                                            $("#newTag").keydown(function () {
                                                if (event.keyCode == 13) {
                                                    addNewTag();
                                                    event.preventDefault();
                                                }
                                            });
                                        });
        </script>
    </body>
</html>
