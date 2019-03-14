<#compress>
<form class="smart-form" id="folder-item-form">
    <fieldset>
        <input type="hidden" name="id" id="id" value="${(filePackage.id)!}"/>
        <input type="hidden" name="parent" id="parent" value="${(filePackage.parent)!}"/>
        <label class="col col-3 text-align-right" style="padding-top: 7px;">
            <dic data-dic="folder.item.name"></dic>
        </label>
        <label class="col col-8 input">
            <div class="input-group input-group-sm" style="width:100%">
                <input type="text" style="width:95%" name="name" class="form-control" value="${(filePackage.name)!}">
            </div>
        </label>
    </fieldset>
    <fieldset class="folder-fieldset">
        <label class="col col-3 text-align-right" style="padding-top: 7px;">
            <dic data-dic="folder.item.icon"></dic>
        </label>
        <label class="col col-8 input marginbottom8">
            <input type="hidden" name="icon" value="<#if filePackage.icon??>${(filePackage.icon)!}<#else>glyphicon glyphicon-align-center</#if>"/>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-align-center"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-align-justify"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-align-left"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-align-right"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-asterisk"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-backward"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-ban-circle"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-book"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-bookmark"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-briefcase"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-bullhorn"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-camera"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-certificate"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-check"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-pushpin"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-folder-open"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-folder-close"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-edit"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-facetime-video"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-file"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-fire"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-flag"></i>
            </a>
            <a class="btn-sm btn btn-default">
                <i class="glyphicon glyphicon-floppy-disk"></i>
            </a>
        </label>
    </fieldset>
</form>
<script type="text/javascript">
    pageSetUp();

    $(document).ready(function(){
        var className=$("[name='icon']").val();
        $("#folder-item-form a.btn-default i."+className.replace(" ",".")).parent().removeClass("btn-default").addClass("btn-warning");
    });

    el.iconShow = '#icon-show';
    el.folderItemForm = '#folder-item-form';
    url.folderSave = '/api/folder/save';
    var id = $('#id').val();
    if (!id) {
        id = 0;
    }
    var form = $(el.folderItemForm);
    //表单校验
    form.bootstrapValidator({
        fields: {
            name: {
                group: ".input",
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    },
                    stringLength: {
                        max: 20,
                        message: '名称不能过长'
                    }
                }
            }
        }
    });
    var bootstrapValidator = form.data('bootstrapValidator');
    var item = {
        _close: function () {
            $(el.list).show();
            $(el.edit).html('');
            $(el.edit).hide();
        },
        _submitForm: function () {
            bootstrapValidator.validate();
            if (bootstrapValidator.isValid()) {
                $(el.itemModal).modal('hide');
                $.ajax({
                    url: url.folderSave,
                    data: $(el.folderItemForm).serialize(),
                    type: 'POST',
                    success: function () {
                        folder._loadFolders();
                    }
                });
            }
        }
    };
    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });

    $("#folder-item-form a.btn-sm").off("click").click(function(){
        $("a.btn-warning").removeClass("btn-warning").addClass("btn-default");
        $('input[name="icon"]').val($(this).find("i:first").attr("class"));
        $(this).removeClass("btn-default").addClass("btn-warning");
    });

</script>
<style>
    .marginbottom8 a{
        margin-bottom:8px;
    }
    .folder-fieldset {
        height: auto !important;
    }
</style>
</#compress>