/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function confirmCreate() {
    var div = $("<div class='modal-body'/>");
    div.append("<h3 style='margin-bottom=10px;'>Are you sure want to create new post?</h3>");
    var okButton = $("<button type='button' class='btn btn-success' data-dismiss='modal' style='margin:0px 15px 0px 150px;'>Yes</button>");
    var cancelButton = $("<button type='button' class='btn btn-danger' data-dismiss='modal'>No</button>");
    div.append(okButton);
    div.append(cancelButton);
    $("#modalContent").html("");
    $("#modalContent").append(div);
    $("#modal").modal('show');
    okButton.click(function () {
        createPost();
    });
}

function createPost() {
    $.ajax({
        url: "CreatePost",
        method: "POST",
        success: function (data) {
            if (String(data) == String("fail")) {
                var div = $("<div class='modal-body'/>");
                div.append("<p>Create post fail. Please try later!</p>");
                div.append("<button type='button' class='btn btn-default' data-dismiss='modal'>Close</button>");
                $("#modalContent").html("");
                $("#modalContent").append(div);
                $("#modal").modal('show');
            } else {
                window.location.href = "Post?p=" + data;
            }
        }
    })
}


