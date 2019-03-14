<#compress>
<div class="stepshow" xmlns="http://www.w3.org/1999/html">
    <span id="stepshow-setting">
        <i class="fa fa-flag"></i>
    </span>
    <form style="display: none" class="product-info-look">
        <section>
            <article class="panel-input col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable" style="width: 370px">
                <div class="jarviswidget">
                    <header>
                        <ul class="nav nav-tabs pull-left">
                            <li class="active">
                                <a data-toggle="tab">
                                    <span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.productInfo"></dic><span role="index"></span></span>
                                </a>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox"></div>
                        <div class="widget-body no-padding">
                            <div class="tab-content padding-10">
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.product"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="productName"></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.certification"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="cert" id="stepshow_cert"></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="product.panel.list.configuration"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="config"></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="product.panel.list.status"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="state"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
            <article class="panel-input col-xs-12 col-sm-4 col-md-4 col-lg-4 sortable-grid ui-sortable" style="width: 370px">
                <div class="jarviswidget">
                    <header>
                        <ul class="nav nav-tabs pull-left">
                            <li class="active">
                                <a data-toggle="tab" href="#">
                                    <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.list.configurationInfo"></dic><span></span></span>
                                </a>
                            </li>
                        </ul>
                    </header>
                    <div>
                        <div class="jarviswidget-editbox"></div>
                        <div class="widget-body no-padding">
                            <div class="tab-content padding-10">
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.customer"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="customer"></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.priceArea"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="area"></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.monetaryUnit"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="moneyType"></span>
                                    </label>
                                </div>

                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.sizeUnit"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="lengthType"></span>
                                    </label>
                                </div>
                                <div class="row pading-seven">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.guarantee"></dic>：</label>
                                    <label class="col col-md-8">
                                        <span role="autogen"></span>
                                    </label>
                                </div>
                                <div class="row">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.payment"></dic>：</label>
                                    <label class="col col-md-8">
                                        <input role="payment" style="width: 214px;height: 32px;border: 1px solid #c9ccd0;padding-left: 8px;" />
                                    </label>
                                </div>
                                <div class="row">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.boundary"></dic>：</label>
                                    <label class="col col-md-8">
                                        <select style="width: 214px" class="select2" role="sizeType"></select>
                                    </label>
                                </div>
                                <div class="row">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="offer.create.company"></dic>：</label>
                                    <label class="col col-md-8">
                                        <select class="select2" style="width: 214px" role="trader"></select>
                                    </label>
                                </div>
                                <div class="row">
                                    <label class="col col-md-4 text-align-right"><dic data-dic="quotes.offer.list.waitingDate"></dic>：</label>
                                    <label class="col col-md-8">
                                        <input id="validDate" style="width: 124px;height: 34px;" />
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </article>
        </section>
    </form>
</div>

<!-- 屏体信息 -->
<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
    <div class="jarviswidget" id="panel-list-header">
        <header>
            <ul class="nav nav-tabs pull-left" >
                <li class="active">
                    <a data-toggle="tab" href="panel-${tag}">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.panel"></dic><span role="index"></span></span>
                        <div class="drop-panel"><i class="fa fa-trash-o"></i></div>
                    </a>
                </li>
            </ul>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="appendNewPanel()"><dic data-dic="header.list.button.add"></dic></a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" role="panel" id="panel-list-content">
                    <div class="tab-pane fade in active" id="panel-${tag}">
                        <div class="row">
                            <label class="col col-md-1 text-align-right padding7"><dic data-dic="product.panel.list.partNo"></dic></label>
                            <label class="col col-md-4">
                                <select name="panel" class="select2 product-select" style="width: 100%">
                                    <option id=""></option>
                                    <#list products as item>
                                        <option data-partno="${(item.partNo)!}"
                                                data-state="${(item.state)!}"
                                                data-certification="${(item.certification)!}"
                                                data-width="0"
                                                data-height="0"
                                                value="${(item.id?c)!}">${(item.partNo)!}<#if item.executionTime??>
                                            [V${(item.executionTime?string("yyyy-MM-dd"))!}]</#if> - [ ${(item.sizeWidth?c)!}*${(item.sizeHeight?c)!} ]<#if item.certification??> - [${(item.certification)!}]</#if>
                                        </option>
                                    </#list>
                                </select>
                            </label>
                            <label class="col col-md-7 padding7">
                                <dic data-dic="product.panel.list.state"></dic>: <span class="panel_state"><dic data-dic="product.panel.no">无</dic></span></label>
                            <#--<label class="col col-md-4 padding7"></label>-->
                        </div>
                        <div class="row">
                            <label class="col col-md-1 padding7 text-align-right"><dic data-dic="offer.create.panelNumber"></dic>:</label>
                            <label class="col col-md-2">
                                <input type="text" tag="qty-width" value="0" placeholder="宽"/>
                            </label>
                            <label class="col col-md-2">
                                <input type="text" tag="qty-height" value="0" placeholder="高"/>
                            </label>
                            <label class="col col-md-2 padding7"><dic data-dic="offer.create.totalCount"></dic>:<span role="total">0</span></label>
                            <label class="col col-md-1 padding7 text-align-right"><dic data-dic="offer.create.price.panel"></dic>:</label>
                            <label class="col col-md-2">
                                <input tag="price" value="0" placeholder="单价" class="form-control" disabled/>
                            </label>
                            <label class="col col-md-2 padding7 text-align-right padding-left">
                                <form class="smart-form">
                                    <label class="toggle">
                                        <input type="checkbox" name="use-price">
                                        <i data-swchon-text="ON" data-swchoff-text="OFF" style="right: auto;top: 0;"></i>
                                    </label>
                                </form>
                                <dic data-dic="offer.create.priceCount"></dic>:${moneySymbol}<span role="price-total">0</span></label>
                            </div>
                        <div class="row">
                            <label class="col col-md-1 padding7 text-align-right"><dic data-dic="offer.create.panelArea"></dic>:</label>
                            <label class="col col-md-2">
                                <input type="text" tag="seq-width" value="0" placeholder="宽"/>
                            </label>
                            <label class="col col-md-2">
                                <input type="text" tag="seq-height" value="0" placeholder="高"/>
                            </label>
                            <label class="col col-md-2 padding7"><dic data-dic="offer.create.totalArea"></dic>:<span role="total">0</span><#if length=="2">sq.ft<#else>㎡</#if></label>
                            <label class="col col-md-1 padding7 text-align-right"><dic data-dic="offer.create.priceLevel"></dic>:</label>
                            <label class="col col-md-2">
                                <input type="text" tag="discount" value="100" placeholder="discount"/>
                            </label>
                        </div>
                        <div class="row">
                            <label class="col col-md-1 padding7 text-align-right">
                                <dic data-dic="offer.create.screenQuantity"></dic>:
                            </label>
                            <label class="col col-md-2">
                                <input type="text" tag="quantity" value="1"/>
                            </label>
                            <label class="col col-md-2" style="display: none">
                                <button id="dialog_test">TEST</button>
                            </label>
                            <label class="col col-md-4 padding7 text-align-right"></label>
                            <label class="col col-md-1 padding7 text-align-right"><dic data-dic="offer.create.reference"></dic>:</label>
                            <label class="col col-md-2">
                                <input type="text" tag="reference" value="0"/>
                            </label>
                            <label class="col col-md-2 padding7 text-align-right"><dic data-dic="offer.create.realPrice"></dic>:${moneySymbol}<span role="price-real">0</span></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row total-tag">
            <label class="col col-md-5">&nbsp;</label>
            <label class="col col-md-2"><dic data-dic="offer.create.totalPrice"></dic>：${moneySymbol}<span tag="total-sale">0.00</span></label>
            <label class="col col-md-1 text-align-right text-nowrap"><dic data-dic="offer.create.discount"></dic>：</label>
            <label class="col col-md-2 no-left" style="margin-top: -7px;"><input type="text" tag="total-discount" value="100"/></label>
            <label class="col col-md-2"><dic data-dic="offer.create.real"></dic>：${moneySymbol}<span tag="total-real">0.00</span></label>
        </div>
    </div>
</article>

<#--<!-- 模组 &ndash;&gt;-->
<#--<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">-->
    <#--<div class="jarviswidget" id="panel-module-1">-->
        <#--<header>-->
            <#--<ul class="nav nav-tabs pull-left">-->
                <#--<li class="active">-->
                    <#--<a data-toggle="tab" href="module-${tag}-1">-->
                        <#--<span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.modual"></dic><span role="index"></span></span>-->
                    <#--</a>-->
                <#--</li>-->
            <#--</ul>-->
        <#--</header>-->
        <#--<div>-->
            <#--<div class="jarviswidget-editbox"></div>-->
            <#--<div class="widget-body no-padding">-->
                <#--<div class="tab-content padding-10" id="panel-module-dialog-1">-->

                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</article>-->

<#--<!-- 箱体 &ndash;&gt;-->
<#--<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">-->
    <#--<div class="jarviswidget" id="panel-box-1">-->
        <#--<header>-->
            <#--<ul class="nav nav-tabs pull-left">-->
                <#--<li class="active">-->
                    <#--<a data-toggle="tab" href="box-${tag}-1">-->
                        <#--<span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.box"></dic><span role="index"></span></span>-->
                    <#--</a>-->
                <#--</li>-->
            <#--</ul>-->
        <#--</header>-->
        <#--<div>-->
            <#--<div class="jarviswidget-editbox"></div>-->
            <#--<div class="widget-body no-padding">-->
                <#--<div class="tab-content padding-10" id="panel-box-dialog-1">-->

                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</article>-->

<!-- 参数 -->
<article class="panel-input col-xs-12 col-sm-12 col-md-12 col-lg-12 sortable-grid ui-sortable">
    <div class="jarviswidget" id="panel-param-1">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="param-${tag}-1">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="offer.create.params"></dic><span role="index"></span></span>
                    </a>
                </li>
            </ul>
        </header>
        <div>
            <div class="jarviswidget-editbox"></div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-params-dialog-1">

                </div>
            </div>
        </div>
    </div>
</article>

<!-- 标配、免费、包装 -->
<article class="panel-input col-xs-12 col-sm-6 col-md-6 col-lg-6 sortable-grid ui-sortable">
    <div class="jarviswidget" id="panel-standard-1">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="spare-${tag}-1">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.standard"></dic><span role="index"></span></span>
                    </a>
                </li>
            </ul>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSelfSpare(1)"><dic data-dic="offer.create.custom"></dic></a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(1)"><dic data-dic="header.list.button.add"></dic></a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-1">
                    <form class="smart-form temp">
                        <fieldset>
                            <label class="col col-3 text-align-left"><dic data-dic="product.standard.list.styType"></dic></label>
                            <label class="col col-3 text-align-left"><dic data-dic="product.spare.list.brand"></dic></label>
                            <label class="col col-2"><dic data-dic="product.standard.list.num"></dic></label>
                            <label class="col col-2"><dic data-dic="offer.create.priceUnit"></label>
                            <label class="col col-2 text-align-center"><dic data-dic="offer.create.price.sum"></dic></label>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row total-tag">
            <label class="col col-md-3"><dic data-dic="offer.create.totalPrice"></dic>：${moneySymbol}<span tag="total-sale">0.00</span></label>
            <label class="col col-md-2 text-align-right no-left no-right"><dic data-dic="offer.create.discount"></dic>：</label>
            <label class="col col-md-3 no-left" style="margin-top: -7px;"><input type="text" tag="total-discount" value="100"></label>
            <label class="col col-md-4"><dic data-dic="offer.create.realPrice"></dic>：${moneySymbol}<span tag="total-real">0.00</span></label>
        </div>
    </div>
    <div class="jarviswidget" id="panel-standard-3">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="spare-${tag}-3">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.free"></dic><span role="index"></span></span>
                    </a>
                </li>
            </ul>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSelfSpare(3)"><dic data-dic="offer.create.custom"></dic></a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(3)"><dic data-dic="header.list.button.add"></dic></a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-3">
                    <form class="smart-form temp">
                        <fieldset>
                            <label class="col col-3 text-align-left"><dic data-dic="product.standard.list.styType"></dic></label>
                            <label class="col col-3 text-align-left"><dic data-dic="product.spare.list.brand"></dic></label>
                            <label class="col col-2"><dic data-dic="product.standard.list.num"></dic></label>
                            <label class="col col-2"><dic data-dic="offer.create.priceUnit"></label>
                            <label class="col col-2 text-align-center"><dic data-dic="offer.create.price.sum"></dic></label>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row total-tag">
            <label class="col col-md-5">&nbsp;</label>
            <label class="col col-md-3 text-nowrap"><dic data-dic="offer.create.totalPrice"></dic>：${moneySymbol}<span tag="total-sale">0.00</span></label>
            <label class="col col-md-4" style="margin-bottom: 12px;"><dic data-dic="offer.create.real"></dic>：${moneySymbol}<span>0.00</span></label>
        </div>
    </div>
    <div class="jarviswidget jarviswidget-sortable" id="package-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-cube"></i> </span>
            <h2><strong><i><dic data-dic="offer.create.package"></dic></i></strong></h2>
            <a class="btn btn-primary btn-xs btn-service" onclick="appendPackage()">
                <i class="fa fa-plus"></i> <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div id="package_part">
            <div class="widget-body no-padding">
                <form class="smart-form" id="package-form" style="word-break: keep-all">
                    <fieldset>
                        <label class="col col-md-4 text-align-left"><dic data-dic="offer.create.package"></label>
                        <label class="col col-md-3 text-align-left"><dic data-dic="product.standard.list.num"></label>
                        <label class="col col-md-2 text-align-left"><dic data-dic="offer.create.priceUnit"></label>
                        <label class="col col-md-2 text-align-center"><dic data-dic="offer.create.price.sum"></label>
                    </fieldset>
                </form>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -3px;" class="row">
            <label class="col col-md-3 text-align-right">
                <dic data-dic="package.form.terms_of_trade"></dic>:
            </label>
            <label class="col col-md-3" style="margin-top: -7px;">
                <select class="packageTrade" style="width:100%">
                    <#list (tradeDict)! as item>
                        <option value="${item.code}">${item.name}</option>
                    </#list>
                </select>
            </label>
            <label class="col col-md-2 text-align-right">
                <dic data-dic="offer.create.transport"></dic>:
            </label>
            <label class="col col-md-3 input" style="margin-top: -7px">
                <input type="text" id="logistics-cost" value="0"/>
            </label>
        </div>
        <div class="row total-tag" style="margin-top: -2px;">
            <label class="col col-md-11 text-align-right" style="margin-bottom: 12px">
                <dic data-dic="offer.create.real"></dic>：${moneySymbol}<span tag="total-real">0.00</span>
            </label>
        </div>
    </div>
</article>

<article class="panel-input col-xs-12 col-sm-6 col-md-6 col-lg-6 sortable-grid ui-sortable">
    <div class="jarviswidget" id="panel-standard-2">
        <header>
            <ul class="nav nav-tabs pull-left">
                <li class="active">
                    <a data-toggle="tab" href="spare-${tag}-2">
                        <span class="hidden-mobile hidden-tablet"><dic data-dic="product.panel.tab.match"></dic><span role="index"></span></span>
                    </a>
                </li>
            </ul>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSelfSpare(2)"><dic data-dic="offer.create.custom"></dic></a>
            <a class="btn btn-xs btn-primary" style="float:right" onclick="addNewSpare(2)"><dic data-dic="header.list.button.add"></dic></a>
        </header>
        <div>
            <div class="widget-body no-padding">
                <div class="tab-content padding-10" id="panel-standard-dialog-2">
                    <form class="smart-form temp">
                        <fieldset>
                            <label class="col col-3 text-align-left"><dic data-dic="product.standard.list.styType"></dic></label>
                            <label class="col col-3 text-align-left"><dic data-dic="product.spare.list.brand"></dic></label>
                            <label class="col col-2"><dic data-dic="product.standard.list.num"></dic></label>
                            <label class="col col-2"><dic data-dic="offer.create.priceUnit"></label>
                            <label class="col col-2 text-align-center"><dic data-dic="offer.create.price.sum"></dic></label>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row total-tag">
            <label class="col col-md-3"><dic data-dic="offer.create.totalPrice"></dic>：${moneySymbol}<span tag="total-sale">0.00</span></label>
            <label class="col col-md-2 text-align-right no-left no-right"><dic data-dic="offer.create.discount"></dic>：</label>
            <label class="col col-md-3 no-left" style="margin-top: -7px;"><input type="text" tag="total-discount" value="100"></label>
            <label class="col col-md-4"><dic data-dic="offer.create.real"></dic>：${moneySymbol}<span tag="total-real">0.00</span></label>
        </div>
    </div>
    <div class="jarviswidget jarviswidget-sortable" id="service-panel">
        <header role="heading">
            <span class="widget-icon"><i class="fa fa-cube"></i> </span>
            <h2><strong><i><dic data-dic="offer.create.service"></dic></i></strong></h2>
            <a class="btn btn-primary btn-xs btn-service" onclick="appendService()">
                <i class="fa fa-plus"></i> <dic data-dic="header.list.button.add"></dic>
            </a>
        </header>
        <div id="service_part">
            <div class="widget-body no-padding">
                <form class="smart-form" id="service-form" style="word-break: keep-all">
                    <fieldset>
                        <label class="col col-4 text-align-left"><dic data-dic="product.standard.list.styType"></label>
                        <label class="col col-3 "><dic data-dic="standard.count.form.count"></dic></label>
                        <label class="col col-2 text-align-left"><dic data-dic="offer.create.priceUnit"></dic></label>
                        <label class="col col-2 text-align-center"><dic data-dic="offer.create.price.sum"></dic></label>
                    </fieldset>
                </form>
            </div>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -2px;" class="row total-tag">
            <label class="col col-md-3"><dic data-dic="offer.create.totalPrice"></dic>：${moneySymbol}<span tag="total-sale">0.00</span></label>
            <label class="col col-md-2 text-align-right no-left no-right"><dic data-dic="offer.create.discount"></dic>：</label>
            <label class="col col-md-3 no-left" style="margin-top: -7px;"><input type="text" tag="total-discount" value="100"></label>
            <label class="col col-md-4" style="margin-bottom: 12px;"><dic data-dic="offer.create.real"></dic>：${moneySymbol}<span tag="total-real">0.00</span></label>
        </div>
    </div>
    <div class="jarviswidget" style="border-top:1px solid #CCC;">
        <div style="border-bottom: none !important;" class="row">
            <form class="smart-form">
                <fieldset>
                    <label class="col col-md-2 text-align-right no-left no-right padding-7">
                        <dic data-dic="offer.create.project.name"></dic>
                    </label>
                    <label class="col col-md-10">
                        <input type="text" class="form-control" maxlength="200" id="project_name"/>
                    </label>
                </fieldset>
                <fieldset>
                    <label class="col col-md-2 text-align-right no-left no-right padding-7">
                        <dic data-dic="offer.create.remark"></dic>:
                    </label>
                    <label class="col col-md-10 textarea">
                        <textarea rows="3" name="remark" class="custom-scroll" style="width: 100%;resize: none;"></textarea>
                    </label>
                </fieldset>
            </form>
        </div>
        <div class="row">
            <label class="col col-md-4"><dic data-dic="offer.create.totalPrice"></dic>：<span id="totalPriceMoneySymbol">${moneySymbol}</span><span id="total-price">0.00</span></label>
            <label class="col col-md-2 text-align-right no-left no-right"><dic data-dic="offer.create.discount"></dic>：</label>
            <label class="col col-md-2 no-left" style="margin-top: -7px;"><input type="text" id="total-discount" value="100"></label>
            <label class="col col-md-4"><dic data-dic="offer.create.finalPrices"></dic>：${moneySymbol}<span id="total-real">0.00</span></label>
        </div>
        <div style="border: 1px solid #ccc;margin-top: -3px;padding: 5px" class="row">
            <button class="btn btn-default pull-right mr10" id="save_button" onclick="backList()"><i class="fa fa fa-floppy-o mr5"></i><dic data-dic="button.save"></dic></button>
            <button class="btn btn-primary pull-right mr10" id="submit_button"><i class="fa fa-cloud-upload mr5"></i><dic data-dic="button.submit"></dic></button>
        </div>
    </div>
</article>

<div class="modal fade in" id="dialog_table" tabindex="-1" role="dialog">
    <div class="modal-dialog" style="width: 900px;">
        <input type="hidden" value="0" id="spare-type">
        <div class="modal-content">
            <div class="modal-header">
                <h4><dic data-dic="portal.series.spares"></dic></h4>
            </div>
            <div class="modal-body no-padding">
                <form class="smart-form">
                    <fieldset>
                        <div id="choose_spare_dialog" class="custom-scroll table-responsive" style="max-height:450px; overflow-y: scroll;">

                        </div>
                    </fieldset>
                    <footer>
                        <button type="button" id="spare-choose" class="btn btn-primary"><dic data-dic="offer.create.choose"></button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><dic data-dic="button.cancel"></dic></button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="del_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width: 350px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    <dic data-dic="admin.window.attention"></dic>
                </h4>
            </div>
            <div class="modal-body">
                <dic data-dic="panel.message.del"></dic>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="ok_del">
                    <dic data-dic="button.confirm"></dic>
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="lower_base_price_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
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
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <dic data-dic="button.cancel"></dic>
                </button>
                <button type="button" class="btn btn-primary" id="ok_low_base_price_submit">
                    <dic data-dic="button.confirm"></dic>
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
                             style="overflow-y: scroll;height: 650px;">
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
                                                <a href=".tabs-choose" data-toggle="tab" aria-expanded="false">
                                                    <span class="badge bg-color-blueDark
                                                    txt-color-white check_count">0</span>
                                                    <dic data-dic="offer.create.free"></dic>
                                                </a>
                                            </li>
                                            <li class="">
                                                <a href=".tabs-free" data-toggle="tab" aria-expanded="false">
                                                    <span class="badge bg-color-greenLight
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
                                                <div class="row show-grid" style="max-height:580px;overflow: auto;">
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
                                                <div class="row show-grid" style="max-height:580px;overflow: auto;">
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
                                                <div class="row show-grid" style="max-height:580px;overflow: auto;">
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
                        <button type="button" id="choose_spares_sure" class="btn btn-primary">
                            <dic data-dic="offer.create.choose">
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><dic data-dic="button.cancel"></dic></button>
                    </footer>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/template" id="product-content-template">
    <div class="tab-pane fade in" id="{{= tag}}">
        <div class="row">
            <label class="col col-md-1 text-align-right padding7">{{= scnNo}}:</label>
            <label class="col col-md-4">
                <select name="panel" class="select2 product-select" style="width: 100%">
                    <option id=""></option>
                    <#list products as item>
                        <option data-partno="${(item.partNo)!}"
                                data-state="${(item.state)!}"
                                data-certification="${(item.certification)!}"
                                data-width="0"
                                data-height="0"
                                value="${(item.id?c)!}">${(item.partNo)!}<#if item.executionTime??>
                            [V${(item.executionTime?string("yyyy-MM-dd"))!}]</#if> - [ ${(item.sizeWidth)!}*${(item.sizeHeight)!} ] - [${(item.certification)!}]</option>
                    </#list>
                </select>
            </label>
            <label class="col col-md-7 padding7">{{= state}}: <span class="panel_state">{{= empty}}</span></label>
        </div>
        <div class="row">
            <label class="col col-md-1 padding7 text-align-right">{{= panelCount}}:</label>
            <label class="col col-md-2">
                <input type="text" tag="qty-width" value="0"/>
            </label>
            <label class="col col-md-2">
                <input type="text" tag="qty-height" value="0"/>
            </label>
            <label class="col col-md-1 padding7">{{= total}}:<span role="total">0</span></label>
            <label class="col col-md-2 padding7 text-align-right">{{= price}}:</label>
            <label class="col col-md-2">
                <input tag="price" value="0" placeholder="{{= price}}" class="form-control" disabled/>
            </label>
            <label class="col col-md-2 padding7 text-align-right padding-left">
                <form class="smart-form">
                    <label class="toggle">
                        <input type="checkbox" name="use-price">
                        <i data-swchon-text="ON" data-swchoff-text="OFF" style="right: auto;top: 0;"></i>
                    </label>
                </form>
                {{= priceCount}}:${moneySymbol}<span role="price-total">0</span>
            </label>
        </div>
        <div class="row">
            <label class="col col-md-1 padding7 text-align-right">{{= panelArea}}:</label>
            <label class="col col-md-2">
                <input type="text" tag="seq-width" value="0"/>
            </label>
            <label class="col col-md-2">
                <input type="text" tag="seq-height" value="0"/>
            </label>
            <label class="col col-md-2 padding7">{{= totalArea}}:<span role="total">0</span><#if length=="2">sq.ft<#else>㎡</#if></label>
            <label class="col col-md-1 padding7 text-align-right">{{= priceLevel}}:</label>
            <label class="col col-md-2">
                <input type="text" tag="discount" value="100" placeholder="reference"/>
            </label>
        </div>
        <div class="row">
            <label class="col col-md-1 padding7 text-align-right">{{= quantity}}:</label>
            <label class="col col-md-2">
                <input type="text" tag="quantity" value="1"/>
            </label>
            <label class="col col-md-4 padding7 text-align-right"></label>
            <label class="col col-md-1 padding7 text-align-right">{{= reference}}:</label>
            <label class="col col-md-2">
                <input type="text" tag="reference" value="0"/>
            </label>
            <label class="col col-md-2 padding7 text-align-right">{{= realPrice}}:${moneySymbol}<span role="price-real">0</span></label>
        </div>
        <div>
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
    <fieldset role="spares">
        <label class="col col-3 text-align-left padding7"></label>
        <label class="col col-3 text-align-left padding7"></label>
        <label class="col col-1 long-label">
            <input amount class="form-control" type="text" value="0"/>
        </label>
        <label class="col col-1 padding7 short-label"></label>
        <label class="col col-1 long-label margin-left-10">
            <input sales class="form-control" type="text" value="0"/>
        </label>
        <label class="col col-2  text-align-right padding7">${moneySymbol}<span price>0.00</span></label>
        <label class="trash">
            <a><i class="fa fa-trash-o"></i></a>
        </label>
    </fieldset>
</script>

<script id="service-template" type="text/template">
    <fieldset role="service">
        <label class="col col-4">
            <input class="form-control" placeholder="" type="text" value=""/>
        </label>
        <label class="col col-3">
            <input amount class="form-control" placeholder="" type="text" value="0"/>
        </label>
        <label class="col col-2">
            <input sales class="form-control" placeholder="" type="text" value="0"/>
        </label>
        <label class="col col-2 text-align-right padding7">${moneySymbol}<span price>0.00</span></label>
        <label class="trash col col-1">
            <a><i class="fa fa-trash-o"></i></a>
        </label>
    </fieldset>
</script>

<script id="package-template" type="text/template">
    <fieldset role="package">
        <label class="col col-4" style="position: inherit;">
            <input type="text" class="input-autosuggest form-control packages" packages style="width: 155px;" />
        </label>
        <label class="col col-3">
            <input amount class="form-control" placeholder="" type="text" value="0"/>
        </label>
        <label class="col col-2">
            <input sales class="form-control" placeholder="" type="text" value="0"/>
        </label>
            <#--<input sales class="form-control" placeholder="" type="text" value="0"/>-->
        <label class="col col-2 text-align-center padding7">${moneySymbol}<span price>0.00</span></label>
        <label class="trash-pack col col-1">
            <a><i class="fa fa-trash-o"></i></a>
        </label>
    </fieldset>
</script>

<script id="self-spare-template" type="text/template">
    <fieldset role="self">
        <label class="col col-3 text-align-left">
            <input type="text" class="form-control" maxlength="50"/>
        </label>
        <label class="col col-3 text-align-left">
            <input type="text" class="form-control" maxlength="100"/>
        </label>
        <label class="col col-1 long-label">
            <input amount class="form-control" type="text" value="0"/>
        </label>
        <label class="col col-1 short-label">
            <input type="text" class="form-control"/>
        </label>
        <label class="col col-1 long-label margin-left-10">
            <input sales type="text" value="0"/>
        </label>
        <label class="col col-2 text-align-right padding7">${moneySymbol}<span price="">0.00</span></label>
        <label class="trash">
            <a><i class="fa fa-trash-o"></i></a>
        </label>
    </fieldset>
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
<script type="text/javascript">
    $("#stepshow-setting").click(function () {
        $(this).parent().toggleClass("activate");
        $(this).parent().find("form:first").toggle();
    });

    $(document).ready(function () {
        addPanel('${tag}');
        initStepShow();
    });
    $("dic").each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
    var lengthType=(${length}==1)?1:0.3048;//用于换算米和英尺
    var squareType=(${length}==1)?1:0.092903;//

    function appendNewPanel() {
        var length = $("#panel-list-header li").length + 1;
        if (length >= 6) {
            return;
        }

        var tag = (new Date()).valueOf();
        var certificationDefault = $('#stepshow_cert').html();
        var panelTemp = {tag: 'panel-' + tag, scnNo:message["panel.list.search.scn"],panelCount:message["offer.create.panelNumber"],
            total:message["offer.create.totalCount"],price:message["offer.create.priceUnit"],priceCount:message["offer.create.priceCount"],
            panelArea:message["offer.create.panelArea"],totalArea:message["offer.create.totalArea"],discount:message["offer.create.discount"],
            realPrice:message["offer.create.realPrice"],certification: message['offer.create.certification'],certificationDefault:certificationDefault,
            state:message['product.panel.list.state'],empty:message['product.panel.no'],reference:message['offer.create.reference'],quantity:message['offer.create.screenQuantity'],
            priceLevel:message['offer.create.priceLevel']};
        $("#product-content-template").tmpl(panelTemp).appendTo($("#panel-list-content"));

        var panelHeader = { name:message["offer.create.panel"],tag: "panel-" + tag,index: length};
        $("#product-header-template").tmpl(panelHeader).appendTo($("#panel-list-header ul:first"));

        var paramHeader = {name: message["offer.create.params"],tag: "param-" + tag + "-1",index: length};
        $("#params-header-template").tmpl(paramHeader).appendTo($("#panel-param-1 ul:first"));

        var modualHeader = {name: message["offer.create.standard"],tag: "spare-" + tag + "-1",index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-1 ul:first"));

        var modualHeader = {name:  message["offer.create.spare"],tag: "spare-" + tag + "-2",index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-2 ul:first"));

        var modualHeader = {name:  message["offer.create.free"],tag: "spare-" + tag + "-3",index: length};
        $("#params-header-template").tmpl(modualHeader).appendTo($("#panel-standard-3 ul:first"));

        addPanel(tag);
        reBuildPanelIndex();
    }

    /**
     * 初始化右边弹出数据
     */
    function initStepShow() {
        var data = evt.findProduceData();
        $('[role="trader"]').html($('#select_trader').html());
        $('[role="trader"]').select2();
        $('[role="trader"]').change(function () {
            $('#select_trader').select2('val',$(this).val());
        });
        $('[role="sizeType"]').html($('#size-type').html());
        $('[role="sizeType"]').select2();
        $('[role="sizeType"]').change(function () {
            $('#size-type').select2('val',$(this).val());
        });
        $('#validDate').spinner({
            step:1,
            min:1,
            max:999,
            change: function () {
                $("#valid-date").val($('#validDate').val());
                $("#accept-days").val($('#validDate').val());
            }
        });
        //付款方式
        $('[role="payment"]').autosuggest({
            dataList:$('#select_payment').data('list'),
            local:true,
            minLength:1,
            onSelect: function (elDom) {
                $('#select_payment').val(elDom.data('value'));
            }
        });
        $('#panel-list-content .panel_certification').html(data['cert']);//第二步 物料号后面加上初始认证

        $('[role="payment"]').change(function () {
            $('#select_payment').val($(this).val());
        });

        $('#validDate').val(data['validDate']);
        $('[role="payment"]').val(data['payment']);

        $.each($('.product-info-look [role]'), function (index,item) {
            if(item.tagName === 'SELECT'){
                $(item).val(data[$(item).attr('role')]).trigger("change");
            }else if(item.tagName === 'SPAN'){
                $(item).html(data[$(item).attr('role')]);
            }
        });
    }

    function addPanel(id){
        var panel = $("#panel-"+id);
        panel.find("input[type='text'][tag]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(){
                if($(this).val() && isNum($(this).val())){

                }else{
                    $(this).val("");
                }
            }
        });

        panel.find("[tag='discount']").change(function(){
            var seqWidth = $(panel).find("input[tag='seq-width']").val()||0;
            var seqHeight = $(panel).find("input[tag='seq-height']").val()||0;
            var seq=seqHeight*seqWidth; //必须乘上面积换算单位，因为系统计算都是按照平米计算的
            var price = parseFloat($(panel).find("[tag='price']").val()) || 0;
            //获取屏体数量
            var quantity = $(panel).find("[tag='quantity']").val() || 0;
            //计算参考价
            $(panel).find("[tag='reference']").val(($(this).val()/100*price).toFixed(2));
            //计算实际总价
            $(panel).find("[role='price-real']").html((price*seq*quantity*$(this).val()/100).toFixed(2));
            calcPanelTotalPrice();
        });

        panel.find("[name='panel']").select2().change(function(){
            var value=$(this).select2("val");
            var certificationStr = $(this).find('option:checked').data('certification');
            var stateStr = $(this).find('option:checked').data('state');
            var panelCertification = $(this).closest('.row').find('.panel_certification');
            var panelState = $(this).closest('.row').find('.panel_state');
            if(certificationStr){
                panelCertification.html(certificationStr);
            }else{
                panelCertification.html($('#stepshow_cert').html());
            }
            if(stateStr){
                panelState.show();
                panelState.html(stateStr);
            }else{
                panelState.hide();
                panelState.html('');
            }
            if(value){
                showLoading();
                $.ajax({
                    url:"/offer/create/panel/params",
                    type:"get",
                    data:{
                        product:value,
                        tag:id,
                        series:$("#select-series").select2("val"),
                        money:'${money}',
                        requestArea:$("#price_area").select2("val")
                    },
                    success:function(html){
                        if($("#param-"+id+"-1").length>0){
                            $("#param-"+id+"-1").html($(html).find("div.tab-pane:eq(0)>div.smart-form"));
                            $("#spare-"+id+"-1").html($(html).find("div.tab-pane:eq(1)>form.smart-form"));
                            $("#spare-"+id+"-2").html($(html).find("div.tab-pane:eq(2)>form.smart-form"));
                            $("#spare-"+id+"-3").html($(html).find("div.tab-pane:eq(3)>form.smart-form"));
                        }else{
                            $(html).find("div.tab-pane:eq(0)").appendTo($("#panel-params-dialog-1"));
                            $(html).find("div.tab-pane:eq(1)").appendTo($("#panel-standard-dialog-1"));
                            $(html).find("div.tab-pane:eq(2)").appendTo($("#panel-standard-dialog-2"));
                            $(html).find("div.tab-pane:eq(3)").appendTo($("#panel-standard-dialog-3"));
                        }
                        var js = $(html).find("#param-" + id + "-js").html();
                        eval(js);
                        initPanelSpare(panel);
                        //重新计算价格
                        reCalcSeq(panel);
                        hideLoading();
                    },
                    error: function (err) {
                        console.log(err);
                        hideLoading();
                        alert(message["http.response.error"],3);
                    }
                });
            }
        });

        panel.find("input[tag='qty-height'],input[tag='qty-width']").change(function(){
            reCalcSeq(panel);
        });

        panel.find("input[tag='seq-height'],input[tag='seq-width']").change(function(){
            calcQty(panel);
        });

        panel.find("input[tag='price']").change(function(){
            var seqWidth = $(panel).find("input[tag='seq-width']").val()||0;
            var seqHeight = $(panel).find("input[tag='seq-height']").val()||0;
            var seq=seqHeight*seqWidth;
            //获取基准价
            var price = $(panel).find("[tag='price']").val()||0;
            //获取实际价
            var reference = $(panel).find("[tag='reference']").val() || 0;
            //获取屏体数量
            var quantity = $(panel).find("[tag='quantity']").val() || 0;
            var priceSale=parseFloat(seq*quantity*price).toFixed(2);
            $(panel).find("[tag='discount']").val(parseInt(reference/price*100));
            $(panel).find("span[role='price-total']").html(priceSale);
        });

        panel.find("input[tag='reference']").change(function(){
            var seqWidth = $(panel).find("input[tag='seq-width']").val()||0;
            var seqHeight = $(panel).find("input[tag='seq-height']").val()||0;
            var seq=seqHeight*seqWidth;
            var price = $(panel).find("input[tag='price']").val() || 0;
            var reference = $(panel).find("[tag='reference']").val() || 0;
            //获取屏体数量
            var quantity = $(panel).find("[tag='quantity']").val() || 0;
            var priceSale=parseFloat(seq*reference*quantity).toFixed(2);
            $(panel).find("input[tag='discount']").val((reference/price*100).toFixed(0));
            $(panel).find("span[role='price-real']").html(priceSale);
            calcPanelTotalPrice();
            lowTotalPrice();
        });

        panel.find("input[tag='quantity']").change(function(){
            var seqWidth = $(panel).find("input[tag='seq-width']").val()||0;
            var seqHeight = $(panel).find("input[tag='seq-height']").val()||0;
            var seq=seqHeight*seqWidth;
            //获取基准单价
            var price = $(panel).find("input[tag='price']").val() || 0;
            //获取实际单价
            var reference = $(panel).find("[tag='reference']").val() || 0;
            //获取屏体数量
            var quantity = $(panel).find("[tag='quantity']").val() || 0;
            var priceSale=parseFloat(seq*price*quantity).toFixed(2);
            var priceReal=parseFloat(seq*reference*quantity).toFixed(2);
            $(panel).find("span[role='price-total']").html(parseFloat(priceSale).toFixed(2));
            $(panel).find("span[role='price-real']").html(parseFloat(priceReal).toFixed(2));
            calcPanelTotalPrice();
            lowTotalPrice();
        });

        panel.find("input[name='use-price']").change(function () {
            var self = $(this);
            if (self.is(":checked")) {
                panel.find("input[tag='price']").removeAttr('disabled');
                panel.find("input[tag='price']").spinner({
                    step:1,
                    min:1,
                    max:1000000,
                    change:function(){
                        if($(this).val() && isNum($(this).val())){

                        }else{
                            $(this).val("");
                        }
                    }
                });
            }else {
                panel.find("input[tag='price']").attr('disabled', true);
            }
        });
    }

    /**
     * 总价低于基准价，最后价格给字体红色展示
     */
    function lowTotalPrice() {
        var isLow = false;
        $.each($('#panel-list-content>.tab-pane'),function () {
            var tagPrice = $(this).find('[tag="price"]').val();
            var tagBasePrice = $(this).find('[tag="price"]').data('base_price');
            if(Number(tagPrice) < Number(tagBasePrice)){
                isLow = true;
                return false;
            }
        });
        if(isLow){
            console.log('总价变红');
            $('#total-price,#totalPriceMoneySymbol').css('color','red');
        }else{
            console.log('总价正常');
            $('#total-price,#totalPriceMoneySymbol').css('color','black');
        }
    }

    function addNewSpare(type){
        var spare_id=$("#panel-standard-"+type).find("li.active a:first").attr("href");
        var tag = spare_id.split("-")[1];
        var panel=$("#panel-"+tag);
        var product=$(panel).find("select[name='panel']").select2("val");
        if(!product){
            alert(message["offer.create.panel.empty"]);
            return;
        }
        showLoading();
        $.ajax({
            url: "/offer/spares",
            data: {
                product: product,
                type: type,
                moneyType:'${money}',
                area:$("#price_area").select2("val"),
                series:$("#select-series").select2("val")
            },
            type: "get",
            success: function (data) {
                $("#choose_spare_dialog").html(data);
                $("#dialog_table").modal("show");
                $("#spare-type").val(spare_id);
                $("#spare-type").data("panel",panel.attr("id"));
                FittingUtil.byTypeChecked(type, $("#choose_spare_dialog"));
                hideLoading();
            }
        });
    }

    function appendService(){
        var template = $("#service-template").html();
        var newItem = $(template).clone(true);

        newItem.find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(e){
                if($(this).val() && isNum($(this).val())){
                    var fieldSet=$(e.target).parents("fieldset");
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val("");
                }
            }
        });

        $("#service-form").append(newItem);
    }

    function appendPackage(){
        var packages = [
            {id: 1, value: message["package.type.paper"]},
            {id: 2, value: message["package.type.wold"]},
            {id: 3, value: message["package.type.plane"]}
            ];
        $("#package-template").tmpl({}).appendTo($("#package-form"));
        $("#package-form .packages:last-child").autosuggest({
            dataList:packages,
            local:true,
            minLength:1
        });

        $("#package-form fieldset:last").find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(e){
                if($(this).val() && isNum($(this).val())){
                    var fieldSet=$(e.target).parents("fieldset");
                    calcTransport(fieldSet);
                }else{
                    $(this).val("");
                }
            }
        });
    }

    function initPanelSpare(panel){
        var id=panel.attr("id").replace("panel-","");
        $("#spare-"+id+"-1,#spare-"+id+"-2,#spare-"+id+"-3").find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:100000,
            change:function(){
                if($(this).val() && isNum($(this).val())){
                    var fieldSet=$(this).parents("fieldset");
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val("");
                }
            }
        });
    }
    /**
     * 计算某一个备件的销售价格，并且重新计算当前的组件集合的全部金额
     */
    function calcSingleSparePrice(fieldSet){
        calcSingleSparePriceNoTop(fieldSet);
        var widget=$(fieldSet).parents(".jarviswidget");
        calcKindSparePrice(widget);
    }

    /**
     *  重新计算所有备件数量、价格
     */
    function reCalcEveryCountsAndPrice(panelTag){
        var widgets=$("#panel-standard-1,#panel-standard-2,#panel-standard-3");

        var guarantee=$("[name='guarantee']").select2("val");
        $.each(widgets,function(index,item){
            var ind =$(item).attr("id").replace("panel-standard-","");
            var fieldSets = $(item).find("#spare-" + panelTag + "-" + ind + " form.smart-form.temp>fieldset");
            $.each(fieldSets,function(index,fied){
                var type=$(fied).data("type"+guarantee);
                var ct=$(fied).data("count"+guarantee);
                var spel=$(fied).data("spel"+guarantee);
                var input = $(fied).find("input[amount]:first");
                var count = calcCounts(type,ct,spel,"panel-"+panelTag)||0;
                if(0!=count){
                    $(input).val(count);
                }
                var price = $(fied).find("input[sales]").val()||0;
                $(fied).find("[price]").html((price*count).toFixed(2));
            });
            calcKindSparePriceNoTop(item);
        });
        calcTotalPrice();
    }

    function calcKindSparePrice(widget){
        calcKindSparePriceNoTop(widget);
        calcTotalPrice();
    }

    /**
     * 计算某个备件的销售价格，不重新计算备件集合价格
     */
    function calcSingleSparePriceNoTop(fieldSet){
        var price=parseFloat($(fieldSet).find("input[sales]").val())||0;
        var amount=parseFloat($(fieldSet).find("input[amount]").val())||0;
        $(fieldSet).find("span[price]").html(parseFloat(price*amount).toFixed(2));
    }

    /**
     * 计算所有屏体的某一个种类的备件总价，不重新计算总体价格
     */
    function calcKindSparePriceNoTop(widget){
        var fieldset = $(widget).find("fieldset");
        var totals = 0;
        $.each(fieldset, function (index,item) {
            var amount=parseFloat($(item).find("input[amount]").val())||0;
            var sales=parseFloat($(item).find("input[sales]").val())||0;
            var total=amount*sales;
            totals+=total;
        });

        $(widget).find("span[tag='total-sale']").html(totals.toFixed(2));
        var discount=parseFloat($(widget).find("input[tag='total-discount']").val())||100;
        $(widget).find("[tag='total-real']").html((totals*discount/100).toFixed(2));
    }

    /**
     * 计算屏幕的总价格
     */
    function calcPanelTotalPrice(){
        var totals =0;
        $.each($("[role='price-real']"),function(index,item){
            var price= parseFloat($(this).html())||0;
            totals+=price;
        });
        var discount=$("#panel-list-header input[tag='total-discount']").val();
        $("#panel-list-header span[tag='total-sale']").html(totals.toFixed(2));
        $("#panel-list-header span[tag='total-real']").html((totals*discount/100).toFixed(2));
        calcTotalPrice();
    }

    /**
     * 计算整个单子的总价格
     */
    function calcTotalPrice(){
        var totals= 0;
        $.each($("[tag='total-real']"),function(index,item){
            var value=parseFloat($(item).html())||0;
            totals+=value;
        });
        var discount = $("#total-discount").val()||100;
        $("#total-price").html(totals.toFixed(2));
        $("#total-real").html(parseFloat(discount*totals/100).toFixed(2));
    }

    /**
     * 通过屏体的数量，计算对应的宽度、高度、面积、价格、功率
     * @param panel
     */
    function reCalcSeq(panel){
        var height = parseInt($(panel).find("input[tag='qty-height']").val())||0;
        var width = parseInt($(panel).find("input[tag='qty-width']").val())||0;
        var total = height*width;
        $(panel).find("span[role='total']:first").html(total);

        var seqHeight=height*$(panel).data("height")||0;
        var seqWidth=width*$(panel).data("width")||0;

        //获取屏体数量
        var quantity = $(panel).find("[tag='quantity']").val() || 0;
        var reference = $(panel).find("[tag='reference']").val()|| 0;
        $(panel).find("input[tag='seq-width']").val(parseFloat(seqWidth/lengthType));
        $(panel).find("input[tag='seq-height']").val(parseFloat(seqHeight/lengthType));

        $(panel).find("span[role='total']:last").html((seqHeight*seqWidth/squareType).toFixed(4));
        var priceSale=parseFloat((seqHeight*seqWidth*quantity*$(panel).find("[tag='price']").val())||0).toFixed(2);
        $(panel).find("span[role='price-total']").html(priceSale);
        var totalPrice=parseFloat(seqHeight*seqWidth*quantity*reference).toFixed(2);
        $(panel).find("span[role='price-real']").html(totalPrice);

        var panelTag=$(panel).attr("id").replace("panel-","");

        $.each($("#param-"+panelTag+"-1 [data-ct1]"),function () {
            var ct1;
            var ct2;
            if (width == 0 || height == 0) {
                ct1=$(this).data("ct1");
                ct2=$(this).data("ct2");
            }else {
                ct1=$(this).data("ct1")*width;
                ct2=$(this).data("ct2")*height;
            }
            $(this).html(ct1+"*"+ct2);
        });

        $.each($("#param-"+panelTag+"-1 [data-counts]"),function () {
            if (width == 0 || height == 0) {
                $(this).html($(this).data("counts"));
            }else {
                $(this).html($(this).data("counts")*width*height);
            }
        });

        calcPanelTotalPrice();
        reCalcEveryCountsAndPrice(panelTag);
    }

    $('#package-panel .packageTrade').select2();

    /**
     * 通过宽度、高度计算屏体的数量
     * @param panel
     */
    function calcQty(panel){
        var heightInput=$(panel).find("input[tag='seq-height']:first").val();
        var widthInput=$(panel).find("input[tag='seq-width']:first").val();
        var heightUnit=parseFloat($(panel).data("height"));
        var widthUnit=parseFloat($(panel).data("width"));
        var widthSize= ($("#size-type").select2("val")==2 || $("#size-type").select2("val")==3);//横向是否允许超出边界
        var heightSize= ($("#size-type").select2("val")==2 || $("#size-type").select2("val")==4);//纵向是否允许超出边界

        if(!isNaN(heightInput)){
            var count_height=parseFloat(heightInput*lengthType)/heightUnit;
            var height=heightSize?Math.ceil(count_height):Math.floor(count_height);
            $(panel).find("input[tag='qty-height']").val(height);
        }
        if(!isNaN(widthInput)){
            var count_width=parseFloat(widthInput*lengthType)/widthUnit;
            var width=widthSize?Math.ceil(count_width):Math.floor(count_width);
            $(panel).find("input[tag='qty-width']").val(width);
        }

        if(!isNaN(heightInput) && !isNaN(widthInput)){
            reCalcSeq(panel);
        }
    }

    $("#panel-list-header").on("click","ul>li>a:not(.active)", function () {
        var tag=$(this).attr("href").replace("panel-","");

        $("#panel-list-content .tab-pane.active").removeClass("active");
        $("#panel-"+tag).addClass("active");

        $("#panel-param-1 li.active").removeClass("active");
        $("a[href='param-"+tag+"-1']").parent().addClass("active");

        $("#panel-params-dialog-1 .tab-pane.active").removeClass("active");
        $("#param-"+tag+"-1").addClass("active");

        $("#panel-standard-1 li.active,#panel-standard-2 li.active,#panel-standard-3 li.active").removeClass("active");
        $("a[href='spare-"+tag+"-1'],a[href='spare-"+tag+"-2'],a[href='spare-"+tag+"-3']").parent().addClass("active");

        $("#panel-standard-dialog-1 .tab-pane.active,#panel-standard-dialog-2 .tab-pane.active,#panel-standard-dialog-3 .tab-pane.active").removeClass("active");
        $("#spare-"+tag+"-1,#spare-"+tag+"-2,#spare-"+tag+"-3").addClass("active");
    });

    $("#panel-param-1,#panel-standard-1,#panel-standard-2,#panel-standard-3").on("click","li:not(.active)",function(){
        var href=$(this).find("a").attr("href");
        $(this).parents(".jarviswidget").find(".tab-pane.active").removeClass("active");
        $("#"+href).addClass("active");
    });

    $("#spare-choose").off("click").click(function (e) {
        FittingUtil.checkedSpare(e);
    });

    function addNewSelfSpare(type){
        var form=$("#panel-standard-"+type).find(".tab-pane.active form.self");
        var template = $("#self-spare-template").html();
        var newItem=$(template).clone(true);
        newItem.find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:1000000,
            change:function(e){
                if($(this).val() && isNum($(this).val())){
                    var fieldSet=$(e.target).parents("fieldset:first");
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val("");
                }
            }
        });
        if (!!$(form) && $(form).length > 0) {
            $(form).append(newItem);
        } else {
            $("#panel-standard-" + type).find(".tab-pane.active").append($("<form class='smart-form self'></form>"));
            $("#panel-standard-" + type).find(".tab-pane.active form.self").append(newItem);
        }
    }

    /**
     * 给备件列表添加一个备件信息
     */
    function appendSpares(data,form,panelId,description){
        var template = $("#standard-template").html();
        var newItem = $(template).clone(true);
        newItem.find("label:eq(0)").html(data.type);
        newItem.find("label:eq(1)").html(data.brand);
        newItem.find("label:eq(3)").html(data.unit);

        newItem.data("id",data.id);
        newItem.data("spel2",data.spel2Year);
        newItem.data("type2",data.type2Year);
        newItem.data("count2",data.count2Year);
        newItem.data("spel3",data.spel3Year);
        newItem.data("type3",data.type3Year);
        newItem.data("count3",data.count3Year);
        newItem.data("spel4",data.spel4Year);
        newItem.data("type4",data.type4Year);
        newItem.data("count4",data.count4Year);
        newItem.data("spel5",data.spel5Year);
        newItem.data("type5",data.type5Year);
        newItem.data("count5",data.count5Year);
        newItem.data("cost",data.costPrice);
        newItem.data("sale",data.salePrice);

        newItem.find("input[amount],input[sales]").spinner({
            step:1,
            min:1,
            max:1000000,
            change:function(e){
                if($(this).val() && isNum($(this).val())){
                    var fieldSet=$(e.target).parents("fieldset:first");
                    calcSingleSparePrice(fieldSet);
                }else{
                    $(this).val("");
                }
            }
        });

        var guarantee=$("[name='guarantee']").select2("val");
        var type=newItem.data("type"+guarantee);
        var ct=newItem.data("count"+guarantee);
        var spel=newItem.data("spel"+guarantee);

        newItem.find("input[sales]").val(data.salePrice);
        newItem.find("label:first").attr("title",description);
        var input = newItem.find("input[amount]:first");
        var count= calcCounts(type,ct,spel,panelId);
        if(0!=count){
            input.val(count);
        }

        $(form).append(newItem);

        calcSingleSparePrice(newItem);
    }

    /**
     * type 质保年限
     * ct N年的数量
     * spel N年的计算公式
     * 箱体Id
     */
    function calcCounts(type, ct, spel, panel) {
        if (type == 1) {
            return ct;
        }

        var result = 0;

        var w_b =$("#"+panel).find("input[tag='qty-width']").val(); //箱体横向数量
        var h_b =$("#"+panel).find("input[tag='qty-height']").val(); //箱体纵向数量
        var w_p = $("#"+panel).find("input[tag='seq-width']").val()*lengthType;//屏体横向长度
        var h_p = $("#"+panel).find("input[tag='seq-height']").val()*lengthType;//屏体纵向长度
        var w_m = $("#"+panel).data("boxwidth");//每个箱体横向多少块模组
        var h_m =  $("#"+panel).data("boxheight");//每个箱体纵向多少块模组

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

    $(".jarviswidget").on("click",".trash",function(){
        var widget = $(this).parents(".jarviswidget");
        $(this).parents("fieldset").remove();
        calcKindSparePrice(widget);
    });

    $(".jarviswidget").on("click",".trash-pack",function () {
        $(this).parents("fieldset").remove();
        var fieldSet=$(this).find("fieldset");
        calcTransport(fieldSet);
    });

    function calcTransport(fieldSet) {
        var price=parseFloat($(fieldSet).find("input[sales]").val())||0;
        var amount=parseFloat($(fieldSet).find("input[amount]").val())||0;
        $(fieldSet).find("span[price]").html(parseFloat(price*amount).toFixed(2));
        var field=$("#package-form fieldset");
        var total=0;
        $.each(field,function () {
            var number = parseInt($(this).find("input[amount]").val());
            var value=parseFloat($(this).find("input[sales]").val());
            if(!!value){
                total+=number*value;
            }
        });
        total+=(parseFloat($("#logistics-cost").val())||0);
        $("#package-panel").find("[tag='total-real']").html(total.toFixed(2));
        calcTotalPrice();
    }

    $(".row.total-tag input:not(.select2-focusser,.select2-input)").spinner({
        step:1,
        min:1,
        max:100,
        change: function () {
            if($(this).val() && isNum($(this).val())){
                var div=$(this).parents(".row.total-tag:first");
                var sale=parseFloat($(div).find("[tag='total-sale']").html())||0;
                var discount=parseFloat($(this).val())||100;
                $(div).find("[tag='total-real']").html((sale*discount/100).toFixed(2));
                calcTotalPrice();
            }else{
                $(this).val("");
            }
        }
    });

    $("#panel-list-header").on("click",".drop-panel",function(event){
        if($(".drop-panel").length==1){
            return;
        }
        $('#del_modal').modal('show');
        $('#ok_del').data("event", event);
        $('#ok_del').data("$dropPanel", $(this));
    });
    $('#del_modal').on('click', '#ok_del', function () {
        $('#del_modal').modal('hide');
        removePanel($(this).data('event'),$(this).data('$dropPanel'))
    });
    function removePanel(event,$panel) {
        event.preventDefault();
        event.stopPropagation();
        if($(".drop-panel").length==1){
            return;
        }
        var active=$panel.parents("li").hasClass("active");
        var panel=$panel.parent("a:first").attr("href");
        var tag=panel.replace("panel-","");
        var panels=$("#"+panel);
        var panelLi=$("[href='"+panel+"']");
        var params = "#param-"+tag+"-1";
        var spares =  "#spare-"+tag+"-1,#spare-"+tag+"-2,#spare-"+tag+"-3";
        var paramLi = "[href='param-"+tag+"-1']";
        var spareLi = "[href='spare-"+tag+"-1'],[href='spare-"+tag+"-2'],[href='spare-"+tag+"-3']";
        $(params).remove();
        $(spares).remove();
        $(panels).remove();
        $(paramLi).parents("li").remove();
        $(spareLi).parents("li").remove();
        $(panelLi).parents("li").remove();
        if(active){
            $("#panel-list-header li:first>a").click();
        }

        calcPanelTotalPrice();
        $.each($("#panel-standard-1,#panel-standard-2,#panel-standard-3"),function(index,item){
            calcKindSparePriceNoTop(item);
        });
        calcTotalPrice();
        reBuildPanelIndex();
    }


    $("#total-discount").spinner({
        step:1,
        min:1,
        max:100,
        change:function(){
            if($(this).val() && isNum($(this).val())){
                var discount = $("#total-discount").val()||100;
                var real = parseFloat($("#total-price").html())||0;
                $("#total-real").html(parseFloat(discount*real/100).toFixed(2));
            }else{
                $(this).val("");
            }
        }
    });

    $("#logistics-cost").spinner({
        step:1,
        min:0,
        change:function(){
            calcTransport();
        }
    });

    $("#submit_button").off("click").click(function(){
        showLoading();
        var valid = checkInput();
        if(!valid){
            hideLoading();
            return false;
        }
        var confirmFlag = checkConfirm();
        if(confirmFlag){
            hideLoading();
            return false;
        }
        //用来标记是否草稿 true-是 false-否
        submitFun(false);
    });
    $("#save_button").off("click").click(function(){
        showLoading();
        var valid = checkInput();
        if(!valid){
            hideLoading();
            return false;
        }
        //用来标记是否草稿 true-是 false-否
        submitFun(true);
    });
    $('#ok_low_base_price_submit').click(function () {
        //用来标记是否草稿 true-是 false-否
        submitFun(false);
    });

    /**
     * 提交之前，检查是否需要给出提醒
     */
    function checkConfirm() {
        var confirmFlag = false;
        $.each($("select[name='panel']"),function(index,item){
            var tabPane = $(item).parents(".tab-pane");
            var basePrice = tabPane.find("input[tag='price']").data('base_price');
            var tagPrice = tabPane.find("input[tag='price']").val();
            if(Number(tagPrice) < Number(basePrice)){
                var tab=$(item).parents(".tab-pane").attr("id");
                var li = $("a[href='"+tab+"']");
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
        var offer={
            customer:$("#select_customer").data("id"),
            customerName:$("#select_customer").val(),
            payment:$("#select_payment").val(),
            guarantee:$("[name='guarantee']").select2("val"),
            waitingDate:$("#valid-date").val(),
            trader:$('#select_trader').val(),
            cost:$("#total-real").html(),
            moneyUnit:$("#money-type").select2("val"),
            sizeUnit:$("#length-type").select2("val"),
            series:$("#select-series").select2("val"),
            remark:$("[name='remark']").val(),
            rate:$("#money-type").select2("val"),
            projectName:$("#project_name").val()
        };
        var transfer={
            toAddress: "",//$("[name='to_address']").val(),
            cost:$("#logistics-cost").val(),
            trade:$('#package-panel .packageTrade').select2('data').text,
            transport:"",//$("[name='transport']").val(),
            size:""//$("[name='size']").val()
        };

        var transport=[];
        $.each($("#package-form fieldset[role='package']"),function(){
            var trans={
                packages:$(this).find("input[packages]").val(),
                priceUnit:$(this).find("input[amount]").val(),
                number:$(this).find("input[sales]").val(),
                price:$(this).find("span[price]").html()
            };
            transport.push(trans);
        });

        var certs=$("[data-role='cert'].btn-warning");
        var certValues=[];
        for (var i=0;i<certs.length;i++){
            var value=$(certs[i]).data("value");
            if(!!value){
                certValues.push(value);
            }
        }
        if(certValues.length==0){
            certValues.push("Basics");
        }

        var basics={
            area:$("#price_area").select2("val"),
            cert:certValues.join(","),
            configuration:$("[data-role='config'].btn-warning").data("value"),
            status:$("[data-role='state'].btn-warning").data("value"),
            sizeType:$("#size-type").select2("val")
        };

        var prices={
            panelPrice:$("#panel-list-header span[tag='total-real']").html(),
            panelDiscount:$("#panel-list-header input[tag='total-discount']").val(),
            standPrie:$("#panel-standard-1 span[tag='total-real']").html(),
            standDiscount:$("#panel-standard-1 input[tag='total-discount']").val(),
            sparePrice:$("#panel-standard-2 span[tag='total-real']").html(),
            spareDiscount:$("#panel-standard-2 input[tag='total-discount']").val(),
            freePrice:$("#panel-standard-3 span[tag='total-sale']").html(),
            freeDiscount:0,
            servicePrice:$("#service-panel span[tag='total-real']").html(),
            serviceDiscount:$("#service-panel input[tag='total-discount']").val(),
            totalPrice:$("#total-real").html(),
            totalDiscount:$("#total-discount").val()
        };

        var serviceList=[];
        $.each($("#service-panel .smart-form fieldset[role='service']"),function(index,item){
            var service={
                name:$(item).find("input:first").val(),
                counts:$(item).find("input:eq(1)").val(),
                price:$(item).find("input:eq(2)").val()
            };
            serviceList.push(service);
        });

        var panelList=[];
        var gua=$("select[name='guarantee']").select2("val");
        $.each($("#panel-list-header li"),function(index,item){
            var panelId=$(item).find("a").attr("href");
            var panelDiv=$("#"+panelId);
            var panels={
                panel:$(panelDiv).find("select[name='panel']").select2("val"),
                wcount:$(panelDiv).find("input[tag='qty-width']").val(),
                lcount:$(panelDiv).find("input[tag='qty-height']").val(),
                horizontal:$(panelDiv).find("input[tag='seq-width']").val(),
                longitudinal:$(panelDiv).find("input[tag='seq-height']").val(),
                price:$(panelDiv).find("input[tag='price']").val(),
                orders:index+1,
                totalPrice:$(panelDiv).find("span[role='price-real']").html(),
                discount:$(panelDiv).find("input[tag='discount']").val(),
                suggPrice:$(panelDiv).data("sales"),
                costPrice:$(panelDiv).data("price"),
                quantity:$(panelDiv).find("input[tag='quantity']").val()
            };

            var standDiv=$("#spare-"+panelId.replace("panel-","")+"-1");
            var standardList=[];
            $.each($(standDiv).find("form.smart-form.temp fieldset[role]"),function(ind,fieldset){
                standardList.push(buildSpare(fieldset,gua,panelId));
            });
            var selfStandList=[];
            $.each($(standDiv).find("form.smart-form.self fieldset[role]"),function(ind,fieldset){
                selfStandList.push(buildSelf(fieldset));
            });

            var spareDiv=$("#spare-"+panelId.replace("panel-","")+"-2");
            var spareList=[];
            $.each($(spareDiv).find("form.smart-form.temp fieldset[role]"),function(ind,fieldset){
                spareList.push(buildSpare(fieldset,gua,panelId));
            });
            var selfSpareList=[];
            $.each($(spareDiv).find("form.smart-form.self fieldset[role]"),function(ind,fieldset){
                selfSpareList.push(buildSelf(fieldset));
            });

            var freeDiv=$("#spare-"+panelId.replace("panel-","")+"-3");
            var freeList=[];
            $.each($(freeDiv).find("form.smart-form.temp fieldset[role]"),function(ind,fieldset){
                freeList.push(buildSpare(fieldset,gua,panelId));
            });
            var selfFreeList=[];
            $.each($(freeDiv).find("form.smart-form.self fieldset[role]"),function(ind,fieldset){
                selfFreeList.push(buildSelf(fieldset));
            });

            panelList.push({
                panels:panels,
                standardList:standardList,
                spareList:spareList,
                freeList:freeList,
                selfStandList:selfStandList,
                selfSpareList:selfSpareList,
                selfFreeList:selfFreeList
            });
        });

        var offerVo = {
            offer:offer,
            prices:prices,
            panelList:panelList,
            serviceList:serviceList,
            transfer:transfer,
            transport:transport,
            basic:basics
        };


        $.ajax({
            url:"/offer/submit",
            type:"post",
            data:{
                draftFlag: draftFlag,
                offer:JSON.stringify(offerVo),
                transfer:false
            },
            success:function(data){
                if(data){
                    alert(message["http.response.success"]);
                    setTimeout(function(){
                        window.location.href="#/offer/list"
                    },2000);
                }else{
                    alert(message["http.response.fail"],3);
                }
                hideLoading();
            },
            error:function(data){
                console.log(data);
                alert(message["http.response.error"],3);
                hideLoading();
            }
        });
    }

    function reBuildPanelIndex(){
        var items= $("#panel-list-header li");
        $.each(items, function (index, item) {
            var tag = $(item).find("a").attr("href").replace("panel-", "");
            if(items.length>1){
                $("a[href*='" + tag + "']").find("span[role='index']").html(index+1);
            }else{
                $("a[href*='" + tag + "']").find("span[role='index']").html("");
            }

        });
    }

    function buildSpare(fieldset,gua,panelId){
        var item={
            spare:$(fieldset).data("id"),
            spareName:$(fieldset).find("label:first").html(),
            brand:$(fieldset).find("label:eq(1)").html(),
            countGuid:calcCounts($(fieldset).data("type"+gua),$(fieldset).data("count"+gua),$(fieldset).data("spel"+gua),panelId),
            countReal:$(fieldset).find("input[amount]").val(),
            priceCost:$(fieldset).data("cost"),
            priceSale:$(fieldset).find("input[sales]").val(),
            priceGuide:$(fieldset).data("sale")
        };
        return item;
    }

    function buildSelf(fieldset){
        var item={
            spareType:$(fieldset).find("input:first").val(),
            brand:$(fieldset).find("input:eq(1)").val(),
            price:$(fieldset).find("input[sales]").val(),
            amount:$(fieldset).find("input[amount]").val(),
            unit:$(fieldset).find("input:eq(3)").val()
        };
        return item;
    }

    function checkInput(){
        var valid=true;
        $.each($("select[name='panel']"),function(index,item){
            if(!$(item).select2("val")){
                var tab=$(item).parents(".tab-pane").attr("id");
                var li = $("a[href='"+tab+"']");
                $(li).click();
                var name=$(li).find("span").html().replace("<span role=\"index\">","").replace("</span>","");
                console.log("quote.empty.product.panel");
                alert(message["quote.empty.product.panel"].replace("_index_",name),2);
                valid=false;
                return false;
            }
        });
        if(valid){
            $.each($("[tag='total-discount'],#total-discount"),function(index,item){
                if(!$(item).val()){
                    console.log("offer.create.empty.discount");
                    alert(message["offer.create.empty.discount"],2);
                    valid=false;
                    return false;
                }
            });
        }
        if(valid){
            $.each($(".panel-input .tab-pane input.ui-spinner-input:not([tag='total-discount'],#total-discount,select2-input),.panel-input .tab-pane .smart-form input:not(.ui-spinner-input)"),function(index,item){
                if(!$(this).val()){
                    var id=$(this).parents(".tab-pane").attr("id");
                    var li = $("a[href='"+id+"']");
                    $(li).click();
                    var name=$(li).find("span").html().replace("<span role=\"index\">","").replace("</span>","");
                    console.log("offer.create.empty.input");
                    alert(message["offer.create.empty.input"].replace("_index_",name),2);
                    valid=false;
                    return false;
                }
            });
        }
        if(valid){
            var inputs=$("#package-panel input:not(.select2-focusser,.select2-input)");
            $.each(inputs,function(){
               if(!$(this).val()){
                   console.log("offer.create.empty.package");
                   alert(message["offer.create.empty.package"],2);
                   valid=false;
                   return false;
               }
            });
        }
        if(valid){
            var inputs=$("#service-form input");
            $.each(inputs,function(){
                if(!$(this).val()){
                    console.log("offer.create.empty.service");
                    alert(message["offer.create.empty.service"],2);
                    valid=false;
                    return false;
                }
            });
        }
        return valid;
    }

    /**
     * 点击checked选择配件
     */
    $('#choose_spare_list_ul').off('click').on('click','tbody tr td',function () {
        if($.inArray(this,$(this).closest('tr').find('td')) !== 0){
            $(this).closest('tr').find('input[type="checkbox"]').click();
        }
        FittingUtil.updateCheckedCount();
    });
    /**
     * 多选配件 确定按钮事件
     */
    $('#choose_spares_sure').off('click').click(function () {
        var spares_list = [];
        var spare_form = $("#panel-standard-1").find("li.active a:first").attr("href");
        var choose_form = $("#panel-standard-2").find("li.active a:first").attr("href");
        var free_form = $("#panel-standard-3").find("li.active a:first").attr("href");

        var spare_form_panel = 'panel-' + spare_form.split("-")[1];
        var choose_form_panel = 'panel-' + choose_form.split("-")[1];
        var free_form_panel = 'panel-' + free_form.split("-")[1];

        $.each($('#choose_spare_list_ul .tabs-spare tbody tr'),function (index,item) {
            if($(item).find('input[type="checkbox"]').prop("checked")){
                spares_list.push({
                    data: $(item).data('item'),
                    form: $("#"+spare_form+" form.smart-form.temp"),
                    panelId: spare_form_panel,
                    description: $(item).data('item').description
                });
            }
        });
        $.each($('#choose_spare_list_ul .tabs-choose tbody tr'),function (index,item) {
            if($(item).find('input[type="checkbox"]').prop("checked")){
                spares_list.push({
                    data: $(item).data('item'),
                    form: $("#"+choose_form+" form.smart-form.temp"),
                    panelId: choose_form_panel,
                    description: $(item).data('item').description
                });
            }
        });
        $.each($('#choose_spare_list_ul .tabs-free tbody tr'),function (index,item) {
            if($(item).find('input[type="checkbox"]').prop("checked")){
                spares_list.push({
                    data: $(item).data('item'),
                    form: $("#"+free_form+" form.smart-form.temp"),
                    panelId: free_form_panel,
                    description: $(item).data('item').description
                });
            }
        });
        spares_list.forEach(function (item) {
            appendSpares(item.data,item.form,item.panelId,item.description);
        });
        $('#choose_spares').modal('hide');
    });

    /**
     * 检测模态框关闭
     */
    $('#choose_spares').on('hide.bs.modal', function () {
        $('body').css('overflow','auto');// 浮层关闭时滚动设置
    });
    /**
     * 检测模态框开启
     */
    $('#choose_spares').on('show.bs.modal', function () {
        $('body').css('overflow','hidden');//浮层出现时窗口不能滚动设置
    });

    $('#dialog_test').off('click').click(function () {
        FittingUtil.chooseSparesDialog();
    });

    /**
     * 整合工具方法
     */
    var FittingUtil = function FittingUtil() {
        return {
            //获取已选择
            findFittingList: function () {
                var map = {
                    spare: [],
                    choose: [],
                    free: []
                };
                var panelKey = $('#panel-list-header li.active a').attr("href").split("-")[1];
                $.each($('#spare-'+ panelKey +'-1 fieldset'), function (index, item) {
                    map.spare.push($(item).data().id);
                });
                $.each($('#spare-'+ panelKey +'-2 fieldset'), function (index, item) {
                    map.choose.push($(item).data().id);
                });
                $.each($('#spare-'+ panelKey +'-3 fieldset'), function (index, item) {
                    map.free.push($(item).data().id);
                });
                return map;
            },
            //多选配件 刷新数量
            updateCheckedCount: function () {
                var spareCount = $('.tabs-spare input[type="checkbox"]:checked').length;
                var chooseCount = $('.tabs-choose input[type="checkbox"]:checked').length;
                var freeCount = $('.tabs-free input[type="checkbox"]:checked').length;

                $('#countListShow a[href=".tabs-spare"] .check_count').html(spareCount);
                $('#countListShow a[href=".tabs-choose"] .check_count').html(chooseCount);
                $('#countListShow a[href=".tabs-free"] .check_count').html(freeCount);
            },
            /**
             * 根据类型勾选已选择的配件
             * @param type 类型 1-标配 2-选配 3-免费
             */
            byTypeChecked: function (type, fatherEl) {
                var ids = [];
                $.each($('#panel-standard-dialog-'+ type +' div.active fieldset'),function (index, item) {
                    ids.push($(item).data('id'));
                });
                $.each(fatherEl.find('tbody tr'),function (index, item) {
                    if($.inArray($(item).data('id'), ids) >= 0){
                        $(item).hide();
//                        $(item).find('input.spare_checkbox').prop('checked',true);
                    }
                });
            },
            /**
             * 配件选中事件
             */
            checkedSpare: function (e) {
                e.preventDefault();
                var type = $("#spare-type").val().split('-')[2];
                //已选配件ID
                var ids = [];
                $.each($('#panel-standard-dialog-'+ type +' div.active fieldset'),function (index, item) {
                    ids.push($(item).data('id'));
                });

                var checkboxs = $("#choose_spare_dialog table input:checkbox:checked");
                if(!checkboxs || checkboxs.length==0){
                    alert(message["offer.create.spare.empty"]);
                    return;
                }

                checkboxs.each(function(e){
                    var tr=$(this).parents("tr:first");
                    if($(tr).data("id") && $.inArray($(tr).data("id"), ids) === -1){
                        var sparetag=$("#spare-type").val();
                        var data={
                            id:$(tr).data("id"),
                            brand:$(tr).data("brand"),
                            type:$(tr).data("type"),
                            model:$(tr).data("model"),
                            unit:$(tr).data("unit"),
                            count2Year:$(tr).data("count2"),
                            type2Year:$(tr).data("type2"),
                            spel2Year:$(tr).data("spel2"),
                            count3Year:$(tr).data("count3"),
                            type3Year:$(tr).data("type3"),
                            spel3Year:$(tr).data("spel3"),
                            count4Year:$(tr).data("count4"),
                            type4Year:$(tr).data("type4"),
                            spel4Year:$(tr).data("spel4"),
                            count5Year:$(tr).data("count5"),
                            type5Year:$(tr).data("type5"),
                            spel5Year:$(tr).data("spel5"),
                            salePrice:$(tr).data("sale"),
                            costPrice:$(tr).data("cost")
                        };
                        appendSpares(data,$("#"+sparetag+" form.smart-form.temp"), $("#spare-type").data("panel"),$(tr).find("td:last").html());
                    }
                });

                $("#dialog_table").modal("hide");
                $("#choose_spare_dialog").html("");
                $("#spare-type").val("");
                $("#spare-type").data("panel","");
            },
            /**
             * 选择 配件、选配、免费
             */
            chooseSparesDialog: function () {
                var self = this;
                showLoading();
                var spare_id = $("#panel-standard-1").find("li.active a:first").attr("href");
                var tag = spare_id.split("-")[1];
                var panel=$("#panel-"+tag);
                var product=$(panel).find("select[name='panel']").select2("val");
                if(!product){
                    alert(message["offer.create.panel.empty"]);
                    hideLoading();
                    return;
                }

                var spareTbody1 = $('#choose_spare_list_ul .tabs-spare1 tbody');
                var spareTbody2 = $('#choose_spare_list_ul .tabs-spare2 tbody');
                var chooseTbody1 = $('#choose_spare_list_ul .tabs-choose1 tbody');
                var chooseTbody2 = $('#choose_spare_list_ul .tabs-choose2 tbody');
                var freeTbody1 = $('#choose_spare_list_ul .tabs-free1 tbody');
                var freeTbody2 = $('#choose_spare_list_ul .tabs-free2 tbody');
                spareTbody1.html('');
                spareTbody2.html('');
                chooseTbody1.html('');
                chooseTbody2.html('');
                freeTbody1.html('');
                freeTbody2.html('');

                $.ajax({
                    url: '/offer/rest/spares',
                    type: 'get',
                    data: {
                        product: product,
                        moneyType:'${money}',
                        area:$("#price_area").select2("val"),
                        series:$("#select-series").select2("val")
                    },
                    success: function (res) {
                        console.log(res);
                        hideLoading();

                        var fittingCount = self.findFittingList();
//                        var windowHeight = parseInt($(window).height() * 0.7);
//                        $('#choose_spare_list_ul').css('height', windowHeight + "px");

                        res.spares.forEach(function (item,index) {
                            if($.inArray(item.id, fittingCount.spare) === -1){
                                var itemEl = $('#self-spare-tr').tmpl(item);
                                itemEl.data('item',item);
                                if(index%2===0){
                                    spareTbody1.append(itemEl);
                                }else{
                                    spareTbody2.append(itemEl);
                                }
                            }
                        });

                        res.choose.forEach(function (item,index) {
                            if($.inArray(item.id, fittingCount.choose) === -1){
                                var itemEl = $('#self-spare-tr').tmpl(item);
                                itemEl.data('item',item);
                                if(index%2===0){
                                    chooseTbody1.append(itemEl);
                                }else{
                                    chooseTbody2.append(itemEl);
                                }
                            }
                        });

                        res.free.forEach(function (item,index) {
                            if($.inArray(item.id, fittingCount.free) === -1){
                                var itemEl = $('#self-spare-tr').tmpl(item);
                                itemEl.data('item',item);
                                if(index%2===0){
                                    freeTbody1.append(itemEl);
                                }else{
                                    freeTbody2.append(itemEl);
                                }
                            }
                        });

                        self.updateCheckedCount();
                        self.searchSpare();
                        $('#choose_spares').modal('show');
                    },
                    error: function (err) {
                        hideLoading();
                        console.log(err);
                        alert(message["operation.failed"]);
                    }
                });
            },
            searchSpare: function () {
                var self = this;
                var spareSearchs = $('#choose_spare_list_ul a.btn_search');
                var spareRest = $('#choose_spare_list_ul a.btn_rest');
                var search_type,search_description,search_brand;
                spareSearchs.off('click').click(function () {
                    console.log(1);
                    if($(this).hasClass('spare_spare')){
                        var tabs_spare = $(this).closest('.tabs-spare');
                        search_type = tabs_spare.find('input.search_type').val();
                        search_description = tabs_spare.find('input.search_description').val();
                        search_brand = tabs_spare.find('input.search_brand').val();

                        $.each(tabs_spare.find('tr.spare_item'), function () {
                            var data = $(this).data('item');
                            if(search_type && data.type.indexOf(search_type) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_description && data.description.indexOf(search_description) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_brand && data.brand.indexOf(search_brand) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_type === '' && search_description === '' && search_brand === ''){
                                $(this).show();
                            }else{
                                $(this).find('input[type="checkbox"]').prop('checked',false);
                                $(this).hide();
                            }
                            self.updateCheckedCount();
                        });

                    }else if($(this).hasClass('spare_choose')){
                        var tabs_choose = $(this).closest('.tabs-choose');
                        search_type = tabs_choose.find('input.search_type').val();
                        search_description = tabs_choose.find('input.search_description').val();
                        search_brand = tabs_choose.find('input.search_brand').val();

                        $.each(tabs_choose.find('tr.spare_item'), function () {
                            var data = $(this).data('item');
                            if(search_type && data.type.indexOf(search_type) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_description && data.description.indexOf(search_description) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_brand && data.brand.indexOf(search_brand) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_type === '' && search_description === '' && search_brand === ''){
                                $(this).show();
                            }else{
                                $(this).find('input[type="checkbox"]').prop('checked',false);
                                $(this).hide();
                            }
                            self.updateCheckedCount();
                        });
                    }else if($(this).hasClass('spare_free')){
                        var tabs_free = $(this).closest('.tabs-free');
                        search_type = tabs_free.find('input.search_type').val();
                        search_description = tabs_free.find('input.search_description').val();
                        search_brand = tabs_free.find('input.search_brand').val();

                        $.each(tabs_choose.find('tr.spare_item'), function () {
                            var data = $(this).data('item');
                            if(search_type && data.type.indexOf(search_type) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_description && data.description.indexOf(search_description) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_brand && data.brand.indexOf(search_brand) >= 0){
                                $(this).show();
                                return;
                            }
                            if(search_type === '' && search_description === '' && search_brand === ''){
                                $(this).show();
                            }else{
                                $(this).hide();
                                $(this).find('input[type="checkbox"]').prop('checked',false);
                            }
                            self.updateCheckedCount();
                        });
                    }
                });
                spareRest.off('click').click(function () {
                    if($(this).hasClass('spare_spare')){
                        var tabs_spare = $(this).closest('.tabs-spare');
                        tabs_spare.find('input.search_type').val('');
                        tabs_spare.find('input.search_description').val('');
                        tabs_spare.find('input.search_brand').val('');
                        tabs_spare.find('tr.spare_item').show();
                    }else if($(this).hasClass('spare_choose')){
                        var tabs_choose = $(this).closest('.tabs-choose');
                        tabs_choose.find('input.search_type').val('');
                        tabs_choose.find('input.search_description').val('');
                        tabs_choose.find('input.search_brand').val('');
                        tabs_choose.find('tr.spare_item').show();
                    }else if($(this).hasClass('spare_free')){
                        var tabs_free = $(this).closest('.tabs-free');
                        tabs_free.find('input.search_type').val('');
                        tabs_free.find('input.search_description').val('');
                        tabs_free.find('input.search_brand').val('');
                        tabs_choose.find('tr.spare_item').show();
                    }
                });
            }
        }
    }();
</script>
</#compress>