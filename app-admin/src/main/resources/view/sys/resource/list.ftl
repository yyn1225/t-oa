<#compress>
    <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <h2><dic data-dic="header.list.search"></dic></h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <form class="smart-form">
                        <fieldset>
                            <div class="row">
                                <label class="col col-1 text-align-right" style="padding-top: 7px; ">
                                    <dic data-dic="header.list.input.name"></dic>
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="name-query"/>
                                </label>
                                <label class="col col-1 text-align-right" style="padding-top: 7px;">
                                    <dic data-dic="header.list.input.code"></dic>
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="code-query"/>
                                </label>
                                <label class="col col-4 text-align-right">
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
                <h2 data-dic="org.resource.list.title"></h2>
                <a class="btn btn-primary btn-xs operate" style="float: right" data-resource-id="0" >
                    <dic data-dic="header.list.button.add"></dic>
                </a>
            </header>
            <div>

                <div class="jarviswidget-editbox"></div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_resource" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>


        </div>
        <#--删除提示弹窗-->
            <div class="modal fade" id="delete-prompt" tabindex="-1" role="dialog">
                <div class="modal-dialog" style="width: 350px;">
                    <input type="hidden" value="0" id="formula-type">
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
                                    <button type="button" id="delete-choose" class="btn btn-primary">
                                        <dic data-dic="header.list.button.ok"></dic>
                                    </button>
                                    <button type="button" class="btn btn-default" id="delete-close" data-dismiss="modal">
                                        <dic data-dic="header.list.button.cancel"></dic>
                                    </button>
                                </footer>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </div>
    <div id="second" style="display: none"></div>
</article>


<script type="text/javascript">
    var deleteId;
    var resourceTable;
    var $delete_prompt =$('#delete-prompt');
    $(document).ready(function(){
        $("[data-dic]").each(function(){
            $(this).html(message[$(this).data("dic")]);
        });

        resourceTable =  new Datatables();
        resourceTable.init({
            src : $("#dt_basic_resource"),
            loadingMessage: message["datatable.loading"],
            dataTable : {
                bStateSave: false,
                ajax: { url: "resource/rest/list" },
                columns   : [
                    DTColumnKit.order,
                    {data:"name",title:message["org.resource.list.name"],width:"160px"},
                    {data:"code",title:message["org.resource.list.code"], width: "160px"},
                    {data:"parentName",title:message["org.resource.list.parent"], width: "170px"},
                    {data:"icon",title:message["org.resource.list.icon"], width: "240px",
                        render: function (data, type, row, meta) {
                            return '<div class="td-show" '+
                                    'title="'+ data +'">'+ '<i class="'+ data +'"></i>'+ data +'</div>';
                        }
                    },
                    {data:"url",title:message["org.resource.list.url"], width: "160px"},
                    {data:"id",title:message["org.resource.list.operate"], width: "120px",
                        render: function (data) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;" data-resource-id="'+data+'">'+message["body.list.edit"]+'</a><a class="btn btn-xs btn-primary delete" data-resource-id="'+data+'">'+message["body.list.delete"]+'</a>';
                            }
                        }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0,1,2,3,4,5,6]
                }],
                drawCallback:function(){
                    //关系表删除
                    $('.delete').off("click").click(function (e) {
                        e.preventDefault();
                        $delete_prompt.modal("show");
                        deleteId = $(this).data("resource-id");
                    });
                }
            }
        });

        $('article').on('click','.operate',function () {
            var id = $(this).data("resource-id");
            var url = "/resource/manage";
            var $second = $('#second');
            var $first = $('#first');
            showLoading();
            $.ajax({
                url:url,
                type:'GET',
                data:{id:id},
                success:function (data) {
                    $first.hide();
                    $second.html(data);
                    $second.show();
                    hideLoading();
                }
            });
        });
        $("#btn_query").click(function(){
            showLoading();
            resourceTable.addAjaxParam('name', $('#name-query').val());
            resourceTable.addAjaxParam('code', $('#code-query').val());
            resourceTable.submitFilter();
            hideLoading();
        });
    });
     //删除提示弹窗
    $delete_prompt.modal({
         backdrop: 'static',
         keyboard: false,
         show: false
     });

    $('#delete-choose').click(function () {
        $delete_prompt.modal("hide");
        $.ajax({
            url:"/resource/rest/delete",
            type:'POST',
            data:{resource:deleteId},
            success:function (data) {
                    alert(message['alert.message.success']);
                    resourceTable.submitFilter();
            },
            error:function (data) {
                if (data.responseJSON.message === "20004") {
                    alert(message['alert.message.resourceError'],2);
                }else {
                    alert(message['alert.message.systemError'], 3);
                }
                hideLoading();
            }
        });

    });
    $('#delete-close').click(function () {
        $delete_prompt.modal("hide");
    });
</script>

<style>
    .delete{
        margin-left: 5px;
    }
    .modal-header{
     padding: 9px;
    }
</style>
</#compress>