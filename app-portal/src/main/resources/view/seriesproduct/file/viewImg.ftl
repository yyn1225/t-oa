<html>
<head>
    <title>文件预览</title>
    <link  href="../../../static/js/plugin/viewer/viewer.min.css" rel="stylesheet">
    <style>
        .viewer-backdrop{
            background-color: #ffffff;
        }
    </style>
</head>
<body style="margin: 0">
<div style="width: 100%;height: 100%;">
    <img id="oImg" style="display: none" src="${url}" data-original="${url}" />
</div>
<script src="../../../static/js/libs/jquery-2.1.1.min.js"></script>
<script src="../../../static/js/plugin/viewer/viewer.min.js"></script>
<script type="text/javascript">
    var oImg = $('#oImg');
    oImg.viewer({
        inline: true,
        toolbar: false,
        title: false,
        button: false,
        transition: false,
        navbar: false,
        viewed: function() {
        }
    });
</script>
</body>
</html>