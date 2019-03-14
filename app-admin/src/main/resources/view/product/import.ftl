<#compress>
<section id="widget-grid" class="">
    <div class="row">
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="jarviswidget" data-widget-colorbutton="false" data-widget-editbutton="false"
                 data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false">
                <header>
                    <ul id="widget-tab-1" class="nav nav-tabs pull-left">
                        <li class="active">
                            <a data-toggle="tab" href="#hr1" class="tab-title">
                                <i class="fa fa-lg fa-gear"></i>
                                <span class="hidden-mobile hidden-tablet"><dic data-dic="importMsg.modual"></dic></span>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#hr2">
                                <i class="fa fa-lg fa-cube"></i>
                                <span class="hidden-mobile hidden-tablet"><dic data-dic="importMsg.box"></dic></span>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#hr3">
                                <i class="fa fa-lg fa-picture-o"></i>
                                <span class="hidden-mobile hidden-tablet"><dic data-dic="importMsg.major"></dic></span>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#hr4">
                                <i class="fa fa-lg fa-picture-o"></i>
                                <span class="hidden-mobile hidden-tablet"><dic data-dic="importMsg.spare"></dic></span>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#hr5">
                                <i class="fa fa-lg fa-picture-o"></i>
                                <span class="hidden-mobile hidden-tablet"><dic
                                        data-dic="importMsg.standard"></dic></span>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#hr6">
                                <i class="fa fa-lg fa-picture-o"></i>
                                <span class="hidden-mobile hidden-tablet"><dic
                                        data-dic="importMsg.select"></dic></span>
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#hr7">
                                <i class="fa fa-lg fa-picture-o"></i>
                                <span class="hidden-mobile hidden-tablet"><dic
                                        data-dic="importMsg.free"></dic></span>
                            </a>
                        </li>
                    </ul>
                </header>
                <div style="border-bottom: 1px solid #CCC !important;" ">
                    <div class="jarviswidget-editbox"></div>
                    <div class="widget-body no-padding">
                        <div class="tab-content padding-10">
                            <div class="tab-pane fade in active" id="hr1">
                                <form class="dropzone" data-url="/import/upload"
                                      id="modual_file_upload">
                                    <input type="hidden" name="attachmentId" class="attachment-id">
                                    <input type="hidden" class="submit_url" value="/import/module">
                                    <input type="hidden" class="download_url" value="/excel/download?type=1">
                                </form>
                                <div class="second"></div>
                            </div>
                            <div class="tab-pane fade" id="hr2">
                                <form class="dropzone" data-url="/import/upload" id="box_file_upload">
                                    <input type="hidden" name="attachmentId" class="attachment-id">
                                    <input type="hidden" class="submit_url" value="/import/box">
                                    <input type="hidden" class="download_url" value="/excel/download?type=2">
                                </form>
                                <div class="second"></div>
                            </div>
                            <div class="tab-pane fade" id="hr3">
                                <form class="dropzone" data-url="/import/upload" id="product_file_upload">
                                    <input type="hidden" name="attachmentId" class="attachment-id">
                                    <input type="hidden" class="submit_url" value="/import/product">
                                    <input type="hidden" class="download_url" value="/excel/download?type=3">
                                </form>
                                <div class="second"></div>
                            </div>
                            <div class="tab-pane fade" id="hr4">
                                <form class="dropzone" data-url="/import/upload" id="spare_file_upload">
                                    <input type="hidden" name="attachmentId" class="attachment-id">
                                    <input type="hidden" class="submit_url" value="/import/spare">
                                    <input type="hidden" class="download_url" value="/excel/download?type=4">
                                </form>
                                <div class="second"></div>
                            </div>
                            <div class="tab-pane fade" id="hr5">
                                <form class="dropzone" data-url="/import/upload">
                                    <input type="hidden" name="attachmentId" class="attachment-id">
                                    <input type="hidden" class="submit_url"
                                           value="/import/standard">
                                    <input type="hidden" class="download_url" value="/excel/download?type=5">
                                </form>
                                <div class="second"></div>
                            </div>
                            <div class="tab-pane fade" id="hr6">
                                <form class="dropzone" data-url="/import/upload">
                                    <input type="hidden" name="attachmentId" class="attachment-id">
                                    <input type="hidden" class="submit_url"
                                           value="/import/standard/select">
                                    <input type="hidden" class="download_url" value="/excel/download?type=5">
                                </form>
                                <div class="second"></div>
                            </div>
                            <div class="tab-pane fade" id="hr7">
                                <form class="dropzone" data-url="/import/upload">
                                    <input type="hidden" name="attachmentId" class="attachment-id">
                                    <input type="hidden" class="submit_url"
                                           value="/import/standard/free">
                                    <input type="hidden" class="download_url" value="/excel/download?type=5">
                                </form>
                                <div class="second"></div>
                            </div>
                        </div>
                        <div class="widget-footer">
                            <a class="btn btn-default btn-success" id="download_btn" type="button" href="/excel/download?type=1">
                                <dic data-dic="importMsg.excel.download"></dic>
                            </a>
                            <button class="btn btn-default btn-primary" type="button" id="import_btn">
                                <dic data-dic="button.submit"></dic>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </article>
    </div>
</section>
<script src="../../static/js/plugin/dropzone/dropzone.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("dic").each(function(){
           $(this).html(message[$(this).data("dic")]);
        });
        pageSetUp();
        Dropzone.autoDiscover = true;

        $("form.dropzone").each(function(){
            var url=$(this).data("url");
            var $this = $(this);
            $(this).dropzone({
                url: url,
                addRemoveLinks : true,
                maxFilesize: 50,
                maxFiles:1,
                dictDefaultMessage: '<span class="text-center"><span class="font-lg visible-xs-block visible-sm-block visible-lg-block"><span class="font-lg"><i class="fa fa-caret-right text-danger"></i>'+message["importMsg.chooseFile"]+'</span><span>&nbsp&nbsp<h4 class="display-inline"> ('+message["importMsg.clickFile"]+')</h4></span>',
                dictFallbackMessage:message["dropzone.dictFallbackMessage"],
                dictFileTooBig:message["dropzone.dictFileTooBig"],
                dictCancelUpload:message["dropzone.dictCancelUpload"],
                dictCancelUploadConfirmation:message["dropzone.dictCancelUploadConfirmation"],
                dictRemoveFile:message["dropzone.dictRemoveFile"],
                dictMaxFilesExceeded:message["dropzone.dictMaxFilesExceeded"],
                dictInvalidFileType:message["dropzone.dictInvalidFileType"],
                acceptedFiles: ".xlsx,.xls,.XLSX,.XLS",
                init: function() {
                    this.on("addedfile", function(file) {
                        console.log('addedfile',file);
                    });
                    this.on("success", function(file, data) {
                        console.log(data);
                        $this.find(".attachment-id").val(data.id);
                    });
                    this.on("complete", function(file) {
                        console.log('complete');
                    });
                }
            });
        });
        $("#import_btn").click(function () {
            var url = $(".tab-pane.active .submit_url").val();
            var attachmentId = $(".tab-pane.active .attachment-id").val();
            if (attachmentId === '') {
                attachmentId = 0;
            }
            if (attachmentId === 0) {
                alert(message['importMsg.excel.todo'], 2);
                return;
            }
            showLoading();
            $.ajax({
                url:url,
                type:'post',
                data: {attachmentId: attachmentId},
                success: function (data) {
                    hideLoading();
                    if (jQuery.isEmptyObject(data)) {
                        alert(message["alert.message.success"]);
                    }else {
                        errorPage(data);
                    }
                },
                error: function (data) {
                    if (data.responseJSON.code === 20015) {
                        alert(message['import.error.excelIsInvalid'], 3);
                    }else if (data.responseJSON.code === 20016){
                        alert(message['import.error.excelIsNull'], 3);
                    }else {
                        alert(message['import.error.systemError'], 3);
                    }
                    hideLoading();
                }
            });
        });

        //页面展示之后执行的函数
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            showLoading();
//            e.target // 激活的标签页
//            e.relatedTarget // 前一个激活的标签页
            $("#download_btn").attr("href", $(".tab-pane.active.in .download_url").val());
            var $second = $(".tab-pane.active.in .second");
            if ($second.html() !== '' && !($second.is(":hidden"))) {
                $(".widget-footer").hide();
            }else {
                $(".widget-footer").show();
            }
            hideLoading();
        });

        $('section').on('click', '.tab-pane.active header .continue', function () {
            showLoading();
            $(".tab-pane.active .second").hide();
            $(".tab-pane.active .dropzone").show();
            $(".widget-footer").show();
            hideLoading();
        });
    });
    
    function errorPage(data) {
        var $second = $(".tab-pane.active .second");
        var $form = $(".tab-pane.active .dropzone");
        $.ajax({
            url: '/import/error',
            type: 'get',
            data: {},
            success: function (d) {
                $second.html(d);
                $second.show();
                $form.hide();
                $(".widget-footer").hide();
                var lastKey = 0;
                $.each(data, function (index, item) {
                    if (isNaN(item)) {
                        $(".tab-pane.active #errorTable").append(
                                '<tr><td>'+ index +'</td><td>'+ item + '</td></tr>'
                        );
                    }
                    lastKey = index;
                });
                var msg = message['importMsg.excel.total'] + (lastKey - 3) + message['importMsg.excel.row'] +
                         message['importMsg.excel.success'] + data[lastKey] + message['importMsg.excel.row'] + ',' +
                         message['importMsg.excel.fail'] + (Object.keys(data).length - 1) +
                        message['importMsg.excel.row'];
                $(".tab-pane.active header h2").html(msg);
                var attachmentId = $('.tab-pane.active .attachment-id').val();
                $('.tab-pane.active header .look').attr('href', '/download?attachmentId=' + attachmentId);
            }
        });
    }
</script>
</#compress>