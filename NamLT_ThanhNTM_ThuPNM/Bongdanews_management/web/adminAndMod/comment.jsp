<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.fullpage eq 'true'}">
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Comments</title>

        </head>
        <body>
            <%@include file="../elements/header.jsp" %>
            <link href="src/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet">
            <link href="src/css/CommentStyles.css" type="text/css" rel="stylesheet">
            <script src="src/js/PostBussiness/CommentFunctions/CommentFunctions.js"></script>
            <script>
                $("#page_name").text("USER INFO");
            </script>
            <section class="content">
                <div class="container-fluid">
                </c:if>
                <div class="readers_comment">
                    <div class="single_media_title">
                        <h2>Comments</h2>
                    </div>
                    <div id="commentHolder" class="commentHolder maxScreenLength">
                    </div>
                </div>
                <script>
                    $(function () {
                        var postId = <c:if test="${not empty param.p}">${param.p}</c:if>;
                        loadAllComment(postId);
                    });
                    </script>
                <c:if test="${param.fullpage eq 'true'}">
                </div>
            </section>
        </body>
    </html>
</c:if>
