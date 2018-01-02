
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All posts</title>

    </head>
    <body>
        <%@include file="../elements/header.jsp" %>
        <script>
            $("#page_name").text("ADD NEW USER");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>Add New User</h2>
                            </div>
                            <div class="body">
                                <form action="AddUserController" id="form_advanced_validation" method="POST">
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="email" class="form-control" name="email" required>
                                            <label class="form-label">Email</label>
                                        </div>
                                        <div class="help-info">Enter email</div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" class="form-control" name="name" required>
                                            <label class="form-label">Name</label>
                                        </div>
                                        <div class="help-info">Enter name</div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" class="form-control" name="birthdate" required>
                                            <label class="form-label">Birthdate</label>
                                        </div>
                                        <div class="help-info">YYYY-MM-DD format</div>
                                    </div>

                                    <div class="form-group">
                                        <input type="radio" name="gender" id="male" class="with-gap" value="male">
                                        <label for="male">Male</label>

                                        <input type="radio" name="gender" id="female" class="with-gap" value="female">
                                        <label for="female" class="m-l-20">Female</label>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" class="form-control" name="address" required>
                                            <label class="form-label">Address</label>
                                        </div>
                                        <div class="help-info">Enter address</div>
                                    </div>

                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" class="form-control" name="phone" required>
                                            <label class="form-label">Phone</label>
                                        </div>
                                        <div class="help-info">Numbers only</div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="number" class="form-control" name="idcard" required>
                                            <label class="form-label">Identify card</label>
                                        </div>
                                        <div class="help-info">Numbers only</div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <input type="text" class="form-control" name="description" maxlength="100" minlength="3" require>
                                            <label class="form-label">Description</label>
                                        </div>
                                        <div class="help-info">Min. 3, Max. 100 characters</div>
                                    </div>
                                    <div class="form-group form-float">
                                        <div class="form-line">
                                            <select class="form-control" name="role">
                                                <option value="1">Moderator</option>
                                                <option value="2">Collaborator</option>
                                            </select>
                                        </div>
                                        <div class="help-info">Set role</div>
                                    </div>
                                    <!--<button class="btn btn-primary waves-effect" type="submit">SUBMIT</button>-->
                                    <input type="submit" class="btn btn-primary waves-effect" value="SUBMIT"/>
                                    <strong>${requestScope.RESULT}</strong>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Slimscroll Plugin Js -->
        <script src="src/plugins/jquery-slimscroll/jquery.slimscroll.js"></script>

        <!-- Jquery Validation Plugin Css -->
        <script src="src/plugins/jquery-validation/jquery.validate.js"></script>

        <!-- JQuery Steps Plugin Js -->
        <script src="src/plugins/jquery-steps/jquery.steps.js"></script>

        <!-- Sweet Alert Plugin Js -->
        <script src="src/plugins/sweetalert/sweetalert.min.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="src/plugins/node-waves/waves.js"></script>

        <!-- Custom Js -->
        <script src="src/js/pages/forms/form-validation.js"></script>
    </body>
</html>
