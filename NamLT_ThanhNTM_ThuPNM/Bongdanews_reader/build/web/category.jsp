<%-- 
    Document   : category
    Created on : Nov 1, 2017, 10:06:46 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="thanhntm" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <style>
            
            #paragraph {
                white-space: nowrap; 
                width: 25vw; 
                overflow: hidden;
                text-overflow: ellipsis; 
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
                        <thanhntm:if test="${not empty param.p}">
                        <li><a href="Category?p=${param.p}">${requestScope.TAGNAME}</a></li>
                        </thanhntm:if>
                </ul>
            </section>
        </div>
        <!-- Feature Category Section & sidebar -->
        <section id="feature_category_section" class="feature_category_section category_page section_wrapper">
            <div class="container">
                <div class="row">

                    <div class="col-md-9">
                        <thanhntm:if test="${requestScope.LISTPOST.size() > 0}" >
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="feature_news_item category_item">
                                        <div class="item">
                                            <div class="item_wrapper">
                                                <div class="item_img">
                                                    <img  class="img-responsive" src="files/${requestScope.LISTPOST.get(0).symbolicImage}" alt="Chania">
                                                </div>
                                                <!--item_img-->
                                                <div class="item_title_date">
                                                    <div class="news_item_title">
                                                        <h1><a href="Post?p=${requestScope.LISTPOST.get(0).id}">${requestScope.LISTPOST.get(0).title}</a></h1>
                                                    </div>
                                                    <!--news_item_title-->
                                                    <div class="item_meta"><a href="#">${requestScope.LISTPOST.get(0).createdDay}</a></div>
                                                </div>
                                                <!--item_title_date-->
                                            </div>
                                            <!--item_wrapper-->
                                            <div class="item_content">
                                                ${requestScope.LISTPOST.get(0).openingParagraph}
                                            </div>
                                            <!--item_content-->

                                        </div>
                                        <!--item-->
                                    </div>
                                    <!--feature_news_item-->
                                </div>
                                <!--col-md-6-->
                            </div>
                            <div class="row">
                                <thanhntm:forEach begin="1" var="post" items="${requestScope.LISTPOST}">
                                    <div class="col-md-6">
                                        <div class="feature_news_item">
                                            <div class="item">
                                                <div class="item_wrapper">
                                                    <div class="item_img">
                                                        <img style="width: 50vw; height: 35vh" class="img-responsive" src="files/${post.symbolicImage}" alt="Chania">
                                                    </div>
                                                    <!--item_img-->
                                                    <div  class="item_title_date">
                                                        <div class="news_item_title">
                                                            <h2><a href="Post?p=${post.id}">${post.title}</a></h2>
                                                        </div>
                                                        <div class="item_meta"><a href="#">${post.createdDay}</a></div>
                                                    </div>
                                                    <!--item_title_date-->
                                                </div>
                                                <!--item_wrapper-->
                                                <div id="paragraph" class="item_content">
                                                    ${post.openingParagraph}
                                                </div>

                                            </div>
                                            <!--item-->
                                        </div>
                                        <!--feature_news_item-->
                                    </div>
                                    <!--col-md-6-->
                                </thanhntm:forEach>


                            </div>
                            <!--row-->
                            <thanhntm:if test="${requestScope.TOTAL > 0}">
                                <ul class="pagination pagination">
                                    <thanhntm:forEach begin="0" end="${requestScope.TOTAL - 1}" varStatus="counter">
                                        <li><a href="Category?p=${requestScope.ID}&n=${counter.count}">${counter.count}</a></li>
                                    </thanhntm:forEach>
                                </ul>
                            </thanhntm:if>
                        </thanhntm:if>
                    </div>

                    <!--col-md-9-->

                    <div class="col-md-3">
                        <%@include file="elements/siteBar.jsp" %>
                        <!--tab-->
                    </div>
                </div>
            </div>
        </section>
        <!--feature_category_section-->

        <!-- Footer Section -->
        <%@include file="elements/footer.jsp" %>
    </body>

</html>

