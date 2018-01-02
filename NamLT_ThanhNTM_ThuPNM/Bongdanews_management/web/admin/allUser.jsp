
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All posts</title>
        <link href="src/plugins/jquery-datatable/skin/bootstrap/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../elements/header.jsp" %>
        <script src="src/plugins/jquery-datatable/jquery.dataTables.js"></script>
        <script src="src/plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.min.js"></script>
        <script>
            $("#page_name").text("ALL USER IN SYSTEM");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6">
                        <div id="title">
                            <h4><strong>Users</strong></h4>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12" style="background: white">
                    <div class="body table-responsive">
                        <thanhntm:if test="${not empty requestScope.listUser}">
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                                <thead>
                                    <tr>
                                        <th>No</th>
                                        <th>Name</th>
                                        <th>Birthday</th>
                                        <th>Mail</th>
                                        <th>Sex</th>
                                        <th>Role</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <thanhntm:forEach items="${requestScope.listUser}" var="dto" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${dto.name}</td>
                                            <td>
                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${dto.birthday}"/>
                                            </td>
                                            <td>${dto.mail}</td>
                                            <td>${dto.sex}</td>
                                            <td>
                                                <thanhntm:if test="${dto.role == 1}">Moderator</thanhntm:if>
                                                <thanhntm:if test="${dto.role == 2}">Collaborator</thanhntm:if>
                                                </td>
                                                <td>
                                                <thanhntm:if test="${dto.status == 1}">
                                                    <font color="green">active</font>
                                                </thanhntm:if>
                                                <thanhntm:if test="${dto.status == 0}">
                                                    <font color="red">banned</font>
                                                </thanhntm:if>
                                            </td>
                                            <td>
                                                <thanhntm:if test="${dto.status != 0}">
                                                    <form action="BanAccountController">
                                                        <input type="hidden" name="userId" value="${dto.id}" />
                                                        <input class="btn btn-success" onclick="return confirmation()" type="submit" value="Ban" name="action" />
                                                    </form>
                                                </thanhntm:if>
                                                <thanhntm:if test="${dto.status == 0}">
                                                    <form action="ActiveAccountController">
                                                        <input type="hidden" name="userId" value="${dto.id}" />
                                                        <input class="btn btn-success" onclick="return confirmation()" type="submit" value="Active" name="action" />
                                                    </form>
                                                </thanhntm:if>
                                                <form action="ResetPassword">
                                                    <input type="hidden" name="userId" value="${dto.id}" />
                                                    <input class="btn btn-success" type="submit" onclick="return confirmation()" value="Reset Password" />
                                                </form>
                                                <form action="UpdateBasicInfo">
                                                    <input type="hidden" name="userId" value="${dto.id}" />
                                                    <input class="btn btn-success" type="submit" value="Update Info" />
                                                </form>
                                            </td>

                                        </tr>
                                    </thanhntm:forEach>
                                </tbody>
                            </table>
                        </thanhntm:if>
                    </div>
                </div>
            </div>
        </section>
        <script>
            function confirmation() {
                return confirm("Are you sure?");
            }

        </script>
        <script>
            $(function () {
                $('.js-basic-example').DataTable({
                    responsive: true
                });
            });
        </script>
    </body>
</html>
