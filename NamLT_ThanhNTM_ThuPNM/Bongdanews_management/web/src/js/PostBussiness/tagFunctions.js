function addNewTag() {
    var tags = $("#newTag").val().trim();
    if (tags.length > 0 && tags.length <= 30) {
        $.ajax({
            url: "AddTag",
            type: 'POST',
            data: {
                tags: tags,
                postId: postId
            }, success: function (data) {
                if (String(data) != "fail") {
                    var holder = $("#tagHolder");
                    for (i in data) {
                        var span = $("<span class='tag label label-info'/>");
                        var button = $("<span class='btnRemoveTag'><i class='fa fa-window-close' aria-hidden='true'></i></span>");
                        span.append(data[i]);
                        span.append(button);
                        holder.append(span);
                        button.click(function () {
                            var holder = $(this).parent().closest("span");
                            var tag = holder.text();
                            if (removeTag(tag)) {
                                holder.remove();
                            } else
                                alert("Remove tag fail! Please try again latter!");
                        });
                    }
                    $("#newTag").val("");
                } else
                    alert("Add new tag fail! Please try again latter!");
            }
        })
        return false;
    }else{
        alert('Tag name can not be null and must be shorter than 30 characters!');
        $("#newTag").val("");
    }
}
function getAllTag(postId) {
    $.ajax({
        url: "GetAllTagOfPost",
        type: 'GET',
        data: {
            postId: postId
        }, success: function (data) {
            if (String(data) != "fail") {
                var holder = $("#tagHolder");
                holder.html("");
                for (i in data) {
                    var span = $("<span class='tag label label-info'/>");
                    var button = $("<span class='btnRemoveTag'><i class='fa fa-window-close' aria-hidden='true'></i></span>");
                    span.append(data[i].tagName);
                    span.append(button);
                    holder.append(span);
                    button.click(function () {
                        var holder = $(this).parent().closest("span");
                        var tag = holder.text();
                        if (removeTag(tag)) {
                            holder.remove();
                        } else
                            alert("Remove tag fail! Please try again latter!");
                    });
                }
            }
        }
    })
}
function removeTag(tag) {
    if (confirm("Do you want to remove this tag?")) {
        return $.ajax({
            url: "UnmapTagWithPost",
            type: 'POST',
            data: {
                tagName: tag,
                postId: postId
            }, success: function (data) {
                if (String(data) != "fail") {
                    return true;
                } else
                    return false;
            }
        })
    }
}