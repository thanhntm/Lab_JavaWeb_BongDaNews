/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function getAllPost() {
    $.ajax({
        url: "GetAllPost",
        method: "GET",
        success: function (data) {
            if (String(data) != "fail") {
                var table = $("<table class='table table-bordered table-striped table-hover dataTable' id='dataTable'/>");
                table.append("<thead><tr>\n\
                    <th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Created day</th><th>Status</th><th>Actions</th>\n\
                    </tr></thead>");
                var tbody = $("<tbody/>");
                var row = null;
                for (i in data) {
                    row = $("<tr/>");
                    //id cell
                    row.append("<td>" + data[i].id + "</td>");
                    //title cell
                    row.append("<td><a href='Post?p=" + data[i].id + "' target='_blank'>" + data[i].title + "</a></td>");
                    //author cell
                    var link = $("<a href='UserInfo?user=" + data[i].author.id + "'>" + data[i].author.name + "</a>");
                    row.append($("<td/>").append(link));
                    link.click(function (e) {
                        e.preventDefault();
                        var href = $(this).attr('href');
                        $.get(href, function (data) {
                            $("#modalContent").html("<div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h4 class='modal-title'>User info</h4></div>");
                            $("#modalContent").append(data);
                            $("#modal").modal('show');
                        })
                    })
                    //category cell
                    if (data[i].category.id != 0) {
                        row.append("<td>" + data[i].category.categoryName + "</td>");
                    } else {
                        row.append("<td>None</td>");
                    }
                    //created day cell
                    row.append("<td>" + data[i].createdDay + "</td>");
                    //status cell
                    row.append("<td>" + defineStatus(data[i].status) + "</td>");
                    //action cell
                    var commentLink = $('<a href="CommentPage?p=' + data[i].id + '" class="btn btn-primary"><i class="fa fa-commenting-o" aria-hidden="true"></i></a>');
                    row.append($('<td/>').append(commentLink));
                    commentLink.click(function (e) {
                        e.preventDefault();
                        var href = $(this).attr('href');
                        $.get(href, function (data) {
                            $("#modalContent").html("<div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button></div>");
                            $("#modalContent").append(data);
                            $("#modal").modal('show');
                        });
                    })
                    tbody.append(row);
                }
                table.append(tbody);
                $("#dataHolder").append(table);

                $('.dataTable').DataTable({
                    responsive: true,
                    "columns": [
                        null,
                        null,
                        null,
                        null,
                        {"searchable": false},
                        null,
                        {"searchable": false}
                    ]
                });
            }
        }
    });
}

function getMyPost() {
    $.ajax({
        url: "GetMyPosts",
        method: "GET",
        success: function (data) {
            if (String(data) != "fail") {
                var table = $("<table class='table table-bordered table-striped table-hover js-exportable dataTable' id='dataTable'/>");
                table.append("<thead><tr>\n\
                    <th>ID</th><th>Title</th><th>Author</th><th>Category</th><th>Created day</th><th>Status</th>\n\
                    </tr></thead>");
                var tbody = $("<tbody/>");
                var row = null;
                for (i in data) {
                    row = $("<tr/>");
                    //id cell
                    row.append("<td>" + data[i].id + "</td>");
                    //title cell
                    row.append("<td><a href='Post?p=" + data[i].id + "' target='_blank'>" + data[i].title + "</a></td>");
                    //author cell
                    var link = $("<a href='UserInfo?user=" + data[i].author.id + "'>" + data[i].author.name + "</a>");
                    row.append($("<td/>").append(link));
                    link.click(function (e) {
                        e.preventDefault();
                        var href = $(this).attr('href');
                        $.get(href, function (data) {
                            $("#modalContent").html("<div class='modal-header'><button type='button' class='close' data-dismiss='modal'>&times;</button><h4 class='modal-title'>User info</h4></div>");
                            $("#modalContent").append(data);
                            $("#modal").modal('show');
                        })
                    })
                    //category cell
                    if (data[i].category.id != 0) {
                        row.append("<td>" + data[i].category.categoryName + "</td>");
                    } else {
                        row.append("<td>None</td>");
                    }
                    //created day cell
                    row.append("<td>" + data[i].createdDay + "</td>");
                    //status cell
                    row.append("<td>" + defineStatus(data[i].status) + "</td>");
                    tbody.append(row);
                }
                table.append(tbody);
                $("#dataHolder").append(table);

                $('.js-exportable').DataTable({
                    dom: 'Bfrtip',
                    responsive: true,
                    buttons: [
                        'copy', 'excel', 'pdf', 'print'
                    ]
                });
            }
        }
    });
}

function defineStatus(status) {
    var result;
    switch (status) {
        case 0:
            result = "<span style='color:red'>DISABLE</span>";
            break;
        case 1:
            result = "<span style='color:orange'>REJECTED</span>";
            break;
        case 2:
            result = "DRAFT";
            break;
        case 3:
            result = "<span style='color:blue'>SUBMITED</span>";
            break;
        case 4:
            result = "<span style='color:green'>PUBLISH</span>";
            break;
    }
    return result;
}