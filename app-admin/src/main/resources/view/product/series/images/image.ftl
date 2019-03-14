<input type="hidden" value="${(seriesId)!}" id="seriesId"/>
<input type="hidden" value="${(seriesId)!}" id="attIds"/>
<input type="hidden" value="${(url)!}" id="parent"/>
<!--dom结构部分-->
<div id="uploader-demo">
    <!--用来存放item-->
    <div id="fileList" class="uploader-list" style="display: none;"></div>
    <div id="filePicker"><dic data-dic="select.a.picture"></dic></div>
</div>
<script type="text/javascript">
    var $list = $('#fileList');
    var parent = ${(parent)!};
    var attId = new Array();
    // if (parent != 0) {
        $.ajax({
            url: '/api/series/image/images',
            data: {seriesId: $('#seriesId').val()},
            type: 'GET',
            success: function (data) {
                data.forEach(function (val) {
                    var $li = $(
                            '<div id="' + val.attachment + '" class="file-item thumbnail">' +
                            '<img src="' + val.urlThum + '"/>' +
                            '<a class="delete" ' +
                            'id="d_' + val.attachment + '" ' +
                            ' data-attid="' + val.attachment + '"' +
                            'href="javascript:;" >'+message["header.panellist.button.delete"]+'</a>' +
                            '</div>'
                    );
                    $list.append($li);
                    attId.push(val.attachment);
                    $list.show();
                });
            }
        });
    // } else {
    //     if ($('#parent').val()) {
    //         var val = {};
    //         val.id = 'wu_1';
    //         val.urlOriginal = $('#parent').val();
    //         var $li = $(
    //                 '<div id="' + val.id + '" class="file-item thumbnail">' +
    //                 '<img src="' + val.urlOriginal + '"/>' +
    //                 '<a class="delete" ' +
    //                 'id="d_1" ' +
    //                 'href="javascript:;">删除</a>' +
    //                 '</div>');
    //         $list.html($li);
    //         $list.show();
    //     }
    // }

    var uploader = WebUploader.create({
        // 文件接收服务端。
        server: '/upload/image',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,
        duplicate: true,
        auto: true,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png'
        }
    });
    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<a class="delete" ' +
                'id="d_' + file.id + '" href="javascript:;">'+message["header.panellist.button.delete"]+'</a>' +
                '</div>'
                ),
                $img = $li.find('img');
        // $list为容器jQuery实例
        if (parent == 0) {
            $list.html($li);
        } else {
            $list.append($li);
        }
        $list.show();
        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                $img.replaceWith('<span>'+message["not.review"]+'</span>');
                return;
            }
            $img.attr('src', src);
        });
    });
    // 文件上传过程中创建进度条实时显示。
    uploader.on('uploadProgress', function (file, percentage) {
        var $li = $('#' + file.id),
                $percent = $li.find('.progress span');
        // 避免重复创建
        if (!$percent.length) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo($li)
                    .find('span');
        }

        $percent.css('width', percentage * 100 + '%');
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on('uploadSuccess', function (file, data) {
        $('#' + file.id).addClass('upload-state-done');
        $('#d_' + file.id).attr("data-attid", data.id);
        if (parent == 0) {
            $('#parent').val(data.id);
        } else {
            attId.push(data.id);
        }

    });

    // 文件上传失败，显示上传出错。
    uploader.on('uploadError', function (file) {
        var $li = $('#' + file.id),
                $error = $li.find('div.error');

        // 避免重复创建
        if (!$error.length) {
            $error = $('<div class="error"></div>').appendTo($li);
        }
        $error.text(message["body.alert.message.file.upload.failure"]);
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on('uploadComplete', function (file) {
        $('#' + file.id).find('.progress').remove();
    });
    var _remove = function (arr, val) {
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                arr.splice(i, 1);
                break;
            }
        }
    }
    $('#fileList').on('click', '.delete', function () {
        if (parent == 0) {
            $('#parent').val(0);
        } else {
            var id = $(this).data('attid');
            if (id) {
                _remove(attId, id);
            }
        }
        $($(this).parent()).remove();
        if($list.html()==''){
            $list.hide();
        }
    });
    var _sumbit = function ($model) {
        var attIds = '';
        if (parent == 0) {
            attIds = $('#parent').val();
        } else {
            attIds = attId.join(',');
        }
        $.ajax({
            url: '/api/series/image/save',
            type: 'POST',
            data: {seriesId: $('#seriesId').val(), attIds: attIds},
            success: function (data) {
                $model.modal("hide");
            }
        })
    }
</script>
<style type="text/css">
    a.delete {
        line-height: 2.5;
    }

    .thumbnail {
        text-align: center;
    }

    .thumbnail img {
        width: 100px;
        height: 100px;
    }

    .uploader-list {
        padding: 20px !important;
    }

    .webuploader-container {
        padding: 20px !important;
    }

</style>