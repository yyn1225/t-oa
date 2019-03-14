<#compress>
<article>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
        </header>
        <div>
            <div class="widget-body no-padding ">
                <form class="smart-form" id="customer-form">
                    <fieldset>
                        <input type="hidden" name="id" id="id" value="${(customer.id)!}"/>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red;
">*</span>
                            <dic data-dic="customer.item.name"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="name" class="form-control" value="${(customer.name)!}">
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.website"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="website" value="${(customer.website)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.tickerSymbol"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="tickerSymbol" class="form-control" value="${(customer.tickerSymbol)!}">
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.staffSize"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="number" name="employees" value="${(customer.employees)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.ownership"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="ownership" class="form-control"
                                   value="${(customer.ownership)!}">
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.type"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <select class="select2" style="width: 100%" name="type">
                                <#list industry as item>
                                    <option value="${(item.name)!}"
                                            <#if customer.type??&&customer.type==item.name>selected</#if>>${(item.name)
                                    !}</option>
                                </#list>
                            </select>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.accountType"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <select class="select2" style="width: 100%" name="accountType">
                                <#list accoutTypes as item>
                                    <option value="${(item.name)!}"
                                            <#if customer.accountType??&&customer.accountType==item.name>selected</#if>>${(item.name)
                                    !}</option>
                                </#list>
                            </select>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.accountNumber"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="accountNumber" value="${(customer.accountNumber)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.location"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="location" class="form-control" value="${(customer.location)!}">
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.phone"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="number" name="phone" value="${(customer.phone)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.fax"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="fax" class="form-control" value="${(customer.fax)!}">
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.email"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="email" value="${(customer.email)!}"/>
                        </label>
                    </fieldset>
                    <fieldset>
                        <#--<label class="col col-1 text-align-right padding">-->
                            <#--<dic data-dic="customer.item.rating"></dic>:-->
                        <#--</label>-->
                        <#--<label class="col col-3 input">-->
                            <#--<select class="select2" style="width: 100%" name="rating">-->
                                <#--<#list levels as item>-->
                                    <#--<option value="${(item.id)!}" <#if customer-->
                                    <#--.rating??&&customer.rating==item.id>selected</#if>>${(item.name)-->
                                    <#--!}</option>-->
                                <#--</#list>-->
                            <#--</select>-->
                        <#--</label>-->
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.revenue"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="number" name="revenue" value="${(customer.revenue)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.sicCode"></dic>:
                        </label>
                        <label class="col col-3 input">
                            <input type="text" name="sicCode" class="form-control" value="${(customer.sicCode)!}">
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.accountAddress"></dic>:
                        </label>
                        <label class="col col-5 input">
                            <input type="hidden" name="billing" class="form-control" value="${(customer.billing)!}">
                            <input type="text" name="billAddress" class="form-control" value="${(billAddress)!}">
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.Address"></dic>:
                        </label>
                        <label class="col col-5 input">
                            <input type="hidden" name="shipping" class="form-control"
                                   value="${(customer.shipping)!}">
                            <input type="text" name="shipAddress" class="form-control"
                                   value="${(shipAddress)!}">
                        </label>
                    </fieldset>
                    <fieldset style="height: 90px;">
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="customer.item.description"></dic>:
                        </label>
                        <label class="col col-md-11 input">
                            <textarea style="width: 100%;resize: none;padding-left: 3px;
                            padding-right: 3px;" rows="4"
                                      name="description">${(customer.description)!}</textarea>
                        </label>
                    </fieldset>
                    <div class="widget-footer">
                        <div class=" text-align-right">
                            <a class="btn btn-primary btn-sm customer-form-submit"
                               style="margin-right: 8px"><dic data-dic="button.submit"></dic></a>
                            <a class="btn btn-default btn-sm customer-back"
                               style="margin-right: 38px"><dic data-dic="button.back"></dic></a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</article>
<script type="text/javascript">
    pageSetUp();
    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    var el = {
        customerForm: '#customer-form'
    };
    var form = $(el.customerForm);
    //表单校验
    form.bootstrapValidator({
        fields: {
            name: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            website: {
                group: ".input",
                validators: {
                    uri: {
                        message: message["prompt.message.formatWebsite"]
                    }
                }
            },
            email: {
                group: ".input",
                validators: {
                    emailAddress: {
                        message: message["prompt.message.formatEmail"]
                    }
                }
            }

        }
    });
    var bootstrapValidator = form.data('bootstrapValidator');
    $('.customer-form-submit').on('click', function () {
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            $.ajax({
                url: '/api/customer/save',
                type: 'POST',
                data: form.serialize(),
                success: function () {
                    alert(message["http.response.success"]);
                    $first.show();
                    $second.hide();
                    dt.submitFilter();
                },
                error: function () {
                    alert(message["http.response.error"]);
                }
            })
        }
    });
    $('.customer-back').on('click', function () {
        $first.show();
        $second.hide();
    });
</script>
<style>
    #customer-form fieldset {
        height: auto; !important;
    }
</style>
</#compress>