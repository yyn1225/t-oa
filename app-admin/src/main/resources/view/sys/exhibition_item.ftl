<#compress>
<div class="row">
<div style="height:150px;overflow-y: auto;">
    <form class="dropzone" style="min-height: 150px !important;" id="box_file_upload" data-url="/import/upload">
        <input type="hidden" name="attachmentId" class="attachment-id">
    </form>
</div>
</div>
    <form class="smart-form" id="exhibition-form">
        <fieldset>
            <label class="col col-2 text-align-right padding">
                <dic data-dic="window.exhibition.url"></dic>:</label>
            <label class="col col-4 input">
                <input type="text" id="img-url"/>
            </label>
            <label class="col col-2 text-align-right padding">
                <span style="color:red;">*</span>
                <dic data-dic="window.exhibition.sort"></dic>:
            </label>
            <label class="col col-4 input">
                <input type="text" class="spinners" id="orders" name="orders"/>
            </label>
        </fieldset>
        <fieldset>
            <label class="col col-2 text-align-right padding">
                应用于:
            </label>
            <label class="col col-4 input">
                <select class="select2" id="type">
                    <option value="2">WEB端</option>
                    <option value="1">移动端</option>
                </select>
            </label>
        </fieldset>
    </form>
<script src="../../static/js/plugin/dropzone/dropzone.min.js"></script>
<script>
    pageSetUp();
    $("[data-dic]").each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
    Dropzone.autoDiscover = true;
    $(".spinners").spinner({
        step: 1,
        min: 1,
        max: 9,
        textFormat: "n"
    });
    $("form.dropzone").each(function(){
        var url=$(this).data("url");
        var $this = $(this);
        $(this).dropzone({
            url: url,
            addRemoveLinks : true,
            maxFilesize: 50,
            maxFiles:1,
            dictDefaultMessage: '<span class="text-center"><span class="font-lg visible-xs-block visible-sm-block visible-lg-block"><span class="font-lg"><i class="fa fa-caret-right text-danger"></i>'+message["importMsg.chooseImg"]+'</span><span>&nbsp&nbsp<h4 class="display-inline"> ('+message["importMsg.clickFile"]+')</h4></span>',
            dictFallbackMessage:message["dropzone.dictFallbackMessage"],
            dictFileTooBig:message["dropzone.dictFileTooBig"],
            dictCancelUpload:message["dropzone.dictCancelUpload"],
            dictCancelUploadConfirmation:message["dropzone.dictCancelUploadConfirmation"],
            dictRemoveFile:message["dropzone.dictRemoveFile"],
            dictMaxFilesExceeded:message["dropzone.dictMaxFilesExceeded"],
            dictInvalidFileType:message["dropzone.dictInvalidFileType"],
            acceptedFiles: ".png,.jpg,.jpeg,.gif",
            init: function() {
                this.on("addedfile", function(file) {
                    console.log('addedfile',file);
                });
                this.on("success", function(file, data) {
                    $(".attachment-id").val(data.id);
                });
                this.on("complete", function(file) {
                    console.log('complete');
                });
            }
        });
    });
    $("#exhibition-form").bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            orders: {
                group: ".col.col-4",
                validators: {
                    notEmpty: {
                        message: message['validate.message.notEmpty']
                    },
                    digits: {
                        message: message["window.exhibition.sortNumber"]
                    },
                    callback: {
                        callback: function (value, validator, $field) {
                            if (!isNaN(value) && value !== '') {
                                if (value < 1 || value > 9) {
                                    return {
                                        valid: false,
                                        message: message["window.exhibition.sortNumber"]
                                    }
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
    });
</script>
</#compress>