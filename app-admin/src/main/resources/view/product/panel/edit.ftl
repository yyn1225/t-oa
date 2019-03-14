<#compress>
<section id="widget-grid" class="">
    <div class="row">
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12" xmlns="http://www.w3.org/1999/html">
            <div id="second">
                <div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false"
                     data-widget-togglebutton="false" data-widget-deletebutton="false"
                     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
                    <header>
                        <ul id="widget-tab-1" class="nav nav-tabs pull-left">
                            <li class="active">
                                <a data-toggle="tab" href="#hr1">
                                    <i class="fa fa-lg fa-gear"></i>
                                    <span class="hidden-mobile hidden-tablet">
                                        <dic data-dic="product.panel.tab.basic"></dic>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr2">
                                    <i class="fa fa-lg fa-cube"></i>
                                    <span class="hidden-mobile hidden-tablet">
                                        <dic data-dic="product.panel.tab.parameter"></dic>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)"  data-standard-id="1">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet">
                                        <dic data-dic="product.panel.tab.standard"></dic>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)" data-standard-id="2">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet">
                                        <dic data-dic="product.panel.tab.match"></dic>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)" data-standard-id="3">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet">
                                        <dic data-dic="product.panel.tab.free"></dic>
                                    </span>
                                </a>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox"></div>
                        <div class="widget-body no-padding">
                            <div class="tab-content padding-10">
                                <div class="tab-pane fade in active" id="hr1">
                                    <#include "basic.ftl">
                                </div>
                                <div class="tab-pane fade" id="hr2">
                                    <#include "parameter.ftl">
                                </div>
                                <div class="tab-pane fade" id="hr3">
                                    <#include "../standard/list.ftl">
                                </div>
                            </div>
                            <div class="widget-footer">
                                <div class=" text-align-right" >
                                    <a class="btn btn-primary btn-sm" id="submit_btn" style="margin-right: 8px"
                                        data-dic="body.list.submit"></a>
                                    <a class="btn btn-default btn-sm"  onclick="back()" style="margin-right: 38px"
                                        data-dic="body.list.back"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <#--备件信息弹窗-->
                <div class="modal fade" id="dialog_table" tabindex="-1" role="dialog">
                    <div class="modal-dialog" style="width: 900px;">
                        <input type="hidden" value="0" id="spare-type">
                        <div class="modal-content" style="width: 964px">
                            <div class="modal-header">
                                <h4><dic data-dic="admin.spare.chooseSpare"></dic></h4>
                            </div>
                            <div class="modal-body no-padding">
                                <form class="smart-form">
                                    <fieldset class="sparePop">
                                        <fieldset style="height: 50px !important;">
                                            <label class="col col-1 text-align-right padding">
                                                <dic data-dic="product.spare.list.material"></dic>:
                                            </label>
                                            <label class="col col-2 input">
                                                <input type="text" id="material-query"/>
                                            </label>
                                            <label class="col col-1 text-align-right padding">
                                                <dic data-dic="product.spare.list.type"></dic>:
                                            </label>
                                            <label class="col col-2 input">
                                                <input type="text" id="type-query"/>
                                            </label>

                                            <label class="col col-1 text-align-right">
                                                <a class="btn btn-primary query-btn" id="btn_query_spare">
                                                    <dic data-dic="header.list.button.search"></dic>
                                                </a>
                                            </label>
                                        </fieldset>
                                        <div id="choose_spare_dialog" class="custom-scroll table-responsive">
                                            <table id="dt_basic_spare" class="table table-striped table-bordered
                                             table-hover" style="width: 100%">


                                            </table>
                                        </div>
                                    </fieldset>
                                    <footer>
                                        <button type="button" id="spare-choose" class="btn btn-primary"><dic data-dic="admin.window.choice"></dic></button>
                                        <button type="button" class="btn btn-default" id="spare-close" data-dismiss="modal">
                                            <dic data-dic="button.cancel"></dic>
                                        </button>
                                    </footer>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </div>
</section>
<script type="text/javascript">
    $(".select2").each(function () {
        $(this).attr('data-placeholder',message["select2.placeholder.msg"]);
    });
    pageSetUp();
    function back() {
        $('#second').hide();
        $('#first').show();
    }

    $(document).ready(function () {

        $('[data-dic]').each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
    });
    if(!!'${(cert)!}'){
        var values = '${(cert)!}'.split(",");
        $("#cert").val(values).trigger('change');
    }

    $form = $(".tab-pane.active form");
    $form.bootstrapValidator({
        feedbackIcons : {
            valid : 'glyphicon glyphicon-ok',
            invalid : 'glyphicon glyphicon-remove',
            validating : 'glyphicon glyphicon-refresh'
        },
        fields : {
            partNo : {
                group:".col.col-3",
                validators : {
                    notEmpty : {
                        message : message['validate.message.notEmpty']
                    }
                }
            },
            series: {
                group:".col.col-3",
                validators : {
                    notEmpty : {
                        message : message['validate.message.notEmpty']
                    }
                }
            },
            num : {
                group:".st-sp",
                validators : {
                    notEmpty : {
                        message : message['validate.message.notEmpty']
                    },
                    digits : {
                        message : message['validate.message.number']
                    }
                }
            },
            styType : {
                group:".st-sp",
                validators : {
                    notEmpty : {
                        message : message["please.select.the.type"]
                    }
                }
            }
        }
    });
    $("#submit_btn").click(function () {
        var url = $(".tab-pane.active .submit_url").val();
        if (url !== undefined) {
            //获取表单对象
            var bootstrapValidator = $form.data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()){
                //获取表单对象
                var bootstrapValidator2 = $("#params-form").data('bootstrapValidator');
                //手动触发验证
                bootstrapValidator2.validate();
                if(bootstrapValidator2.isValid()) {
                    showLoading();
                    var panelLangs = [];
                    var paramsLangs = [];
                    $.each(lang, function(index,item){
                        panelLangs.push({
                            lang: item,
                            colorLang:$("#color").data(item)
                        });
                        paramsLangs.push({
                            lang: item,
                            controlLang: $("#control").data(item),
                            fixModualLang: encodeContent($("#fixModual").data(item)),
                            fixPsu: encodeContent($("#fixPsu").data(item))
                        })
                    });
                    $.ajax({
                        url: url,
                        data: $(".tab-pane.active form").serialize() +
                        "&panelLang=" + JSON.stringify(panelLangs) + "&paramsLang=" + JSON.stringify(paramsLangs) +
                        "&partNo=" + $("select[name='box'] option:selected").text(),
                        type: 'POST',
                        success: function (data) {
                            hideLoading();
                            if (data.id !== undefined && !isNaN(data.id)) {
                                $("#paramsId").val(data.id);
                            }
                            alert(message['alert.message.success']);
                            productTable.resetFilter();
                        },
                        error: function (data) {
                            if (data.responseJSON.message === "20001") {
                                alert(message['alert.message.codeExist'], 3);
                            }else {
                                alert(message['alert.message.systemError'], 3);
                            }
                        }
                    });
                }
            }
        }
    });
    bindLanguages($("#color"));
    bindLanguages($("#control"));
    bindLanguages($("#fixModual"));
    bindLanguages($("#fixPsu"));
</script>
<script src="../../static/js/panel/add.js"></script>
<style>
    .smart-form fieldset+fieldset{
        border-top: none !important;
    }
    .padding{
        padding-top: 7px !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
    .sparePop{
        height: 466px !important;
        width: 934px;
    }
    .textPop{
        height: 107px !important;
    }

    textarea{
        border-radius: 4px;
        width: 100%;
    }
    h3{
        margin: 0px;
    }
    tr td.nopadding{
        padding: 0 !important;
    }
    #choose_spare_dialog{
        border: 1px solid #cccccc;
        max-height:380px;
        overflow-y: scroll;
    }
    .dataTable tbody tr.on{
        background-color: #b4daff !important;
    }
</style>
</#compress>