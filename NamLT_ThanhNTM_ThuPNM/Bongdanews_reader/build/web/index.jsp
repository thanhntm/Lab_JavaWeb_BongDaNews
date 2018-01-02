
<%@ taglib prefix="thanhntm" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">

        <style>
            #ExtendSentence{
                width: 100%;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
            }

            #ExtendParagraph {
                white-space: nowrap; 
                width: 200px; 
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
                </ul>
            </section>
        </div>
        <!-- Feature Category Section & sidebar -->
        <section id="feature_category_section" class="feature_category_section section_wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <thanhntm:if test="${requestScope.LISTPOST != null}">
                            <thanhntm:forEach items="${requestScope.LISTPOST}" var="list">
                                <thanhntm:if test="${fn:length(list.get(1)) gt 0}">
                                    <div class="category_layout">
                                        <div class="item_caregory red">
                                            <h2><a href="Category?p=${list.get(0).id}">${list.get(0).categoryName}</a></h2>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-7">
                                                <div class="item feature_news_item">
                                                    <div class="item_wrapper">
                                                        <div class="item_img">
                                                            <img class="img-responsive" src="files/${list.get(1).get(0).symbolicImage}" alt="Chania">
                                                        </div>
                                                        <div class="item_title_date">
                                                            <div class="news_item_title">
                                                                <h2><a href="Post?p=${list.get(1).get(0).id}">${list.get(1).get(0).title}</a></h2>
                                                            </div>
                                                            <div class="item_meta"><a href="#">${list.get(1).get(0).createdDay}</a></div>
                                                        </div>
                                                    </div>
                                                    <div class="item_content">
                                                        ${list.get(1).get(0).openingParagraph}


                                                    </div>

                                                </div>
                                            </div>

                                            <div class="col-md-5">
                                                <div class="media_wrapper">
                                                    <thanhntm:forEach items="${list.get(1)}" begin="1"  varStatus="counter">
                                                        <div class="media">
                                                            <div class="media-left">
                                                                <a href="#"><img style="width: 80px; height: 80px" class="media-object" src="files/${list.get(1).get(counter.count).symbolicImage}" alt="Generic placeholder image"></a>
                                                            </div>
                                                            <div class="media-body">
                                                                <h3 class="media-heading"><a href="Post?p=${list.get(1).get(counter.count).id}">${list.get(1).get(counter.count).title}</a></h3>
                                                                <div id="ExtendParagraph">
                                                                    <p id="ExtendSentence">${list.get(1).get(counter.count).openingParagraph}</p>

                                                                </div>        

                                                            </div>

                                                        </div>

                                                    </thanhntm:forEach>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <!--category_layout-->
                                </thanhntm:if>
                            </thanhntm:forEach>
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
        <%@include file="elements/footer.jsp" %>
    </body>
</html>
