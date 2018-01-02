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
                    holder.html("<span>There are no comment for this post. Write a comment now!</span><br/>");
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
    var result = $("<div class='media' id='cmt" + element.id + "'/>");
    if (parent <= 0) {
        parent = element.id;
    }
    result.append("<div class='media-left'/>");
    //create body
    var body = $("<div class='media-body'/>");
    body.append("<h2 class='media-heading'>" + element.name + "</h2><span class='commentTime'>" + element.postedDay + "</span><br/>");
    body.append(element.comment);
    var responseDiv = $("<div class='comment_article_social'/>");
    responseDiv.append("<button style='margin:10px;' onclick='showCommentPopup(" + parent + ");' class='btn btn-success'>Reply</button>");
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
function validateComment(name, comment) {
    var result = "";
    if (name.length < 3 || name.length > 50) {
        result += "The name length must be at least 3 letter and at most 50 letter!\n";
    }
    if (comment.length < 10 || comment.length > 1000) {
        result += "The comment length must be at least 10 letter and at most 1000 letter!";
    }
    return result === "" ? null : result;
}

function writeComment(responseId, name, comment) {
    if (responseId === null) {
        responseId = "";
    }
    return $.ajax({
        url: "CreateNewComment",
        type: 'POST',
        async: false,
        data: {
            postId: postId,
            name: name,
            comment: comment,
            responseId: responseId
        }, success: function (data) {
            if (String(data) === "fail") {
                return -1;
            } else {
                alert("Write comment successfully!");
                return data.id;
            }
        }
    });
}


