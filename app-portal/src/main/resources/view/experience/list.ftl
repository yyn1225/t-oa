<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
  <div id="first">
    <div class="jarviswidget" data-widget-editbutton="false" style="margin: 0 0 10px">
      <header>
        <h2>
          <dic data-dic="header.list.search"></dic>
        </h2>
      </header>
      <div>
        <div class="widget-body no-padding" style="min-height: 80px">
          <form class="smart-form">
            <fieldset style="height: 50px !important;">
              <div class="row">
                <label class="col col-1 text-align-right padding">
                  <dic data-dic="product.panel.list.name"></dic>
                  :
                </label>
                <label class="col col-2 input">
                  <input type="text" id="productName" >
                </label>

                <label class="col col-1 text-align-right padding">
                  <dic data-dic="experience.list.people"></dic>
                  :
                </label>
                <label class="col col-2 input">
                  <input type="text" id="createUserName">
                </label>

                <label class="col col-1 text-align-right padding">
                  <dic data-dic="experience.list.title"></dic>
                  :
                </label>
                <label class="col col-2 input">
                  <input type="text" id="title">
                </label>

                <label class="col col-1 pull-right">
                  <a class="btn btn-primary btn-sm query-btn" id="btn_query">
                    <dic data-dic="header.list.button.search"></dic>
                  </a>
                </label>
              </div>
            </fieldset>
          </form>
        </div>
      </div>
    </div>
    <div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false">
      <header>
        <span class="widget-icon"> <i class="fa fa-table"></i></span>
        <h2>
          <dic data-dic="experience.list"></dic>
        </h2>
        <a class="btn btn-primary btn-xs" style="float: right" data-spare-id="0"
           data-dic="header.list.button.add" id="add_offer_btn"></a>
      </header>
      <div>
        <div class="widget-body no-padding">
          <table id="dt_basic_quotes" class="table table-striped table-bordered table-hover"
                 width="100%">
          </table>
        </div>
      </div>
    </div>
  </div>
  <div id="second" hidden >
    <div>
      <div  >
        <h5>
          <dic data-dic="main.title.spare"></dic>
        </h5>
        <div class="  no-padding">
          <form class="smart-form">
            <div id="window-html"></div>
            <footer>
              <button type="button" id="approval-todo" class="btn btn-primary">
                <dic data-dic="button.confirm"></dic>
              </button>
              <button type="button" class="btn btn-default"
                      id="cancal-edit" <#--data-dismiss="modal"-->>
                <dic data-dic="button.cancel"></dic>
              </button>
            </footer>
          </form>
        </div>
      </div>
    </div>
  </div>
  <div id="three" hidden>
    <div  >
      <div  >
        <h5>
          <dic data-dic="main.title.spare"></dic>
        </h5>
        <div  >
          <form class="smart-form">
            <div id="window-details">

            </div>
            <footer>
              <button type="button" id="cancal-detail" class="btn btn-default" data-dismiss="modal">
                <dic data-dic="button.back"></dic>
              </button>
            </footer>
          </form>
        </div>
      </div>
    </div>
  </div>
</article>
<div class="modal fade" id="delete-list" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 350px;margin: 30px auto;">
        <input type="hidden" value="0" id="delete_id">
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
                        <button type="button" class="btn btn-default" id="delete-close"
                                data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                        <button type="button" id="delete-list-confirm" class="btn btn-primary">
                            <dic data-dic="button.confirm"></dic>
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

<!--引入CSS-->
<link rel="stylesheet" type="text/css" href="../../../static/js/plugin/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="../../../static/js/plugin/webuploader/image.css">
<link rel="stylesheet" type="text/css" href="../../../static/css/lightbox.min.css">
<!--引入JS-->
<script type="text/javascript"
        src="../../../static/js/plugin/webuploader/webuploader.html5only.js"></script>
<#--lightbox-->
<script type="text/javascript"
        src="../../../static/js/lightbox.min.js"></script>
<script type="text/javascript">
  var $article = $("article");
  var dt;
  var uploader;
  var attId;

  $(document).ready(function () {
    pageSetUp();
    $("dic").each(function () {
      $(this).html(message[$(this).data("dic")]);
    });
    $('[data-dic]').each(function () {
      $(this).html(message[$(this).data("dic")]);
    });
    dt = new Datatables();
    dt.init({
      src: $("#dt_basic_quotes"),
      dataTable: {
        bStateSave: false,
        ajax: {url: "/experience/rest/list?status=1"},
        columns: [
          DTColumnKit.order,
          {
            data: "productName", title: message["product.panel.list.name"],
            width: '140px'
          },
          {
            data: "title",
            title: message["experience.list.title"],
            className: 'text-right',
            width: "120px"
          },
          {
            data: "content", title: message["experience.list.content"], className: 'text-right'
          },
          {
            data: "createTime", title: message["portal.approval.shareTime"],
            width: "140px",
            render: function (data, type, row, meta) {
              if (data === null) {
                return data;
              } else {
                return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
              }
            }
          },
          {
            data: "createUserName",
            title: message["experience.list.people"],
            className: 'text-right',
            width: "120px"
          },
          {
            data: "id", title: message["quotes.offer.list.operate"],
            width: "250px",
            render: function (data, type, row, meta) {
                if(row.isAllow == '1'){
                    return '<a class="btn btn-xs btn-primary operate" data-id="' + row.id + '">' +
                            message["body.list.edit"] + '</a>' +
                            '<a class="btn btn-xs btn-success viewOffer" data-id="' + row.id
                            + '"data-offer-id="'
                            + data + '">' +
                            message["body.list.viewOffer"] + '</a> '+' <a class="btn btn-xs btn-danger deleteShare" data-id="' + row.id
                            + '"data-offer-id="'
                            + data + '">' +
                            message["body.list.delete"] + '</a>';
                }else{
                    return '<a class="btn btn-xs btn-success viewOffer" data-id="' + row.id
                            + '"data-offer-id="'
                            + data + '">' +
                            message["body.list.viewOffer"] + '</a>' ;
                }

            }
          }
        ],
        columnDefs: [{
          orderable: false,
          targets: [0, 1, 2, 3, 4, 5, 6]
        }],
        drawCallback: function () {
        }
      }
    });
    //编辑
    $article.on('click', '.operate', function () {
      showLoading();
      var id = $(this).data('id');
      $.ajax({
        url: '/experience/edit',
        type: 'GET',
        data: {id: id},
        success: function (data) {
          console.log(data);
          $("#window-html").html(data);
          $("#second").show();
          $("#first").hide();
          var $model = $('#second');
          $model.on('shown.bs.modal', function () {
            // 执行一些动作...
            // 添加“添加文件”的按钮，
            uploader.addButton({
              id: '#filePicker',
              label: message["series.images.window.btn"]
            });
          });
          hideLoading();
        }
      });
    });

      //详情
    $article.on('click', '.viewOffer', function () {
      showLoading();
      var id = $(this).data('id');
      $.ajax({
        url: '/experience/details',
        type: 'GET',
        data: {id: id},
        success: function (data) {
          $("#window-details").html(data);
          $("#three").show();
          $("#first").hide();
          hideLoading();
        }
      });
    });

      //删除
      $article.on('click', '.deleteShare', function () {
          $("#delete-list").modal('show');
          $("#delete_id").val( $(this).data('id'));
      });

    $("#btn_query").click(function () {

      dt.addAjaxParam('productName', $('#productName').val());
      dt.addAjaxParam('title', $('#title').val());
      dt.addAjaxParam('createUserName', $('#createUserName').val());
      dt.submitFilter();
    });
  });

  $("#add_offer_btn").click(function () {
    showLoading();
    $.ajax({
      url: "/experience/item",
      type: "get",
      success: function (data) {
        $("#window-html").html(data);
        $("#second").show();
        $("#first").hide();
        var $model = $('#second');
        $model.on('shown.bs.modal', function () {
          // 执行一些动作...
          // 添加“添加文件”的按钮，
          uploader.addButton({
            id: '#filePicker',
            label: message["series.images.window.btn"]
          });
        });
        hideLoading();
      }
    });
  });
  //取消
  $("#cancal-edit").click(function () {
    $("#second").hide();
    $("#first").show();
  });
  //取消
  $("#cancal-detail").click(function () {
    $("#three").hide();
    $("#first").show();
  });

  $('#delete-close').click(function () {
      $("#delete-list").modal("hide");
  });

  $('#delete-list-confirm').click(function () {
      var id = $("#delete_id").val();
      $.ajax({
          url: "/experience/rest/share/delete",
          type: 'post',
          data: {
              id: id
          },
          success: function (data) {
              alert(message['http.response.success']);
              $("#delete-list").modal("hide");
              dt.resetFilter();
          },
          error: function (data) {
              alert(message[data.responseJSON.message], 3);
          }
      });
  });

  //保存
  $("#approval-todo").click(function () {
    
    //触发全部验证

    if (checkInput()) {
      $.ajax({
        url: "/experience/rest/share/save",
        type: "post",
        data: {
          id:$("#shareId").val(),
          seriesId: $("[name='product']").val(),
          title: $("[name='title']").val(),
          content: $("[name='content']").val(),
          images: JSON.stringify(attId)
        },
        success: function (data) {
          $("#second").hide();
          $("#first").show();
          hideLoading();
          dt.resetFilter();
        },
        error: function (data) {
          console.log(data);
          alert(message["http.response.error"], 3);
          hideLoading();
        }
      });
    }

  });
  //时间的格式化
  Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
      "M+": this.getMonth() + 1,
      //月份 
      "d+": this.getDate(),
      //日 
      "h+": this.getHours(),
      //小时 
      "m+": this.getMinutes(),
      //分 
      "s+": this.getSeconds(),
      //秒 
      "q+": Math.floor((this.getMonth() + 3) / 3),
      //季度 
      "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
      if (new RegExp("(" + k + ")").test(fmt)) {
        fmt = fmt.replace(RegExp.$1,
            (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
      }
    }
    return fmt;
  }
</script>
<style type="text/css">
  .btn-xs {
    margin-right: 5px;
  }

  .smart-form fieldset {
    padding: 20px 14px 5px;
    height: 50px;
  }
</style>
</#compress>