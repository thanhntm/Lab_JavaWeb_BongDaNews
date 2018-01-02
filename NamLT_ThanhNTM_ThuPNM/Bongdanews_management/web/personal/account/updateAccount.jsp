
<%@page import="DTO.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All posts</title>

    </head>
    <body>
        <%@include file="../../elements/header.jsp" %>
        <script>
            $("#page_name").text("UPDATE ACCOUNT INFO");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>Update Account Information</h2>
                            </div>
                            <div class="body">
                                <form id="form_advanced_validation" action="UpdatePersonalAccountController" method="POST">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input class="form-control" name="email" readonly="true" value="${UPDATEPROFILE.mail}"/>
                                            <label class="form-label">Email</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" class="form-control" name="name" required value="${UPDATEPROFILE.name}"
                                                   pattern="[a-zA-Z]+([a-zA-Z\s]+)*$" title="Your name must be aaa or aa ab"/>
                                            <label class="form-label">Name</label>
                                        </div>
                                        <div class="help-info">Enter name</div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <fmt:formatDate pattern="yyyy-MM-dd" type="date" value="${UPDATEPROFILE.birthday}" var="dob"/>
                                            <input type="date" class="form-control" name="birthday" required value="${dob}"/>
                                            <label class="form-label">Birthday</label>
                                        </div>
                                        <div class="help-info">MM-dd-yyyy</div>
                                        <strong><font color="red">${requestScope.errorDob}</font></strong>
                                    </div>

                                    <div class="form-group"> 
                                        <% String tmp = ((User) request.getAttribute("UPDATEPROFILE")).getSex().trim();
                                            pageContext.setAttribute("sex", tmp);
                                        %>
                                        <input type="radio" name="gender" value="male" <thanhntm:if test="${sex eq 'male'}">checked="checked"</thanhntm:if> id="male" class="with-gap"/>
                                            <label for="male">Male</label>
                                            <input type="radio" name="gender" value="female" <thanhntm:if test="${sex eq 'female'}">checked="checked"</thanhntm:if> id="female" class="with-gap"/>
                                            <label for="female" class="m-l-20">Female</label>     
                                        </div>

                                        <div class="form-group form-float">
                                            <div class="form-line">
                                                <input type="text" class="form-control" name="address" required value="${UPDATEPROFILE.address}"/>
                                            <label class="form-label">Address</label>
                                        </div>
                                        <div class="help-info">Enter address</div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" class="form-control" name="phoneNumber" required value="${UPDATEPROFILE.phone}"
                                                   pattern="[0-9]{8,14}$" title="8 or 14 numbers for phone"/>
                                            <label class="form-label">Phone</label>
                                        </div>
                                        <div class="help-info">Numbers only</div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" class="form-control" name="description" maxlength="100" minlength="3" required value="${UPDATEPROFILE.description}"/>
                                            <label class="form-label">Description</label>
                                        </div>
                                        <div class="help-info">Min. 3, Max. 100 characters</div>
                                    </div>
                                    <input type="submit" o class="btn btn-primary waves-effect" name="" value="Update" />
                                    <strong><font color="red">${requestScope.RESULT}</font></strong>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
