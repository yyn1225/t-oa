<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <h2><dic data-dic="header.list.search"></dic></h2>
        </header>
        <div>
            <div class="widget-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div class="row">
                            <label class="col col-1 text-align-right"
                                   style="padding-top: 7px; "><dic data-dic="org.role.list.name"></dic>:</label>
                            <label class="col col-2 input">
                                <input type="text" id="name-query"/>
                            </label>

                            <label class="col col-1 pull-right">
                                <a class="btn btn-primary query-btn" id="btn_query">
                                    <dic data-dic="header.list.button.search"></dic>
                                </a>
                            </label>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>

    </div>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <span class="widget-icon"> <i class="fa fa-table"></i></span>
            <h2 data-dic="org.role.list.title"></h2>
            <a class="btn btn-primary btn-xs operate" style="float: right" data-role-id="0">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>

            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <table id="dt_basic_user" class="table table-striped table-bordered table-hover"
                       width="100%">
                </table>
            </div>
        </div>


    </div>
    <!-- 删除模态框（Modal） -->
    <div class="modal fade" id="del_modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 350px;">
            <input type="hidden" id="roleId">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        <dic data-dic="admin.window.attention"></dic>
                    </h4>
                </div>
                <div class="modal-body no-padding">
                    <form class="smart-form">
                        <fieldset class="textPop">
                            <dic data-dic="prompt.message.delete"></dic>
                        </fieldset>
                        <footer>
                            <button type="button" id="ok_del" class="btn btn-primary">
                                <dic data-dic="button.confirm"></dic>
                            </button>
                            <button type="button" class="btn btn-default" id="delete-close"
                                    data-dismiss="modal">
                                <dic data-dic="button.cancel"></dic>
                            </button>
                        </footer>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div id="second"></div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="resource_modal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <input type="hidden" id="roleId">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        <dic data-dic="window.exhibition.resource"></dic>
                    </h4>
                </div>
                <div class="modal-body">
                    <div id="resource_html"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                    <button type="button" class="btn btn-primary" id="resourceSubmit">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="ru_model" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel1"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel1">
                        <dic data-dic="body.list.role"></dic>
                    </h4>
                </div>
                <div class="modal-body">
                    <div id="ru_opt_html"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                    <button type="button" class="btn btn-primary" id="ru_update">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</article>


<script type="text/javascript">
    var dt;
    pageSetUp();

    $("#dialog_table").modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });
    $("#del_modal").modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });
    $("#myModal").modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        dt = new Datatables();
        dt.init({
            src: $("#dt_basic_user"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "role/rest/list"},
                columns: [
                    DTColumnKit.order,
                    {data: "name", title: message["org.role.list.name"]},
                    {data: "username", title: message["org.role.list.creater"],width: "300px",},
                    {
                        data: "createTime", title: message["org.role.list.createTime"],
                        width: "220px",
                        render: function (data, type, row, meta) {
                            if (data === null) {
                                return data;
                            } else {
                                return (new Date(data)).Format("yyyy-MM-dd");
                            }
                        }
                    },
                    {
                        data: "id", title: message["org.role.list.operate"],
                        width: "290px",
                        render: function (data) {
                            var editHtml = '<a class="btn btn-xs btn-primary operate" ' +
                                    'href="javascript:;" data-role-id="' + data + '">'+
                                    message["org.user.list.operate"]+'</a>&nbsp;';
                            var removeHtml = '<a class="btn btn-xs btn-primary remove" href="javascript:;" data-role-id="' + data + '">'
                            + message["body.list.delete"] +'</a>';
                            var resourceHtml = '<a class="btn btn-xs btn-primary resource" ' +
                                    'href="javascript:;" data-role-id="' + data + '">'+
                                    message["role.list.resource"]+'</a>&nbsp;';
                            var userHtml = '<a class="btn btn-xs btn-primary user-role" ' +
                                    'href="javascript:;" data-role-id="' + data +
                                    '">'+ message["role.list.user"] +'</a>&nbsp;';
                            return editHtml + resourceHtml + userHtml + removeHtml;
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4]
                }],
                drawCallback: function () {

                }
            }
        });
        $article = $('article');
        $article.on('click', '.operate', function () {
            var id = $(this).data('role-id');
            var $second = $('#second');
            $.ajax({
                url: '/role/addOrEdit',
                type: 'get',
                data: {id: id},
                success: function (data) {
                    $second.show();
                    $second.html(data);
                    $("#dialog_table").modal("show");
                }
            });
        });

        $article.on('click', '.resource', function () {
            var id = $(this).data('role-id');
            var $resourceModal = $('#resource_modal');
            var $resourceHtml = $('#resource_html');
            $.ajax({
                url: '/role/resouce/item',
                type: 'get',
                data: {roleId: id},
                success: function (data) {
                    $resourceHtml.html(data);
                    $resourceModal.modal("show");
                }
            });
        });
        $article.on('click', '.user-role', function () {
            var id = $(this).data('role-id');
            var url = "/role/user/item";
            var $modelHtml = $("#ru_opt_html");
            var $model = $("#ru_model");
            $.ajax({
                url: url,
                type: 'GET',
                data: {roleId: id},
                success: function (data) {
                    $model.modal('show');
                    $modelHtml.html(data);
                }
            });
        });
        $('#ru_update').on('click', function () {
            var id_array = new Array();
            var userVals = $('#userIds').select2().val();
            userVals.forEach(function(val){
                id_array.push(val);
            });
            var idstr = id_array.join(',');
            if (idstr == '' || !idstr) {
                alert(message["no.choice.of.users"]);
                return false;
            }
            $.ajax({
                url: '/api/role/user/update',
                type: 'POST',
                data: {roleId: $('#role_id').val(), userIds: idstr},
                success: function (data) {
                    $("#ru_model").modal('hide');
                },
                error: function () {
                    alert(message["operation.failed"]);
                }
            });
        });
        $('#resourceSubmit').on('click', function () {
            var id_array = new Array();
            var $resourceModal = $('#resource_modal');
            var $resourceHtml = $('#resource_html');
            $('input:checked').each(function () {
                id_array.push($(this).val());
            });
            var idstr = id_array.join(',');
            if (idstr == '' || !idstr) {
                alert(message["please.select.a.piece.of.data"]);
                return false;
            }
            $.ajax({
                url: '/api/role/resource/update',
                type: 'POST',
                data: {roleId: $('#id').val(), resourceIds: idstr},
                success: function (data) {
                    $resourceModal.modal("hide");
                }
            });
        });
        $("#btn_query").click(function () {
            dt.addAjaxParam('name', $('#name-query').val());
            dt.submitFilter();
        });

        $article.on('click', '.remove', function () {
            var id = $(this).data('role-id');
            $("#roleId").val(id);
            $("#del_modal").modal("show");
        });

        $article.on('click', '#lanager_btn', function () {
            $("#myModal").modal("show");
        });

        $article.on('click', '#add_lang_btn', function () {
            var html = $('#langerTmpl').html().replace("tttt", "select2");
            $('#lang_form fieldset:last').after(html);
            pageSetUp();
        });
        $article.on('click', '.remove-lang', function (target) {
            $($($(target.currentTarget).parent()).parent()).remove();//找到父级的父级，然后删除
        });
        $article.on('click', '.lang-ok-btn', function () {
            langArray = {};
            var fieldset = $('#lang_form fieldset');
            for (var i = 0; i < fieldset.length; i++) {
                var tmp = fieldset[i];
                var lang = $(tmp).find(".select2").select2("val");
                var val = $(tmp).find('input[name="lang-val"]').val();
                langArray[lang] = val;
            }
            $('#myModal').modal('hide');
            $('#lang_val').val(JSON.stringify(langArray));
        });

        $("#ok_del").click(function () {
            var id = $("#roleId").val();
            $.ajax({
                url: '/role/delete',
                type: 'get',
                data: {id: id},
                success: function (data) {
                    dt.resetFilter();
                    $("#del_modal").modal("hide");
                },
                error: function (data) {
                    console.log(data);
                    alert(data.responseJSON.message);
                }
            });
        });
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
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }
</script>

<style>
    .modal-header{
        padding: 9px;
    }
</style>
</#compress>