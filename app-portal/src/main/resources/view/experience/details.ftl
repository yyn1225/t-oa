<form id="share-form" class="smart-form form-group">
  <input id="shareCommentId" value="${(share.id?c)!}" hidden/>
  <fieldset class="panel">
    <label class="col col-2 text-align-right padding">
      <dic data-dic="offer.create.type"></dic>
      :
    </label>
    <label class="col col-10 input;">
    <#if share??>
      <select class="select2" style="width: 100%;height:32px;" name="line" disabled="disabled">
        <#if share.seriesLine == 1>
          <option value="1" data-dic="offer.create.indoor" selected disabled></option>
          <option value="2" data-dic="offer.create.outdoor"></option>
          <option value="3" data-dic="offer.create.lease"></option>
        </#if>
        <#if share.seriesLine == 2>
          <option value="1" data-dic="offer.create.indoor"></option>
          <option value="2" data-dic="offer.create.outdoor" disabled selected></option>
          <option value="3" data-dic="offer.create.lease"></option>
        </#if>
        <#if share.seriesLine == 3>
          <option value="1" data-dic="offer.create.indoor"></option>
          <option value="2" data-dic="offer.create.outdoor"></option>
          <option value="3" data-dic="offer.create.lease" disabled selected></option>
        </#if>
      </select>
    <#else>
      <select class="select2" style="width: 100%;height:32px;" name="line">
        <option value="1" data-dic="offer.create.indoor"></option>
        <option value="2" data-dic="offer.create.outdoor"></option>
        <option value="3" data-dic="offer.create.lease"></option>
      </select>
    </#if>
    </label>
  </fieldset>
  <fieldset class="panel">
    <label class="col col-2 text-align-right padding">
      <dic data-dic="product.panel.list.type"></dic>
      :
    </label>
    <label class="col col-10 input">
      <input name="productName" bodySheet style="width: 100%" type="text"
             value="${(share.productName)!}" disabled/>
    </label>
  </fieldset>
  <fieldset class="panel">
    <label class="col col-2 text-align-right padding">
      <dic data-dic="experience.list.title"></dic>
      :
    </label>
    <label class="col col-10 input">
      <input type="text" name="title" value="${(share.title)!}" bodySheet disabled/>
    </label>
  </fieldset>
  <fieldset class="panel" style="height: auto">
    <label class="col col-2 text-align-right padding">
      <dic data-dic="experience.list.content"></dic>
      :
    </label>
    <label class="col col-10 input">
      <textarea disabled style="width: 100%;resize: none;padding-left: 3px;
                            padding-right: 3px;" rows="4"
                name="content" bodySheet>${(share.content)!}</textarea>
    </label>
  </fieldset>
  <!--dom结构部分-->
  <div style="padding: 20px 14px 5px; " id="uploader-demo">
  <#if listImages?exists>
    <#if (listImages?size>0) >
      <div class="col col-2"
           style="float: left;text-align: right;margin-top: 30px;padding-left: 0px;padding-right: 0px;">
        <dic data-dic="experience.list.images"></dic>
        :
      </div>
      <!--用来存放item-->
      <div id="fileList" class="uploader-list  col col-10  ">

        <#list listImages as item>
          <div id="${(item.id?c)!}" class="file-item thumbnail">
              <a class="example-image-link" href="${(item.url)!}" data-lightbox="example-set"  ><img <#--class="pimg"--> src="${(item.url)!}"/></a></div>
        </#list>
      </div>
    </#if>
  </#if>
  </div>
</form>
<fieldset class="panel" style="height: 0px;" id="experience-comment-append">
  <div id="experience-comment" style="border: 0px solid red;">
    <div class="text-with-hr" style="margin-top: -20px;">
      <a onclick="onOpen()"><span style="float: right"> <dic
          data-dic="main.title.spare.comment"></dic></span></a>
    </div>
  </div>
</fieldset>


<div class="modal fade" style="overflow-y:auto;display: none" id="experience-prompt-comment" tabindex="-1"
     role="dialog">
  <div class="modal-dialog" style="margin: 30px auto;">
    <div class="modal-content">
      <div class="modal-header" style="padding: 15px;">
        <h5>
          <dic data-dic="main.title.spare.comment"></dic>
        </h5>
      </div>
      <div class="modal-body no-padding">
        <form class="smart-form" class="smart-form form-group">
          <div id="window-html-comment">
            <fieldset class="panel" style="height: auto">
              <label class="col col-2 text-align-right padding">
                <dic data-dic="experience.list.content"></dic>
                :
              </label>
              <label class="col col-10 input">
                <textarea style="width: 100%;resize: none;padding-left: 3px;
                            padding-right: 3px;" rows="4"
                          name="comment-content" bodySheet> </textarea>
              </label>
            </fieldset>
            <!--dom结构部分-->
            <div id="uploader-demo">
              <!--用来存放item-->
              <div id="fileNewList" class="uploader-list"></div>
              <div class="row" style="padding: 20px 14px 5px;margin: 0 0px;">
                <div class="col col-2" style="display: block;padding-left: 15px;"></div>
                <div class="col col-10" id="fileNewPicker" style="float: right;padding-left: 15px;">
                  选择图片
                </div>
              </div>
            </div>
          </div>
          <footer>
            <button type="button" id="comment-todo" class="btn btn-primary">
              <dic data-dic="button.confirm"></dic>
            </button>
            <button type="button" class="btn btn-default"
                    id="comment-todo-cancel"<#-- data-dismiss="modal"-->>
              <dic data-dic="button.back"></dic>
            </button>
          </footer>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" style="overflow-y:auto;display: none" id="experience-prompt-reply"
     tabindex="-1"
     role="dialog">
  <input hidden id="reply"/>
  <div class="modal-dialog" style="margin: 30px auto;">
    <div class="modal-content">
      <div class="modal-header" style="padding: 15px;">
        <h5>
          <dic data-dic="main.title.spare.reply"></dic>
        </h5>
      </div>
      <div class="modal-body no-padding">
        <form class="smart-form" class="smart-form form-group">
          <div id="window-html-comment">
            <fieldset class="panel" style="height: auto">
              <label class="col col-2 text-align-right padding">
                <dic data-dic="experience.list.content"></dic>
                :
              </label>
              <label class="col col-10 input">
                <textarea style="width: 100%;resize: none;padding-left: 3px;
                            padding-right: 3px;" rows="4"
                          name="contentRepluy" bodySheet> </textarea>
              </label>
            </fieldset>
          </div>
          <footer>
            <button type="button" id="reply-todo" class="btn btn-primary">
              <dic data-dic="button.confirm"></dic>
            </button>
            <button type="button" class="btn btn-default"
                    id="comment-todo-reply" <#--data-dismiss="modal"-->>
              <dic data-dic="button.cancel"></dic>
            </button>
          </footer>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="delete-prompt" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width: 350px;margin: 30px auto;">
    <input type="hidden" value="0" id="delete_comment_id">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">
          <dic data-dic="portal.window.attention"></dic>
        </h4>
      </div>
      <div class="modal-body no-padding">
        <form class="smart-form">
          <fieldset class="textPop" style="padding-bottom: 25px">
            <dic data-dic="prompt.message.delete"></dic>
          </fieldset>
          <footer>
            <button type="button" class="btn btn-default" id="delete-comment-close"
                    data-dismiss="modal">
              <dic data-dic="button.cancel"></dic>
            </button>
            <button type="button" id="delete-comment" class="btn btn-primary">
              <dic data-dic="button.confirm"></dic>
            </button>
          </footer>
        </form>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="delete-reply-modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" style="width: 350px;margin: 30px auto; ">
    <input type="hidden" value="0" id="delete_reply_id">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">
          <dic data-dic="portal.window.attention"></dic>
        </h4>
      </div>
      <div class="modal-body no-padding">
        <form class="smart-form">
          <fieldset class="textPop" style="padding-bottom: 25px">
            <dic data-dic="prompt.message.delete"></dic>
          </fieldset>
          <footer>
            <button type="button" class="btn btn-default" id="delete-reply-close"
                    data-dismiss="modal">
              <dic data-dic="button.cancel"></dic>
            </button>
            <button type="button" id="delete-reply" class="btn btn-primary">
              <dic data-dic="button.confirm"></dic>
            </button>
          </footer>
        </form>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  $('[data-dic]').each(function () {
    $(this).html(message[$(this).data("dic")]);
  });
  var uploader;
  var attId;
  $(function () {
    attId = new Array();
    var $list = $('#fileNewList');
    uploader = WebUploader.create({
      // 文件接收服务端。
      server: '/upload/image',
      // 选择文件的按钮。可选。
      // 内部根据当前运行是创建，可能是input元素，也可能是flash.
      pick: '#fileNewPicker',
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
    $('#fileNewList').on('click', '.delete', function () {
      $.ajax({
        url: '/experience/rest/image/delete',
        type: 'POST',
        data: {id: $(this).data('attid')},
        success: function (data) {
          console.log(data)
        }
      });
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

    //保存
    $("#comment-todo").click(function () {
      //触发全部验证
//      var bootstrapValidator = $("#share-form").data("bootstrapValidator");
//      bootstrapValidator.validate();
//      var flag = bootstrapValidator.isValid();

      var content = $("[name='comment-content']").val();
      if (content == null || content.trim() == '') {
        alert(message["http.response.empty"], 2);
        return false;
      }
      if (content.length>1000) {
        alert(message["share.must.content.length.msg"], 2);
        return false;
      }
      if (content != null && content.trim() != '') {
        $.ajax({
          url: "/experience/rest/comment/save",
          type: "post",
          data: {
            shareId: $("#shareCommentId").val(),
            parentId: 0,
            content: $("[name='comment-content']").val(),
            images: JSON.stringify(attId)
          },
          success: function (data) {
            $("#comment-append").remove();
            appendComment();
            console.log(data);
            $("[name='comment-content']").val("");
            $("#experience-prompt-comment").modal("hide");
            $("#fileNewList").empty();
            attId.length = 0;
            hideLoading();
          },
          error: function (data) {
            console.log(data);
            alert(message["http.response.error"], 3);
            $("[name='content']").val("");
            hideLoading();
            attId.length = 0;
          }
        });
      }

    });
    $("#comment-todo-reply").click(function () {
      $("#experience-prompt-reply").modal("hide");
      $("[name='contentRepluy']").val("");
    });
    $("#comment-todo-cancel").click(function () {
      $("#experience-prompt-comment").modal("hide");
      $("[name='content']").val("");
      attId.length = 0;
      $("#fileNewList").empty();
    });

    $("#reply-todo").click(function () {
      //触发全部验证
      var content = $("[name='contentRepluy']").val();
      if (content == null || content.trim() == '') {
        alert(message["http.response.empty"], 3);
        return;
      }
      if (content.length>1000) {
        alert(message["share.must.content.length.msg"], 3);
        return;
      }
      if (content != null && content.trim() != '') {
        $.ajax({
          url: "/experience/rest/reply/save",
          type: "post",
          data: {
            shareId: $("[id='shareCommentId']").val(),
            parentId: $("#reply").val(),
            content: $("[name='contentRepluy']").val()
          },
          success: function (data) {
            $("#comment-append").remove();
            appendComment();
            $("#experience-prompt-reply").modal("hide");
            $("[name='contentRepluy']").val("");
            $("#reply").val("");
            hideLoading();
          },
          error: function (data) {
            console.log(data);
            alert(message["http.response.error"], 3);
            hideLoading();
          }
        });
      }

    });

    appendComment();
  });

  function appendComment() {
    $.ajax({
      url: "/experience/shareComment",
      type: "get",
      data: {
        id: $("[id='shareCommentId']").val()
      },
      success: function (data) {
        console.log(data);
        $("#experience-comment-append").after(data);
        hideLoading();
      },
      error: function (data) {
        console.log(data);
        alert(message["http.response.error"], 3);
        hideLoading();
      }
    });
  }

  $('#delete-repy-close').click(function () {
    $("#delete-reply").modal("hide");
  });
  $('#delete-comment-close').click(function () {
    $("#delete-prompt").modal("hide");
  });

  function onDeleteComment(commentId) {
    $("#delete-prompt").modal('show');

    $("#delete_comment_id").val(commentId);
  }

  $("#delete-comment").click(function () {
    var id = $("#delete_comment_id").val();
    $("#delete-prompt").modal('hide');
    $.ajax({
      url: "/experience/rest/commentDelete",
      type: 'post',
      data: {
        id: id
      },
      success: function (data) {
        alert(message['http.response.success']);
        $("#comment-append").remove();
        appendComment();
      },
      error: function (data) {
        alert(message[data.responseJSON.message], 3);
      }
    });
  });

  function onDeleteReply(commentId) {

    $("#delete-reply-modal").modal('show');
    $("#delete_reply_id").val(commentId);
  }

  $("#delete-reply").click(function () {
    var id = $("#delete_reply_id").val();
    $("#delete-reply-modal").modal('hide');
    $.ajax({
      url: "/experience/rest/replyDelete",
      type: "post",
      data: {
        id: id
      },
      success: function (data) {

        alert(message['http.response.success']);
        $("#comment-append").remove();
        appendComment();
      },
      error: function (data) {
        alert(message[data.responseJSON.message], 3);
      }
    });
  });

  function onOpen() {
    $("#experience-prompt-comment").modal("show");
//    $("#experience-prompt-comment").prop('scrollTop',0);
    var $model = $('#experience-prompt-comment');
    $model.on('shown.bs.modal', function () {
      // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
      // 执行一些动作...
      // 添加“添加文件”的按钮，
      uploader.addButton({
        id: '#fileNewPicker',
        label: message["series.images.window.btn"]
      });
    });
  }

  function onOpenComment(commentId) {
    $("#experience-prompt-reply").modal("hide");
    $("[name='contentRepluy']").val("");
    $("#experience-prompt-reply").modal("show");
    var $model = $('#experience-prompt-reply');
    $("#reply").val("");
    $("#reply").val(commentId);
    $model.on('shown.bs.modal', function () {
      // 执行一些动作...
      // 添加“添加文件”的按钮，
    });
  }

  function imgShow(outerdiv, innerdiv, bigimg, _this) {
    var src = _this.attr("src");//获取当前点击的pimg元素中的src属性
    $(bigimg).attr("src", src);//设置#bigimg元素的src属性

    /*获取当前点击图片的真实大小，并显示弹出层及大图*/
    $("<img/>").attr("src", src).load(function () {
      var windowW = $(window).width();//获取当前窗口宽度
      var windowH = $(window).height();//获取当前窗口高度
      var realWidth = this.width;//获取图片真实宽度
      var realHeight = this.height;//获取图片真实高度
      var imgWidth, imgHeight;
      var scale = 0.8;//缩放尺寸，当图片真实宽度和高度大于窗口宽度和高度时进行缩放

      if (realHeight > windowH * scale) {//判断图片高度
        imgHeight = windowH * scale;//如大于窗口高度，图片高度进行缩放
        imgWidth = imgHeight / realHeight * realWidth;//等比例缩放宽度
        if (imgWidth > windowW * scale) {//如宽度扔大于窗口宽度
          imgWidth = windowW * scale;//再对宽度进行缩放
        }
      } else if (realWidth > windowW * scale) {//如图片高度合适，判断图片宽度
        imgWidth = windowW * scale;//如大于窗口宽度，图片宽度进行缩放
        imgHeight = imgWidth / realWidth * realHeight;//等比例缩放高度
      } else {//如果图片真实高度和宽度都符合要求，高宽不变
        imgWidth = realWidth;
        imgHeight = realHeight;
      }
      $(bigimg).css("width", imgWidth);//以最终的宽度对图片缩放

      var w = (windowW - imgWidth) / 2;//计算图片与窗口左边距
      var h = (windowH - imgHeight) / 2;//计算图片与窗口上边距
      $(innerdiv).css({"top": h, "left": w});//设置#innerdiv的top和left属性
      $(outerdiv).fadeIn("fast");//淡入显示#outerdiv及.pimg
    });

    $(outerdiv).click(function () {//再次点击淡出消失弹出层
      $(this).fadeOut("fast");
    });
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

  .text-with-hr {
    text-align: center;
    position: relative;
    z-index: 2;
  }

  .text-with-hr:before {
    position: absolute;
    content: '';
    top: 20px;
    left: 0px;
    width: 100%;
    border-bottom: 1px solid #d4d4d4;
    z-index: -1;
  }

  a {
    cursor: pointer;
  }

</style>