<input id="shareId" hidden value="${(share.id?c)!}" type="text">
<input id="seriesLine" hidden value="${(share.seriesLine)!}" type="text">
<form id="share-form" class="smart-form form-group">
  <fieldset class="panel">
    <label class="col col-2 text-align-right padding7">
      <dic data-dic="offer.create.type"></dic>
    </label>
    <label class="col col-10">
    <#if share??>
      <select class="select2" style="width: 100%" name="line">
        <#if share.seriesLine == 1>
          <option value="1" data-dic="offer.create.indoor" selected></option>
          <option value="2" data-dic="offer.create.outdoor"></option>
          <option value="3" data-dic="offer.create.lease"></option>
        </#if>
        <#if share.seriesLine == 2>
          <option value="1" data-dic="offer.create.indoor"></option>
          <option value="2" data-dic="offer.create.outdoor" selected></option>
          <option value="3" data-dic="offer.create.lease"></option>
        </#if>
        <#if share.seriesLine == 3>
          <option value="1" data-dic="offer.create.indoor"></option>
          <option value="2" data-dic="offer.create.outdoor"></option>
          <option value="3" data-dic="offer.create.lease" selected></option>
        </#if>
      </select>
    <#else>
      <select class="select2" style="width: 100%" name="line">
        <option value="1" data-dic="offer.create.indoor"></option>
        <option value="2" data-dic="offer.create.outdoor"></option>
        <option value="3" data-dic="offer.create.lease"></option>
      </select>
    </#if>
    </label>
  </fieldset>

  <fieldset class="panel">
    <label class="col col-2 text-align-right padding7">
      <dic data-dic="product.panel.list.type"></dic>
    </label>
    <label class="col col-10">
    <#if share ??>
      <input name="product" style="width: 100%" data-name="${(share.productName)!}"
             value="${(share.seriesId?c)!}"/>
    <#else>
      <input name="product" style="width: 100%" class="form-control" disabled
             data-name="${(share.productName)!}"
             value="${(share.productName)!}"/>
    </#if>
    </label>
  </fieldset>
  <fieldset class="panel">
    <label class="col col-2 text-align-right padding">
      <dic data-dic="experience.list.title"></dic>
      :
    </label>
    <label class="col col-10 input">
      <input type="text" name="title" value="${(share.title)!}" bodySheet/>
    </label>
  </fieldset>
  <fieldset class="panel" style="height: auto">
    <label class="col col-2 text-align-right padding">
      <dic data-dic="experience.list.content"></dic>
      :
    </label>
    <label class="col col-10 input">
      <textarea style="width: 100%;resize: none;padding-left: 3px;
                            padding-right: 3px;" rows="4"
                name="content" bodySheet>${(share.content)!}</textarea>
    </label>
  </fieldset>
  <!--dom结构部分-->
  <div id="uploader-demo" >
    <!--用来存放item-->
    <div  id="fileList" class="uploader-list"></div>
    <div class="row" style="padding: 20px 14px 5px;margin: 0 0px;"  >
      <div class="col col-2" style="display: block;padding-left: 15px;"></div>
      <div class="col col-10" id="filePicker" style="float: right;padding-left: 15px;"><dic data-dic="series.images.window.btn"></dic> </div>
    </div>
  </div>
</form>
<script type="text/javascript">
  $('[data-dic]').each(function () {
    $(this).html(message[$(this).data("dic")]);
  });
  pageSetUp();
  $(".spinner").spinner({
    step: 1,
    min: 0,
    textFormat: "n"
  });
  $(document).ready(function () {
    attId = new Array();
    if ($("#shareId").val() != null && $("#shareId").val() != "") {
      var images = '${listImages?size}';
      if (images != 0) {
      <#list listImages as img >
        attId.push('${(img.id?c)!}');
      </#list>
      }
    }
    //上传文件
    upload();

    var panel = $(".panel");
    panel.find("[name='line']").select2();

    lineChange(panel);

    //产品线与产品下拉框联动
    panel.find("[name='line']").change(function () {
      lineChange(panel);
    });

  });

  function lineChange(panel) {
    var value = $(this).select2("val");
    // 让对话框的标题支持html
    $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
      _title: function (title) {
        if (!this.options.title) {
          title.html("&#160;");
        } else {
          title.html(this.options.title);
        }
      }
    }));
    var $product = panel.find("[name='product']");

    if (!value || value.length === 0) {
      $product.select2({
        data: [],
        placeholder: message['select2.placeholder.msg']
      });
      $product.prop("disabled", true);
      return;
    }

    var values = [];
    $.ajax({
      url: '/offer/line/getall',
      type: 'get',
      data: {line: panel.find('[name="line"]').select2('val')},
      success: function (data) {
        $.each(data, function (index, item) {
          values.push({
            text: item.text,
            children: item.children
          });
        });
        $product.select2({
          data: values,
          placeholder: message['select2.placeholder.msg'],
          minimumResultsForSearch: -1
        });
        $product.removeAttr("disabled");
      }, error: function (data) {
      }
    });
  }

  //上传图片
  function upload() {
    var $list = $('#fileList');

    if ($("#shareId").val() != null && !("" == $("#shareId").val())) {
      $.ajax({
            url: '/experience/rest/images',
            data: {id: $("#shareId").val()},
            type: 'POST',
            success: function (data) {

              data.forEach(function (val) {
                var $li = $(
                    '<div id="' + val.id + '" class="file-item thumbnail">' +
                    '<a class="example-image-link" href="' + val.url + '" data-lightbox="example-set"  ><img src="' + val.url + '"/>' +
                    '<a class="delete" ' +
                    'id="d_' + val.id + '" ' +
                    ' data-attid="' + val.id + '"' +
                    'href="javascript:;" >' + message["header.panellist.button.delete"] + '</a></img>' +
                    '</div>'
                );
                $list.append($li);
                attId.push(val.id);
                $list.show();
              });
            }
          }
      );
    }
    uploader = WebUploader.create({
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
          'id="d_' + file.id + '" href="javascript:;">' + message["header.panellist.button.delete"]
          + '</a>' +
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
          $img.replaceWith('<span>' + message["not.review"] + '</span>');
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
      console.log(attId)
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

      $.ajax({
        url: '/experience/rest/image/delete',
        type: 'POST',
        data: {id: $(this).data('attid')},
        success: function (data) {
          console.log(data)
        }
      })
      if (parent == 0) {
        $('#parent').val(0);
      } else {
        var id = $(this).data('attid');
        if (id) {
          _remove(attId, id);
        }
      }
      $($(this).parent()).remove();
      if ($list.html() == '') {
        $list.hide();
      }
    });
  }

  //校验
  function checkInput() {
    var valid = true;
    if ($("[name='product']").val() == null || $("[name='product']").val() == '') {
      valid = false;
      alert(message["share.must.input.msg"],2);
      return false;
    }
    if ($("[name='title']").val() == null || $("[name='title']").val() == '') {
      valid = false;
      alert(message["share.must.input.msg"],2);
      return false;
    }
    if ($("[name='content']").val() == null || $("[name='content']").val() == '') {
      valid = false;
      alert(message["share.must.input.msg"],2);
      return false;
    }
    if ($("[name='title']").val().length >150){
      valid = false;
      alert(message["share.must.title.length.msg"],2);
      return false;
    }
    if ($("[name='content']").val().length >1000){
      valid = false;
      alert(message["share.must.content.length.msg"],2);
      return false;
    }

    return valid;
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

</style>