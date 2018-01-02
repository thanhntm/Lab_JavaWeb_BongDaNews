function getPost(postId) {
    $.ajax({
        url: "PostContent/" + postId,
        type: 'GET',
        success: function (data) {
            if (!$.isEmptyObject(data)) {
                var symbolImageUrl = "files/" + data.symbolicImage;
                var meta = data.createdDay + " <b>by</b> " + data.author.name;
                //set Image
                $("#symbolImage").attr("src", symbolImageUrl);
                //set title
                $("#title").text(data.title);
                $("#title").attr("href", window.location.path);
                //set bread crumb items
                $("#postBreadCrumbItem").text(data.title);
                $("#postBreadCrumbItem").attr("href", "Post?p=" + data.id);
                $("#categoryBreadCrumbItem").text(data.category.categoryName);
                $("#categoryBreadCrumbItem").attr("href", "Category?p=" + data.category.id);
                //set post meta
                $("#postMeta").html(meta);
                //set content
                $("#contentHolder").html(data.postContent);
                //set title of page
//                document.title = data.title;
                $(document).prop('title', $(document).prop('title') + " - " + data.title);
            }
        }
    })
}

