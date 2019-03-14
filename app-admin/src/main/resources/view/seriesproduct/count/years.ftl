<#compress>
<form id="yearForm" class="smart-form">
    <input type="hidden" id="standardId" name="standardId"
           value="${(standard.id)!}"/>
    <input type="hidden" id="standardType" name="standardId"
           value="${(standard.type)!}"/>
    <fieldset>
        <label class="col col-2 text-align-right"
               style="padding-top: 7px;">
            <dic data-dic="standard.count.form.seriesNo"></dic>
            :
        </label>
        <label class="col col-10 input">
            <input type="text" name="name" readonly="readonly"
                   value="${(series.name)!}"/>
        </label>
    </fieldset>
    <fieldset>
        <label class="col col-2 text-align-right"
               style="padding-top: 7px;">
            <dic data-dic="standard.count.form.auto"></dic>
            :
        </label>
        <label class="col col-10 input">
            <select class="select2" name="auto" id="auto" disabled="">
                <option value="0"
                        <#if standard.standard??&&standard.standard==0>selected="selected"</#if>
                        data-dic="select.option.no">
                </option>
                <option value="1"
                        <#if standard.standard??&&standard.standard==1>selected="selected"</#if>
                        data-dic="select.option.yes">
                </option>
            </select>
        </label>
    </fieldset>
    <ul class="nav nav-tabs bordered" id="year_tab">
        <#list years as item>
            <li <#if item_index==0>class="active"</#if> id="t_${(item_index)}">
                <a href="#s${item_index}" data-toggle="tab">${(item.year)!}
                    <dic data-dic="standard.count.form.year"></dic>
                </a>
            </li>
        </#list>
    </ul>
    <div class="tab-content padding-10">
        <#list years as item>
            <div class="tab-pane fade in <#if item_index==0>active</#if>" id="s${item_index}">
                <input value="${(item.year)!}" type="hidden" name="year">
                <fieldset>
                    <label class="col col-2 text-align-right"
                           style="padding-top: 7px;">
                        <span style="color: red">*</span>
                        <dic data-dic="standard.count.form.count"></dic>:
                    </label>
                    <label class="col col-10 input">
                        <input type="number" name="counts" readonly
                               value="${(item.counts)!}"/>
                    </label>
                </fieldset>
                <fieldset>
                    <label class="col col-2 text-align-right padding">
                        <span style="color: red">*</span>
                        <dic data-dic="standard.count.form.unit"></dic>:
                    </label>
                    <label class="col col-10 input">
                        <input class="unit-type" name="unitType" style="width: 100%;" readonly
                               id="unit_${(item_index)}"
                               data-year="${(item.year)!}" data-unit="${(item.unitType)!}">
                    </label>
                </fieldset>

                <fieldset id="spel_input_${(item.year)!}"
                    <#if item.unitType??&&item.unitType==5><#else>
                          style="display: none;"</#if>>
                    <label class="col col-2 text-align-right"
                           style="padding-top: 7px;">
                        <span style="color: red">*</span>
                        <dic data-dic="standard.count.form.spel"></dic>:
                    </label>
                    <label class="col col-10 input">
                        <input type="text" name="spel" readonly
                               value="${(item.spel)!}"/>
                    </label>
                </fieldset>
            </div>

        </#list>
    </div>
</form>
<script type="text/javascript">
    $('[data-dic]').each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    pageSetUp();
    var datalist = [{id: 1, text: "每单"}, {id: 2, text: "每平米"}, {id: 3, text: "每个箱体"}, {
        id: 4,
        text: "每个模组"
    }, {id: 5, text: "自定义"}];
    $('.unit-type').each(function (index, val) {
        var unitType = $(val).data('unit');
        $(val).select2({
            data: datalist,
            minimumResultsForSearch: -1
        }).val(unitType).trigger('change');
    });

    $('.unit-type').on('change', function () {
        var year = $(this).data("year");
        var select = $(this).select2("val");
        if (select == '5') {
            $('#spel_input_' + year).show();
        } else {
            $('#spel_input_' + year).hide();
        }
    });
    var year = {
        submit: function () {
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                var years = [];
                var standardId = $('#standardId').val();
                for (var i = 0; i < 4; i++) {
                    var year = {};
                    var input = $('#s' + i + ' input');
                    var unitType = $('#s' + i + ' fieldset').find('.unit-type').select2('val');
                    if ((!unitType || unitType == '')) {
                        $('#year_tab #t_' + i + ' a').tab('show');
                        var tipHtml = '<small class="help-block" ' +
                                'style="color: #b94a48;"' +
                                ' data-bv-validator="notEmpty" ' +
                                'data-bv-for="counts" data-bv-result="INVALID" ' +
                                'style="">'+message["measurement.units.must.be.filled"]+'</small>';
                        $($('#s' + i + ' fieldset').find('.unit-type')[0]).find('small').empty();
                        $($('#s' + i + ' fieldset').find('.unit-type')[0]).append(tipHtml);
                        return false;
                    } else {
                        year.unitType = unitType;
                        year.counts = $('#s' + i + ' input[name="counts"]').val();
                        year.spel = $('#s' + i + ' input[name="spel"]').val();
                        year.year = $('#s' + i + ' input[name="year"]').val();
                        year.type = $('#standardType').val();
                        $($('#s' + i + ' fieldset').find('.unit-type')[0]).find('small').empty();
                        years.push(year);
                    }
                }
                $.ajax({
                    url: '/api/series/count/year/save',
                    data: {
                        standardId: standardId,
                        yearsJsonStr: JSON.stringify(years),
                        auto: $('#auto').select2('val')
                    },
                    type: 'POST',
                    success: function (data) {
                        alert(message["alert.message.success"]);
                        var $model = $('#edit_count_model');
                        $model.modal('hide');
                        standardTable.submitFilter();
                    }, error: function () {
                        alert(message["operation.failed"]);
                    }
                });
            }
        }
    };
    var form = $('#yearForm');
    //表单校验
    form.bootstrapValidator({
        fields: {
            auto: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["is.it.automatic"]
                    }
                }
            },
            counts: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["quantity.required"]
                    },
                    number: {
                        message:message["validate.message.number"]
                    }
                }
            },
            spel: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["expression.must.be.filled"]
                    }
                }
            }
        }
    });
    var bootstrapValidator = form.data('bootstrapValidator');
</script>
</#compress>