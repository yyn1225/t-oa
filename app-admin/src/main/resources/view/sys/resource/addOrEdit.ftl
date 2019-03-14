<#compress>
<article>
    <div class="jarviswidget" data-widget-editbutton="false">
        <header>
        </header>
        <div>
            <div class="widget-body no-padding ">
                <form class="smart-form" id="addOrEdit-form">
                    <fieldset>
                        <input type="hidden" id="id" name="id" value="${(id)!}"/>
                        <input type="hidden" name="langVal" id="lang_val"/>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="prompt.input.name"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="resource-name" name="name" class="form-control" data-zh="${(resource.name)!}"
                                   data-en="${(langMap["nameLang"]["en"])!}" data-category="resource_name" value="${(resource.name)!}">
                        </label>


                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.code"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="code" name="code" value="${(resource.code)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <dic data-dic="prompt.input.parent"></dic>
                        </label>
                        <label class="col col-3 input">
                            <select class="select2" style="width: 100%" name="parent" id="parent">
                                <option value="0"> <dic data-dic="nothing"></dic></option>
                                <#list resourceList as item>
                                    <option value="${(item.id)!}"
                                            <#if resource.parent??&& item.id == resource.parent >selected</#if>
                                            <#if resource.id??&&item.id == resource.id>disabled</#if>
                                    >${(item.name)!}</option>
                                </#list>
                            </select>
                        </label>
                    </fieldset>
                    <fieldset>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.icon"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="icon" name="icon" value="${(resource.icon)!}"/>
                        </label>
                        <label class="col col-1 text-align-right padding">
                            <span style="color: red">*</span>
                            <dic data-dic="prompt.input.url"></dic>
                        </label>
                        <label class="col col-3 input">
                            <input type="text" id="url" name="url" value="${(resource.url)!}"/>
                        </label>

                    </fieldset>
                    <div class="widget-footer">
                        <div class=" text-align-right">
                            <a class="btn btn-primary btn-sm" id="ok"
                               style="margin-right: 8px">
                                <dic data-dic="body.list.submit"></dic>
                            </a>
                            <a class="btn btn-default btn-sm" onclick="back()"
                               style="margin-right: 38px">
                                <dic data-dic="body.list.back"></dic>
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

</article>
<script type="text/javascript">
    pageSetUp();
    var $form = $('#addOrEdit-form');
    var $article = $('article');

    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    $("#myModal").modal({
        backdrop: 'static',
        keyboard: false,
        show: false
    });
    $("#ok").on("click", function () {
        //获取表单对象
        var bootstrapValidator = $form.data('bootstrapValidator');
        //手动触发验证
        bootstrapValidator.validate();
        if (bootstrapValidator.isValid()) {
            showLoading();
            var langs = [];
            $.each(lang, function(index,item){
                langs.push({
                    language:item,
                    nameLang:$("#resource-name").data(item)
                });
            });
            $.ajax({
                url: '/resource/rest/save',
                data: $('form').serialize() + '&lang=' + JSON.stringify(langs),
                type: 'POST',
                success: function (data) {
                    resourceTable.resetFilter();
                    $('#second').hide();
                    $('#first').show();
                    hideLoading();
                },
                error: function (data) {
                    if (data.responseJSON.message === "20001") {
                        alert(message['alert.message.encodedExist'], 3);
                    } else {
                        alert(message['alert.message.systemError'], 3);
                    }
                    hideLoading();
                }
            });
        }
    });
    function back() {
        $('#second').hide();
        $('#second').html('');
        $('#first').show();
    }
    var id = $('#id').val();
    if (!id) {
        id = 0;
    }
    //表单校验
    $form.bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    },
                    stringLength: {
                        max: 20,
                        message: message['prompt.message.name.length']
                    }
                }
            },
            code: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    },
                    stringLength: {
                        max: 20,
                        message: message['prompt.message.code.length']
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: message['prompt.message.code.format']
                    }
                }
            },
            icon: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },

            url: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            },
            parent: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: message["prompt.message.null"]
                    }
                }
            }

        }
    });
    bindLanguages($("#resource-name"));

</script>
</#compress>