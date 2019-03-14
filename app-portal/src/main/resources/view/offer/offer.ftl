<#compress>
<#--基本信息-->
<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
    <div class="jarviswidget jarviswidget-sortable" id="basic-panel">
        <div id="basic_part" style="border-bottom: 1px solid #ccc;border-top: 1px solid #ccc;">
            <div class="widget-body">
                <form class="smart-form">
                    <div class="row">
                        <label class="col col-md-1 text-align-right padding7 no-left no-right">
                            <dic data-dic="offer.create.sales"></dic>
                        </label>
                        <label class="col col-md-2">
                            <select class="select2 sales-select" style="width: 100%">
                                <#list user as item>
                                <option value="${(item.id)?c}">${(item.name)!}</option>
                                </#list>
                            </select>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="quotes.offer.list.customer"></dic>
                        </label>
                        <label class="col col-md-2">
                            <div class="input-group colorpicker-demo2 colorpicker-element" style="width: 100%">
                                <div class='input-group input-group-sm' style="width: 100%">
                                    <input id="select_customer" class="input-autosuggest" data-id=""/>
                                    <div class='input-group-btn'>
                                        <button class='btn btn-default' type='button' style='height: 34px;'
                                                onclick="addNewCustomer()">
                                            <i class='fa fa-list'></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="quotes.offer.list.priceArea"></dic>
                        </label>
                        <label class="col col-md-2">
                            <input id="price_area" class="select2" style="width: 100%" />
                                    <#--<#if department??>-->
                                        <#--<#list department as item>-->
                                            <#--<option value="${(item.id)!}">${(item.name)!}</option>-->
                                        <#--</#list>-->
                                    <#--</#if>-->
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="offer.create.monetaryUnit"></dic>
                        </label>
                        <label class="col col-md-2">
                            <select id="money-type" class="select2" style="width: 100%">
                                <option value="CNY" data-key="￥">CNY(￥)</option>
                                    <#list rates as rate>
                                        <option value="${rate.code}" data-key="${rate.currency}">${rate.code!}
                                            (${rate.currency})
                                        </option>
                                    </#list>
                            </select>
                        </label>
                    </div>
                    <div class="row" style="margin-top: 10px">
                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="quotes.offer.list.guarantee"></dic>
                        </label>
                        <label class="col col-md-2">
                            <select name="guarantee" id="guarantee-years" class="select2" style="width: 100%">
                                <option value="2" data-dic="portal.my.twoYears"></option>
                                <option value="3" data-dic="portal.my.threeYears"></option>
                                <option value="4" data-dic="portal.my.fourYears"></option>
                                <option value="5" data-dic="portal.my.fiveYears"></option>
                            </select>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.boundary"></dic></label>
                        <label class="col col-md-2">
                            <select id="size-type" class="select2" style="width: 100%">
                                <option value="1" data-dic="offer.create.not"></option>
                                <option value="2" data-dic="offer.create.yes"></option>
                                <option value="3" data-dic="offer.create.left"></option>
                                <option value="4" data-dic="offer.create.top"></option>
                            </select>
                        </label>

                        <label class="col col-md-1 padding7 no-left no-right">
                            <dic data-dic="offer.create.unit"></dic>
                        </label>
                        <label class="col col-md-3" style="padding-top: 5px">
                            <div class="inline-group">
                                <label class="radio">
                                    <input name="radio-inline" checked="checked" type="radio" value="1">
                                    <i></i>
                                    <dic data-dic="offer.create.meters"></dic>
                                </label>
                                <label class="radio">
                                    <input name="radio-inline" type="radio" value="2">
                                    <i></i>
                                    <dic data-dic="offer.create.foot"></dic>
                                </label>
                            </div>
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
</article>

<!-- 屏体信息 -->
<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable" style="padding-bottom: 52px;">
    <div class="jarviswidget" id="panel-list-header">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="panel-${tag}">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.panel"></dic>
                            <span role="index"></span>
                        </span>
                        <div class="drop-panel"><i class="fa fa-trash-o"></i></div>
                    </a>
                </li>
            </ul>
            <#--<a class="btn btn-xs btn-default" id="dialog_test">-->
                <#--<dic data-dic="offer.create.addPartsBatch"></dic>-->
            <#--</a>-->
            <a class="btn btn-xs btn-primary" onclick="appendNewPanel()">
                <dic data-dic="customer.list.btn.addPanel"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" role="panel" id="panel-list-content">
                    <div class="tab-pane fade in active" id="panel-${tag}">
                        <fieldset style="margin: 0 -12px;border-bottom: solid 1px gainsboro">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="row">
                                    <label class="col col-md-1">
                                    </label>
                                    <label class="col col-md-1 text-align-right padding7">
                                        <dic data-dic="offer.create.type">产品类型</dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <select class="select2" style="width: 100%" name="line">
                                            <option></option>
                                            <option value="1" data-dic="offer.create.indoor"></option>
                                            <option value="2" data-dic="offer.create.outdoor"></option>
                                            <option value="3" data-dic="offer.create.lease"></option>
                                        </select>
                                    </label>
                                    <label class="col col-md-1 text-align-right padding7">
                                        <dic data-dic="offer.create.product"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input name="product" id="select-series" style="width: 100%" disabled />
                                    </label>
                                    <label class="col col-md-1 text-align-right padding7">
                                        <dic data-dic="product.panel.list.configuration"></dic>
                                    </label>
                                    <label class="col col-md-3">
                                        <input name="panel" style="width: 100%" disabled />
                                    </label>
                                    <label class="col col-md-1">
                                        <a class="btn btn-primary" id="split-screen">
                                            <dic data-dic="offer.create.split"></dic>
                                        </a>
                                    </label>
                                </div>
                                <div class="row cust_size" name="amountDiv">
                                    <label class="col col-md-1"></label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.custWidth"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="number" name="cust_width" tag="cust-width" value="0" placeholder="宽"/>
                                    </label>
                                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                        ×
                                    </label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.custHeight"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="number" name="cust_height" tag="cust-height" value="0" placeholder="高"/>
                                    </label>
                                    <label class="col col-md-2 padding7 text-align-right">
                                        <span role="cust-total">0</span>
                                    </label>
                                </div>
                                <div class="row" name="amountDiv">
                                    <label class="col col-md-1 text-align-right panel-index">
                                        <dic data-dic="portal.dashboard.panel"></dic>
                                    </label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.lateral"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="number" integer tag="qty-width" value="0" placeholder="宽"/>
                                    </label>
                                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                        ×
                                    </label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.longitudinal"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="number" integer tag="qty-height" value="0" placeholder="高"/>
                                    </label>
                                    <label class="col col-md-2 padding7 text-align-right">
                                        <span role="total">0</span>
                                    </label>
                                </div>
                                <div class="row" name="amountDiv">
                                    <label class="col col-md-1">
                                    </label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.realWidth"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="number" tag="seq-width" value="0" placeholder="宽"/>
                                    </label>
                                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                        ×
                                    </label>
                                    <label class="col col-md-1 padding7 text-align-right">
                                        <dic data-dic="offer.create.realHeight"></dic>
                                    </label>
                                    <label class="col col-md-2">
                                        <input type="number" tag="seq-height" value="0" placeholder="高"/>
                                    </label>
                                    <label class="col col-md-2 padding7 text-align-right">
                                        <span role="total">0</span>
                                    </label>
                                </div>
                            </div>
                        </fieldset>
                        <div class="row product-footer" style="border-bottom: 1px solid gainsboro; padding: 10px 0">
                            <label class="col col-md-2 padding7 text-align-right">
                                <dic data-dic="offer.create.screenQuantity"></dic>
                            </label>
                            <label class="col col-md-2">
                                <input type="number" tag="quantity" value="1" integer/>
                            </label>

                            <label class="col col-md-3 padding7 text-align-right">
                                <dic data-dic="offer.create.totalArea">总面积</dic>
                            </label>
                            <label class="col col-md-2 padding7">
                                <span role="totalArea"></span>
                            </label>
                            <label class="col col-md-2 no-left no-right padding7 text-align-center" style="font-weight: 700;">
                                <dic data-dic="offer.create.panelTotal"></dic>：
                                <span class="moneyUnit"></span><span id="price-panel-${tag}">0.00</span>
                            </label>
                        </div>
                        <div style="margin-top: 10px" class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" role="widget">
                            <!-- widget options:
                            usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                            data-widget-colorbutton="false"
                            data-widget-editbutton="false"
                            data-widget-togglebutton="false"
                            data-widget-deletebutton="false"
                            data-widget-fullscreenbutton="false"
                            data-widget-custombutton="false"
                            data-widget-collapsed="true"
                            data-widget-sortable="false"

                            -->
                            <header role="heading">
                                <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                                <h2>Accordions </h2>

                                <span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>

                            <!-- widget div-->
                            <div role="content">
                                <!-- widget content -->
                                <div class="widget-body" style="min-height: 0 !important;">
                                    <div class="panel-group smart-accordion-default" id="accordion">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a title="单击查看参数详情" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" class="collapsed">
                                                        <i class="fa fa-lg fa-angle-down pull-right"></i>
                                                        <i class="fa fa-lg fa-angle-up pull-right"></i>
                                                        <span><i class="fa fa-lg fa-cog"></i></span>
                                                        <dic data-dic="offer.create.params"></dic>
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse" aria-expanded="false">
                                                <div class="panel-body no-padding">
                                                    <div class="tab-content padding-10" id="param-${tag}-dialog-1">

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- end widget content -->
                            </div>
                            <!-- end widget div -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="jarviswidget" id="panel-standard-1">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="spare-${tag}-1">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.standard"></dic><span role="index"></span></span>
                    </a>
                </li>
            </ul>
            <a class="btn btn-xs btn-default" style="float:right" onclick="addNewSelfSpare(1)">
                <dic data-dic="offer.create.custom"></dic>
            </a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(1)">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-1" style="padding-bottom: 0 !important;">
                    <div class="row">
                        <div class="col col-md-12">
                            <form class="smart-form temp">
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-2 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-3">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>

                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-2 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-3">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="jarviswidget" id="panel-standard-3">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="spare-${tag}-3">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.free"></dic>
                            <span role="index"></span>
                        </span>
                    </a>
                </li>
            </ul>
            <a class="btn btn-xs btn-default" style="float:right" onclick="addNewSelfSpare(3)">
                <dic data-dic="offer.create.custom"></dic>
            </a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(3)">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-3" style="padding-bottom: 0 !important;">
                    <div class="row">
                        <div class="col col-md-12">
                           <form class="smart-form temp">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <fieldset style="border-right: 1px dashed #ccc;">
                                <label class="col col-3 text-align-left">
                                    <dic data-dic="product.standard.list.styType"></dic>
                                </label>
                                <label class="col col-2 text-align-left">
                                    <dic data-dic="product.spare.list.brand"></dic>
                                </label>
                                <label class="col col-2">
                                    <dic data-dic="product.standard.list.num"></dic>
                                </label>
                                <label class="col col-3">
                                    <dic data-dic="offer.create.priceUnit">
                                </label>
                                <label class="col col-2">
                                    <dic data-dic="offer.create.price.sum"></dic>
                                </label>
                            </fieldset>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                            <fieldset style="border-right: 1px dashed #ccc;">
                                <label class="col col-3 text-align-left">
                                    <dic data-dic="product.standard.list.styType"></dic>
                                </label>
                                <label class="col col-2 text-align-left">
                                    <dic data-dic="product.spare.list.brand"></dic>
                                </label>
                                <label class="col col-2">
                                    <dic data-dic="product.standard.list.num"></dic>
                                </label>
                                <label class="col col-3">
                                    <dic data-dic="offer.create.priceUnit">
                                </label>
                                <label class="col col-2">
                                    <dic data-dic="offer.create.price.sum"></dic>
                                </label>
                            </fieldset>
                        </div>
                    </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="jarviswidget" id="panel-standard-2">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="spare-${tag}-2">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.match"></dic><span
                                role="index"></span></span>
                    </a>
                </li>
            </ul>
            <a class="btn btn-xs btn-default" style="float:right" onclick="addNewSelfSpare(2)">
                <dic data-dic="offer.create.custom"></dic>
            </a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(2)">
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-2" style="padding-bottom: 0 !important;">
                    <div class="row">
                        <div class="col col-md-12">
                            <form class="smart-form temp">
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-2 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-3">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                                    <fieldset style="border-right: 1px dashed #ccc;">
                                        <label class="col col-3 text-align-left">
                                            <dic data-dic="product.standard.list.styType"></dic>
                                        </label>
                                        <label class="col col-2 text-align-left">
                                            <dic data-dic="product.spare.list.brand"></dic>
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="product.standard.list.num"></dic>
                                        </label>
                                        <label class="col col-3">
                                            <dic data-dic="offer.create.priceUnit">
                                        </label>
                                        <label class="col col-2">
                                            <dic data-dic="offer.create.price.sum"></dic>
                                        </label>
                                    </fieldset>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <#--此处的tab样式（tab-pane fade in active）单纯为了寻找元素所用，下同-->
    <div class="jarviswidget tab-pane fade in active" id="service-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-star"></i> </span>
            <h2>
                <strong>
                    <span><dic data-dic="offer.create.service"></dic></span>
                </strong>
            </h2>
            <a class="btn btn-primary btn-xs btn-service" onclick="appendService()">
                <i class="fa fa-plus"></i>
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div id="service_part">
            <div class="widget-body">
                <form class="smart-form" id="service-form" style="word-break: keep-all">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-3 text-align-left">
                                <dic data-dic="product.standard.list.styType">
                            </label>
                            <label class="col col-3 ">
                                <dic data-dic="product.standard.list.num"></dic>
                            </label>
                            <label class="col col-3 text-align-left">
                                <dic data-dic="offer.create.priceUnit"></dic>
                            </label>
                            <label class="col col-3 text-align-center">
                                <dic data-dic="offer.create.price.sum"></dic>
                            </label>
                        </fieldset>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-3 text-align-left">
                                <dic data-dic="product.standard.list.styType">
                            </label>
                            <label class="col col-3 ">
                                <dic data-dic="product.standard.list.num"></dic>
                            </label>
                            <label class="col col-3 text-align-left">
                                <dic data-dic="offer.create.priceUnit"></dic>
                            </label>
                            <label class="col col-3 text-align-center">
                                <dic data-dic="offer.create.price.sum"></dic>
                            </label>
                        </fieldset>
                    </div>
                </form>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row total-tag">
            <label class="col col-md-5"></label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.totalPrice"></dic>：
                <span class="moneyUnit"></span><span tag="total-sale">0.00</span>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.discount"></dic>：
            </label>
            <label class="col col-md-1 no-left" style="margin-top: -7px;"><input type="text" tag="total-discount" value="100"></label>
            <label class="col col-md-2 text-align-right" style="margin-bottom: 12px;">
                <dic data-dic="offer.create.real"></dic>：
                <span class="moneyUnit"></span><span tag="total-real">0.00</span>
            </label>
        </div>
    </div>

    <div class="jarviswidget" id="package-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-cube"></i> </span>
            <h2>
                <strong>
                    <span><dic data-dic="offer.create.package"></dic></span>
                </strong>
            </h2>
            <a class="btn btn-primary btn-xs btn-service" onclick="appendPackage()">
                <i class="fa fa-plus"></i>
                <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div id="package_part">
            <div class="widget-body">
                <form class="smart-form" id="package-form" style="word-break: keep-all">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="offer.create.package">
                            </label>
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="product.standard.list.num">
                            </label>
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="offer.create.priceUnit">
                            </label>
                            <label class="col col-md-3 text-align-center">
                                <dic data-dic="offer.create.price.sum">
                            </label>
                        </fieldset>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <fieldset style="border-right: 1px dashed #ccc;">
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="offer.create.package">
                            </label>
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="product.standard.list.num">
                            </label>
                            <label class="col col-md-3 text-align-left">
                                <dic data-dic="offer.create.priceUnit">
                            </label>
                            <label class="col col-md-3 text-align-center">
                                <dic data-dic="offer.create.price.sum">
                            </label>
                        </fieldset>
                    </div>
                </form>
            </div>
        </div>

        <div class="row total-tag" style="border: 1px solid #ccc;margin-top: -3px;">
            <label class="col col-md-2"></label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="package.form.terms_of_trade"></dic>:
            </label>
            <label class="col col-md-2" style="margin-top: -7px;">
                <select class="packageTrade" style="width:100%">
                    <#list (tradeDict)! as item>
                        <option value="${item.code}">${item.name}</option>
                    </#list>
                </select>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.transport"></dic>:
            </label>
            <label class="col col-md-2 input" style="margin-top: -7px">
                <input type="number" id="logistics-cost" value="0"/>
            </label>
            <label class="col col-md-2 text-align-right" style="margin-bottom: 12px">
                <dic data-dic="offer.create.real"></dic>:
                <span class="moneyUnit"></span>
                <span tag="total-real">0.00</span>
            </label>
        </div>
    </div>

    <div class="jarviswidget" style="border-top:1px solid #CCC;border-bottom:1px solid #CCC;">
        <div style="border-bottom: none !important;" class="row">
            <form class="smart-form">
                <fieldset>
                    <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="quotes.offer.list.payment"></dic></label>
                    <label class="col col-md-2">
                        <input id="select_payment" class="form-control" style="width: 100%" />
                    </label>

                    <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.company"></dic></label>
                    <label class="col col-md-2">
                        <select id="select_trader" class="select2" style="width: 100%">
                        <#if tradeCompany??><#list tradeCompany as item><option value="${(item.code)!}">${(item.name)}</option></#list></#if>
                        </select>
                    </label>

                    <label class="col col-md-1 padding7 no-left no-right input"><dic data-dic="offer.create.shipping"></dic></label>
                    <label class="col col-md-5">
                        <select class="select2" id="shipping" style="width: 100%">
                            <#if tradeAddress??><#list tradeAddress as item><option value="${(item.code)!}">${(item.name)}</option></#list></#if>
                        </select>
                    </label>
                </fieldset>

                <fieldset>
                    <label class="col col-md-1 text-align-right no-left no-right padding-7">
                        <dic data-dic="offer.create.project.name"></dic>
                    </label>
                    <label class="col col-md-2">
                        <input type="text" class="form-control" maxlength="200" id="project_name" style="width: 100%;"/>
                    </label>
                    <label class="col col-md-1 padding7 no-left no-right input"><dic data-dic="quotes.offer.list.waitingDate"></dic></label>
                    <label class="col col-md-2">
                        <input id="valid-date" type="number" integer value="45" style="width: 100%"/>
                    </label>
                    <label class="col col-md-1 text-align-right no-left no-right padding-7">
                        <dic data-dic="offer.create.remark"></dic>:
                    </label>
                    <label class="col col-md-5 textarea">
                        <textarea rows="3" name="remark" class="custom-scroll" style="width: 100%;resize: none;"></textarea>
                    </label>
                </fieldset>
            </form>
        </div style="border-bottom: none !important;">
    </div>
</article>
<#--页脚锁定行-->
<div class="page-footer" style="position: fixed;left: 0;margin: 0;z-index: 1000;background: #3a3f51">
    <div class="row">
        <div class="col-xs-12 col-sm-12" style="color: #c0bbb7">
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.totalPrice"></dic>：
                <span id="totalPriceMoneySymbol"><span class="moneyUnit"></span></span><span id="total-price">0.00</span>
            </label>
            <label class="col col-md-2 text-align-right no-left no-right">
                <dic data-dic="offer.create.discount"></dic>：
            </label>
            <label class="col col-md-1 no-left" style="margin-top: -5px;"><input type="number" id="total-discount" value="100"></label>
            <label class="col col-md-2 text-align-right">
                <dic data-dic="offer.create.finalPrices"></dic>：<span class="moneyUnit"></span><span id="total-real">0.00</span>
            </label>
            <label class="col col-md-1" style="padding-left: 0;"><button class="btn btn-primary" id="edit_realprice" style="margin-top: -5px"><dic data-dic="offer.create.adjust"></dic></button></label>
            <label class="col col-md-1"><span special-unit></span><span special></span></label>
            <button class="btn btn-default pull-right mr10" id="save_button" style="margin-top: -5px"><i class="fa fa fa-floppy-o mr5"></i>
                <dic data-dic="offer.create.save"></dic>
            </button>
            <button class="btn btn-primary pull-right mr10" id="submit_button" style="margin-top: -5px"><i class="fa fa-cloud-upload mr5" ></i>
                <dic data-dic="offer.create.submit"></dic>
            </button>
        </div>
    </div>
</div>

<div class="modal fade in" id="dialog_table" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="min-width: 1000px">
        <input type="hidden" value="0" id="spare-type">
        <div class="modal-content">
            <#--<div class="modal-header">-->
                <#--<h4>-->
                    <#--<dic data-dic="portal.series.spares"></dic>-->
                <#--</h4>-->
            <#--</div>-->
            <div class="modal-body no-padding">
                <div id="choose_spare_dialog">
                </div>
                <div class="smart-form">
                    <footer>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                        <button type="button" id="spare-choose" class="btn btn-primary">
                            <dic data-dic="offer.create.choose">
                        </button>
                    </footer>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal）删除屏 -->
<div class="modal fade" id="del_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <i class="fa fa-warning"></i>
                    <dic data-dic="portal.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="panel.message.del"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ok_del">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal）重置提示框 -->
<div class="modal fade" id="reset_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <i class="fa fa-warning"></i>
                    <dic data-dic="portal.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="offer.create.resetMessage"></dic>
                <input type="hidden" id="resetHidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ok_reset">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_reset">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="lower_base_price_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <dic data-dic="admin.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="panel.message.lowerBasePrice"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ok_low_base_price_submit">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#--模态框  选择客户-->
<div class="modal fade in" id="dialog_table_customer" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 900px;">
            <input type="hidden" value="0" id="spare-type">
            <div class="modal-content">
                <div class="modal-header">
                    <h4><dic data-dic="portal.dashboard.myCustomer"></dic></h4>
                </div>
                <div class="modal-body no-padding">
                    <form class="smart-form">
                        <fieldset>
                            <div id="choose_spare_dialog_customer" class="custom-scroll table-responsive" style="max-height:450px; overflow-y: scroll;">

                            </div>
                        </fieldset>
                        <footer>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><dic data-dic="button.cancel"></dic></button>
                            <button type="button" id="spare-choose_customer" class="btn btn-primary"><dic data-dic="offer.create.choose"></button>
                        </footer>
                    </form>
                </div>
            </div>
        </div>
    </div>

<#--模态框  更改屏体单价-->
<div id="dialog-message" title="Dialog Simple Title" style="display: none;">
    <fieldset style="margin-top: 13px">
        <div class="col col-md-2 text-align-right padding">
            销售价
        </div>
        <div class="col col-md-8">
            <input type="number" class="form-control" tag="sale-price" />
        </div>
        <a class="btn btn-primary" id="reset-price">重置</a>
    </fieldset>
    <fieldset style="margin-top: 13px">
        <div class="col col-md-2 text-align-right padding">
            折扣(%)
        </div>
        <div class="col col-md-8">
            <input type="number" class="form-control" tag="panel-discount" />
        </div>
    </fieldset>
    <fieldset style="margin-top: 13px">
        <div class="col col-md-2 text-align-right padding">
            折后价格
        </div>
        <div class="col col-md-8">
            <input type="number" class="form-control" tag="discounted-price" />
        </div>
    </fieldset>
</div><!-- #dialog-message -->

<!-- 模态框（Modal）更改最终价格 -->
<div class="modal fade" id="edit_totalPrice_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    调整价格
                </h4>
            </div>
            <div class="modal-body">
                <fieldset style="margin-top: 10px">
                    <div class="col col-md-3 text-align-right" style="padding-left: 0; padding-right: 0">
                        <dic data-dic="offer.create.specialRemark">优惠价格</dic>
                    </div>
                    <div class="col col-md-8">
                        <input type="number" class="form-control" id="special_price"/>
                    </div>
                </fieldset>
                <fieldset style="margin-top: 13px">
                    <div class="col col-md-3 text-align-right" style="padding-left: 0; padding-right: 0">
                        <dic data-dic="offer.create.desc">说明</dic>
                    </div>
                    <div class="col col-md-8">
                        <textarea class="form-control" id="special_price_remark"></textarea>
                    </div>
                </fieldset>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="comfirm_special">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框 选择配件、选配、免费 -->
<div class="modal fade" id="choose_spares" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 1350px;">
        <input type="hidden" value="0" id="spare-type">
        <div class="modal-content">
            <div class="modal-header">
                <h4><dic data-dic="offer.create.choose"></dic></h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div id="choose_spare_list_ul" class="custom-scroll table-responsive"
                             style="overflow-y: scroll;height: 450px">
                            <div role="content">
                                <div class="jarviswidget-editbox">
                                </div>
                                <div class="widget-body">
                                    <div class="tabs-left">
                                        <ul class="nav nav-tabs tabs-left" id="countListShow"
                                            style="position: absolute;">
                                            <li class="active">
                                                <a href=".tabs-spare" data-toggle="tab"
                                                   aria-expanded="true" style="margin-top: 1px;">
                                                    <span class="badge bg-color-blue
                                                    txt-color-white check_count">0</span>
                                                    <dic data-dic="offer.create.standard"></dic>
                                                </a>
                                            </li>
                                            <li class="">
                                                <a href=".tabs-free" data-toggle="tab" aria-expanded="false">
                                                    <span class="badge bg-color-greenLight
                                                    txt-color-white check_count">0</span>
                                                    <dic data-dic="offer.create.free"></dic>
                                                </a>
                                            </li>
                                            <li class="">
                                                <a href=".tabs-choose" data-toggle="tab" aria-expanded="false">
                                                    <span class="badge bg-color-blueDark
                                                    txt-color-white check_count">0</span>
                                                    <dic data-dic="offer.create.spare"></dic>
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane tabs-spare active">
                                                <div class="row" style="margin: 0">
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.standard.list.styType">
                                                                类型
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_type"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.description">
                                                                描述
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_description"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.brand">
                                                                品牌
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_brand"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm
                                                        btn_search spare_spare"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="header.list.search">搜索</dic>
                                                        </a>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm btn_rest spare_spare"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="button.reset">重置</dic>
                                                        </a>
                                                    </section>
                                                </div>
                                                <div class="row show-grid" style="max-height:395px;">
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-spare2">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-spare1">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane tabs-choose">
                                                <div class="row" style="margin: 0">
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.standard.list.styType">
                                                                类型
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_type"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.description">
                                                                描述
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_description"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.brand">
                                                                品牌
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_brand"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm
                                                        spare_choose btn_search"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="header.list.search">搜索</dic>
                                                        </a>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm btn_rest spare_choose"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="button.reset">重置</dic>
                                                        </a>
                                                    </section>
                                                </div>
                                                <div class="row show-grid" style="max-height:395px;overflow: auto;">
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-choose1">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-choose2">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane tabs-free">
                                                <div class="row" style="margin: 0">
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.standard.list.styType">
                                                                类型
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_type"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.description">
                                                                描述
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_description"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1">
                                                        <label class="control-label" style="margin-top: 10px;">
                                                            <dic data-dic="product.spare.list.brand">
                                                                品牌
                                                            </dic>
                                                        </label>
                                                    </section>
                                                    <section class="col col-2 "
                                                             style="padding-left: 0;">
                                                        <label class="input">
                                                            <input type="text"
                                                                   class="search_brand"
                                                                   placeholder="">
                                                        </label>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm
                                                        spare_free btn_search"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="header.list.search">搜索</dic>
                                                        </a>
                                                    </section>
                                                    <section class="col col-1"
                                                             style="margin-top: 4px;padding-left: 0;">
                                                        <a class="btn btn-primary btn-sm btn_rest spare_free"
                                                           href="javascript:void(0);">
                                                            <dic data-dic="button.reset">重置</dic>
                                                        </a>
                                                    </section>
                                                </div>
                                                <div class="row show-grid" style="max-height:395px;overflow: auto;">
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-free1">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-xs-6">
                                                        <table class="table table-bordered">
                                                            <thead>
                                                            <tr>
                                                                <th width="30px">
                                                                </th>
                                                                <th width="20%">
                                                                    <dic data-dic="product.standard.list.styType"></dic>
                                                                </th>
                                                                <th width="">
                                                                    <dic data-dic="customer.item.description"></dic>
                                                                </th>
                                                                <th width="10%">
                                                                    <dic data-dic="product.spare.list.brand"></dic>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                        <table class="table table-bordered
                                                        choose_spare_table tabs-free2">
                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <footer>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <dic data-dic="button.cancel"></dic>
                        </button>
                        <button type="button" id="choose_spares_sure" class="btn btn-primary">
                            <dic data-dic="offer.create.choose">
                        </button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal）提交预览 -->
<div class="modal fade" id="view_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    预览
                </h4>
            </div>
            <div class="modal-body">
                <div class="row" id="view-content"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="view_submit">
                    <dic data-dic="button.confirm"></dic>
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<#--添加屏体的tab-->
<script type="text/template" id="product-content-template">
    <div class="tab-pane fade in" id="{{= tag}}">
        <fieldset style="margin: 0 -12px;border-bottom: solid 1px gainsboro">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="row">
                    <label class="col col-md-1">
                    </label>
                    <label class="col col-md-1 text-align-right padding7">
                        {{= productType}}
                    </label>
                    <label class="col col-md-2">
                        <select class="select2" style="width: 100%" name="line">
                            <option></option>
                            <option value="1" data-dic="offer.create.indoor">户内</option>
                            <option value="2" data-dic="offer.create.outdoor">户外</option>
                            <option value="3" data-dic="offer.create.lease">租赁</option>
                        </select>
                    </label>
                    <label class="col col-md-1 text-align-right padding7">
                        {{= product}}
                    </label>
                    <label class="col col-md-2">
                        <input name="product" id="select-series" style="width: 100%" disabled />
                    </label>
                    <label class="col col-md-1 text-align-right padding7">
                        {{= configuration}}
                    </label>
                    <label class="col col-md-3">
                        <input name="panel" style="width: 100%" disabled />
                    </label>
                    <label class="col col-md-1">
                        <a class="btn btn-primary" id="split-screen">
                            {{= split}}
                        </a>
                    </label>
                </div>
                <div class="row cust_size">
                    <label class="col col-md-1"></label>
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= custWidth}}
                    </label>
                    <label class="col col-md-2">
                        <input type="number" name="cust_width" tag="cust-width" value="0" placeholder="宽"/>
                    </label>
                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                        ×
                    </label>
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= custHeight}}
                    </label>
                    <label class="col col-md-2">
                        <input type="number" name="cust_height" tag="cust-height" value="0" placeholder="高"/>
                    </label>
                    <label class="col col-md-2 padding7 text-align-right">
                        <span role="cust-total">0</span>
                    </label>
                </div>

                <div class="row">
                    <label class="col col-md-1 text-align-right panel-index">
                        {{= box}}
                    </label>
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= lateral}}
                    </label>
                    <label class="col col-md-2">
                        <input type="number" tag="qty-width" integer value="0" placeholder="宽"/>
                    </label>
                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                        ×
                    </label>
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= longitudinal}}
                    </label>
                    <label class="col col-md-2">
                        <input type="number" tag="qty-height" integer value="0" placeholder="高"/>
                    </label>
                    <label class="col col-md-2 padding7 text-align-right">
                        <span role="total">0</span>
                    </label>
                </div>
                <div class="row">
                    <label class="col col-md-1">
                    </label>
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= realWidth}}
                    </label>
                    <label class="col col-md-2">
                        <input type="number" tag="seq-width" value="0" placeholder="宽"/>
                    </label>
                    <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                        ×
                    </label>
                    <label class="col col-md-1 padding7 text-align-right">
                        {{= realHeight}}
                    </label>
                    <label class="col col-md-2">
                        <input type="number" tag="seq-height" value="0" placeholder="高"/>
                    </label>
                    <label class="col col-md-2 padding7 text-align-right">
                        <span role="total">0</span>
                    </label>
                </div>
            </div>
        </fieldset>
        <div class="row product-footer" style="border-bottom: 1px solid gainsboro; padding: 10px 0">
            <label class="col col-md-2 padding7 text-align-right">
                {{= screenQty}}
            </label>
            <label class="col col-md-2">
                <input type="number" tag="quantity" integer value="1"/>
            </label>

            <label class="col col-md-3 padding7 text-align-right">
                {{= totalArea}}
            </label>
            <label class="col col-md-2 padding7">
                <span role="totalArea"></span>
            </label>
            <label class="col col-md-2 no-left no-right padding7 text-align-center" style="font-weight: 700;">
                {{= panelTotal}}：
                <span class="moneyUnit"></span><span id="price-{{= tag}}">0.00</span>
            </label>
        </div>
        <div style="margin-top: 10px" class="jarviswidget well transparent" id="wid-id-9" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false" data-widget-sortable="false" role="widget">
            <!-- widget options:
            usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

            data-widget-colorbutton="false"
            data-widget-editbutton="false"
            data-widget-togglebutton="false"
            data-widget-deletebutton="false"
            data-widget-fullscreenbutton="false"
            data-widget-custombutton="false"
            data-widget-collapsed="true"
            data-widget-sortable="false"

            -->
            <header role="heading">
                <span class="widget-icon"> <i class="fa fa-comments"></i> </span>
                <h2>Accordions </h2>

                <span class="jarviswidget-loader"><i class="fa fa-refresh fa-spin"></i></span></header>

            <!-- widget div-->
            <div role="content">
                <!-- widget content -->
                <div class="widget-body" style="min-height: 0 !important;">
                    <div class="panel-group smart-accordion-default" id="accordion-{{= tag}}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion-{{= tag}}" href="#collapseOne-{{= tag}}" aria-expanded="false" class="collapsed">
                                        <i class="fa fa-lg fa-angle-down pull-right"></i>
                                        <i class="fa fa-lg fa-angle-up pull-right"></i>
                                        <span><i class="fa fa-lg fa-cog"></i></span>
                                        {{= params}}
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseOne-{{= tag}}" class="panel-collapse collapse" aria-expanded="false">
                                <div class="panel-body no-padding">
                                    <div class="tab-content padding-10" id="param-{{= panelTag}}-dialog-1">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end widget content -->
            </div>
            <!-- end widget div -->
        </div>
    </div>
</script>

<script type="text/template" id="product-header-template">
    <li>
        <a data-toggle="tab" href="{{= tag}}">
            <span class="hidden-mobile hidden-tablet">{{= name}}<span role="index">{{= index}}</span></span>
            <div class="drop-panel"><i class="fa fa-trash-o"></i></div>
        </a>
    </li>
</script>

<script type="text/template" id="params-header-template">
    <li>
        <a data-toggle="tab" href="{{= tag}}">
            <span class="hidden-mobile hidden-tablet">{{= name}}<span role="index">{{= index}}</span></span>
        </a>
    </li>
</script>

<script id="standard-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="spares" style="border-right: 1px dashed #ccc;border-bottom: 1px dashed #ccc">
            <label class="col col-3 text-align-left padding7 text-spare"></label>
            <label class="col col-2 text-align-left padding7"></label>
            <label class="col col-1 long-label">
                <input amount class="form-control" type="number" value="0"/>
            </label>
            <label class="col col-1 padding7 short-label"></label>
            <label class="col col-2 no-padding margin-left-10">
                <input sales class="form-control" type="number" value="0"/>
            </label>
            <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price>0.00</span></label>
            <label class="trash">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
            <label class="col col-md-12 spare-desc">
                <span class="desc-title"></span><span class="desc"></span>
            </label>
        </fieldset>
    </div>
</script>

<script id="service-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="service" style="border-right: 1px dashed #ccc;">
            <label class="col col-3" style="position: inherit;">
                <#--<select class="select2" style="width: 100%;" placeholder="" type="text">-->
                    <#--<#list serviceList as item>-->
                        <#--<option value="${item.code!}">${item.name!}</option>-->
                    <#--</#list>-->
                <#--</select>-->
                <#--<div class="input-group colorpicker-demo2 colorpicker-element" style="width: 100%">-->
                    <#--<div class='input-group input-group-sm' style="width: 100%">-->
                        <#--<input class="input-autosuggest" data-id=""/>-->
                        <#--<div class='input-group-btn'>-->
                            <#--<button class='btn btn-default' type='button' style='height: 34px;'-->
                                    <#--onclick="addAnother()">-->
                                <#--<i class='fa fa-list'></i>-->
                            <#--</button>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <input type="text" class="service-list form-control" >
            </label>
            <label class="col col-3">
                <input amount class="form-control" placeholder="" type="number" value="0"/>
            </label>
            <label class="col col-3">
                <input sales class="form-control" placeholder="" type="number" value="0"/>
            </label>
            <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price>0.00</span></label>
            <label class="trash col col-1">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
        </fieldset>
    </div>
</script>

<script id="package-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="package" style="border-right: 1px dashed #ccc;">
            <label class="col col-3" style="position: inherit;">
                <input type="text" class="form-control packages" packages />
            </label>
            <label class="col col-3">
                <input amount class="form-control" placeholder="" type="number" value="0"/>
            </label>
            <label class="col col-3">
                <input sales class="form-control" placeholder="" type="number" value="0"/>
            </label>
        <#--<input sales class="form-control" placeholder="" type="text" value="0"/>-->
            <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price>0.00</span></label>
            <label class="trash-pack col col-1">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
        </fieldset>
    </div>
</script>

<script id="self-spare-template" type="text/template">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
        <fieldset role="self" style="border-right: 1px dashed #ccc;border-bottom: 1px dashed #ccc;">
            <label class="col col-3 text-align-left">
                <input type="text" class="form-control" maxlength="50"/>
            </label>
            <label class="col col-2 text-align-left">
                <input type="text" class="form-control" maxlength="100"/>
            </label>
            <label class="col col-1 long-label">
                <input amount class="form-control" type="number" value="0"/>
            </label>
            <label class="col col-1 short-label">
                <input type="text" class="form-control"/>
            </label>
            <label class="col col-2 no-padding margin-left-10">
                <input sales type="number" value="0"/>
            </label>
            <label class="col col-2 text-align-right padding7"><span class="moneyUnit"></span><span price="">0.00</span></label>
            <label class="trash">
                <a class="trash-a"><i class="fa fa-trash-o"></i></a>
            </label>
        </fieldset>
    </div>
</script>

<script id="self-spare-tr" type="text/template">
    <tr class="spare_item">
        <td width="30px">
            <label class="checkbox">
                <input type="checkbox" class="r">
                <i></i>
            </label>
        </td>
        <td width="20%" title="{{= type}}">{{= type}}</td>
        <td width="" class="font-diy" title="{{= description}}">{{= description}}</td>
        <td width="10%" title="{{= brand}}">{{= brand}}</td>
    </tr>
</script>

<script id="split-screen-template" type="text/template">
    <fieldset style="border-bottom: solid 1px gainsboro;margin-top: 10px; margin-left: -12px;margin-right: -12px;margin-bottom: 0">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="row">
                <label class="col col-md-1">
                </label>
                <label class="col col-md-1 text-align-right padding7">
                    {{= productType}}
                </label>
                <label class="col col-md-2">
                    <input type="text" class="form-control" name="line" disabled/>
                </label>
                <label class="col col-md-1 text-align-right padding7">
                    {{= product}}
                </label>
                <label class="col col-md-2">
                    <input name="product" class="form-control" id="select-series" style="width: 100%" disabled />
                </label>
                <label class="col col-md-1 text-align-right padding7">
                    {{= configuration}}
                </label>
                <label class="col col-md-3">
                    <input name="panel" style="width: 100%" disabled />
                </label>
                <label class="col col-md-1">
                    <a class="btn btn-default" onclick="deleteSplit(this)">
                        {{= deleteScreen}}
                    </a>
                </label>
            </div>
            <div class="row">
                <label class="col col-md-1 text-align-right panel-index">

                </label>
                <label class="col col-md-1 padding7 text-align-right">
                    {{= lateral}}
                </label>
                <label class="col col-md-2">
                    <input type="number" tag="qty-width" integer value="0" placeholder="宽"/>
                </label>
                <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                    ×
                </label>
                <label class="col col-md-1 padding7 text-align-right">
                    {{= longitudinal}}
                </label>
                <label class="col col-md-2">
                    <input type="number" tag="qty-height" integer value="0" placeholder="高"/>
                </label>
                <label class="col col-md-2 padding7 text-align-right">
                    <span role="total">0</span>
                </label>
            </div>
            <div class="row">
                <label class="col col-md-1">
                </label>
                <label class="col col-md-1 padding7 text-align-right">
                    {{= realWidth}}
                </label>
                <label class="col col-md-2">
                    <input type="number" tag="seq-width" value="0" placeholder="宽"/>
                </label>
                <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                    ×
                </label>
                <label class="col col-md-1 padding7 text-align-right">
                    {{= realHeight}}
                </label>
                <label class="col col-md-2">
                    <input type="number" tag="seq-height" value="0" placeholder="高"/>
                </label>
                <label class="col col-md-2 padding7 text-align-right">
                    <span role="total">0</span>
                </label>
            </div>
        </div>
    </fieldset>
</script>

<script id="split-params-template" type="text/x-jquery-tmpl">
    <div class="fnt" style="margin-top: 10px;">
        <p class="pline panel-index"><dic class="ml5 mr5" data-dic="offer.create.box"></dic></p>
        <table class="table" style="width: 100%">
            <tbody style="border-left: 1px dashed #ccc;">
            <tr class="no-border">
                <td class="text-align-left first-td"><dic data-dic="offer.create.box.size">{{= boxSize}}</dic></td>
                <td class="text-align-right first-td">{{= boxWidth}}*{{= boxHeight}}*{{= boxThickness}}</td>
                <td class="text-align-left first-td"><dic data-dic="offer.create.box.pix">{{= boxResolve}}</dic></td>
                <td class="text-align-right first-td">{{= boxTransversePix}}*{{= boxPortraitPix}}</td>
                <td class="text-align-left first-td"><dic data-dic="offer.create.box.number">{{= moduleQty}}</dic></td>
                <td class="text-align-right first-td">{{= boxTransverseCount*boxPortraitCount}}</td>
                <td class="text-align-left first-td" style="width: 10% !important;"><dic data-dic="offer.create.box.display">{{= pixels}}</dic></td>
                <td class="text-align-right first-td" style="width: 15% !important;">{{= boxTransversePix*boxPortraitPix}}</td>
            </tr>
            <tr>
                <td class="text-align-left"><dic data-dic="offer.create.module.weight">{{= singleWeight}}</dic></td>
                <td class="text-align-right">{{= boxWeight}}kg</td>
                <td class="text-align-left"><dic data-dic="offer.create.params.powerAvg">{{= powerAvgMsg}}</dic></td>
                <td class="text-align-right">{{= powerAvg}}</td>
                <td class="text-align-left"><dic data-dic="offer.create.params.powerMax">{{= powerMaxMsg}}</dic></td>
                <td class="text-align-right">{{= powerMax}}</td>
                <td class="text-align-left" style="width: 10% !important;"><dic data-dic="offer.create.BoxMaterial">{{= boxTypeMsg}}</dic></td>
                <td class="text-align-right" style="width: 15% !important;">{{= boxType}}</td>
            </tr>
            </tbody>
        </table>
    </div>
</script>

<script id="view-template" type="text/template">
    <#--基本信息-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
        <div class="jarviswidget jarviswidget-sortable" style="margin-bottom: 0 !important;">
            <div style="border-bottom: none;border-top: 1px solid #ccc;padding-top: 0 !important;">
                <div class="widget-body" style="padding-bottom: 0 !important;">
                    <div class="row view-row">
                        <label class="col col-md-2 text-align-right no-left no-right">
                            {{= basic.salesLang}}
                        </label>
                        <label class="col col-md-4">
                            {{= basic.sales}}
                        </label>
                        <label class="col col-md-2 text-align-right">
                            {{= basic.customerLang}}
                        </label>
                        <label class="col col-md-4">
                            {{= basic.customer}}
                        </label>
                    </div>

                    <div class="row view-row">
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.areaLang}}
                        </label>
                        <label class="col col-md-4">
                            {{= basic.area}}
                        </label>
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.moneyUnitLang}}
                        </label>
                        <label class="col col-md-4">
                            {{= basic.moneyUnit}}
                        </label>
                    </div>

                    <div class="row view-row">
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.guaranteeLang}}
                        </label>
                        <label class="col col-md-4">
                            {{= basic.guarantee}}
                        </label>

                        <label class="col col-md-2 no-left no-right">
                            {{= basic.sizeTypeLang}}
                        </label>
                        <label class="col col-md-4">
                            {{= basic.sizeType}}
                        </label>
                    </div>

                    <div class="row view-row">
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.sizeUnitLang}}
                        </label>
                        <label class="col col-md-4">
                            {{= basic.sizeUnit}}
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--屏体信息-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable" style="margin-top: 10px;">
        <div class="jarviswidget">
            <header>
                <ul class="nav nav-tabs pull-left">
                    {{each(i,panel) panelList}}
                    <li {{if i == 0}} class="active" {{/if}} >
                        <a data-toggle="tab" href="#view-panel-{{= i+1}}">
                        <span class="hidden-mobile hidden-tablet">
                            {{= basic.panelLang}}{{= i+1}}
                        </span>
                        </a>
                    </li>
                    {{/each}}
                </ul>
            </header>
            <div style="border-bottom: 1px solid #ccc;">
                <div class="widget-body no-padding">
                    <div class="tab-content padding-10">
                        {{each(i,item) panelList}}
                        <div class="tab-pane fade in {{if i == 0}} active {{/if}}" id="view-panel-{{= i+1}}">
                            {{each(j, child) item.panels.childPanels}}
                            <div class="row">
                                <label class="col col-md-1">
                                </label>
                                <#--<label class="col col-md-1 view-col text-align-right">-->
                                    <#--{{= basic.productTypeLang}}-->
                                <#--</label>-->
                                <#--<label class="col col-md-2 view-col">-->
                                    <#--{{= child.line}}-->
                                <#--</label>-->
                                <label class="col col-md-2 view-col text-align-right">
                                    {{= basic.productLang}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.seriesText}}
                                </label>
                                <label class="col col-md-2 view-col text-align-right">
                                    {{= basic.configLang}}
                                </label>
                                <label class="col col-md-5 view-col">
                                    {{= child.remark}}
                                </label>
                            </div>
                            {{if item.panels.childPanels.length == 1}}
                            <div class="row">
                                <label class="col col-md-1"></label>
                                <label class="col col-md-2 view-col text-align-right">
                                    {{= basic.custWidthLang}}
                                </label>
                                <label class="col col-md-1 view-col">
                                    {{= item.panels.custHorizontal}}
                                </label>
                                <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                    ×
                                </label>
                                <label class="col col-md-2 view-col text-align-right">
                                    {{= basic.custHeightLang}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= item.panels.custLongitudinal}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= item.panels.custHorizontal*item.panels.custLongitudinal}}{{= basic.unit}}
                                </label>
                            </div>
                            {{/if}}
                            <div class="row">
                                <label class="col col-md-1 view-col text-align-right panel-index">
                                    {{= basic.boxLang}}{{= j+1}}
                                </label>
                                <label class="col col-md-2 view-col text-align-right view-desc">
                                    {{= basic.lateralLang}}
                                </label>
                                <label class="col col-md-1 view-col view-desc">
                                    {{= child.wcount}}
                                </label>
                                <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                    ×
                                </label>
                                <label class="col col-md-2 view-col text-align-right view-desc">
                                    {{= basic.longitudinalLang}}
                                </label>
                                <label class="col col-md-2 view-col view-desc">
                                    {{= child.lcount}}
                                </label>
                                <label class="col col-md-2 view-col view-desc">
                                    {{= child.wcount*child.lcount}}pcs
                                </label>
                            </div>
                            <div class="row" style="border-bottom: 1px solid #CCC">
                                <label class="col col-md-1 view-col">
                                </label>
                                <label class="col col-md-2 view-col text-align-right">
                                    {{= basic.realWidthLang}}
                                </label>
                                <label class="col col-md-1 view-col">
                                    {{= child.horizontal}}
                                </label>
                                <label class="col col-md-1 text-align-center" style="font-size: 16pt;">
                                    ×
                                </label>
                                <label class="col col-md-2 view-col text-align-right">
                                    {{= basic.realHeightLang}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.longitudinal}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.realArea}}{{= basic.unit}}
                                </label>
                            </div>
                            {{/each}}
                            <div class="row" style="padding-top: 10px">
                                <label class="col col-md-1">
                                </label>
                                <label class="col col-md-2 view-col text-align-right view-desc">
                                    {{= basic.quantityLang}}
                                </label>
                                <label class="col col-md-2 view-col view-desc">
                                    {{= item.panels.quantity}}
                                </label>
                                <label class="col col-md-2 view-col text-align-right view-desc">
                                    {{= basic.totalAreaLang}}
                                </label>
                                <label class="col col-md-2 view-col view-desc">
                                    {{= item.panels.totalArea}}
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.thisPanelPriceLang}}:{{= basic.priceUnit}}{{= item.panels.totalPrice}}
                                </label>
                            </div>
                        </div>
                        {{/each}}
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--标配-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
        <div class="jarviswidget">
            <header>
                <ul class="nav nav-tabs pull-left">
                    {{each(i, panel) panelList}}
                    <li {{if i == 0}} class="active" {{/if}} >
                        <a data-toggle="tab" href="#view-standard-{{= i+1}}">
                        <span class="hidden-mobile hidden-tablet">
                            {{= basic.standardLang}}{{= i+1}}
                        </span>
                        </a>
                    </li>
                    {{/each}}
                </ul>
            </header>
            <div style="border-bottom: 1px solid #ccc;">
                <div class="widget-body no-padding" style="min-height: auto !important;">
                    <div class="tab-content padding-10">
                        {{each(i, item) panelList}}
                        <div class="tab-pane fade in {{if i == 0}} active {{/if}} >" id="view-standard-{{= i+1}}">
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1">
                                    {{= basic.brandLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.typeLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.descLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.priceLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.countsLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.subTotalLang}}
                                </label>
                            </div>
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1 view-col">
                                    LED-Screen
                                </label>
                                <label class="col col-md-2 view-col">
                                    Absen-{{= item.panels.seriesText}}
                                </label>
                                <label class="col col-md-3 view-col">

                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= item.panels.price}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= item.panels.totalArea}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= item.panels.screenPrice}}
                                </label>
                            </div>
                            {{each(j, child) item.standardList}}
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1 view-col">
                                    {{= child.brand}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.spareName}}
                                </label>
                                <label class="col col-md-3 view-col view-spare-desc">
                                    {{= child.desc}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.priceSale}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.countReal}}pcs
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.subTotal}}
                                </label>
                            </div>
                            {{/each}}
                            {{each(j, child) item.selfStandList}}
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1 view-col">
                                    {{= child.brand}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.spareType}}
                                </label>
                                <label class="col col-md-3 view-col">

                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.price}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.amount}}pcs
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.subTotal}}
                                </label>
                            </div>
                            {{/each}}
                            <div class="row">
                                <label class="col col-md-3 view-col view-desc">
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.standardTotal}}:{{= basic.priceUnit}}{{= item.panels.standardTotal}}
                                </label>
                                <label class="col col-md-2 view-col view-desc">
                                    {{= basic.discount}}:{{= item.panels.standardDiscount}}%
                                </label>
                                <label class="col col-md-4 view-col view-desc">
                                    {{= basic.realPrice}}:{{= basic.priceUnit}}{{= item.panels.standardPrice}}
                                </label>
                            </div>
                        </div>
                        {{/each}}
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--免费-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
        <div class="jarviswidget">
            <header>
                <ul class="nav nav-tabs pull-left">
                    {{each(i, panel) panelList}}
                    <li {{if i == 0}} class="active" {{/if}} >
                    <a data-toggle="tab" href="#view-free-{{= i+1}}">
                        <span class="hidden-mobile hidden-tablet">
                            {{= basic.freeLang}}{{= i+1}}
                        </span>
                    </a>
                    </li>
                    {{/each}}
                </ul>
            </header>
            <div style="border-bottom: 1px solid #ccc;">
                <div class="widget-body no-padding" style="min-height: auto !important;">
                    <div class="tab-content padding-10">
                        {{each(i, item) panelList}}
                        <div class="tab-pane fade in {{if i == 0}} active {{/if}} >" id="view-free-{{= i+1}}">
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1">
                                    {{= basic.brandLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.typeLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.descLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.priceLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.countsLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.subTotalLang}}
                                </label>
                            </div>
                            {{each(j, child) item.freeList}}
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1 view-col">
                                    {{= child.brand}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.spareName}}
                                </label>
                                <label class="col col-md-3 view-col view-spare-desc">
                                    {{= child.desc}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.priceSale}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.countReal}}pcs
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.subTotal}}
                                </label>
                            </div>
                            {{/each}}
                            {{each(j, child) item.selfFreeList}}
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1 view-col">
                                    {{= child.brand}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.spareType}}
                                </label>
                                <label class="col col-md-3 view-col">

                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.price}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.amount}}pcs
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.subTotal}}
                                </label>
                            </div>
                            {{/each}}
                            <div class="row">
                                <label class="col col-md-3 view-col view-desc">
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.freeTotal}}:{{= basic.priceUnit}}{{= item.panels.freePrice}}
                                </label>
                                <label class="col col-md-2 view-col view-desc">
                                </label>
                                <label class="col col-md-4 view-col view-desc">
                                    {{= basic.realPrice}}:{{= basic.priceUnit}}0
                                </label>
                            </div>
                        </div>
                        {{/each}}
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--选配-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
        <div class="jarviswidget">
            <header>
                <ul class="nav nav-tabs pull-left">
                    {{each(i, panel) panelList}}
                    <li {{if i == 0}} class="active" {{/if}} >
                    <a data-toggle="tab" href="#view-spare-{{= i+1}}">
                        <span class="hidden-mobile hidden-tablet">
                            {{= basic.spareLang}}{{= i+1}}
                        </span>
                    </a>
                    </li>
                    {{/each}}
                </ul>
            </header>
            <div style="border-bottom: 1px solid #ccc;">
                <div class="widget-body no-padding" style="min-height: auto !important;">
                    <div class="tab-content padding-10">
                        {{each(i, item) panelList}}
                        <div class="tab-pane fade in {{if i == 0}} active {{/if}} >" id="view-spare-{{= i+1}}">
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1">
                                    {{= basic.brandLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.typeLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.descLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.priceLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.countsLang}}
                                </label>
                                <label class="col col-md-2">
                                    {{= basic.subTotalLang}}
                                </label>
                            </div>
                            {{each(j, child) item.spareList}}
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1 view-col">
                                    {{= child.brand}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.spareName}}
                                </label>
                                <label class="col col-md-3 view-col view-spare-desc">
                                    {{= child.desc}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.priceSale}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.countReal}}pcs
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.subTotal}}
                                </label>
                            </div>
                            {{/each}}
                            {{each(j, child) item.selfSpareList}}
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-1 view-col">
                                    {{= child.brand}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.spareType}}
                                </label>
                                <label class="col col-md-3 view-col">

                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.price}}
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= child.amount}}pcs
                                </label>
                                <label class="col col-md-2 view-col">
                                    {{= basic.priceUnit}}{{= child.subTotal}}
                                </label>
                            </div>
                            {{/each}}
                            <div class="row">
                                <label class="col col-md-3 view-col view-desc">
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.spareTotal}}:{{= basic.priceUnit}}{{= item.panels.spareTotal}}
                                </label>
                                <label class="col col-md-2 view-col view-desc">
                                    {{= basic.discount}}:{{= item.panels.spareDiscount}}%
                                </label>
                                <label class="col col-md-4 view-col view-desc">
                                    {{= basic.realPrice}}:{{= basic.priceUnit}}{{= item.panels.sparePrice}}
                                </label>
                            </div>
                        </div>
                        {{/each}}
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--服务-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
        <div class="jarviswidget">
            <header>
                <ul class="nav nav-tabs pull-left">
                    <li class="active">
                        <a data-toggle="tab" href="#view-service-1">
                        <span class="hidden-mobile hidden-tablet">
                            {{= basic.serviceLang}}
                        </span>
                        </a>
                    </li>
                </ul>
            </header>
            <div style="border-bottom: 1px solid #ccc;">
                <div class="widget-body no-padding" style="min-height: auto !important;">
                    <div class="tab-content padding-10">
                        <div class="tab-pane fade in active" id="view-service-1">
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-3">
                                    {{= basic.typeLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.priceLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.countsLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.subTotalLang}}
                                </label>
                            </div>
                            {{each(i,service) serviceList}}
                            <div class="row" style="border-bottom: 1px solid #ccc;">
                                <label class="col col-md-3 view-col">
                                    {{= service.name}}
                                </label>
                                <label class="col col-md-3 view-col">
                                    {{= basic.priceUnit}}{{= service.price}}
                                </label>
                                <label class="col col-md-3 view-col">
                                    {{= service.counts}}
                                </label>
                                <label class="col col-md-3 view-col">
                                    {{= basic.priceUnit}}{{= service.price*service.counts}}
                                </label>
                            </div>
                            {{/each}}
                            <div class="row">
                                <label class="col col-md-3 view-col view-desc">
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.serviceTotalLang}}:{{= basic.priceUnit}}{{= basic.serviceTotal}}
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.discount}}:{{= basic.serviceDiscount}}%
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.realPrice}}:{{= basic.priceUnit}}{{= basic.servicePrice}}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--包装箱-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
        <div class="jarviswidget">
            <header>
                <ul class="nav nav-tabs pull-left">
                    <li class="active">
                        <a data-toggle="tab" href="#view-package-1">
                        <span class="hidden-mobile hidden-tablet">
                            {{= basic.packageLang}}
                        </span>
                        </a>
                    </li>
                </ul>
            </header>
            <div style="border-bottom: 1px solid #ccc;">
                <div class="widget-body no-padding">
                    <div class="tab-content padding-10">
                        <div class="tab-pane fade in active" id="view-package-1">
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-3">
                                    {{= basic.typeLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.priceLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.countsLang}}
                                </label>
                                <label class="col col-md-3">
                                    {{= basic.subTotalLang}}
                                </label>
                            </div>
                            {{each(i,package) packageList}}
                            <div class="row" style="border-bottom: 1px solid #ccc">
                                <label class="col col-md-3 view-col">
                                    {{= package.packages}}
                                </label>
                                <label class="col col-md-3 view-col">
                                    {{= package.priceUnit}}
                                </label>
                                <label class="col col-md-3 view-col">
                                    {{= package.number}}
                                </label>
                                <label class="col col-md-3 view-col">
                                    {{= package.price}}
                                </label>
                            </div>
                            {{/each}}
                            <div class="row">
                                <label class="col col-md-3 view-col view-desc">
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.tradeTermLang}}:{{= basic.tradeTerm}}
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.transportLang}}:{{= basic.priceUnit}}{{= basic.tradeCost}}
                                </label>
                                <label class="col col-md-3 view-col view-desc">
                                    {{= basic.realPrice}}:{{= basic.priceUnit}}{{= basic.transportTotal}}
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--其他信息-->
    <article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
        <div class="jarviswidget jarviswidget-sortable" style="margin-bottom: 0 !important;">
            <div style="border-bottom: none;border-top: 1px solid #ccc;padding-top: 0 !important;">
                <div class="widget-body" style="padding-bottom: 0 !important;">
                    <div class="row view-row">
                        <label class="col col-md-2 text-align-right no-left no-right">
                            {{= basic.paymentLang}}:
                        </label>
                        <label class="col col-md-4">
                            {{= basic.payment}}
                        </label>
                        <label class="col col-md-2 text-align-right no-left no-right">
                            {{= basic.companyLang}}:
                        </label>
                        <label class="col col-md-4">
                            {{= basic.company}}
                        </label>
                    </div>

                    <div class="row view-row">
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.addressLang}}:
                        </label>
                        <label class="col col-md-4 view-spare-desc">
                            {{= basic.address}}
                        </label>
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.waitingDateLang}}:
                        </label>
                        <label class="col col-md-4">
                            {{= basic.waitingDate}}
                        </label>
                    </div>

                    <div class="row view-row">
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.projectNameLang}}:
                        </label>
                        <label class="col col-md-4">
                            {{= basic.projectName}}
                        </label>
                        <label class="col col-md-2 no-left no-right">
                            {{= basic.remarkLang}}:
                        </label>
                        <label class="col col-md-4">
                            {{= basic.remark}}
                        </label>
                    </div>

                    <div class="row view-row">
                        <label class="col col-md-3 view-desc">
                        </label>
                        <label class="col col-md-3 view-desc">
                            {{= basic.totalPriceLang}}:{{= basic.priceUnit}}{{= basic.totalbeforeDiscount}}
                        </label>
                        <label class="col col-md-3 view-desc">
                            {{= basic.discount}}{{= basic.totalDiscount}}%
                        </label>
                        <label class="col col-md-3 view-desc">
                            {{= basic.realPrice}}:{{= basic.priceUnit}}{{= basic.totalPrice}}
                        </label>
                    </div>

                    {{if basic.specialPrice != ''}}
                    <div class="row view-row">
                        <label class="col col-md-3">
                        </label>
                        <label class="col col-md-6">
                            {{= basic.specialPriceRemarkLang}}:{{= basic.specialPriceRemark}}
                        </label>
                        <label class="col col-md-3">
                            {{= basic.specialPriceLang}}:{{= basic.priceUnit}}{{= basic.specialPrice}}
                        </label>
                    </div>
                    {{/if}}

                </div>
            </div>
        </div>
    </article>
</script>

<script type="text/javascript" src="../../static/js/plugin/bootstrap-autosuggest/autosuggest.js"></script>
<script>

    //设置输入框只能输入至多4位小数点,排除含有integer,amount属性的dom
    $('body').on('keypress', 'input[type="number"]:not([integer]):not([amount])', function () {
        if (String.fromCharCode(event.keyCode) == 'e') {
            return false;
        }
        var value = $(this).val();
        if (!value) {
            return true;
        }
        return /^\d+(?:\.\d{1,3})?$/.test(value);
    });

    //设置输入框只能输入整数,含有integer,amount属性的dom
    $('body').on('keypress', 'input[integer],input[amount]', function () {
        return /[\d]/.test(String.fromCharCode(event.keyCode));
    });

    /**
     * 0获取焦点时变为空
     */
    $('body').on('focus', 'input[type="number"]', function () {
        if ($(this).val() === '0') {
            $(this).val('');
        }
    });

    $("#panel-" + '${tag}').find('[name=product]').attr('data-placeholder', message["offer.create.select"]);
    $('[data-dic]').each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    pageSetUp();

    initArea();

    /**
     * 如果销售助理报价，存在选择销售的可能，则重新加载下拉数据
     */
    $('.sales-select').change(function () {
        //每次下拉前先清除选中数据
        $('#price_area').select2('val', '');
        initArea();
    });

    /**
     * 报价区域初始化赋值
     */
    function initArea() {
        var areaValues = [];
        $.ajax({
            url: '/offer/area/get',
            data: {userId: $('.sales-select').select2('val')},
            async: false,
            success: function (data) {
                $.each(data, function (index, item) {
                    areaValues.push({
                        text: item.name,
                        id: item.id
                    });
                });
            }
        });
        $('#price_area').select2({
            data: areaValues,
            minimumResultsForSearch:-1
        });
        if (areaValues.length > 0) {
            $('#price_area').val(areaValues[0]['id']).trigger('change');
        }
        //如果没有选中值，则无法选择产品类型进行报价
        if (!$('#price_area').select2('val')) {
            $('#panel-list-content').find('[name="line"]').each(function (index, item) {
                $(item).attr('disabled', true);
            });
        }
    }

    //初始化货币单位的方法
    function initMoneyType() {
        var unit = $('#money-type option:selected').data('key');
        $('.moneyUnit').each(function () {
            $(this).html(unit);
        });
    }

    //加载箱体index 的函数拼屏按钮时
    function initPanelIndex(self) {
        self.find('fieldset .panel-index').each(function (index, item) {
            $(item).html(message["portal.dashboard.panel"] + (index + 1));
        });
    }

    //加载参数列箱体index 的函数
    function initParamsIndex(self) {
        self.find('.panel-index').each(function (index, item) {
            $(item).html(message["portal.dashboard.panel"] + (index + 1));
        });
    }

    // 交期数字框
    $("#valid-date").spinner({
        step: 1,
        min: 1,
        max: 999
    });

    //报价区域加载
    if ($("#price_area,#price_area").select2("val")) {
        loadUnit($("#price_area").select2("val"));
    }

    /*客户选择*/
    $("#spare-choose_customer").off("click").click(function (e) {
        e.preventDefault();
        var checkboxs = $("#choose_spare_dialog_customer table input:checkbox:checked");
        if (!checkboxs || checkboxs.length == 0) {
            alert(message["offer.create.customer"], 2);
            return false;
        }
        checkboxs.each(function () {
            var tr = $(this).parents("tr:first");
            var data = {
                name: $(tr).data("name"),
                id: $(tr).data("id")
            };
            $("#select_customer").val(data.name);
            $("#select_customer").data("id", data.id);
        });

        $("#dialog_table_customer").modal("hide");
        $("#choose_spare_dialog_customer").html("");
    });

    /**
     * 添加新客户
     **/
    function addNewCustomer() {
        var name = $("#name").val();
        var type = $("#type").val();
        var location = $("#location").val();
        var phone = $("#phone").val();

        showLoading();
        $.ajax({
            url: "/customer/customer/list",
            data: {
                name: name,
                type: type,
                location: location,
                phone: phone
            },
            type: "get",
            success: function (data) {
                $("#choose_spare_dialog_customer").html(data);
                $("#dialog_table_customer").modal("show");
                hideLoading();
            }
        });
    }

    /**
     * 加载单位
     **/
    function loadUnit(area) {
        $.ajax({
            url: "/offer/area/unit",
            type: "get",
            data: {area: area},
            success: function (data) {
                if (data) {
                    $("#money-type").select2("val", data);
                    //成功后加载单位
                    initMoneyType();
                }
            }
        });
    }

    var evt = function () {
        return {
            /**
             * 客户以及付款方式autosuggest
             **/
            initForm: function () {
                //客户
                // $('#select_customer').autosuggest({
                //     url: '/api/customer/customer/autosuggest',
                //     queryParamName: 'name',
                //     minLength: 1,
                //     onSelect: function (option) {
                //         $('#select_customer').data('id', $(option).data('id'));
                //     }
                // });
                $.ajax({
                    url: '/api/dict/find/category',
                    data: {category: 'payment_mode'},
                    type: "GET",
                    success: function (res) {
                        //付款方式'
                        $('#select_payment').data('list', res);
                        $('#select_payment').autosuggest({
                            dataList: res,
                            local: true,
                            minLength: 1
                        });
                    }
                });
            }
        }
    }();
    evt.initForm();
</script>
<script type="text/javascript">
    var lengths = $("[name='radio-inline']:radio:checked").val();
    var lengthType = (lengths == 1)? 1 : 0.3048;//用于换算米和英尺
    var squareType = (lengths == 1)? 1 : 0.092903;//
    var text="";
    $(document).ready(function () {
        addPanel('${tag}');

        //禁止键盘输入e
        // $('body').on('keypress', 'input[type="number"]', function () {
        //     return (/[\d]/.test(String.fromCharCode(event.keyCode)));
        // });
    });

    //每次选择尺寸，则切换尺寸单位
    $("[name='radio-inline']").change(function () {
        lengths = $("[name='radio-inline']:radio:checked").val();
        lengthType = (lengths == 1)? 1 : 0.3048;//用于换算米和英尺
        squareType = (lengths == 1)? 1 : 0.092903;//
    });

    function addAnother() {

    }

    //添加新屏体的方法
    function appendNewPanel() {
        var length = $("#panel-list-header li").length + 1;
        if (length >= 6) {
            return;
        }

        var tag = (new Date()).valueOf();
        var panelTemp = {
            tag: 'panel-' + tag,
            panelTag: tag,
            panelTotal: message["offer.create.panelTotal"],
            scnNo: message["panel.list.search.scn"],
            panelCount: message["offer.create.panelNumber"],
            total: message["offer.create.totalCount"],
            price: message["offer.create.priceUnit"],
            priceCount: message["offer.create.priceCount"],
            panelArea: message["offer.create.panelArea"],
            totalArea: message["offer.create.totalArea"],
            discount: message["offer.create.discount"],
            realPrice: message["offer.create.realPrice"],
            certification: message['offer.create.certification'],
            // certificationDefault: certificationDefault,
            state: message['product.panel.list.state'],
            empty: message['product.panel.no'],
            quantity: message['offer.create.screenQuantity'],
            priceLevel: message['offer.create.priceLevel'],
            product: message['offer.create.product'],
            configuration: message['product.panel.list.configuration'],
            params: message['offer.create.params'],
            productType: message['offer.create.type'],
            custWidth: message['offer.create.custWidth'],
            custHeight: message['offer.create.custHeight'],
            split: message['offer.create.split'],
            box: message['portal.dashboard.panel'],
            lateral: message['offer.create.lateral'],
            longitudinal: message['offer.create.longitudinal'],
            realWidth: message['offer.create.realWidth'],
            realHeight: message['offer.create.realHeight'],
            screenQty: message['offer.create.screenQuantity']
        };
        $("#product-content-template").tmpl(panelTemp).appendTo($("#panel-list-content"));

        var panelHeader = {name: message["offer.create.panel"], tag: "panel-" + tag, index: length};
        $("#product-header-template").tmpl(panelHeader).appendTo($("#panel-list-header ul:first"));

        var paramHeader = {name: message["offer.create.params"], tag: "param-" + tag + "-1", index: length};
        $("#params-header-template").tmpl(paramHeader).appendTo($("#panel-param-1 ul:first"));

        var modualHeader = {name: message["offer.create.standard"], tag: "spare-" + tag + "-1", index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-1 ul:first"));

        var modualHeader = {name: message["offer.create.spare"], tag: "spare-" + tag + "-2", index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-2 ul:first"));

        var modualHeader = {name: message["offer.create.free"], tag: "spare-" + tag + "-3", index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-3 ul:first"));

        addPanel(tag);
        reBuildPanelIndex();
        initMoneyType();
    }

    //重新刷新屏体以及
    function refreshPanel(panel) {
        var panelId = panel.find('[name="panel"]').select2('val');
        if (panelId) {
            panel.find("[name='panel']").change();
        }else {
            initMoneyType();
        }
    }

    //添加屏体的函数
    function addPanel(id) {
        var panel = $("#panel-" + id);


        // 让对话框的标题支持html
        $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
            _title: function(title) {
                if (!this.options.title ) {
                    title.html("&#160;");
                } else {
                    title.html(this.options.title);
                }
            }
        }));

        // 价格水平 start
        $(".jarviswidget").on("click", ".edit-a", function () {
            var $dialog = $("#dialog-message");
            $dialog.find('[tag="sale-price"]').val($(this).parents("fieldset").find("[sales]").val());
            $dialog.find('[tag="panel-discount"]').val(100);
            $dialog.find('[tag="discounted-price"]').val($(this).parents("fieldset").find("[sales]").val());
            $dialog.dialog('open');
            $dialog.find('[tag="sale-price"]').spinner({
                step: 1, min: 0, max: 1000000,
                change: function () {
                    var self = $(this);
                    if (self.val() && isNum(self.val()) && self.val() > 0) {
                        var discount = $dialog.find('[tag="panel-discount"]').val() || 0;
                        $dialog.find('[tag="panel-discount"]').val(discount);
                        $dialog.find('[tag="discounted-price"]').val(toDecimal(parseFloat(self.val())*discount/100));
                    } else {
                        self.val(0);
                        $dialog.find('[tag="discounted-price"]').val(0);
                    }
                }
            });
            $dialog.find('[tag="panel-discount"]').spinner({
                step: 1, min: 0, max: 1000000,
                change: function () {
                    var self = $(this);
                    if (self.val() && isNum(self.val()) && self.val() > 0) {
                        var selfNum = self.val();
                        var salePrice = parseFloat($dialog.find('[tag="sale-price"]').val()) || 0;
                        $dialog.find('[tag="discounted-price"]').val(toDecimal(salePrice*selfNum/100));
                    } else {
                        self.val(0);
                        $dialog.find('[tag="discounted-price"]').val(0);
                    }
                }
            });
            $dialog.find('[tag="discounted-price"]').spinner({
                step: 1, min: 0, max: 1000000,
                change: function () {
                    var self = $(this);
                    if (self.val() && isNum(self.val()) && self.val() > 0) {
                        var salePrice = parseFloat($dialog.find('[tag="sale-price"]').val()) || 0;
                        var discount = parseFloat(toDouble(self.val()/salePrice*100));
                        $dialog.find('[tag="panel-discount"]').val(discount);
                        $dialog.find('[tag="discounted-price"]').val(toDecimal(salePrice*discount/100));
                    } else {
                        self.val(0);
                        $dialog.find('[tag="discounted-price"]').val(0);
                    }
                }
            });
            return false;
        });
        $("#dialog-message").dialog({
            autoOpen : false,
            width : 600,
            resizable : false,
            modal : true,
            title : "<div class='widget-header'><h4>编辑价格</h4></div>",
            buttons : [{
                html : "<i class='fa fa-check'></i>&nbsp; OK",
                "class" : "btn btn-primary",
                click : function() {
                    var realPrice = $("#dialog-message").find('[tag="discounted-price"]').val();
                    $("#panel-standard-dialog-1").find(".tab-pane.fade.in.active fieldset").eq(0).find("[sales]").val(realPrice);
                    var fieldSet = $("#panel-standard-dialog-1").find(".tab-pane.fade.in.active fieldset").eq(0).find("[sales]").parents("fieldset");
                    calcSingleSparePrice(fieldSet);
                    $(this).dialog("close");
                }
            }, {
                html : "Cancel",
                "class" : "btn btn-default",
                click : function() {
                    $(this).dialog("close");
                }
            }]
        });
        $("#reset-price").click(function () {
            var $dialog = $("#dialog-message");
            var price = parseFloat($("#spare-" + id + "-1 fieldset").eq(0).find("[sales]").val());
            $dialog.find('[tag="sale-price"]').val(price);
            $dialog.find('[tag="panel-discount"]').val(100);
            $dialog.find('[tag="discounted-price"]').val(price);
        });
        //价格水平 end

        // 下列方法用于下拉框刷新 start
        $("#money-type").change(function () {
            refreshPanel( panel);
        });
        $("#price_area").change(function () {
            refreshPanel(panel);
        });
        $('[name="radio-inline"]').change(function () {
            refreshPanel(panel);
        });
        $('select[name="guarantee"]').change(function () {
            refreshPanel(panel);
        });
        //end

        panel.find("[name='line']").select2();
        //产品线与产品下拉框联动
        panel.find("[name='line']").change(function () {
            var value = $(this).select2("val");

            var $product = panel.find("[name='product']");
            var $panel = panel.find("[name='panel']");
            if(!value || value.length===0) {
                $product.select2({
                    data:[],
                    placeholder: message['select2.placeholder.msg']
                });
                $product.prop("disabled",true);

                $panel.select2({
                    data:[],
                    placeholder: message['select2.placeholder.msg']
                });
                $panel.prop("disabled",true);
                return;
            }
            var values = [];
            $.ajax({
                url:'/offer/line/get',
                type:'get',
                data: {line: value, area: $("#price_area").select2("val")},
                success: function (data) {
                    $.each(data, function (index, item) {
                        values.push({
                            text: item.text,
                            children: item.children
                        });
                    });
                    $product.select2({
                        data:values,
                        placeholder: message['select2.placeholder.msg'],
                        minimumResultsForSearch:-1
                    });
                    $product.removeAttr("disabled");

                    $panel.select2({
                        data:[],
                        placeholder: message['select2.placeholder.msg']
                    });
                    $panel.prop("disabled",true);
                },error: function (data) {

                }
            });
        });

        //产品与物料号两个下拉菜单二级联动
        panel.find("[name='product']").select2({data:[],minimumResultsForSearch:-1,placeholder: message['offer.create.select']});
        panel.find("[name='panel']").select2({data:[],minimumResultsForSearch:-1,placeholder: message['offer.create.choosePanel']});
        panel.find("[name='product']").change(function () {
            var value = $(this).select2("val");
            text = $(this).select2("data").text.toLowerCase();
            var $panel = panel.find("[name='panel']");
            if(!value || value.length === 0) {
                $panel.select2({
                    data:[],
                    placeholder: message['select2.placeholder.msg']
                });
                $panel.prop("disabled",true);
                return;
            }
            var values = [];
            var transverseCount=0;
            var portraitCount=0;
            $.ajax({
               url:'/offer/product/get',
               type:'get',
               data: {id: value, area: $("#price_area").select2("val")},
               success: function (data) {
                   $.each(data, function (index, item) {
                   	transverseCount=item.transverseCount;
                   	portraitCount=item.portraitCount;
                       values.push({
                           text: item.state,
                           id: item.id
                       });
                   });
                   $panel.select2({
                       data:values,
                       placeholder: message['select2.placeholder.msg'],
                       minimumResultsForSearch:-1
                   });
                   $panel.removeAttr("disabled");
                   var $amountDiv=$('div [name="amountDiv"]');
                   var isIcon=text.indexOf("icon")==0?true:false;
                   if(isIcon) {
                   		//$('input[tag="qty-height"]').val(portraitCount);
                   		//$('input[tag="qty-width"]').val(transverseCount);
                   		$('input[tag="qty-height"]').val(1);
                   		$('input[tag="qty-width"]').val(1);
                   }
                   $amountDiv.each(function () {
                   		//icon屏隐藏后面的参数
			           var visibility="block";
	                   if(isIcon) {
	                   		visibility="none";
	                   }
	                   $(this).css("display",visibility);
			        });
               },error: function (data) {

               }
            });
        });

        //屏体物料选择事件
        $('body').on('change', "#panel-" + id + " [name='panel']", function () {
            //获取当前change下拉框的父元素的下标
            var fieldsets = $('#panel-' + id + ' fieldset');
            var fieldsetIndex = fieldsets.index($(this).parents("fieldset"));
            var self = $(this);

            //获取当前选择的屏体id
            var value = $(this).select2("val");
            if (value) {
                showLoading();
                $.ajax({
                    url: "/offer/create/panel/params",
                    type: "get",
                    data: {
                        product: value,
                        tag: id,
                        series: panel.find($("[name='product']")).select2("val"),
                        money: $("#money-type").select2('val'),
                        requestArea: $("#price_area").select2("val"),
                        fieldsetIndex: fieldsetIndex
                    },
                    success: function (html) {
                        //判断是否是第一个屏,如果是，则加载参数,配件
                        if (fieldsetIndex === 0) {
                            //判断是否已经选择过屏体物料
                            if ($("#param-" + id + "-1").length > 0) {
                                $("#param-" + id + "-1").html($(html).find("div.tab-pane:eq(0)").html());
                                $("#spare-" + id + "-1").html($(html).find("div.tab-pane:eq(1)").html());
                                $("#spare-" + id + "-2").html($(html).find("div.tab-pane:eq(2)").html());
                                $("#spare-" + id + "-3").html($(html).find("div.tab-pane:eq(3)").html());
                            } else {
                                $(html).find("div.tab-pane:eq(0)").appendTo($("#param-" + id + "-dialog-1"));
                                $(html).find("div.tab-pane:eq(1)").appendTo($("#panel-standard-dialog-1"));
                                $(html).find("div.tab-pane:eq(2)").appendTo($("#panel-standard-dialog-2"));
                                $(html).find("div.tab-pane:eq(3)").appendTo($("#panel-standard-dialog-3"));
                            }
                            initPanelSpare(panel);
                        }else {
                            $.ajax({
                                url: '/offer/rest/get/params',
                                type: 'get',
                                data: {panel: value},
                                success: function (data) {
                                    if (data) {
                                        var boxParamsData = {
                                            boxWidth: data.boxWidth,
                                            boxHeight: data.boxHeight,
                                            boxThickness: data.boxThickness,
                                            boxTransversePix: data.boxTransversePix,
                                            boxPortraitPix: data.boxPortraitPix,
                                            boxTransverseCount: data.boxTransverseCount,
                                            boxPortraitCount: data.boxPortraitCount,
                                            boxWeight: data.boxWeight,
                                            powerMax: data.powerMax,
                                            powerAvg: data.powerAvg,
                                            boxType: data.boxType,
                                            boxSize: message['offer.create.box.size'],
                                            boxResolve: message['offer.create.box.pix'],
                                            moduleQty: message['offer.create.box.number'],
                                            pixels: message['offer.create.box.display'],
                                            singleWeight: message['offer.create.module.weight'],
                                            powerAvgMsg: message['offer.create.params.powerAvg'],
                                            powerMaxMsg: message['offer.create.params.powerMax'],
                                            boxTypeMsg: message['offer.create.BoxMaterial']
                                        };
                                        //如果是拼屏下拉选择，则添加箱体参数
                                        $("#param-" + id + "-dialog-1").find('.fnt').eq(2).before($("#split-params-template").tmpl(boxParamsData));
                                        //初始化箱体index
                                        initParamsIndex($("#param-" + id + "-dialog-1"));
                                    }
                                }
                            });
                        }

                        var js = $(html).find("#param-" + id + "-js").html();
                        eval(js);
                        //重新计算价格
                        reCalcSeq(panel, self);
                        initMoneyType();
                        reCalcEveryCountsAndPrice(id);
                        hideLoading();
                        //成功后加载所有备件的折扣控件
                        $(".row.spare-tag input").spinner({
                            step: 1,
                            min: 1,
                            max: 100,
                            change: function () {
                                var div = $(this).parents(".row.total-tag:first");
                                var sale = parseFloat($(div).find("[tag='total-sale']").html()) || 0;
                                var discount;
                                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                                    discount = parseFloat($(this).val()) || 100;
                                    $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                                    calcPanelTotalPrice(id);
                                    calcTotalPrice();
                                } else {
                                    $(this).val(100);
                                    discount = parseFloat($(this).val()) || 100;
                                    $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                                    calcPanelTotalPrice(id);
                                    calcTotalPrice();
                                }
                            }
                        });
                        // $.each($(this).data('panelInfo'),function () {
                        //     if(Number(value) === Number(this.id)){
                        //         $(self).data('panelItemInfo',this);
                        //     }
                        // });
                    },
                    error: function (err) {
                        hideLoading();
                        alert(message["http.response.error"], 3);
                    }
                });
            }
        });

        //拼屏按钮点击事件
        panel.find("#split-screen").click(function () {
            if (!$(this).parents('fieldset').find('[name="panel"]').val()) {
                alert(message['offer.create.splitBtn1'], 2);
                return;
            }
            if ($("#panel-" + id).find('fieldset').length == 3) {
                alert(message['offer.create.splitBtn2'], 2);
                return;
            }
            $(this).parents('fieldset').find(".cust_size").hide();
            var lineText = $(this).parents('fieldset').find('[name="line"]').select2('data').text;
            var productText = $(this).parents('fieldset').find('[name="product"]').select2('data').text;
            //分别获取第一个屏体的系列和物料号
            var productVal = $(this).parents('fieldset').find('[name="product"]').select2('val');
            var panelArr = [];
            //获取已选择的物料号，备用
            panel.find('[name="panel"]').each(function (index, item) {
                var panelValue = $(item).select2('val');
                if(panelValue) {
                    panelArr.push(panelValue);
                }
            });

            //填充翻译数据
            var traslate = {
                productType: message['offer.create.type'],
                product: message['offer.create.product'],
                configuration: message['product.panel.list.configuration'],
                deleteScreen: message['body.list.delete'],
                lateral: message['offer.create.lateral'],
                longitudinal: message['offer.create.longitudinal'],
                realWidth: message['offer.create.realWidth'],
                realHeight: message['offer.create.realHeight']
            };
            var template= $("#split-screen-template").tmpl(traslate);
            var newItem = $(template).clone(true);
            newItem.find('input[tag="qty-height"],input[tag="qty-width"]').spinner({
                step: 1,
                min: 1,
                max: 100000,
                change: function () {
                    if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                        reCalcSeq(panel, $(this));
                    } else {
                        $(this).val(0);
                        reCalcSeq(panel, $(this));
                    }
                }
            });
            newItem.find("input[tag='seq-height'],input[tag='seq-width']").spinner({
                step: 1,
                min: 1,
                max: 100000,
                change: function () {
                    if ($(this).val() > 0) {
                        calcQty(panel, $(this));
                    } else {
                        $(this).val(0);
                        calcQty(panel, $(this));
                    }
                }
            });
            newItem.find('[name="line"]').val(lineText);
            newItem.find('[name="product"]').val(productText);
            var values = [];
            $.ajax({
                url: '/offer/split/get',
                type: 'get',
                data: {id: productVal, area: $("#price_area").select2("val"), panelIds: panelArr.join(',')},
                success: function (data) {
                    $.each(data, function (index, item) {
                        values.push({
                            text: item.state,
                            id: item.id
                        });
                    });
                    newItem.find('[name="panel"]').select2({
                        data:values,
                        placeholder: message['select2.placeholder.msg'],
                        minimumResultsForSearch:-1
                    });
                    newItem.find('[name="panel"]').removeAttr("disabled");
                },error: function (data) {

                }
            });

            panel.find(".product-footer").before(newItem);

            initPanelIndex(panel);
        });

        //客户要求宽度和客户要求高度spinner  change  and 计算事件
        panel.find("input[tag='cust-width'],input[tag='cust-height']").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function () {
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    reCalcCustSeq(panel, $(this));
                } else {
                    $(this).val(0);
                    reCalcCustSeq(panel, $(this));
                }
            }
        });

        //箱体数量spinner  change  and 计算事件
        panel.find("input[tag='qty-height'],input[tag='qty-width']").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function () {
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    reCalcSeq(panel, $(this));
                } else {
                    $(this).val(0);
                    reCalcSeq(panel, $(this));
                }
            }
        });

        //屏体面积spinner  change  and 计算事件
        panel.find("input[tag='seq-height'],input[tag='seq-width']").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function () {
                if ($(this).val() > 0) {
                    calcQty(panel, $(this));
                } else {
                    $(this).val(0);
                    calcQty(panel, $(this));
                }
            }
        });

        //屏体数量spinner  change  and 计算事件
        panel.find("input[tag='quantity']").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function () {
                if (!$(this).val()) {
                    $(this).val(0);
                }
                reCalcSplitTotalArea(panel, panel.attr("id").split("-")[1]);
            }
        });
    }


    /**
     * 计算客户要求面积
     **/
    function reCalcCustSeq(panel, self) {
        var widthInput = parseFloat($(panel).find("input[tag='cust-width']").val()) || 0;
        var heightInput = parseFloat($(panel).find("input[tag='cust-height']").val()) || 0;

        if (widthInput*heightInput !== 0) {
            $(panel).find('[role="cust-total"]').html(widthInput*heightInput + ($("[name='radio-inline']:radio:checked").val() == 1 ? "㎡" : "sq.ft"));
        }
        var heightUnit = parseFloat(self.parents('fieldset').data("height"));
        var widthUnit = parseFloat(self.parents('fieldset').data("width"));
        var widthSize = ($("#size-type").select2("val") == 2 || $("#size-type").select2("val") == 3);//横向是否允许超出边界
        var heightSize = ($("#size-type").select2("val") == 2 || $("#size-type").select2("val") == 4);//纵向是否允许超出边界

        if (!isNaN(heightInput)) {
            var count_height = parseFloat(heightInput * lengthType) / heightUnit;
            var height = heightSize ? Math.ceil(count_height) : Math.floor(count_height);
            self.parents('fieldset').find("input[tag='qty-height']").val(height);
        }
        if (!isNaN(widthInput)) {
            var count_width = parseFloat(widthInput * lengthType) / widthUnit;
            var width = widthSize ? Math.ceil(count_width) : Math.floor(count_width);
            self.parents('fieldset').find("input[tag='qty-width']").val(width);
        }

        if (!isNaN(heightInput) && !isNaN(widthInput)) {
            reCalcSeq(panel, self);
        }
    }

    /**
     * 计算拼屏总面积
     **/
    function reCalcSplitTotalArea(panel, panelTag) {
        var total = 0;
        $(panel).find('fieldset').each(function (index, item) {
            var width = parseFloat($(item).find("input[tag='seq-width']").val()) || 0;
            var height = parseFloat($(item).find("input[tag='seq-height']").val()) || 0;
            total += width*height;
        });
        var quantity = parseFloat(panel.find("input[tag='quantity']").val()) || 0;

        var totalArea = toDecimal(total*quantity) + ($("[name='radio-inline']:radio:checked").val() == 1 ? "㎡" : "sq.ft");
        $(panel).find('[role="totalArea"]').html(totalArea);

        var html = '<input type="text" readonly class="acreage" amount value="'+ totalArea +'"/>';
        //标配中屏体面积框赋值

        $("#spare-" + panelTag + "-1").find('[tag="total-area"]').html(html);

        var fieldset = $("#spare-" + panelTag + "-1").find('[tag="total-area"]').parents('fieldset');
        //重新计算配件中屏体部分价格
        calcSingleSparePriceNoTop(fieldset);
        //计算屏体总价
        calcPanelTotalPrice(panelTag);
        //计算所有数量和价格
        reCalcEveryCountsAndPrice(panelTag);
    }

    //添加备件
    function addNewSpare(type) {
        var spare_id = $("#panel-standard-" + type).find("li.active a:first").attr("href");
        var tag = spare_id.split("-")[1];
        var panel = $("#panel-" + tag);
        var productIds = [];
        $(panel).find("[name='panel']").each(function (index, item) {
            productIds.push($(item).select2('val'));
        });
        var product = $(panel).find("[name='panel']").select2("val");
        if (!product) {
            alert(message["quote.empty.product"], 2);
            return;
        }
        showLoading();
        $.ajax({
            url: "/offer/spares",
            data: {
                productIds: productIds.join(","),
                type: type,
                moneyType: $("#money-type").select2('val'),
                area: $("#price_area").select2("val"),
                series:  $("#panel-" + tag).find('[name=product]').select2("val")
            },
            type: "get",
            success: function (data) {
                $("#choose_spare_dialog").html(data);
                $("#dialog_table").modal("show");
                $("#spare-type").val(spare_id);
                $("#spare-type").data("panel", panel.attr("id"));
                FittingUtil.byTypeChecked(type, $("#choose_spare_dialog"));
                hideLoading();
            }
        });
    }

    //删除拼屏函数
    function deleteSplit(self) {
        //获取当前fieldset下标
        var index = $(self).parents('fieldset').index();

        var panel = $(self).parents('.in.active');

        var panelTag = panel.attr("id").split("-")[1];

        panel.find('.pline.panel-index').eq(index).closest('.fnt').remove();
        $(self).parents('fieldset').remove();
        //判断是否只有一个屏了，如果就显示客户要求宽高
        if (panel.find('fieldset').length == 1) {
            panel.find('fieldset').eq(0).find(".cust_size").show();
        }
        //重新计算屏体总面积和价格
        reCalcSplitTotalArea(panel, panelTag);
        //重新计算配件和价格
        reCalcEveryCountsAndPrice(panelTag);
    }

    //添加自定义备件
    function addNewSelfSpare(type) {
        var form = $("#panel-standard-" + type).find(".tab-pane.active form.self");
        var template = $("#self-spare-template").html();
        var newItem = $(template).clone(true);
        newItem.find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 1000000,
            change: function (e) {
                var fieldSet = $(e.target).parents("fieldset:first");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcSingleSparePrice(fieldSet);
                } else {
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
        if (!!$(form) && $(form).length > 0) {
            $(form).append(newItem);
        } else {
            $("#panel-standard-" + type).find(".tab-pane.active .spare").append($("<form class='smart-form self'></form>"));
            $("#panel-standard-" + type).find(".tab-pane.active form.self").append(newItem);
        }
    }

    //添加服务
    function appendService() {
        var template = $("#service-template").html();
        var newItem = $(template).clone(true);

        newItem.find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function (e) {
                var fieldSet = $(e.target).parents("fieldset");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcSingleSparePrice(fieldSet);
                } else {
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });

        $.ajax({
            url: '/api/dict/find/category',
            data: {category: 'offer_service'},
            type: "GET",
            success: function (res) {
                //服务
                newItem.find('.service-list').autosuggest({
                    dataList: res,
                    local: true,
                    minLength: 1
                });
            }
        });

        // newItem.find(".select2").select2();

        $("#service-form").append(newItem);
    }

    //添加包装
    function appendPackage() {
        var packages = [
            {id: 1, value: message["package.type.paper"]},
            {id: 2, value: message["package.type.wold"]},
            {id: 3, value: message["package.type.plane"]}
        ];

        var template = $("#package-template").html();
        var newItem = $(template).clone(true);

        newItem.find("input[amount],input[sales]").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function (e) {
                var fieldSet = $(e.target).parents("fieldset");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcTransport(fieldSet);
                } else {
                    $(this).val(0);
                    calcTransport(fieldSet);
                }
            }
        });

        newItem.find('.packages').autosuggest({
            dataList: packages,
            local: true,
            minLength: 1
        });

        $("#package-form").append(newItem)
    }

    //初始化所有备件区域spinner控件
    function initPanelSpare(panel) {
        var id = panel.attr("id").replace("panel-", "");

        $("#spare-" + id + "-1,#spare-" + id + "-2,#spare-" + id + "-3").find("input[amount],input[sales]").not(".acreage").spinner({
            step: 1,
            min: 1,
            max: 100000,
            change: function () {
                var fieldSet = $(this).parents("fieldset");
                if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                    calcSingleSparePrice(fieldSet);
                } else {
                    $(this).val(0);
                    calcSingleSparePrice(fieldSet);
                }
            }
        });
    }

    /**
     * 计算某一个备件的销售价格，并且重新计算当前的组件集合的全部金额
     */
    function calcSingleSparePrice(fieldSet) {
        calcSingleSparePriceNoTop(fieldSet);
        var widget = $(fieldSet).parents(".tab-pane.fade.active");
        calcKindSparePrice(widget);
    }

    /**
     *  重新计算所有备件数量、价格
     */
    function reCalcEveryCountsAndPrice(panelTag) {
        var widgets = $("#spare-" + panelTag + "-1,#spare-" + panelTag + "-2,#spare-" + panelTag + "-3");
        var guarantee = $("[name='guarantee']").select2("val");
        $.each(widgets, function (index, item) {
            //寻找fieldset，排除screen
            var fieldSets = $(item).find("form.smart-form.temp").find('fieldset').not("#screen-fieldset-" + panelTag);
            $.each(fieldSets, function (index, fied) {
                var input = $(fied).find("input[amount]:first");

                var count = 0;
                //通过某个配件的一个或者多个公式计算数量
                var dataList = $(fied).data('list') || [];
                $.each(dataList, function (index, child) {
                    count += calcCounts(child.product, child["type" + guarantee],
                            child["count" + guarantee], child["spel" + guarantee], "panel-" + panelTag);
                });

                if (0 != count) {
                    $(input).val(count);
                }
                var price = $(fied).find("input[sales]").val() || 0;
                $(fied).find("[price]").html((price * count).toFixed(2));
            });
            calcKindSparePriceNoTop(item);
        });
        calcPanelTotalPrice(panelTag);
        calcTotalPrice();
    }

    function calcKindSparePrice(widget) {
        calcKindSparePriceNoTop(widget);
        var tag = widget.attr('id').split("-")[1];
        calcPanelTotalPrice(tag);
        calcTotalPrice();
    }

    /**
     * 计算某个备件的销售价格，不重新计算备件集合价格
     */
    function calcSingleSparePriceNoTop(fieldSet) {
        var price = parseFloat($(fieldSet).find("input[sales]").val()) || 0;
        var amount = parseFloat($(fieldSet).find("input[amount]").val()) || 0;
        $(fieldSet).find("span[price]").html(parseFloat(price * amount).toFixed(2));
    }

    /**
     * 计算所有屏体的某一个种类的备件总价，不重新计算总体价格
     */
    function calcKindSparePriceNoTop(widget) {
        var fieldset = $(widget).find("fieldset");
        var totals = 0;
        $.each(fieldset, function (index, item) {
            var amount = parseFloat($(item).find("input[amount]").val()) || 0;
            var sales = parseFloat($(item).find("input[sales]").val()) || 0;
            var total = amount * sales;
            totals += total;
        });

        $(widget).find("span[tag='total-sale']").html(totals.toFixed(2));
        var discount = parseFloat($(widget).find("input[tag='total-discount']").val()) || 100;
        $(widget).find("[tag='total-real']").html((totals * discount / 100).toFixed(2));
    }

    /**
     * 计算单个屏体价格，不计算整个单子的总价
     */
    function calcPanelTotalPrice(tag) {
        var totals = 0;
        $.each($("#spare-"+ tag + "-1 [tag='total-real'],#spare-" + tag + "-2 [tag='total-real'],#spare-" + tag + "-3 [tag='total-real']"), function (index, item) {
            var value = parseFloat($(item).html()) || 0;
            totals += value;
        });
        $("#price-panel-" + tag).html(totals.toFixed(2));
    }

    /**
     * 计算整个单子的总价格
     */
    function calcTotalPrice() {
        var totals = 0;
        $.each($("[tag='total-real']"), function (index, item) {
            var value = parseFloat($(item).html()) || 0;
            totals += value;
        });
        var discount = $("#total-discount").val() || 100;
        $("#total-price").html(totals.toFixed(2));
        $("#total-real").html(parseFloat(discount * totals / 100).toFixed(2));
    }

    /**
     * 保留四位小数的函数
     * @param x 数值
     */
    function toDecimal(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return 0;
        }
        f = Math.round(x*10000)/10000;
        return f;
    }

    /**
     * 保留两位小数的函数
     * @param x 数值
     */
    function toDouble(x) {
        var f = parseFloat(x);
        if (isNaN(f)) {
            return 0;
        }
        f = Math.round(x*100)/100;
        return f;
    }

    /**
     * 计算屏体对应的宽度、高度、面积、价格、功率
     * @param panel
     * @param self 变动的该dom
     */
    function reCalcSeq(panel, self) {
        var height = parseInt(self.parents('fieldset').find("input[tag='qty-height']").val()) || 0;
        var width = parseInt(self.parents('fieldset').find("input[tag='qty-width']").val()) || 0;
        var total = height * width;

        //总数量赋值
        self.parents('fieldset').find("span[role='total']:first").html(total + "pcs");

        var seqHeight = height * self.parents('fieldset').data("height") || 0;
        var seqWidth = width * self.parents('fieldset').data("width") || 0;

        //获取屏体数量
        self.parents('fieldset').find("input[tag='seq-width']").val(toDecimal(seqWidth / lengthType));
        self.parents('fieldset').find("input[tag='seq-height']").val(toDecimal(seqHeight / lengthType));

        self.parents('fieldset').find("span[role='total']:last").html(toDecimal(seqHeight * seqWidth / squareType));
        self.parents('fieldset').find("span[role='total']:last").append($("[name='radio-inline']:radio:checked").val() == 1 ? "㎡" : "sq.ft");
        var panelTag = $(panel).attr("id").replace("panel-", "");

        var totalWidth = 0;
        var totalHeight = 0;
        var totalArea = 0;

        //得出总横向数量
        panel.find('input[tag="qty-width"]').each(function (index, item) {
            if ($(item).val()) {
                totalWidth += $(item).val();
            }
        });

        //得出总纵向数量
        panel.find('input[tag="qty-height"]').each(function (index, item) {
            if ($(item).val()) {
                totalHeight += $(item).val();
            }
        });

        //得出总面积
        panel.find('fieldset').each(function (index, item) {
            var width = parseFloat($(item).find("input[tag='seq-width']").val()) || 0;
            var height = parseFloat($(item).find("input[tag='seq-height']").val()) || 0;
            totalArea += width*height;
        });

        //计算参数中部分属性
        $.each($("#param-" + panelTag + "-1 [data-ct1]"), function () {
            var ct1;
            var ct2;
            if (totalWidth == 0 || totalHeight == 0) {
                ct1 = $(this).data("ct1");
                ct2 = $(this).data("ct2");
            } else {
                ct1 = $(this).data("ct1") * totalWidth;
                ct2 = $(this).data("ct2") * totalHeight;
            }
            $(this).html(ct1 + "*" + ct2);
        });

        $.each($("#param-" + panelTag + "-1 [data-counts]"), function () {
            if (totalArea == 0) {
                $(this).html($(this).data("counts"));
            } else {
                $(this).html(parseInt($(this).data("counts") * totalArea));
            }
        });
        //重新计算当前拼屏总面积，备件部分屏体部分价格
        reCalcSplitTotalArea(panel, panelTag);
    }

    $('#package-panel .packageTrade').select2();

    /**
     * 通过宽度、高度计算屏体的数量
     * @param panel
     */
    function calcQty(panel, self) {
        var heightInput = self.parents('fieldset').find("input[tag='seq-height']:first").val();
        var widthInput = self.parents('fieldset').find("input[tag='seq-width']:first").val();
        var heightUnit = parseFloat(self.parents('fieldset').data("height"));
        var widthUnit = parseFloat(self.parents('fieldset').data("width"));
        var widthSize = ($("#size-type").select2("val") == 2 || $("#size-type").select2("val") == 3);//横向是否允许超出边界
        var heightSize = ($("#size-type").select2("val") == 2 || $("#size-type").select2("val") == 4);//纵向是否允许超出边界

        if (!isNaN(heightInput)) {
            var count_height = parseFloat(heightInput * lengthType) / heightUnit;
            var height = heightSize ? Math.ceil(count_height) : Math.floor(count_height);
            self.parents('fieldset').find("input[tag='qty-height']").val(height);
        }
        if (!isNaN(widthInput)) {
            var count_width = parseFloat(widthInput * lengthType) / widthUnit;
            var width = widthSize ? Math.ceil(count_width) : Math.floor(count_width);
            self.parents('fieldset').find("input[tag='qty-width']").val(width);
        }

        if (!isNaN(heightInput) && !isNaN(widthInput)) {
            reCalcSeq(panel, self);
        }
    }

    $("#panel-list-header").on("click", "ul>li>a:not(.active)", function () {
        var tag = $(this).attr("href").replace("panel-", "");

        $("#panel-list-content .tab-pane.active").removeClass("active");
        $("#panel-" + tag).addClass("active");

        $("#panel-param-1 li.active").removeClass("active");
        $("a[href='param-" + tag + "-1']").parent().addClass("active");

        $("#panel-params-dialog-1 .tab-pane.active").removeClass("active");
        $("#param-" + tag + "-1").addClass("active");

        $("#panel-standard-1 li.active,#panel-standard-2 li.active,#panel-standard-3 li.active").removeClass("active");
        $("a[href='spare-" + tag + "-1'],a[href='spare-" + tag + "-2'],a[href='spare-" + tag + "-3']").parent().addClass("active");

        $("#panel-standard-dialog-1 .tab-pane.active,#panel-standard-dialog-2 .tab-pane.active,#panel-standard-dialog-3 .tab-pane.active").removeClass("active");
        $("#spare-" + tag + "-1,#spare-" + tag + "-2,#spare-" + tag + "-3").addClass("active");
    });

    $("#panel-param-1,#panel-standard-1,#panel-standard-2,#panel-standard-3").on("click", "li:not(.active)", function () {
        var href = $(this).find("a").attr("href");
        $(this).parents(".jarviswidget").find(".tab-pane.active").removeClass("active");
        $("#" + href).addClass("active");
    });

    $("#spare-choose").off("click").click(function (e) {
        FittingUtil.checkedSpare(e);
    });

    /**
     * 给备件列表添加一个备件信息
     * @param data 即将进行添加的数据
     * @param form 目标dom
     * @param panelId
     */
    function appendSpares(data, form, panelId, description) {
        var template = $("#standard-template").html();
        var newItem = $(template).clone(true);
        var oldItem = null;
        var isCommonSpare = false;
        var count = 0;
        //获取质保年限
        var guarantee = $("[name='guarantee']").select2("val");

        form.find('fieldset').each(function (index, item) {
            if (data.id == $(item).data('id')) {
                isCommonSpare = true;
                oldItem = $(item);
            }
        });
        if (!isCommonSpare) {
            var newfieldset = $(newItem).find('fieldset');
            var dataList = [];
            dataList.push(data);
            newfieldset.find("label:eq(0)").html(data.typeName);
            newfieldset.find("label:eq(3)").html(data.unit);
            newfieldset.find("label:eq(1)").html(data.brand);
            newfieldset.find(".desc-title").html(message['customer.item.description'] + ":");
            newfieldset.find(".desc").html(data.desc);
            newfieldset.find(".desc").attr('title', data.desc);

            newfieldset.data("id", data.id);
            newfieldset.data("cost", data.costPrice);
            newfieldset.data("sale", data.salePrice);
            newfieldset.data("list", dataList);

            newItem.find("input[amount],input[sales]").spinner({
                step: 1,
                min: 1,
                max: 1000000,
                change: function (e) {
                    var fieldSet = $(e.target).parents("fieldset:first");
                    if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                        calcSingleSparePrice(fieldSet);
                    } else {
                        $(this).val(0);
                        calcSingleSparePrice(fieldSet);
                    }
                }
            });


            //循环计算配件数量   PS：天才思路
            $.each(newfieldset.data("list"), function (index, item) {
                var type = item["type" + guarantee];
                var ct = item["count" + guarantee];
                var spel = item["spel" + guarantee];
                count += calcCounts(item.product, type, ct, spel, panelId);
            });
            newItem.find("input[sales]").val(data.salePrice);
            newItem.find("label:first").attr("title", description);
            var input = newItem.find("input[amount]:first");
            if (0 != count) {
                input.val(count);
            }

            $(form).append(newItem);
            calcSingleSparePrice(newItem);
        }else {

            oldItem.data("list").push(data);

            //循环计算配件数量   PS：天才思路
            $.each(oldItem.data("list"), function (index, item) {
                var type = item["type" + guarantee];
                var ct = item["count" + guarantee];
                var spel = item["spel" + guarantee];
                count += calcCounts(item.product, type, ct, spel, panelId);
            });

            oldItem.find("input[sales]").val(data.salePrice);
            oldItem.find("label:first").attr("title", description);
            var input = oldItem.find("input[amount]:first");
            if (0 != count) {
                input.val(count);
            }
            calcSingleSparePrice(oldItem);
        }
    }

    function calcCounts(product, type, ct, spel, panel) {
        if (type == 1) {
            return ct;
        }
        var result = 0;

        var w_b = 0;
        var h_b = 0;
        var w_p = 0;
        var h_p = 0;
        var w_m = 0;
        var h_m = 0;
        var w_r = 0;
        var h_r = 0;
        $("#" + panel).find('fieldset').each(function (index, item) {
            if ($(item).find('[name="panel"]').select2('val') == product) {
                w_b = parseInt($(item).find("input[tag='qty-width']").val()) || 0; //箱体横向数量
                h_b = parseInt($(item).find("input[tag='qty-height']").val()) || 0; //箱体纵向数量
                w_p = parseFloat($(item).find("input[tag='seq-width']").val()) * lengthType;//屏体横向长度
                h_p = parseFloat($(item).find("input[tag='seq-height']").val()) * lengthType;//屏体纵向长度
                w_m = parseInt($(item).data("boxwidth")) || 0;//每个箱体横向多少块模组
                h_m = parseInt($(item).data("boxheight")) || 0;//每个箱体纵向多少块模组
                w_r = parseFloat($(item).data("transversePix"))*w_b/10000 || 0; //屏横向总分辨率 这里除以10000是表示每万点多少
                h_r = parseFloat($(item).data("portraitPix"))*h_b || 0; //屏横向总分辨率
            }
        });

        if (type == 2) {
            result = w_p * h_p * ct; //单位面积
        } else if (type == 3) {
            result = w_b * h_b * ct; //每个箱体
        } else if (type == 4) {
            result = w_b * w_m * h_b * h_m * ct;//每个模组
        } else if (type == 5) {
            if (!spel) {
                return ct;
            }
            try {
                result = eval(spel);
            } catch (err) {
                return ct;
            }
        }

        return Math.ceil(result);
    }

    //所有备件右边删除按钮事件
    $(".jarviswidget").on("click", ".trash", function () {
        var widget = $(this).parents(".tab-pane.fade.active");
        $(this).parents("fieldset").parent().remove();
        calcKindSparePrice(widget);
    });
    //包装右边删除按钮事件
    $(".jarviswidget").on("click", ".trash-pack", function () {
        $(this).parents("fieldset").parent().remove();
        var fieldSet = $(this).find("fieldset");
        calcTransport(fieldSet);
    });
    //包装计算事件
    function calcTransport(fieldSet) {
        var price = parseFloat($(fieldSet).find("input[sales]").val()) || 0;
        var amount = parseFloat($(fieldSet).find("input[amount]").val()) || 0;
        $(fieldSet).find("span[price]").html(parseFloat(price * amount).toFixed(2));
        var field = $("#package-form fieldset");
        var total = 0;
        $.each(field, function () {
            var number = parseInt($(this).find("input[amount]").val());
            var value = parseFloat($(this).find("input[sales]").val());
            if (!!value) {
                total += number * value;
            }
        });
        total += (parseFloat($("#logistics-cost").val()) || 0);
        $("#package-panel").find("[tag='total-real']").html(total.toFixed(2));
        calcTotalPrice();
    }

    $(".row.total-tag input:not(.select2-focusser,.select2-input)").spinner({
        step: 1,
        min: 1,
        max: 100,
        change: function () {
            var div = $(this).parents(".row.total-tag:first");
            var sale = parseFloat($(div).find("[tag='total-sale']").html()) || 0;
            var discount;
            if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                discount = parseFloat($(this).val()) || 100;
                $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                calcTotalPrice();
            } else {
                $(this).val(100);
                discount = parseFloat($(this).val()) || 100;
                $(div).find("[tag='total-real']").html((sale * discount / 100).toFixed(2));
                calcTotalPrice();
            }
        }
    });

    $("#panel-list-header").on("click", ".drop-panel", function (event) {
        if ($(".drop-panel").length == 1) {
            return;
        }
        $('#del_modal').modal('show');
        $('#ok_del').data("event", event);
        $('#ok_del').data("$dropPanel", $(this));
    });
    $('#del_modal').on('click', '#ok_del', function () {
        $('#del_modal').modal('hide');
        removePanel($(this).data('event'), $(this).data('$dropPanel'))
    });

    //删除屏体事件
    function removePanel(event, $panel) {
        event.preventDefault();
        event.stopPropagation();
        if ($(".drop-panel").length == 1) {
            return;
        }
        var active = $panel.parents("li").hasClass("active");
        var panel = $panel.parent("a:first").attr("href");
        var tag = panel.replace("panel-", "");
        var panels = $("#" + panel);
        var panelLi = $("[href='" + panel + "']");
        var params = "#param-" + tag + "-1";
        var spares = "#spare-" + tag + "-1,#spare-" + tag + "-2,#spare-" + tag + "-3";
        var paramLi = "[href='param-" + tag + "-1']";
        var spareLi = "[href='spare-" + tag + "-1'],[href='spare-" + tag + "-2'],[href='spare-" + tag + "-3']";
        $(params).remove();
        $(spares).remove();
        $(panels).remove();
        $(paramLi).parents("li").remove();
        $(spareLi).parents("li").remove();
        $(panelLi).parents("li").remove();
        if (active) {
            $("#panel-list-header li:first>a").click();
        }

        calcPanelTotalPrice(tag);
        $.each($("#panel-standard-1,#panel-standard-2,#panel-standard-3"), function (index, item) {
            calcKindSparePriceNoTop(item);
        });
        calcTotalPrice();
        reBuildPanelIndex();
    }


    $("#total-discount").spinner({
        step: 1,
        min: 1,
        max: 100,
        change: function () {
            var discount;
            var real = parseFloat($("#total-price").html()) || 0;
            if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                discount = $(this).val() || 100;
                $("#total-real").html(parseFloat(discount * real / 100).toFixed(2));
            } else {
                $(this).val(100);
                discount = $(this).val() || 100;
                $("#total-real").html(parseFloat(discount * real / 100).toFixed(2));
            }
        }
    });

    $("#logistics-cost").spinner({
        step: 1,
        min: 0,
        max: 10000,
        change: function () {
            if ($(this).val() && isNum($(this).val()) && $(this).val() > 0) {
                calcTransport();
            }else {
                $(this).val(0);
                calcTransport();
            }
        }
    });

    /**
     * 提交按钮
     */
    $("#submit_button").off("click").click(function () {

        // showLoading();
        if (!$("#select_customer").val()) {
            alert(message["offer.create.step1.customer"], 2);
            hideLoading();
            return;
        }
        var valid = checkInput();
        if (!valid) {
            hideLoading();
            return false;
        }
        var confirmFlag = checkConfirm();
        if (confirmFlag) {
            hideLoading();
            return false;
        }
        //预览函数
        view();

    });

    $("#view_submit").off('click').click(function () {
        // 用来标记是否草稿 true-是 false-否
        $('#view_modal').modal('hide');
        showLoading();
        submitFun(false);
    });
    /**
     * 保存按钮
     */
    $("#save_button").off("click").click(function () {
        showLoading();
        if (!$("#select_customer").val()) {
            alert(message["offer.create.step1.customer"], 2);
            hideLoading();
            return;
        }
        var valid = checkInput();
        if (!valid) {
            hideLoading();
            return false;
        }
        var confirmFlag = checkConfirm();
        if (confirmFlag) {
            hideLoading();
            return false;
        }
//用来标记是否草稿 true-是 false-否
        submitFun(true);
    });

    /**
     * 最终优惠价调整
     */
    $("#edit_realprice").off('click').click(function () {
        if (!$('#special_price').val()) {
            $('#special_price').val($('#total-real').html());
        }
        if (!$('#special_price_remark').val()) {
            $('#special_price_remark').val(message['offer.create.specialRemark']);
        }
        $('#edit_totalPrice_modal').modal('show');
    });

    $('#special_price_remark').focus(function () {
       if ($(this).val() == message['offer.create.specialRemark']) {
           $(this).val('');
       }
    });

    $('#special_price_remark').blur(function () {
        if (!$(this).val()) {
            $('#special_price_remark').val(message['offer.create.specialRemark']);
        }
    });

    $('#comfirm_special').off('click').click(function () {
        $('span[special-unit]').html($('#money-type option:selected').data('key'));
        $('span[special]').html($('#special_price').val());
        $('#edit_totalPrice_modal').modal('hide');
    });

    /**
     * 贸易公司发货地址联动事件
     */
    $('#select_trader').change(function () {
       var thisCompany = $(this).select2('val');
        $('#shipping').val(thisCompany).trigger('change');
    });

    /**
     * 提交之前，检查是否需要给出提醒
     */
    function checkConfirm() {
        var confirmFlag = false;
        $.each($("[name='panel']"), function (index, item) {
            var tabPane = $(item).parents(".tab-pane");
            var basePrice = tabPane.find("input[tag='price']").data('base_price');
            var tagPrice = tabPane.find("input[tag='price']").val();
            if (Number(tagPrice) < Number(basePrice)) {
                var tab = $(item).parents(".tab-pane").attr("id");
                var li = $("a[href='" + tab + "']");
                $(li).click();
                confirmFlag = true;
                $('#lower_base_price_modal').modal('show');
                return false;
            }
        });
        return confirmFlag;
    }

    /**
     * 提交方法
     */
    function submitFun(draftFlag) {
        var offer = {
            customer: $("#select_customer").data("id"),
            customerName: $.trim($("#select_customer").val()),
            payment: $.trim($("#select_payment").val()),
            shipping: $("#shipping").select2('val'),
            guarantee: $("[name='guarantee']").select2("val"),
            waitingDate: $("#valid-date").val(),
            trader: $('#select_trader').val(),
            moneyUnit: $("#money-type").select2("val"),
            sizeUnit: $("[name='radio-inline']:radio:checked").val(),
            series: $("#select-series").select2("val"),
            remark: $.trim($("[name='remark']").val()),
            projectName: $.trim($("#project_name").val()),
            area: $("#price_area").select2("val"),
            sizeType: $("#size-type").select2("val"),
            totalPrice: $("#total-real").html(),
            totalDiscount: $("#total-discount").val(),
            servicePrice: $("#service-panel span[tag='total-real']").html(),
            serviceDiscount: $("#service-panel input[tag='total-discount']").val(),
            rate: $('#money-type option:selected').data('key'),
            offere: $(".sales-select").select2('val'),
            specialPrice: $('span[special]').html(),
            specialPriceRemark: $('#special_price_remark').val()
        };
        var transfer = {
            toAddress: "",//$("[name='to_address']").val(),
            cost: $("#logistics-cost").val() || 0,
            trade: $('#package-panel .packageTrade').select2('data').text,
            transport: "",//$("[name='transport']").val(),
            size: ""//$("[name='size']").val()
        };

        var transport = [];
        $.each($("#package-form fieldset[role='package']"), function() {
            var trans = {
                packages: $.trim($(this).find("input[packages]").val()),
                number: $(this).find("input[amount]").val(),
                priceUnit: $(this).find("input[sales]").val(),
                price: $(this).find("span[price]").html()
            };
            transport.push(trans);
        });

        var serviceList = [];
        $.each($("#service-panel .smart-form fieldset[role='service']"), function(index, item) {
            var service = {
                name: $.trim($(item).find('.service-list').val()),
                counts: $(item).find("input[amount]").val(),
                price: $(item).find("input[sales]").val()
            };
            serviceList.push(service);
        });

        var panelList = [];
        var gua = $("select[name='guarantee']").select2("val");
        $.each($("#panel-list-header li"), function (index, item) {
            var panelId = $(item).find("a").attr("href");
            var panelDiv = $("#" + panelId);
            var tag = panelId.split("-")[1];
            var panelChildList = [];
            $(panelDiv).find('fieldset').each(function (index, item) {
                var panelDetail = {
                    panel: $(item).find("[name='panel']").select2("val"),
                    wcount: $(item).find("input[tag='qty-width']").val(),
                    lcount: $(item).find("input[tag='qty-height']").val(),
                    horizontal: $(item).find("input[tag='seq-width']").val(),
                    longitudinal: $(item).find("input[tag='seq-height']").val(),
                    suggPrice: $(item).data("sales"),
                    costPrice: $(item).data("price"),
                    series: $(panelDiv).find('[name="product"]').select2("val"),
                    description: $(item).find("[name='panel']").select2("data").text,
                    partNo: $(item).data('partNo'),
                    remark: $(item).find("[name='panel']").select2("data").text
                };
                panelChildList.push(panelDetail);
            });

            var panels = {
                orders: index + 1,
                totalPix: $(panelDiv).find('.totalPix').html(),
                powerAvg: $(panelDiv).find('.powerAvg').html(),
                powerMax: $(panelDiv).find('.powerMax').html(),
                custHorizontal: $(panelDiv).find('input[tag="cust-width"]').val(),
                custLongitudinal: $(panelDiv).find('input[tag="cust-height"]').val(),
                quantity: $(panelDiv).find("input[tag='quantity']").val(),
                price: $("#spare-" + tag + "-1").find("input[tag='reference']").val(),
                discount: $("#spare-" + tag + "-1").find("input[tag='total-discount']").val(),
                totalPrice: $("#price-panel-" + tag).html(),
                standardDiscount: $("#spare-" + tag + "-1").find("input[tag='total-discount']").val(),
                standardPrice: $("#spare-" + tag + "-1").find("span[tag='total-real']").html(),
                spareDiscount: $("#spare-" + tag + "-2").find("input[tag='total-discount']").val(),
                sparePrice: $("#spare-" + tag + "-2").find("span[tag='total-real']").html(),
                freePrice:$("#spare-" + tag + "-3").find("span[tag='total-sale']").html(),
                childPanels: panelChildList
            };

            var standDiv = $("#spare-" + panelId.replace("panel-", "") + "-1");
            var standardList = [];
            $.each($(standDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                standardList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfStandList = [];
            $.each($(standDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfStandList.push(buildSelf(fieldset));
            });

            var spareDiv = $("#spare-" + panelId.replace("panel-", "") + "-2");
            var spareList = [];
            $.each($(spareDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                spareList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfSpareList = [];
            $.each($(spareDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfSpareList.push(buildSelf(fieldset));
            });

            var freeDiv = $("#spare-" + panelId.replace("panel-", "") + "-3");
            var freeList = [];
            $.each($(freeDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                freeList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfFreeList = [];
            $.each($(freeDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfFreeList.push(buildSelf(fieldset));
            });

            panelList.push({
                panels: panels,
                standardList: standardList,
                spareList: spareList,
                freeList: freeList,
                selfStandList: selfStandList,
                selfSpareList: selfSpareList,
                selfFreeList: selfFreeList
            });
        });

        var offerVo = {
            offer: offer,
            // prices: prices,
            panelList: panelList,
            serviceList: serviceList,
            transfer: transfer,
            transport: transport
            // basic: basics
        };




        $.ajax({
            url: "/offer/submit",
            type: "post",
            data: {
                draftFlag: draftFlag,
                offer: JSON.stringify(offerVo)
            },
            success: function (data) {
                if (data) {
                    alert(message["http.response.success"]);
                    setTimeout(function () {
                        window.location.href = "#/offer/list"
                    }, 2000);
                } else {
                    alert(message["http.response.fail"], 3);
                }
                hideLoading();
            },
            error: function (data) {
                alert(message["http.response.error"], 3);
                hideLoading();
            }
        });
    }

    function reBuildPanelIndex() {
        var items = $("#panel-list-header li");
        $.each(items, function (index, item) {
            var tag = $(item).find("a").attr("href").replace("panel-", "");
            if (items.length > 1) {
                $("a[href*='" + tag + "']").find("span[role='index']").html(index + 1);
            } else {
                $("a[href*='" + tag + "']").find("span[role='index']").html("");
            }

        });
    }

    function buildSpare(fieldset, gua, panelId) {
        var item = {
            spare: $(fieldset).data("id"),
            spareName: $(fieldset).find("label:first").html(),
            brand: $(fieldset).find("label:eq(1)").html(),
            countGuid: calcCounts($(fieldset).data("type" + gua), $(fieldset).data("count" + gua), $(fieldset).data("spel" + gua), panelId),
            desc: $(fieldset).find(".desc").html(),
            subTotal: toDouble(parseFloat($(fieldset).find("input[amount]").val())*parseFloat($(fieldset).find("input[sales]").val())),
            countReal: $(fieldset).find("input[amount]").val(),
            priceCost: $(fieldset).data("cost"),
            priceSale: $(fieldset).find("input[sales]").val(),
            priceGuide: $(fieldset).data("sale"),
            unit: $(fieldset).find(".short-label").html(),
            formula: $(fieldset).data('list')
        };
        return item;
    }

    function buildSelf(fieldset) {
        var item = {
            spareType: $.trim($(fieldset).find("input:first").val()),
            brand: $.trim($(fieldset).find("input:eq(1)").val()),
            price: $(fieldset).find("input[sales]").val(),
            amount: $(fieldset).find("input[amount]").val(),
            unit: $(fieldset).find("input:eq(3)").val(),
            subTotal: toDouble(parseFloat($(fieldset).find("input[sales]").val())*parseFloat($(fieldset).find("input[amount]").val()))
        };
        return item;
    }

    function checkInput() {
        var valid = true;
        $.each($("[name='panel']"), function (index, item) {
            if (!$(item).select2("val")) {
                var tab = $(item).parents(".tab-pane").attr("id");
                var li = $("a[href='" + tab + "']");
                $(li).click();
                var name = $(li).find("span").html().replace("<span role=\"index\">", "").replace("</span>", "");
                alert(message["quote.empty.product.panel"].replace("_index_", name), 2);
                valid = false;
                return false;
            }
        });
        if (valid) {
            $.each($("[tag='total-discount'],#total-discount"), function (index, item) {
                if (!$(item).val()) {
                    alert(message["offer.create.empty.discount"], 2);
                    valid = false;
                    return false;
                }
            });
        }
        // if (valid) {
        //     $.each($(".panel-input .tab-pane input.ui-spinner-input:not([tag='total-discount'],#total-discount,select2-input),.panel-input .tab-pane .smart-form input:not(.ui-spinner-input)"), function (index, item) {
        //         if (!$(this).val()) {
        //             var id = $(this).parents(".tab-pane").attr("id");
        //             var li = $("a[href='" + id + "']");
        //             $(li).click();
        //             var name = $(li).find("span").html().replace("<span role=\"index\">", "").replace("</span>", "");
        //             console.log("offer.create.empty.input");
        //             alert(message["offer.create.empty.input"].replace("_index_", name), 2);
        //             valid = false;
        //             return false;
        //         }
        //     });
        // }
        if (valid) {
            var inputs = $("#package-panel input:not(.select2-focusser,.select2-input)");
            $.each(inputs, function () {
                if (!$(this).val()) {
                    alert(message["offer.create.empty.package"], 2);
                    valid = false;
                    return false;
                }
            });
        }
        if (valid) {
            var inputs = $("#service-form input:not(.select2-focusser,.select2-input)");
            $.each(inputs, function () {
                if (!$(this).val()) {
                    alert(message["offer.create.empty.service"], 2);
                    valid = false;
                    return false;
                }
            });
        }
        return valid;
    }

    function view() {
        //预览基本信息
        var basic = {
            salesLang: message['offer.create.sales'],
            sales: $(".sales-select").select2('data').text,
            customerLang: message['quotes.offer.list.customer'],
            customer: $("#select_customer").val(),
            areaLang: message['quotes.offer.list.priceArea'],
            area: $("#price_area").select2('data').text,
            moneyUnitLang: message['offer.create.monetaryUnit'],
            moneyUnit: $("#money-type").select2('data').text,
            guaranteeLang: message['quotes.offer.list.guarantee'],
            guarantee: $("#guarantee-years").select2('data').text,
            sizeTypeLang: message['offer.create.boundary'],
            sizeType: $("#size-type").select2('data').text,
            sizeUnitLang: message['offer.create.unit'],
            sizeUnit: $('[name="radio-inline"]').val() == 1 ? message['offer.create.meters']: message['offer.create.foot'],

            paymentLang: message['quotes.offer.list.payment'],
            payment: $("#select_payment").val(),
            companyLang: message['offer.create.company'],
            company: $('#select_trader').select2('data').text,
            addressLang: message['offer.create.address'],
            address: $("#shipping").select2('data').text,
            waitingDateLang: message['quotes.offer.list.waitingDate'],
            waitingDate: $("#valid-date").val(),
            projectNameLang: message['offer.create.project.name'],
            projectName: $("#project_name").val(),
            remarkLang: message['offer.create.remark'],
            remark: $('[name="remark"]').val(),
            priceUnit: $('#money-type option:selected').data('key'),
            unit: $('[name="radio-inline"]').val() == 1 ? 'm²' : 'sq.ft',
            totalPrice: $("#total-real").html(),
            totalbeforeDiscount: $("#total-price").html(),
            totalDiscount: $("#total-discount").val(),
            servicePrice: $("#service-panel span[tag='total-real']").html(),
            serviceTotal: $("#service-panel span[tag='total-sale']").html(),
            serviceDiscount: $("#service-panel input[tag='total-discount']").val(),
            tradeCost: $("#logistics-cost").val() || 0,
            tradeTerm: $('#package-panel .packageTrade').select2('data').text,
            transportTotal: $('#package-panel [tag="total-real"]').html(),
            specialPrice: $('span[special]').html(),
            specialPriceRemark: $('#special_price_remark').val(),

            productTypeLang: message['offer.create.type'],
            productLang: message['offer.create.product'],
            configLang: message['product.panel.list.configuration'],
            custWidthLang: message['offer.create.custWidth'],
            custHeightLang: message['offer.create.custHeight'],
            lateralLang: message['offer.create.lateral'],
            longitudinalLang: message['offer.create.longitudinal'],
            realWidthLang: message['offer.create.realWidth'],
            realHeightLang: message['offer.create.realHeight'],

            typeLang: message['product.panel.list.productType'],
            priceLang: message['offer.create.priceUnit'],
            countsLang: message['product.standard.list.num'],
            subTotalLang: message['offer.create.price.sum'],
            serviceLang: message['offer.create.service'],
            packageLang: message['offer.create.package'],
            brandLang: message['product.spare.list.brand'],
            descLang: message['customer.item.description'],
            boxLang: message['portal.dashboard.panel'],
            panelLang: message['offer.create.panel'],
            quantityLang: message['offer.create.screenQuantity'],
            totalAreaLang: message['offer.create.totalArea'],
            thisPanelPriceLang: message['offer.create.panelTotal'],
            standardLang: message['offer.create.standard'],
            freeLang: message['offer.create.free'],
            spareLang: message['offer.create.spare'],
            standardTotal: message['offer.create.standardTotal'],
            freeTotal: message['offer.create.freeTotal'],
            spareTotal: message['offer.create.spareTotal'],
            discount: message['offer.create.discount'],
            realPrice: message['offer.create.real'],
            serviceTotalLang: message['offer.create.total'],
            tradeTermLang: message['package.form.terms_of_trade'],
            transportLang: message['offer.create.transport'],
            totalPriceLang: message['offer.create.totalPrice'],
            specialPriceLang: message['offer.create.specialRemark'],
            specialPriceRemarkLang: message['offer.create.desc']
        };

        //服务信息
        var serviceList = [];
        $.each($("#service-panel .smart-form fieldset[role='service']"), function(index, item) {
            var service = {
                name: $(item).find('.service-list').val(),
                counts: $(item).find("input[amount]").val(),
                price: $(item).find("input[sales]").val()
            };
            serviceList.push(service);
        });

        //包装箱
        var packageList = [];
        $.each($("#package-form fieldset[role='package']"), function() {
            var trans = {
                packages: $(this).find("input[packages]").val(),
                number: $(this).find("input[amount]").val(),
                priceUnit: $(this).find("input[sales]").val(),
                price: $(this).find("span[price]").html()
            };
            packageList.push(trans);
        });

        var panelList = [];
        //获取年份值1,2,3,4
        var gua = $("select[name='guarantee']").select2("val");

        $.each($("#panel-list-header li"), function (index, item) {
            var panelId = $(item).find("a").attr("href");
            var panelDiv = $("#" + panelId);
            var tag = panelId.split("-")[1];
            var panelChildList = [];
            $(panelDiv).find('fieldset').each(function (index, item) {
                var panelDetail = {
                    panel: $(item).find("[name='panel']").select2("val"),
                    wcount: $(item).find("input[tag='qty-width']").val(),
                    lcount: $(item).find("input[tag='qty-height']").val(),
                    horizontal: $(item).find("input[tag='seq-width']").val(),
                    longitudinal: $(item).find("input[tag='seq-height']").val(),
                    realArea: toDecimal(parseFloat($(item).find("input[tag='seq-width']").val() || 0)*parseFloat($(item).find("input[tag='seq-height']").val() || 0)),
                    suggPrice: $(item).data("sales"),
                    costPrice: $(item).data("price"),
                    series: $(panelDiv).find('[name="product"]').select2("val"),
                    description: $(item).find("[name='panel']").select2("data").text,
                    partNo: $(item).data('partNo'),
                    remark: $(item).find("[name='panel']").select2("data").text,
                    line: $(panelDiv).find('[name="line"]').select2('data').text,
                    seriesText: $(panelDiv).find('[name="product"]').select2('data').text
                };
                panelChildList.push(panelDetail);
            });

            var panels = {
                orders: index + 1,
                totalPix: $(panelDiv).find('.totalPix').html(),
                powerAvg: $(panelDiv).find('.powerAvg').html(),
                powerMax: $(panelDiv).find('.powerMax').html(),
                totalArea: $(panelDiv).find('[role="totalArea"]').html(),
                custHorizontal: $(panelDiv).find('input[tag="cust-width"]').val(),
                custLongitudinal: $(panelDiv).find('input[tag="cust-height"]').val(),
                quantity: $(panelDiv).find("input[tag='quantity']").val(),
                price: $("#spare-" + tag + "-1").find("input[tag='reference']").val(),
                discount: $("#spare-" + tag + "-1").find("input[tag='total-discount']").val(),
                totalPrice: $("#price-panel-" + tag).html(),
                standardDiscount: $("#spare-" + tag + "-1").find("input[tag='total-discount']").val(),
                standardPrice: $("#spare-" + tag + "-1").find("span[tag='total-real']").html(),
                standardTotal: $("#spare-" + tag + "-1").find("span[tag='total-sale']").html(),
                spareDiscount: $("#spare-" + tag + "-2").find("input[tag='total-discount']").val(),
                sparePrice: $("#spare-" + tag + "-2").find("span[tag='total-real']").html(),
                spareTotal: $("#spare-" + tag + "-2").find("span[tag='total-sale']").html(),
                freePrice:$("#spare-" + tag + "-3").find("span[tag='total-sale']").html(),
                screenPrice: $("#screen-total-" + tag + ' [price]').html(),
                seriesText: $(panelDiv).find('[name="product"]').select2('data').text,
                childPanels: panelChildList
            };

            var standDiv = $("#spare-" + panelId.replace("panel-", "") + "-1");
            var standardList = [];
            $.each($(standDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                standardList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfStandList = [];
            $.each($(standDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfStandList.push(buildSelf(fieldset));
            });

            var spareDiv = $("#spare-" + panelId.replace("panel-", "") + "-2");
            var spareList = [];
            $.each($(spareDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                spareList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfSpareList = [];
            $.each($(spareDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfSpareList.push(buildSelf(fieldset));
            });

            var freeDiv = $("#spare-" + panelId.replace("panel-", "") + "-3");
            var freeList = [];
            $.each($(freeDiv).find("form.smart-form.temp fieldset[role]"), function (ind, fieldset) {
                freeList.push(buildSpare(fieldset, gua, panelId));
            });
            var selfFreeList = [];
            $.each($(freeDiv).find("form.smart-form.self fieldset[role]"), function (ind, fieldset) {
                selfFreeList.push(buildSelf(fieldset));
            });

            panelList.push({
                panels: panels,
                standardList: standardList,
                spareList: spareList,
                freeList: freeList,
                selfStandList: selfStandList,
                selfSpareList: selfSpareList,
                selfFreeList: selfFreeList
            });
        });

        var data = {
            basic: basic,
            panelList: panelList,
            serviceList: serviceList,
            packageList: packageList
        };

        $('#view-content').html($('#view-template').tmpl(data));
        $('#view_modal').modal('show');
    }

</script>
<script type="text/javascript" src="../../static/js/choose-spare.js"></script>
<link rel="stylesheet" href="../../static/js/plugin/bootstrap-autosuggest/autosuggest.css"/>
<link rel="stylesheet" href="../../static/css/offer.css"/>
</#compress>