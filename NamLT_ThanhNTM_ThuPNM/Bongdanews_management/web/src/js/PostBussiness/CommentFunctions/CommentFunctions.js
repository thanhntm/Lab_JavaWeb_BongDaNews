function loadAllComment(postId) {
    $.ajax({
        url: "GetAllCommentOfPost",
        type: 'GET',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) !== "fail") {
                var holder = $("#commentHolder");
                if ($.isEmptyObject(data)) {
                    holder.html("<span>There are no comment for this post.</span><br/>");
                } else {
                    holder.html("");
                    for (i in data) {
                        holder.append(generateCommentElement(null, data[i]));
                    }
                }
            }
        }
    })
}
function generateCommentElement(parent, element) {
    //create element
    var result = $("<div class='media " + (element.status == 0 ? "disableComment" : "activeComment") + "' id='cmt" + element.id + "'/>");
    if (parent <= 0) {
        parent = element.id;
    }
    result.append("<div class='media-left'/>");
    //create body
    var body = $("<div class='media-body'/>");
    body.append("<h2 class='media-heading'>" + element.name + "</h2><span class='commentTime'>" + element.postedDay + "</span><br/>");
    body.append(element.comment);
    var responseDiv = $("<div class='comment_article_social'/>");
    var button = $("<button style='margin:10px;' class='btn " + (element.status == 0 ? "btn-success" : "btn-danger") + "'>" + (element.status == 0 ? "Active" : "Disable") + "</button>");
    responseDiv.append(button);
    button.click(function () {
        var result;
        var parent = $(this).parent().closest("div.media");
        if (parent.hasClass('disableComment')) {
            result = activeComment(element.id);
            parent.removeClass('disableComment');
            parent.addClass('activeComment');
            $(this).removeClass('btn-success');
            $(this).addClass('btn-danger');
            $(this).html('Disable');
        } else {
            result = disableComment(element.id);
            parent.removeClass('activeComment');
            parent.addClass('disableComment');
            $(this).removeClass('btn-danger');
            $(this).addClass('btn-success');
            $(this).html('Active');
        }
    });
    body.append(responseDiv);
    //append body
    result.append(body);
    if (!$.isEmptyObject(element.responseList)) {
        var list = element.responseList;
        for (i in element.responseList) {
            body.append(generateCommentElement(element.id, element.responseList[i]));
        }
    }
    return result;
}
function disableComment(commentId) {
    if (confirm('Are you sure?')) {
        return $.ajax({
            url: "commentAction/disable",
            type: 'POST',
            async: false,
            data: {
                commentId: commentId
            }, success: function (data) {
                if (String(data) !== 'fail') {
                    return true;
                }
                return false;
            }
        })
    } else {
        return false;
    }
}
function activeComment(commentId) {
    if (confirm('Are you sure?')) {
        return $.ajax({
            url: "commentAction/active",
            type: 'POST',
            async: false,
            data: {
                commentId: commentId
            }, success: function (data) {
                if (String(data) !== 'fail') {
                    return true;
                }
                return false;
            }
        })
    } else {
        return false;
    }
}
function showCommentPopup(response) {
    var modal = $("#modal");
    var contentHolder = $('#modal-content');
    var form = $("<div class='form-group comment-form'>");
    form.append("<label for='NAME'>*Name:</label><input type='text' id='NAME' class='form-control'/>");
    form.append("<label for='COMMENT'>*Comment:</label><textarea id='COMMENT' class='form-control' rows='10'></textarea>");
    var button = $("<button style='float:right;margin:10px;' class='btn btn-success'>Submit</button>");
    form.append(button);
    contentHolder.html(form);
    button.click(function () {
        var name = $("#NAME").val();
        var comment = $("#COMMENT").val();
        var validateRs = validateComment(name, comment);
        if ($.isEmptyObject(validateRs)) {
            var id = writeComment(response, name, comment);
            if (id !== -1) {
                id = id.responseJSON.id;
                var holder = null;
                var element = new Object();
                element.id = id;
                element.name = name.trim();
                element.comment = comment.trim();
                element.postedDay = "Fews seconds ago";
                var tagElement = generateCommentElement(response, element);
                if (response <= 0) {
                    holder = $("#commentHolder");
                    holder.append(tagElement);
                } else {
                    holder = $("#cmt" + response);
                    holder.children().eq(1).append(tagElement);
                }
                window.location.hash = "#cmt" + id;
            } else {
                alert("Something went wrong! Please try again latter!");
            }
            $("#modal").modal('hide');
            $("#NAME").val("");
            $("#COMMENT").val("");
        } else {
            alert(validateRs);
        }
    });
    modal.modal('show');
}




