<html>
    <head>
        <title>文件预览</title>
        <script src="../../../static/js/libs/jquery-2.1.1.min.js"></script>
    </head>
    <body>
        <#if show==1>
            <div id="hideLoading">&nbsp;</div>
        </#if>
        <iframe id="view" src=""></iframe>
        <style type="text/css">
            iframe {
                width: 100%;
                height: 100%;
                border: hidden;
                position: absolute;
                top: 0;
                left: 0
            }
            #hideLoading{
                width: 40%;
                height: 40px;
                margin-top: 42px;
                z-index: 99999999999;
                color: red;
                position: absolute;
                right: 0;
            }
        </style>
        <script type="text/javascript">
            var fileUrl = '${url}';
            var fileType = '${type}';
            if (fileType == 'pdf') {
                $("#view").attr("src", "/static/pdf/web/viewer.html?path=" + fileUrl);
            } else if (fileType === 'png' || fileType === 'jpg' || fileType === 'jpeg' || fileType === 'gif') {
                $("#view").attr("src", fileUrl);
            } else if (fileType === 'doc' || fileType === 'docx' || fileType === 'xls' || fileType === 'xlsx' || fileType === 'ppt' || fileType === 'pptx') {
                $("#view").attr("src", "http://view.officeapps.live.com/op/view.aspx?src=" + fileUrl);
            } else {
                $("#view").attr("src", '/static/pdf/web/unsupport.html');
            }
        </script>
    </body>
</html>

