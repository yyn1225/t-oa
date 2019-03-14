<#compress>
<article>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
        </header>
        <div>
            <div class="widget-body no-padding ">
                <form class="smart-form addOrEdit-form" id="addOrEdit-form">

                    <fieldset>
                        <input type="hidden" id="spareId" name="id" value="${(id?c)!}"/>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="product.spare.list.material"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="material" name="material" value="${(spare.material)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.spare.list.type"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="type" name="type" value="${(spare.type)!}"
                            data-zh="${(spare.type)!}" data-en="${(spareLang["en"].typeLang)!}" data-category="spare_type"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.spare.list.brand"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="brand" name="brand" value="${(spare.brand)!}"
                            data-zh="${(spare.brand)!}" data-en="${(spareLang["en"].brandLang)!}" data-category="spare_brand"/>
                        </label>
                    </fieldset>

                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.spare.list.model"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="model" name="model" value="${(spare.model)!}"
                            data-zh="${(spare.model)!}" data-en="${(spareLang["en"].modelLang)!}" data-category="spare_model"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.spare.list.unit"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="unit" name="unit" value="${(spare.unit)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.spare.list.executeTime"></dic>:
                        </label>
                        <label class="col col-3 input-group">
                            <input type="text" name="execute" id="execute" class="form-control datepicker"
                                   data-dateformat="yy-mm-dd" value="${(spare.executeTime?string("yyyy-MM-dd"))!}">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                        </label>
                    </fieldset>

                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.spare.list.classify"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <select class="select2" name="classify" id="classify">
                                <#if (spare.classify)??>
                                    <#if spare.classify == 1>
                                        <option value="1" selected data-dic="product.spare.list.spares"></option>
                                        <option value="2" data-dic="product.spare.list.parts"></option>
                                    </#if>
                                    <#if spare.classify == 2>
                                        <option value="1" data-dic="product.spare.list.spares"></option>
                                        <option value="2" selected data-dic="product.spare.list.parts"></option>
                                    </#if>
                                <#else>
                                    <option value="1" data-dic="product.spare.list.spares"></option>
                                    <option value="2" data-dic="product.spare.list.parts"></option>
                                </#if>
                            </select>
                        </label>
                    </fieldset>

                    <fieldset style="height: 75px;">
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="product.spare.list.description"></dic>:
                        </label>
                        <label class="col textarea" style="width: 91.66%">
                        <textarea rows="3" class="custom-scroll" name="description">
                        ${(spare.description)!}
                        </textarea>
                        </label>
                    </fieldset>

                    <#--<fieldset style="border-top: 1px solid rgba(0,0,0,.1) !important;">-->
                        <#--<input type="hidden" name="priceId" value="${(price.id?c)!}">-->
                        <#--<label class="col col-1 text-align-right padding">-->
                            <#--<dic data-dic="product.panel.price.basePrice"></dic>:-->
                        <#--</label>-->
                        <#--<label class="col col-3 input">-->
                            <#--<input type="text" class="spinners" name="basePrice" value="${(price.price?c)!}"/>-->
                        <#--</label>-->

                        <#--<label class="col col-1 text-align-right padding">-->
                            <#--<dic data-dic="product.panel.price.salePrice"></dic>:-->
                        <#--</label>-->
                        <#--<label class="col col-3 input">-->
                            <#--<input type="text" class="spinners" name="salePrice" value="${(price.salePrice?c)!}"/>-->
                        <#--</label>-->
                        <#--<label class="col col-1 text-align-right padding">-->
                            <#--<dic data-dic="product.panel.price.unit"></dic>:-->
                        <#--</label>-->
                        <#--<label class="col col-3 input">-->
                            <#--<select class="select2" name="priceUnit" id="priceUnit">-->
                                <#--<#if (price.unit)??>-->
                                    <#--<option value="CNY" data-key="￥">CNY(￥)</option>-->
                                        <#--<#list rates as rate>-->
                                            <#--<option value="${rate.code}" data-key="${rate.currency}"-->
                                                <#--<#if rate.code == price.unit>selected</#if>>${rate.code!}(${rate.currency})-->
                                            <#--</option>-->
                                        <#--</#list>-->
                                <#--<#else>-->
                                    <#--<#if rates?? && (rates?size > 0)>-->
                                        <#--<option value="CNY" data-key="￥">CNY(￥)</option>-->
                                        <#--<#list rates as rate>-->
                                            <#--<option value="${rate.code}" data-key="${rate.currency}">${rate.code!}(${rate.currency})-->
                                            <#--</option>-->
                                        <#--</#list>-->
                                    <#--</#if>-->
                                <#--</#if>-->
                            <#--</select>-->
                        <#--</label>-->
                    <#--</fieldset>-->

                    <div class="widget-footer">
                        <div class=" text-align-right">
                            <a class="btn btn-primary btn-sm" id="ok" style="margin-right: 8px" data-dic="body.list.submit"></a>
                            <a class="btn btn-default btn-sm" onclick="back()" style="margin-right: 38px" data-dic="body.list.back"></a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</article>
<script type="text/javascript">
    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    $(".spinners").spinner({
        step: 10,
        textFormat: "n"
    });
    $(function () {
        var spareId = parseInt($("#spareId").val());
        if (spareId !== 0) {
            $("#sparePrice").hide();
        }
    });
    var $form = $('#addOrEdit-form');
    $("#ok").on("click", function () {
        //获取表单对象
        var bootstrapValidator = $form.data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            var langs = [];
            $.each(lang, function(index,item){
                langs.push({
                    lang: item,
                    brandLang: $("#brand").data(item),
                    modelLang: $("#model").data(item),
                    typeLang: $("#type").data(item)
                });
            });
            showLoading();
            $.ajax({
                url: '/spare/save',
                data: $('form').serialize() + '&lang=' + JSON.stringify(langs),
                type: 'POST',
                success: function (data) {
                    spareTable.resetFilter();
                    $('#second').hide();
                    $('#first').show();
                    alert(message["alert.message.success"]);
                    hideLoading();
                },
                error: function (data) {
                    if (data.responseJSON.message === "20001") {
                        alert(message["alert.message.codeExist"], 3);
                    }else {
                        alert(message["alert.message.systemError"], 3);
                    }
                    hideLoading();
                }
            });
        }
    });
    function back() {
        $('#second').hide();
        $('#first').show();
    }
    $(function () {
        $('#executeTime').datepicker({
            dateFormat: 'yy-mm-dd'
        });
    });
    $form.bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            material: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            brand: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            model: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            type: {
                group: ".col.col-3",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            }
        }
    });
    bindLanguages($("#brand"));
    bindLanguages($("#model"));
    bindLanguages($("#type"));
    pageSetUp();
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
</style>
</#compress>