
<%@page import="DTO.User"%>
<%@page import="DAO.UserDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.fullpage eq 'true'}">

    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>User Info</title>

        </head>
        <body>
            <%@include file="../../elements/header.jsp" %>
            <script>
                $("#page_name").text("USER INFO");
            </script>
            <section class="content">
                <div class="container-fluid">
                </c:if>
                <%
                    UserDAO dao = new UserDAO();
                    String userId = request.getParameter("user");
                    User user = dao.getProfile(Integer.parseInt(userId));
                    pageContext.setAttribute("user", user);
                %>
                <c:if test="${not empty user}">
                    <div style="padding: 10px;margin: 20px;">
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td><strong>Email:</strong></td>
                                    <td><c:out value="${user.mail}"/></td>
                                </tr>
                                <tr>
                                    <td><strong>Name:</strong></td>
                                    <td><c:out value="${user.name}"/></td>
                                </tr>
                                <tr>
                                    <td><strong>Sex:</strong></td>
                                    <td><c:out value="${user.sex}"/></td>
                                </tr>
                                <tr>
                                    <td><strong>Description:</strong></td>
                                    <td><c:out value="${user.description}"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${param.fullpage eq 'true'}">
                </div>
            </section>
        </body>
    </html>
</c:if>
