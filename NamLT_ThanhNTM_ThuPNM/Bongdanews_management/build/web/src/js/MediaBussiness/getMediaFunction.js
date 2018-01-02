/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function loadImage(postId) {
    $.ajax({url: "load_images/" + postId,
        type: "GET",
        async: false,
        success: function (result) {
            var source = $("#imageHolder");
            source.html("");
            for (i in result) {
                var div = $("<div class='col-lg-3 col-md-4 col-sm-6 col-xs-12 image_cover_responsive'/>");
                var link = $("<a href='" + result[i].url + "' data-sub-html='" + result[i].name + "'/>");
                link.append("<img src='" + result[i].url + "'  class='img-responsive thumbnail'>");
                div.append(link);
                source.append(div);
            }
            source.lightGallery({
                thumbnail: true,
                selector: 'a'
            });
        }});
}
function getAllFolder() {
    $.ajax({
        url: "GetAllPost",
        method: "GET",
        success: function (data) {
            if (String(data) != "fail") {
                var table = $("<table class='table folderTable js-basic-example dataTable' id='dataTable'/>");
                table.append("<thead><tr>\n\
                    <th>Folder ID</th><th>Post's Name</th><th>Creator</th><th>Created Day</th>\n\
                    </tr></thead>");
                $("#folderHolder").append(table);
                var tbody = $("<tbody/>");
                table.append(tbody);
                var row = null;
                for (i in data) {
                    row = $("<tr/>");
                    row.append("<td>" + data[i].id + "</td>");
                    row.append("<td>" + data[i].title + "</td>");
                    row.append("<td>" + data[i].author.name + "</td>");
                    row.append("<td>" + data[i].createdDay + "</td>");
                    tbody.append(row);
                    row.dblclick(function () {
                        var postId = $(this).children().first().text();
                        loadImage(postId);
                        var postTitle = $(this).children("td:nth-child(2)").text();
                        $("#folderTitle").text(postTitle);
                        showImageCard();

                    });
                }

                $('.js-basic-example').DataTable({
                    responsive: true
                });
                $('.folderTable tbody>tr').click(function () {
                    $('.folderTable tbody>tr').css("background", "#FFF");
                    $('.folderTable tbody>tr').css("color", "#555");
                    $(this).css("background", "#0089ec");
                    $(this).css("color", "white");
                });

            }
        }
    });
}

function showFolderCard() {
    $('.page-loader-wrapper').fadeIn();
    $("#folderCard").css("display", "block");
    $("#imageCard").css("display", "none");
    setTimeout(function () {
        $('.page-loader-wrapper').fadeOut();
    }, 500);
}

function showImageCard() {
    $('.page-loader-wrapper').fadeIn();
    $("#imageCard").css("display", "block");
    $("#folderCard").css("display", "none");
    setTimeout(function () {
        $('.page-loader-wrapper').fadeOut();
    }, 500);
}

