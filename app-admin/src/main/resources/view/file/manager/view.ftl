<html>
<head>
    <title>文件预览</title>
</head>
<body>
<iframe id="view" src=""></iframe>
<style type="text/css">
    iframe {
        width: 100%;
        height: 100%;
        border: hidden;
    }
</style>
<script type="text/javascript">
    var fileUrl = '${url}';
    var fileType = '${type}';
    var pdfUrl = "/static/pdf/web/viewer.html?path=" + fileUrl;
    var officeUrl = "http://view.officeapps.live.com/op/view.aspx?src=" + fileUrl;
    var unsupportUrl =
            '/static/pdf/web/unsupport.html';
    if (fileType == 'pdf') {
        document.getElementById("view").src = pdfUrl;
    } else if (fileType == 'png'
            || fileType == 'jpg'
            || fileType == 'jpeg'
            || fileType == 'gif'
    ) {
        document.getElementById("view").src = fileUrl;
    } else if (fileType == 'doc'
            ||fileType == 'docx'
            ||fileType == 'xls'
            ||fileType == 'xlsx'
            ||fileType == 'ppt'
            ||fileType == 'pptx'
    ) {
        document.getElementById("view").src = officeUrl;
    } else {
        document.getElementById("view").src = unsupportUrl;
    }

</script>
</body>
</html>