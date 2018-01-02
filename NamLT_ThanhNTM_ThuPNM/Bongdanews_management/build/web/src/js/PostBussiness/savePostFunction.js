/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var categoryHolder = $(".category-list").first();
function loadContent(postId) {
    $.ajax({
        url: "PostContent/" + postId,
        type: "GET",
        success: function (data) {
            //write saved title
            $('#title').val(data.title);
            //write info card
            var status = defineStatus(data.status);
            var createdDay = data.createdDay;
            $('#info').html("");
            $('#info').html("<h5>Status: " + status + "</h5><h5>Created day: " + createdDay + "</h5>");
            //init action buttons
            var saveForm = $("#savePostForm");
            var button = $("<input type='button' class='btn btn-primary'/>");
            if (data.status != 0) {
                button.val("Save Draft");
                saveForm.append(button);
                button.click(function () {
                    var title = $("#title").val().trim();
                    if (title.length > 0 && title.length <= 100) {
                        if (savePost(data.id).responseText == "success") {
                            alert("Save post successfully!");
                        } else {
                            alert("Save post fail!");
                        }
                    }else{
                        alert('Title can not be null and must be shorter than 100 characters!');
                    }
                });
            } else {
            }
            //function for moderator
            if ($("#role").val() == 1 || $("#role").val() == 0) {
                if (data.status != 0) {
                    //disable button
                    button = $("<input type='button' class='btn btn-danger' style='margin-left:10px;'/>");
                    saveForm.append(button);
                    button.val("Disable");
                    button.click(function () {
                        disablePost(postId);
                        savePost(data.id);
                        location.reload();
                    });
                } else {
                    //active button
                    button = $("<input type='button' class='btn btn-success' style='margin-left:10px;'/>");
                    saveForm.append(button);
                    button.val("Active");
                    button.click(function () {
                        activePost(postId);
                        location.reload();
                    });
                }

                if (data.status == 3) {
                    //reject button
                    button = $("<input type='button' class='btn btn-warning' style='margin-left:10px;'/>");
                    saveForm.append(button);
                    button.val("Reject");
                    button.click(function () {
                        rejectPost(postId);
                        savePost(data.id);
                        location.reload();
                    });

                    //publish button
                    button = $("<input type='button' class='btn btn-success' style='margin-left:10px;'/>");
                    saveForm.append(button);
                    button.val("Publish");
                    button.click(function () {
                        publishPost(postId);
                        savePost(data.id);
                        location.reload();
                    });
                }
                if (data.status == 4) {
                    //unpublish button
                    button.val("Unpublish");
                    button.click(function () {
                        unpublishPost(postId);
                        savePost(data.id);
                        location.reload();
                    });
                }
            }
            if (data.status == 2 || data.status == 1) {
                if (userId == data.author.id) {
                    //submit button
                    button = $("<input type='button' class='btn btn-success' style='margin-left:10px;'/>");
                    saveForm.append(button);
                    button.val("Submit");
                    button.click(function () {
                        submitPost(postId);
                        savePost(data.id);
                        location.reload();
                    });
                }
            }
            //get the symbolic image
            if (data.symbolicImage !== 0) {
                setSymbolImage(data.symbolicImage, '.postSymbolImage');
            }
            //get the saved content
            $('div#froala-editor').froalaEditor('html.set', data.postContent);
            //init the category list
            loadCategoryList(data.category.id, data.id);
            getAllTag(data.id);
        }
    });
}

function publishPost(postId) {
    $.ajax({
        url: "postAction/publish",
        type: 'POST',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) == "success") {
                alert("This post is published!");
            } else
                alert("Something went wrong! Please try again latter!");
        }
    });
}
function unpublishPost(postId) {
    $.ajax({
        url: "postAction/unpublish",
        type: 'POST',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) == "success") {
                alert("This post is unpublished!");
            } else
                alert("Something went wrong! Please try again latter!");
        }
    });
}
function rejectPost(postId) {
    $.ajax({
        url: "postAction/reject",
        type: 'POST',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) == "success") {
                alert("This post is rejected!");
            } else
                alert("Something went wrong! Please try again latter!");
        }
    });
}
function submitPost(postId) {
    $.ajax({
        url: "postAction/submit",
        type: 'POST',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) == "success") {
                alert("This post is submited!");
            } else
                alert("Something went wrong! Please try again latter!");
        }
    });
}
function disablePost(postId) {
    $.ajax({
        url: "postAction/disable",
        type: 'POST',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) == "success") {
                alert("This post is disabled!");
            } else
                alert("Something went wrong! Please try again latter!");
        }
    });
}
function activePost(postId) {
    $.ajax({
        url: "postAction/active",
        type: 'POST',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) == "success") {
                alert("This post is active!");
            } else
                alert("Something went wrong! Please try again latter!");
        }
    });
}
function loadCategoryList(currentCategoryId, postId) {
    $.ajax({
        url: "GetAllCategory",
        method: "GET",
        async: false,
        success: function (data) {
            if (String(data) != "fail") {
                var select = $("<select class='form-control' show-tick name='categoryId'/>");
                for (i in data) {
                    select.append("<option value='" + data[i].id + "' " + (data[i].id === currentCategoryId ? "selected" : "") + ">" + data[i].categoryName + "</option>");
                }
                var button = $("<input type='button' value='Change' class='btn btn-success'/>");
                var holder = $(".category-list");
                holder.html("");
                holder.append(select);
                holder.append("<br/>");
                holder.append(button);
                button.click(function () {
                    setCategoryOfPost(postId);
                });
            }
        }
    });
}

function setCategoryOfPost(postId) {
    if (confirm("Do you want to update category of this post?")) {
        var categoryId = $("select[name=categoryId]").first().val();
        $.ajax({
            url: "SetCategoryOfPost",
            type: "POST",
            data: {
                postId: postId,
                categoryId: categoryId
            }, success: function (data) {
                if (String(data) === "success") {
                    alert("Save category successfully!");
                } else {
                    alert("Something went wrong! Please try later!");
                }
            }
        })
    }
}
function setSymbolImage(imageId, holder) {
    $("#form-upload").addClass("form-upload");
    $("#form-upload").css("display", "none");
    $("#tmpbtn").text("Change image");

    var div = getPostSymbolImage(imageId);
    $(holder).children("div.image_cover_responsive").remove();
    $(holder).append(div);
}
function getPostSymbolImage(imageId) {
    var url = "files/" + imageId;
    var div = $("<div class='image_cover_responsive'/>");
    var image = $("<img src='" + url + "' class='img-responsive'>");
    div.append(image);
    return div;
}

function defineStatus(status) {
    var result;
    switch (status) {
        case 0:
            result = "DISABLE";
            break;
        case 1:
            result = "REJECTED";
            break;
        case 2:
            result = "DRAFT";
            break;
        case 3:
            result = "APPROVING";
            break;
        case 4:
            result = "PUBLISH";
            break;
    }
    return result;
}

function clear() {

}
function getContent() {
    return $('div#froala-editor').froalaEditor('html.get');
}
function alertContent(content) {
    alert(content);
}
function getFirstParagraph(content) {
    var div = $('<div/>');
    div.append(content);
    var result = div.find('p:first').text();
    return result;
}
function savePost(postId) {
    var title = $("#title").val();
    var content = getContent();
    var firstParagraph = getFirstParagraph(content);
    return $.ajax({
        url: "SavePost/" + postId,
        type: "POST",
        async: false,
        data: {
            title: encodeURIComponent(title),
            'content': encodeURIComponent(content.toString()),
            firstParagraph: encodeURIComponent(firstParagraph)
        },
        success: function (data) {
            var result;
            if (String(data) == "success") {
                return true;
            } else {
                return false;
            }
        }
    });
}
