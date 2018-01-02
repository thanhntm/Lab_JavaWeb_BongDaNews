<%-- 
    Document   : tag
    Created on : Nov 1, 2017, 10:07:50 PM
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
                    <thanhntm:if test="${requestScope.TAG != null}">
                    <li><a href="#" >${requestScope.TAG}</a></li>
                    </thanhntm:if>
                    <thanhntm:if test="${requestScope.TAG == null}">
                        <thanhntm:forEach items="${requestScope.TAGNAME}" var="tag">
                        <li><a href="Tag?name=${tag.tagName}">${tag.tagName}</a></li>
                        </thanhntm:forEach>
                    </thanhntm:if>
                </ul>
            </section>
        </div>
        <!-- Feature Category Section & sidebar -->
        <section id="feature_category_section" class="feature_category_section category_page section_wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="feature_news_item category_item">
                                    <div class="item">
                                        <div class="item_wrapper">

                                            <div class="item_title_date" style="display:none">
                                                <div class="item_meta"></div>
                                            </div>
                                            <!--item_title_date-->
                                        </div>
                                        <!--item_wrapper-->
                                        <div class="category_list" style="float:left;" id="tagHolder">
                                            <thanhntm:if test="${requestScope.TAG != null}">
                                                <a href="#" >${requestScope.TAG}</a>
                                            </thanhntm:if>
                                            <thanhntm:if test="${requestScope.TAG == null}">
                                                <thanhntm:forEach items="${requestScope.TAGNAME}" var="tag">
                                                    <a href="Tag?name=${tag.tagName}">${tag.tagName}</a>
                                                </thanhntm:forEach>
                                            </thanhntm:if>
                                        </div>
                                        <!--category_list-->

                                    </div>
                                    <!--item-->
                                </div>
                                <!--feature_news_item-->
                            </div>
                            <!--col-md-6-->
                        </div>
                        <div class="row">

                            <thanhntm:if test="${requestScope.LISTPOST != null}">
                                <thanhntm:forEach items="${requestScope.LISTPOST}" var="post" varStatus="counter">

                                    <div class="col-md-6">
                                        <div class="feature_news_item">
                                            <div class="item">
                                                <div class="item_wrapper">
                                                    <div class="item_img">
                                                        <img style="width: 50vw; height: 35vh" class="img-responsive" src="files/${post.symbolicImage}" alt="Chania">
                                                    </div>
                                                    <!--item_img-->
                                                    <div class="item_title_date">
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
                            </thanhntm:if>
                            <thanhntm:if test="${requestScope.LISTPOST == null}">
                                <h1 style="color: white">${requestScope.NOTFOUND}</h1>
                            </thanhntm:if>

                        </div>
                        <ul class="pagination pagination">
                            <thanhntm:if test="${requestScope.TOTAL > 1}">
                                <thanhntm:forEach begin="0" end="${requestScope.TOTAL - 1}" varStatus="counter">
                                    <thanhntm:if test="${requestScope.SEARCH == null}">
                                        <li><a href="Tag?name=${requestScope.TAG}&p=${counter.count}">${counter.count}</a></li>
                                        </thanhntm:if>
                                        <thanhntm:if test="${requestScope.SEARCH != null}">
                                        <li><a href="Tag?searchtxt=${requestScope.SEARCH}&p=${counter.count}">${counter.count}</a></li>
                                        </thanhntm:if>
                                    </thanhntm:forEach>
                                </thanhntm:if>
                        </ul>
                        <!--row-->

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

