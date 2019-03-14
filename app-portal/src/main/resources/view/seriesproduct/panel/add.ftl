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
                                    <span class="hidden-mobile hidden-tablet"><dic
                                            data-dic="product.panel.tab.basic"></dic>
                                    </span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr2">
                                    <i class="fa fa-lg fa-cube"></i>
                                    <span class="hidden-mobile hidden-tablet"><dic
                                            data-dic="product.panel.tab.parameter"></dic></span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)"  data-standard-id="1">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet"><dic
                                            data-dic="product.panel.tab.standard"></dic></span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)" data-standard-id="2">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet"><dic
                                            data-dic="product.panel.tab.match"></dic></span>
                                </a>
                            </li>
                            <li>
                                <a data-toggle="tab" href="#hr3" onclick="standardPage(this)" data-standard-id="3">
                                    <i class="fa fa-lg fa-picture-o"></i>
                                    <span class="hidden-mobile hidden-tablet"><dic
                                            data-dic="product.panel.tab.free"></dic></span>
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
                                    <a class="btn btn-default btn-sm"  onclick="back()" style="margin-right: 38px">
                                        <dic data-dic="button.back"></dic>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </div>
</section>
<script type="text/javascript">
    function back() {
        showLoading();
        $('#second').hide();
        $('#first').show();
        hideLoading();
    }
    $(document).ready(function () {
        $("dic").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
    });
</script>
<style>
    .smart-form fieldset {
        border-top: none !important;
        height: auto !important;
    }
    .sparePop{
        height: 466px !important;
        width: 875px;
    }
    .textPop{
        height: 107px !important;
    }
    h3{
        margin: 0px;
    }
</style>
</#compress>