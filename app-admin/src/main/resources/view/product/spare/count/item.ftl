<#compress>
<section id="widget-grid" class="">
    <div class="row">
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div id="second">
                <div class="jarviswidget" data-widget-colorbutton="false"
                     data-widget-editbutton="false"
                     data-widget-togglebutton="false" data-widget-deletebutton="false"
                     data-widget-fullscreenbutton="false" data-widget-custombutton="false">
                    <header>
                        <ul class="nav nav-tabs pull-left">
                            <li class="active">
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)"
                                   data-standard-id="1">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet"><dic
                                            data-dic="product.panel.tab.standard"></dic></span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)"
                                   data-standard-id="2">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet"><dic
                                            data-dic="product.panel.tab.match"></dic></span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)"
                                   data-standard-id="3">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet">
                                        <dic data-dic="product.panel.tab.free"></dic></span>
                                </a>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox"></div>
                        <div class="widget-body no-padding">
                            <div class="tab-pane fade in active" id="hr3">
                                <#include "standard.ftl">
                            </div>
                            <div class="widget-footer">
                                <div class=" text-align-right">
                                    <a class="btn btn-default btn-sm" onclick="back()"
                                       style="margin-right: 38px"><dic data-dic="button.back"></dic></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <#--备件信息弹窗-->
                <div class="modal fade" id="dialog_table" tabindex="-1" role="dialog">
                    <div class="modal-dialog" style="width: 905px;">
                        <input type="hidden" value="0" id="spare-type">
                        <div class="modal-content">
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
                                            <label class="col col-3 input">
                                                <input type="text" id="material-query"/>
                                            </label>
                                            <label class="col col-1 text-align-right padding">
                                                <dic data-dic="product.spare.list.type"></dic>:
                                            </label>
                                            <label class="col col-3 input">
                                                <input type="text" id="type-query"/>
                                            </label>

                                            <label class="col col-1 text-align-right">
                                                <a class="btn btn-primary query-btn" id="btn_query_spare">
                                                    <dic data-dic="header.list.button.search"></dic>
                                                </a>
                                            </label>
                                        </fieldset>

                                        <div id="choose_spare_dialog"
                                             class="custom-scroll table-responsive"
                                             style="max-height:461px; overflow-y: scroll;">
                                            <table id="dt_basic_spare" class="table table-striped table-bordered
                                             table-hover" style="width: 100%">

                                            </table>
                                        </div>
                                    </fieldset>
                                    <footer>
                                        <button type="button" id="spare-choose"
                                                class="btn btn-primary">
                                            <dic data-dic="admin.window.choice"></dic>
                                        </button>
                                        <button type="button" class="btn btn-default"
                                                data-dismiss="modal">
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
    var productId = '${(productId)!}';
    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    function back() {
        var $second = $('#second');
        var $first = $('#first');
        $first.show();
        $second.hide();
        $second.html('');
    }
</script>
<style>
    .smart-form fieldset + fieldset {
        border-top: none !important;
    }

    .padding {
        padding-top: 7px !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }

    .sparePop {
        height: 466px !important;
        width: 875px;
    }

    /*.textPop {*/
        /*height: 107px !important;*/
    /*}*/

    textarea {
        border-radius: 4px;
        width: 100%;
    }

    h3 {
        margin: 0px;
    }

    tr td.nopadding {
        padding: 0 !important;
    }

    #choose_spare_dialog{
        border: 1px solid #cccccc;
        max-height:380px !important;
        overflow-y: scroll;
    }
    .dataTable tbody tr.on{
        background-color: #b4daff !important;
    }
</style>
</#compress>