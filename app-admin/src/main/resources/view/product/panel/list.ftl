<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <h2>
                    <dic data-dic="header.list.search"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <form class="smart-form">
                        <fieldset style="height: 100px !important;">
                            <div class="row">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="panel.list.search.scn"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <input type="text" id="partNo-query"/>
                                </label>

                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.configuration"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="configuration-query">
                                        <option value=""></option>
                                        <#list (configurationList)! as item>
                                            <option value="${(item.id)!}">${(item.remark)!}</option>
                                        </#list>
                                    </select>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.certification"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="certification-query">
                                        <option value=""></option>
                                        <#list (certificationList)! as item>
                                            <option value="${(item.code)!}">${(item.name)!}</option>
                                        </#list>
                                    </select>
                                </label>

                            </div>
                            <div class="row" style="margin-top: 15px">
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="panel.list.series"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" multiple id="series-query">
                                        <option value=""></option>
                                        <#list (series)! as item>
                                            <optgroup label="${(item.text)!}">
                                                <#list item.children as child>
                                                    <option value="${(child.id)!}">${(child.text)!}</option>
                                                </#list>
                                            </optgroup>
                                        </#list>
                                    </select>
                                </label>
                                <label class="col col-1 text-align-right padding">
                                    <dic data-dic="product.panel.list.status"></dic>
                                    :
                                </label>
                                <label class="col col-3 input">
                                    <select class="select2" style="width: 100%" id="status-query">
                                        <option value=""></option>
                                        <#list (productStatus)! as item>
                                            <option value="${(item.code)!}">${(item.name)!}</option>
                                        </#list>
                                    </select>
                                </label>
                                <label class="col col-4 text-align-right">
                                    <a class="btn btn-primary query-btn" id="btn_query">
                                        <dic data-dic="header.list.button.search"></dic>
                                    </a>
                                </label>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>

        </div>
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <span class="widget-icon">
                    <i class="fa fa-table"></i>
                </span>
                <h2>
                    <dic data-dic="panel.list.header"></dic>
                </h2>
                <h2>
                    <dic dic="header.list.button.add"></dic>
                </h2>
                <a class="btn btn-primary btn-xs operate" style="float: right" data-product-id="0">
                    <dic data-dic="header.list.button.add"></dic>
                </a>
                <a id="common_export" class="btn btn-primary btn-xs" style="float: right"
                   data-dic="common.export"></a>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_product" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>
            <input type="hidden" id="area" value="${(area)!}">

        </div>
    </div>
    <div id="second" style="display: none">123</div>
    <div id="four"></div>
</article>


<script type="text/javascript">
    var productTable;
    $(".select2").each(function () {
        $(this).attr('data-placeholder',message["select2.placeholder.msg"]);
    });
    pageSetUp();
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });
        var area = $("#area").val();
        productTable = new Datatables();
        productTable.init({
            src: $("#dt_basic_product"),
            loadingMessage: message["datatable.loading"],
            scrollX: true,
            dataTable: {
                bStateSave: false,
                ajax: {url: "panel/rest/list?area=" + area},
                columns: [
                    DTColumnKit.order,
                    {
                        data: "parentSeriesName",
                        title: message["product.panel.list.series"],
                        width: "60px",
                        'orderable': false
                    },
                    {
                        data: "productSeries",
                        title: message["product.panel.list.name"],
                        width: "100px",
                        'orderable': false
                    },
                    {
                        data: "partNo",
                        title: message["product.panel.list.partNo"],
                        'orderable': false
                    },
                    {
                        data: "productConfiguration",
                        title: message["product.panel.list.configuration"],
                        width: "120px",
                        'orderable': false
                    },
                    {
                        data: "certification",
                        title: message["product.panel.list.certification"],
                        width: "120px",
                        'orderable': false,
                        render: function (data, type, row, meta) {
                            return data.replace(",","+");
                        }
                    },
//                    {
//                        data: "state",
//                        title: message["product.panel.list.state"],
//                        width: "80px",
//                        'orderable': false
//                    },
                    {
                        data: "featured",
                        title: message["product.panel.list.featured"],
                        width: "100px",
                        'orderable': false,
                        render: function (data, type, row, meta) {
                            if (data === 0) {
                                return message["select.option.no"];
                            }
                            if (data === 1) {
                                return message["select.option.yes"];
                            }
                        }
                    },
//                    {
//                        data: "executionTime",
//                        title: message["product.panel.list.executionTime"],
//                        width: "100px",
//                        'orderable': false,
//                        render: function (data, type, row, meta) {
//                            if (data === null) {
//                                return data;
//                            } else {
//                                return (new Date(data)).Format("yyyy-MM-dd");
//                            }
//                        }
//                    },
                    {
                        data: "productType",
                        title: message["product.panel.list.type"],
                        width: "80px",
                        'orderable': false
                    },
//                    {
//                        data: "color",
//                        title: message["product.panel.list.color"],
//                        width: "140px",
//                        'orderable': false
//                    },
                    {
                        data: "productStatus",
                        title: message["product.panel.list.status"],
                        width: "80px",
                        'orderable': false,
                        render: function (data, type, row, meta) {
                            if (row.code == 1) {
                                return "<span class='text-color-green'>" + data + "</span>";
                            } else {
                                return "<span class='text-color-yellow'>" + data + "</span>";
                            }
                        }
                    },
                    {
                        data: "id",
                        title: message["product.module.list.operate"],
                        width: "160px",
                        'orderable': false,
                        render: function (data, type, row, meta) {
                            return '<a class="btn btn-xs btn-primary operate" href="javascript:;" ' +
                                    'data-product-id="' + data + '">' + message["body.list.edit"] + '</a>';
//                                    '<a class="btn btn-xs btn-primary price" href="javascript:;" ' +
//                                    'data-product-id="' + data + '">' + message["product.panel.price.basePrice"] + '</a>';
                        }
                    }
                ],
                drawCallback: function () {
                }
            }
        });

        $("#btn_query").click(function () {
            showLoading();
            productTable.addAjaxParam('partNo', $('#partNo-query').val());
            productTable.addAjaxParam('configuration', $('#configuration-query').val());
            productTable.addAjaxParam('certification', $('#certification-query').val());
            productTable.addAjaxParam('series', $('#series-query').val());
            productTable.addAjaxParam('status', $('#status-query').val());
            productTable.submitFilter();
            hideLoading();
        });
        $('article').on('click', '.operate', function () {
            var id = $(this).data("product-id");
            var url = "/product/addUI";
            var $second = $('#second');
            var $first = $('#first');
            showLoading();
            $.ajax({
                url: url,
                type: 'GET',
                data: {id: id},
                success: function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                    hideLoading();
                }
            });
        });
        $('#common_export').click(function () {
        	
            var partNo=$('#partNo-query').val();
            var configuration= $('#configuration-query').val();
            var certification= $('#configuration-query').val();
            var series= $('#series-query').val();
            if(series==null) {
            	series='';
            }
            var status= $('#status-query').val();
            var url = "/panel/rest/export";
            
           	window.location.href=url+"?partNo="+partNo+
           			"&configuration="+configuration+
           			"&certification="+certification+
           			"&series="+series+
           			"&area="+area+
           			"&status="+status;
        });
    });


//    //时间的格式化
//    Date.prototype.Format = function (fmt) { //author: meizz 
//        var o = {
//            "M+": this.getMonth() + 1,
//            //月份 
//            "d+": this.getDate(),
//            //日 
//            "h+": this.getHours(),
//            //小时 
//            "m+": this.getMinutes(),
//            //分 
//            "s+": this.getSeconds(),
//            //秒 
//            "q+": Math.floor((this.getMonth() + 3) / 3),
//            //季度 
//            "S": this.getMilliseconds() //毫秒 
//        };
//        if (/(y+)/.test(fmt)) {
//            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//        }
//        for (var k in o) {
//            if (new RegExp("(" + k + ")").test(fmt)) {
//                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//            }
//        }
//        return fmt;
//    }
    function formValidator() {
        $("#price-form").bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                price: {
                    group: ".col.col-10",
                    validators: {
                        notEmpty: {
                            message: message["prompt.message.null"]
                        },
                        numeric: {
                            message: message["prompt.message.digital"]
                        }
                    }
                }
            }
        });
    }
</script>

<style>
    .btn-xs {
        margin-right: 5px;
        margin-top: 5px;
    }
    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px;
    }

    .jarviswidget .widget-body {
        min-height: 50px;
    }
</style>
</#compress>