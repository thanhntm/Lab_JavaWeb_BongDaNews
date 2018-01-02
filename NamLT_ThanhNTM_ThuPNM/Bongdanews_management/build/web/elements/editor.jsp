<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Include Editor style. -->
<link href="froala/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
<link href="froala/css/froala_style.min.css" rel="stylesheet" type="text/css" />
<link href="src/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

<!-- Include Froala Editor Plugins styles -->
<link rel="stylesheet" href="src/css/codemirror.css">
<link rel="stylesheet" href="froala/css/plugins/code_view.min.css">
<link rel="stylesheet" href="froala/css/plugins/table.min.css">
<link rel="stylesheet" href="froala/css/plugins/image.min.css">
<link rel="stylesheet" href="froala/css/plugins/image_manager.min.css">

<!-- Include Editor JS files. -->
<script type="text/javascript" src="froala/js/froala_editor.pkgd.min.js"></script>
<!-- Include external JS libs. -->
<script type="text/javascript" src="src/js/codemirror.min.js"></script>
<script type="text/javascript" src="froala/js/plugins/code_view.min.js"></script>
<script type="text/javascript" src="src/js/xml.js"></script>
<script type="text/javascript" src="froala/js/plugins/table.min.js"></script>
<script type="text/javascript" src="froala/js/plugins/image.min.js"></script>
<script type="text/javascript" src="froala/js/plugins/image_manager.min.js"></script>
<!-- Include file for image upload -->

<!-- Create a tag that we will use as the editable area. -->
<!-- You can use a div tag as well. -->
<div id="froala-editor">

</div>

<!-- Initialize the editor. -->
<script>
    $(function () {
        $('div#froala-editor').froalaEditor({
            //set height
            height: 500,
            //define features
            toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'paragraphStyle', '-', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '|', 'emoticons', 'specialCharacters', 'insertHR', 'clearFormatting', '-', 'insertLink', 'insertImage', 'insertTable', '|', 'help', 'html', '|', 'undo', 'redo', 'insertHTML'],
            fontFamilySelection: true,
            fontSizeSelection: true,
            paragraphFormatSelection: true,
            dragInline: false,
            tabSpaces: 4,
            //resize table define
            tableResizerOffset: 10,
            tableResizingLimit: 50,
            // Set the image upload URL.
            imageUploadURL: 'upload_image<c:if test="${param.p != null}">/${param.p}</c:if>',
            imageUploadMethod: "POST",
            imageUploadParams: {id: 'my_editor'},
            imageMaxSize: 5 * 1024 * 1024,
            imageAllowedTypes: ['jpeg', 'jpg', 'png'],
            // Set the image upload URL.
            imageManagerLoadURL: 'load_images<c:if test="${param.p != null}">/${param.p}</c:if>',
            // Set the image delete URL.
            imageManagerDeleteURL: 'delete_image'
        });
        loadContent(<c:if test="${param.p != null}">${param.p}</c:if>);
    }).on('froalaEditor.image.error', function (e, editor, error, response) {
        window.alert("Wrong!");
    }).on('froalaEditor.image.removed', function (e, editor, $img) {
        $.ajax({
            // Request method.
            method: 'POST',
            // Request URL.
            url: 'delete_image',
            // Request params.
            data: {
                src: $img.attr('src')
            }
        }).done(function (data) {
            console.log('Image was deleted');
        }).fail(function (err) {
            console.log('Image delete problem: ' + JSON.stringify(err));
        })
    });
</script>