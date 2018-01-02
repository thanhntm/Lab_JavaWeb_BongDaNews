<%-- 
    Document   : siteBar
    Created on : Nov 1, 2017, 10:45:21 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="thanhntm" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="tab sitebar">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#1" data-toggle="tab">Latest</a></li>
        <li><a href="#2" data-toggle="tab">Popular</a></li>
    </ul>


    <div class="tab-content">
        <div class="tab-pane active" id="1">
            <thanhntm:if test="${requestScope.LATEST != null}">
                <thanhntm:forEach items="${requestScope.LATEST}" var="post">
                    <div class="media">
                        <div class="media-left">
                            <a href="Post?p=${post.id}"><img style="width: 80px; height: 80px" class="media-object" src="files/${post.symbolicImage}" alt="Generic placeholder image"></a>
                        </div>
                        <!--media-left-->
                        <div class="media-body">
                            <h4 class="media-heading"><a href="Post?p=${post.id}">${post.title}</a></h4>
                        </div>
                        <!--media-body-->
                    </div>
                    <!--media-->
                </thanhntm:forEach>
            </thanhntm:if>
        </div>
        <!--tab-pane-->

        <div class="tab-pane" id="2">
            <thanhntm:if test="${requestScope.POPULAR != null}">
                <thanhntm:forEach items="${requestScope.POPULAR}" var="post">
                    <div class="media">
                        <div class="media-left">
                            <a href="Post?p=${post.id}"><img style="width: 80px; height: 80px" class="media-object" src="files/${post.symbolicImage}" alt="Generic placeholder image"></a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading"><a href="Post?p=${post.id}">${post.title}</a></h4>
                        </div>
                        <!--media-body-->
                    </div>
                    <!--media-->
                </thanhntm:forEach>
            </thanhntm:if>
        </div>
        <!--tab-pane-->
    </div>
    <!--tab-content-->
</div>
