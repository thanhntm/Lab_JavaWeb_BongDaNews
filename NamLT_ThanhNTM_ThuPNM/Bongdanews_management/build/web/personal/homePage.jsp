
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>

    </head>
    <body>
        <%@include file="../elements/header.jsp" %>
        <script>
            $("#page_name").text("HOME");
        </script>
        <section class="content">
            <div class="container-fluid">
                <div class="card">
                    <div class="header">
                        <form action="GetUserHasMostPost" method="GET">
                            <h2>Top ten users that have most posts!</h2><br/>
                            Started time:<input type="date" name="start"/><br/>
                            End time:<input type="date" name="end"/><br/>
                            <c:if test="${not empty param.ERROR}">
                                <span style="color: red">${param.ERROR}</span><br/>
                            </c:if>
                            <input type="submit" class="btn btn-success" value="GET"/>
                        </form>
                    </div>
                    <div class="body" style="background: #FFF">
                        <div class="table-responsive" id="dataHolder">
                            <c:if test="${not empty requestScope.RS}">
                                <script src="src/plugins/jquery-datatable/jquery.dataTables.js"></script>
                                <script src="src/plugins/jquery-datatable/skin/bootstrap/js/dataTables.bootstrap.min.js"></script>
                                <script src="src/plugins/jquery-datatable/extensions/export/dataTables.buttons.min.js"></script>
                                <script src="src/plugins/jquery-datatable/extensions/export/buttons.flash.min.js"></script>
                                <script src="src/plugins/jquery-datatable/extensions/export/jszip.min.js"></script>
                                <script src="src/plugins/jquery-datatable/extensions/export/pdfmake.min.js"></script>
                                <script src="src/plugins/jquery-datatable/extensions/export/vfs_fonts.js"></script>
                                <script src="src/plugins/jquery-datatable/extensions/export/buttons.html5.min.js"></script>
                                <script src="src/plugins/jquery-datatable/extensions/export/buttons.print.min.js"></script>
                                <table class="table table-bordered table-striped table-hover js-exportable dataTable">
                                    <thead>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Birthday</th>
                                    <th>Number of posts</th>
                                    <th>Status</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.RS}" var="i">
                                            <tr>
                                                <td>${i.id}</td>
                                                <td>${i.name}</td>
                                                <td>${i.birthday}</td>
                                                <td>${i.postQuantity}</td>
                                                <td>${(i.status == 0)?"BANNED":"ACTIVE"}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <script>
            $(function () {
                $('.js-exportable').DataTable({
                    dom: 'Bfrtip',
                    responsive: true,
                    buttons: [
                        'copy', 'excel', 'pdf', 'print'
                    ]
                });
            });
                                </script>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
