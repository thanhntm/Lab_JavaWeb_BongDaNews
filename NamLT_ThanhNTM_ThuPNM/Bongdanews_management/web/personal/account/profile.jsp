
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="thanhntm" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All posts</title>

    </head>
    <body>
        <%@include file="../../elements/header.jsp" %>
        <script>
            $("#page_name").text("PERSONAL PROFILE");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>Profile <strong style="color: red">${requestScope.ERROR}</strong> </h2>
                            </div>
                            <div class="body">
                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Email: ${PROFILE.mail}
                                    </div>
                                </div>

                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Name: ${PROFILE.name}
                                    </div>
                                </div>

                                <div class="form-group form-float">
                                    <div class="form-line">
                                        <fmt:formatDate pattern="dd-MM-yyyy" var="dob" value="${PROFILE.birthday}" type="date"/>
                                        Birthday: ${dob}
                                    </div>
                                </div>

                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Gender: ${PROFILE.sex}
                                    </div>
                                </div>

                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Address: ${PROFILE.address}
                                    </div>
                                </div>

                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Phone: ${PROFILE.phone}
                                    </div>
                                </div>
                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Identity card: ${PROFILE.identityCard}
                                    </div>
                                </div>
                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Description: ${PROFILE.description}
                                    </div>
                                </div>
                                <div class="form-group form-float">
                                    <div class="form-line">
                                        Role: <thanhntm:if test="${PROFILE.role == 0}">Administrator</thanhntm:if>
                                        <thanhntm:if test="${PROFILE.role == 1}">Moderator</thanhntm:if>
                                        <thanhntm:if test="${PROFILE.role == 2}">Collaborator</thanhntm:if>
                                    </div>
                                </div>
                                <a href="UpdatePersonalAccount">Update profile</a> | 
                                <a href="ChangePassword">Change password</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
