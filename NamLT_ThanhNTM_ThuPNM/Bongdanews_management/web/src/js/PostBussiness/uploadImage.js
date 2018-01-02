//using jquery form plugin
$('#form-upload').ajaxForm({
    // Do something before uploading
    beforeUpload: function () {
        alert($("#select-file").val());
    },
    // Do somthing while uploading
    uploadProgress: function (event, position, total, percentComplete) {
    },
    // Do something while uploading file finish
    success: function () {
    },
    // Set uploaded image as symbolic image of this post
    complete: function (xhr) {
        changePostSymbolImage(postId, JSON.stringify(xhr.responseJSON.link));
    }
});

function changePostSymbolImage(postId, imageUrl) {
    imageUrl = imageUrl.replace(/\"/g, "");
    var imageId = imageUrl.split("/");
    imageId = imageId[imageId.length - 1];
    $.ajax({
        url: "ChangeSymbolImage",
        type: 'POST',
        data: {
            postId: postId,
            imageId: imageId
        },
        success: function (data) {
            setSymbolImage(imageId, '.postSymbolImage');
        }
    })
}




