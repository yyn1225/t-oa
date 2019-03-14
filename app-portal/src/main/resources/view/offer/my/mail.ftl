<form class="smart-form" id="mail_send_form">
    <fieldset>
        <#--<input type="hidden" value="${(fileId?c)!}" id="fileId">-->
        <#--<input type="hidden" value="${idList!}" id="fileIds">-->
            <input id="fileIds" type="hidden">
            <input id="language" type="hidden">
        <label class="col col-2 text-align-right padding">
            <span style="color:red;">*</span><dic data-dic="prompt.mail.to"></dic>
        </label>
        <label class="col col-8 input" id="money-unit">
            <input name="acceptMail" type="text" id="acceptMail"/>
        </label>
    </fieldset>
    <fieldset>
        <label class="col col-2 text-align-right padding">
            <span style="color:red;">*</span><dic data-dic="prompt.mail.subject"></dic>
        </label>
        <label class="col col-8 input" id="money-unit">
            <input name="mailSubject" type="text" id="mailSubject"/>
        </label>
    </fieldset>
    <#--<fieldset>-->
        <#--<label class="col col-2 text-align-right padding">-->
            <#--<span style="color:red;">*</span><dic data-dic="prompt.mail.validity"></dic>-->
        <#--</label>-->
        <#--<label class="col col-8 input" id="money-unit">-->
            <#--<input name="mailValidity" type="text" id="mailValidity"   /></dic>-->
        <#--</label>-->
    <#--</fieldset>-->
</form>
<script>

    $('#fileIds').data('ids', '${fileIds}');
    $('#language').data('language', '${language}');

    $('[data-dic]').each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    $(function () {
        //表单校验
        $('#mail_send_form').bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                mailSubject: {
                    group: ".input",
                    validators: {
                        notEmpty: {
                            message: message['prompt.message.null']
                        }
                    }
                },
                acceptMail: {
                    group: ".input",
                    validators: {
                        notEmpty: {
                            message: message['prompt.message.null']
                        },
                        emailAddress: {
                            message: message['prompt.message.email.format']
                        }
                    }
                }/*,
                mailValidity : {
                    group: ".input",
                    validators: {
                        notEmpty: {
                            message: message['prompt.message.null']
                        } ,
                        number: {
                            message: message['prompt.message.digital']
                        }
                    }
                }*/
            }
        })
    });
</script>