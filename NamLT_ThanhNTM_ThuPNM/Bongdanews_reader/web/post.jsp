<%-- 
    Document   : post
    Created on : Nov 1, 2017, 10:07:23 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <style>
            .comment-form{
                padding: 15px;
                overflow: auto;
            }
            .comment-form input{
                border: 1px solid #000;
                border-radius: 5px; 
                color: #000;
            }
            .comment-form textarea{
                border: 1px solid #000;
                border-radius: 5px;
                color: #000;
            }
            .commentTime{
                color: #000;
            }
            .media{
                border-top: 1px #000 solid !important;
            }
            .media-body .media{
                margin-left: 40px;
                padding: 0px !important;
                border:1px solid whitesmoke;
            }
            .media-body .media-left{
                padding-right: 30px !important;
            }
        </style>
    </head>

    <body id="page-top" data-spy="scroll" data-target=".navbar">
        <!-- Header Section -->
        <%@include file="elements/header.jsp" %>

        <!-- Feature Carousel Section -->
        <div class="container">
            <section class="section_wrapper">
                <ul class="breadcrumb" style="background: rgba(111, 147, 202, 0);">
                    <li><a href="Home">Home</a></li>
                    <li><a href="Home" id="categoryBreadCrumbItem">Category</a></li>
                    <li><a href="Home" id="postBreadCrumbItem">Post</a></li>
                </ul>
            </section>
        </div>
        <!-- Feature Category Section & sidebar -->
        <section id="feature_category_section" class="feature_category_section single-page section_wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <div class="single_content_layout">
                            <div class="item feature_news_item">
                                <div class="item_img">
                                    <img class="img-responsive" id="symbolImage" src="">
                                </div>
                                <!--item_img-->
                                <div class="item_wrapper">
                                    <div class="news_item_title">
                                        <h2><a href="#" id="title"></a></h2>
                                    </div>
                                    <!--news_item_title-->
                                    <div class="item_meta" id="postMeta"></div>

                                    <div class="item_content" id="contentHolder">
                                        <p>Not found this content!</p>
                                    </div>
                                    <!--item_content-->
                                    <div class="category_list" id="tagHolder">
                                        <c:forEach items="${requestScope.TAGNAME}" var="tag">
                                            <a href="Tag?name=${tag.tagName}">${tag.tagName}</a>
                                        </c:forEach>
                                    </div>
                                    <!--category_list-->
                                </div>
                                <!--item_wrapper-->
                            </div>
                            <!--feature_news_item-->

                            

                            <!--readers_comment-->
                            <div class="readers_comment">
                                <div class="single_media_title">
                                    <h2>Comments</h2>
                                </div>
                                <div id="commentHolder">
                                </div>
                                <button class="btn btn-primary" id="addCommentButton">Write a comment</button>
                            </div>
                            <!--readers_comment-->
                        </div>
                    </div>

                    <div class="col-md-3">
                        <%@include file="elements/siteBar.jsp" %>
                    </div>
                </div>
            </div>
        </section>
        <!--feature_category_section-->
        <c:if test="${not empty param.p}">
            <script src="src/js/GetPostFunctions.js"></script>
            <script src="src/js/CommentFunctions.js"></script>
            <script>
                var postId = <c:if test="${not empty param.p}">${param.p}</c:if>;
                $(function () {
                    getPost(postId);
                    loadAllComment(postId);
                    $("#addCommentButton").click(function () {
                        showCommentPopup(-1);
                    });
                })
                </script>
        </c:if>
        <!-- Footer Section -->
        <%@include file="elements/footer.jsp" %>
    </body>

</html>

