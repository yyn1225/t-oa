<#compress>
    <div class="modal fade" id="dialog_table" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <input type="hidden" value="0" id="spare-type">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 data-dic="window.role.maintain"></h4>
                </div>
                <div class="modal-body no-padding">
                    <form class="smart-form" id="price-form">
                        <input type="hidden" name="langVal" id="lang_val"/>
                        <input type="hidden" name="id" value="${(roleId)!}">
                        <fieldset>
                            <label class="col col-2 text-align-right padding">
                                <span style="color: red">*</span>
                                <dic data-dic="org.role.list.name"></dic>:
                            </label>
                            <label class="col col-8 input">
                                <input type="text" name="name" value="${(role.name)!}">
                            </label>
                        </fieldset>

                        <footer>
                            <button type="button" id="submit_price" class="btn btn-primary"><dic data-dic="button.submit"></dic></button>
                            <button type="button" id="cancel" class="btn btn-default" data-dismiss="modal">
                                <dic data-dic="button.cancel"></dic>
                            </button>
                        </footer>
                    </form>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
    pageSetUp();
    $(document).ready(function() {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        $form = $("#price-form");
        $form.bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    group: ".col.col-8",
                    validators: {
                        notEmpty: {
                            message: message["message.error.ameCantEmpty"]
                        }
                    }
                }
            }
        });

        $("#second").on('click','#submit_price',function () {
            //获取表单对象
            var bootstrapValidator = $form.data('bootstrapValidator');
            //手动触发验证
            bootstrapValidator.validate();
            if(bootstrapValidator.isValid()) {
                $("#dialog_table").modal("hide");
                $.ajax({
                    url: '/role/save',
                    type: 'post',
                    data: $("#price-form").serialize(),
                    success: function (data) {
                        dt.resetFilter();
                    }
                });
            }
        });
    });
</script>
</#compress>