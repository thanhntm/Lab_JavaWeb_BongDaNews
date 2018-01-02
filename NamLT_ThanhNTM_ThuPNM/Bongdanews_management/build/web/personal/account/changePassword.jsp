
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
            $("#page_name").text("CHANGE PASSWORD");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>Update Account's Password</h2>
                            </div>
                            <div class="body">
                                <form id="form_advanced_validation" action="ChangePasswordController" method="POST">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input class="form-control" name="email" value="${UPDATEPASS.mail}" readonly="true">
                                            <label class="form-label">Email</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input class="form-control" name="name" value="${UPDATEPASS.name}" readonly="true">
                                            <label class="form-label">Name</label>
                                        </div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="hidden" name="oldPass" value="${OLDPASS}"/> 
                                            <input type="password" class="form-control" name="oldPassword" required>                                           
                                            <label class="form-label">Old Password</label>
                                        </div>
                                        <strong><font color="red">${requestScope.errorPass}</font></strong>
                                    </div>
                                        
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="password" class="form-control" name="newPassword" required>
                                            <label class="form-label">New Password</label>
                                        </div>                           
                                    </div>
                                        
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="password" class="form-control" name="confirmPassword" required>
                                            <label class="form-label">Confirm Password</label>
                                        </div>
                                        <strong><font color="red">${requestScope.errorConfirm}</font></strong>
                                    </div>
                                    <!--                                    <button class="btn btn-primary waves-effect" type="submit">SUBMIT</button>-->
                                    <input type="submit" value="Update Password" class="btn btn-primary waves-effect"/>
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
