<#compress>
<article class="col-xs-12 <#if limit>col-sm-12 col-md-12 col-lg-12<#else>col-sm-7 col-md-7 col-lg-7</#if>" id="first">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <span class="widget-icon">
                <i class="fa fa-table"></i>
            </span>
            <#if !limit>
                <a class="btn btn-primary btn-xs" style="float: right" id="addNewSystem">
                    <dic data-dic="header.list.button.add"></dic>
                </a>
            </#if>
        </header>
        <div>
            <div class="widget-body no-padding">
                <table id="dt_basic_product_price" class="table table-striped table-bordered table-hover" width="100%">
                    <thead>
                        <tr>
                            <th><dic data-dic="header.panellist.button.system.name"></dic></th>
                            <th><dic data-dic="header.panellist.button.start.time"></dic></th>
                            <th><dic data-dic="header.panellist.button.end.time"></dic></th>
                            <th><dic data-dic="header.panellist.button.large.area"></dic></th>
                            <th><dic data-dic="header.panellist.button.operate"></dic></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</article>
<#if !limit>
<article class="col-xs-12 col-sm-5 col-md-5 col-lg-5" id="third">
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
            <span class="widget-icon" style=" margin-left: 0; width: 100px; text-align: left; padding-left: 10px;">
                <i class="fa fa-table"></i>
                <span id="last_assign_name"></span>
            </span>

            <a class="btn btn-primary btn-xs" style="float: right" id="assignSystems">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <table id="dt_basic_price_assign" class="table table-striped table-bordered table-hover" width="100%">
                    <thead>
                        <tr>
                            <th><dic data-dic="header.panellist.button.distribution.area"></dic></th>
                            <th><dic data-dic="header.panellist.button.distribution.time"></dic></th>
                            <th><dic data-dic="header.panellist.button.distribution.people"></dic></th>
                            <th><dic data-dic="header.panellist.button.distribution.operate"></dic></th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</article>
</#if>

<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="second" style="display: none;"></article>

<div class="modal fade" id="assign-prompt" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 350px;">
        <div class="modal-content">
            <div class="modal-header">
                <h5><dic data-dic="header.panellist.button.distribution"></dic></h5>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form" style="padding-top: 20px;">
                    <label class="col col-md-4" style="padding-top: 7px;text-align: right">
                        <dic data-dic="header.panellist.button.apply.department">：
                    </label>
                    <label class="col col-md-8">
                        <input id="assign-select-input" type="text" class="form-control">
                    </label>
                </form>
                <footer style="text-align: right; padding-top: 48px; padding-bottom: 10px; padding-right: 10px;">
                    <button style="margin-right: 20px;" type="button" class="btn btn-primary" id="button-ok">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                </footer>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="security-prompt" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 350px;">
        <div class="modal-content">
            <div class="modal-header">
                <h5><dic data-dic="body.list.authorize"></dic></h5>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form" style="padding-top: 20px;">
                    <label class="col col-md-4" style="padding-top: 7px;text-align: right">
                        <dic data-dic="body.list.authorize.people"></dic>：
                    </label>
                    <label class="col col-md-8">
                        <input type="hidden" id="system-id-input"/>
                        <input id="user-select-input" type="text" class="form-control">
                    </label>
                </form>
                <footer style="text-align: right; padding-top: 48px; padding-bottom: 10px; padding-right: 10px;">
                    <button style="margin-right: 20px;" type="button" class="btn btn-primary" id="button-security-ok">
                        <dic data-dic="button.confirm"></dic>
                    </button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <dic data-dic="button.cancel"></dic>
                    </button>
                </footer>
            </div>
        </div>
    </div>
</div>

<div class="modal fade in" id="delete-confirm" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <input type="hidden" id="data-id" value="0"/>
        <input type="hidden" id="data-type" value="0"/>
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <dic data-dic="admin.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="prompt.message.delete"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="btn-confirm">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script type="text/javascript" src="../../../static/js/plugin/webuploader/webuploader.html5only.js"></script>
<script type="text/javascript">
    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    var systemTable = new Datatables();
    var assignTable = new Datatables();

    var lastSystemId=0;
    var lastArea =0;

    systemTable.init({
        src: $("#dt_basic_product_price"),
        loadingMessage: message["datatable.loading"],
        dataTable: {
            bStateSave: false,
            ajax: {url: "/panel/price/systems"},
            columns: [
                {data: "name", 'orderable': false},
                {data: "startTime", 'orderable': false,render:function(data){
                        if(!data){
                            return data;
                        }
                        return (new Date(data)).format("yyyy-MM-dd");
                    }
                },
                {data: "endTime", 'orderable': false,render:function(data){
                        if(!data){
                            return data;
                        }
                        return (new Date(data)).format("yyyy-MM-dd");
                    }
                },
                {data: "areaName", 'orderable': false},
                {
                    data: "id", 'orderable': false,
                    render: function (data, type, row, meta) {
                        console.log(data);
                        var user = '<a class="btn btn-xs btn-primary operate" data-area="'+row.area+'" data-name="'+row.name+'" data-id="' + data + '">'+message["header.panellist.button.apply"]+'</a>';
                        var deletes = '<a class="btn btn-xs btn-primary delete" data-id="' + data + '">'+message["header.panellist.button.delete"]+'</a>';
                        var edit = '<a class="btn btn-xs btn-primary edit" data-id="' + data + '">'+message["header.panellist.button.edit"]+'</a>';
                        var security = '<a class="btn btn-xs btn-primary security" data-id="' + data + '">'+message["body.list.authorize"]+'</a>';
                        <#if limit>
                            return edit;
                        <#else>
                             return user + "&nbsp;&nbsp;" + deletes + "&nbsp;&nbsp;" + edit + "&nbsp;&nbsp;" + security;
                        </#if>
                    }
                }
            ],
            drawCallback: function () {
                $(".btn.btn-xs.btn-primary.operate").off("click").click(function(){
                    lastSystemId=$(this).data("id");
                    lastArea=$(this).data("area");
                    assignTable.addAjaxParam('systems', lastSystemId);
                    assignTable.submitFilter();
                    $("#last_assign_name").html($(this).data("name"));
                });

                $(".btn.btn-xs.btn-primary.edit").off("click").click(function(){
                    $("#first").hide();
                    $("#third").hide();
                    var id=$(this).data("id");
                    $.ajax({
                        type:"get",
                        url:"/price/page/edit",
                        data:{systemId:id},
                        success:function (html) {
                            $("#second").html(html);
                            $("#second").show();
                        }
                    });
                });

                $(".btn.btn-xs.btn-primary.delete").off("click").click(function () {
                    $("#data-id").val($(this).data("id"));
                    $("#data-type").val(1);
                    $("#delete-confirm").modal("show");
                });

                $(".btn.btn-xs.btn-primary.security").off("click").click(function () {
                     var id=$(this).data("id");
                     $.ajax({
                         type:"get",
                         data:{systemId:id},
                         url:"/panel/price/securitys/users",
                         beforeSend:showLoading,
                         success:function (data) {
                             if(data.code==0){
                                 alert(message[data.message],2);
                             }else{
                                 var users= data.data;
                                 var userList = [];
                                 userList.push({
                                     id:0,
                                     text:message["price.assign.security.empty"]
                                 });
                                 users.forEach(function (item) {
                                     userList.push({
                                         id:item.id,
                                         text:item.name
                                     });
                                 });
                                 $("#user-select-input").select2({
                                     data:userList
                                 });
                                 hideLoading();
                                 $("#system-id-input").val(id);
                                 $("#security-prompt").modal("show");
                             }
                             hideLoading();
                         },error:function (data) {
                             hideLoading();
                            alert(message["http.response.error"],3);
                         }
                     })
                });
            }
        }
    });

    assignTable.init({
        src: $("#dt_basic_price_assign"),
        loadingMessage: message["datatable.loading"],
        dataTable: {
            bStateSave: false,
            ajax: {url: "/panel/price/asssign"},
            columns: [
                {data: "areaName", 'orderable': false},
                {data: "assignTime", 'orderable': false,render:function(data){
                        if(!data){
                            return data;
                        }
                        return (new Date(data)).format("yyyy-MM-dd");
                    }
                },
                {data: "assignName", 'orderable':false },
                {
                    data: "id", 'orderable': false,
                    render: function (data) {
                       return '<a class="btn btn-xs btn-primary ass-delete" data-id="' + data + '"><dic data-dic="header.panellist.button.delete"></dic></a>';
                    }
                }
            ],
            drawCallback: function () {
                $("dic").each(function () {
                    $(this).html(message[$(this).data("dic")]);
                });
                $(".ass-delete").off("click").click(function () {
                   var id=$(this).data("id");
                    $("#data-id").val($(this).data("id"));
                    $("#data-type").val(2);
                    $("#delete-confirm").modal("show");
                });
            }
        }
    });
    
    $("#button-security-ok").off("click").click(function () {
        debugger;
        $.ajax({
            url:"/panel/price/security/save",
            type:"post",
            data:{systemId:$("#system-id-input").val(),user:$("#user-select-input").select2("val")},
            beforeSend:showLoading,
            success:function (data) {
                hideLoading();
                if(data==true){
                    alert(message["alert.message.success"]);
                    $("#security-prompt").modal("hide");
                }else if(data==false){
                    alert(message["alert.message.systemError"],2);
                }else{
                    alert(message[data],2);
                }
            },error:function(){
                hideLoading();
                alert(message["alert.message.systemError"],3);
            }
        });
    });

    $("#button-ok").off("click").click(function () {
        var area=$("#assign-select-input").select2("val");
        if(!area){
            alert(message[""]);
            return;
        }
        $.ajax({
            url:"/panel/price/assign/save",
            type:"post",
            data:{
                systems:lastSystemId,
                area:area
            },
            success:function (res) {
                if(res==true){
                    assignTable.addAjaxParam('systems', lastSystemId);
                    assignTable.submitFilter();
                    $("#assign-prompt").modal("hide");
                }else{
                    alert(message[res],2);
                }
            },error:function () {
                alert(message["body.exception.operate"],3);
            }
        })
    })

    $("#assignSystems").off("click").click(function(){
        if(0==lastArea){
            alert(message["body.alert.message.select.first"]);
            return;
        }
        $.ajax({
            type:"get",
            data:{parent:lastArea},
            url:"/organization/rest/deparment/parents",
            success:function (data) {
                var dataList=[];
                data.forEach(function (item) {
                    dataList.push({
                        id:item.id,
                        text:item.name
                    });
                });
                $("#assign-select-input").select2({data:dataList,minimumResultsForSearch:-1});
                $("#assign-prompt").modal("show");
            }
        });
    });

    $("#addNewSystem").off("click").click(function () {
        $("#first").hide();
        $("#third").hide();
        $.ajax({
            type:"get",
            url:"/price/page/add",
            success:function (html) {
                $("#second").html(html);
                $("#second").show();
            }
        });
    });

    $("#btn-confirm").off("click").click(function () {
        var id=$("#data-id").val();
        var type=$("#data-type").val();
        if(type==1){ //删除价格体系
            $.ajax({
                type:"post",
                url:"/panel/price/delete",
                data:{id:id},
                success:function (data) {
                    if(data==true){
                        systemTable.submitFilter();
                        $("#delete-confirm").modal("hide");
                        alert(message["alert.message.success"]);
                    }else{
                        alert(message["alert.message.systemError"],2);
                    }
                },error:function (data) {
                    alert(message[data.responseJSON.message],3);
                }
            });
        } else if(type==2){ //删除价格体系分配
            $.ajax({
                type:"post",
                url:"/panel/price/assign/delete",
                data:{assignId:id},
                success:function (data) {
                    if(data){
                        assignTable.submitFilter();
                        $("#delete-confirm").modal("hide");
                        alert(message["alert.message.success"]);
                    }else{
                        alert(message["alert.message.success"],2);
                    }
                },error:function () {
                    alert(message["alert.message.systemError"],3);
                }
            })
        }
    });

    pageSetUp();
</script>

<style>

    .modal.fade.in .select2-container .select2-choice{
        border: 0 !important;
    }

    .webuploader-element-invisible{
        display: none !important;
    }

    .btn-xs {
        margin-right: 5px;
        margin-top: 5px;
    }

    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px;
    }

    .padding7{
        padding-top: 7px !important;
    }

    .dt-toolbar {
        display: block;
        position: relative;
        padding: 0;
        height: 32px;
        width: 100%;
        float: left;
        border: 1px solid #ccc;
        background: #f7f7f7;
    }

    #second div.dataTables_paginate{
        margin: 0 !important;
        padding:2px;
    }
</style>
</#compress>