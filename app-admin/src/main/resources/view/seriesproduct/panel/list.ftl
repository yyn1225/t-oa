<#compress>
<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
    <div id="first">
        <div class="jarviswidget" data-widget-editbutton="false">
            <header>
                <span class="widget-icon">
                    <i class="fa fa-table"></i>
                </span>
                <span class="widget-icon" style="margin-left: 3px;" data-dic="panel.list.head1er"></span>
                <h2>
                    <dic dic="header.list.button.add"></dic>
                </h2>
            </header>
            <div>
                <div class="widget-body no-padding">
                    <table id="dt_basic_product" class="table table-striped table-bordered table-hover" width="100%">
                    </table>
                </div>
            </div>
            <input type="hidden" id="series" value="${(series)!}">
            <input type="hidden" id="area" value="2">

        </div>
    </div>
    <div id="second" style="display: none">123</div>
</article>


<script type="text/javascript">
    var productTable;
    $(document).ready(function(){
        $("[data-dic]").each(function(){
            $(this).html(message[$(this).data("dic")]);
        });

        var area = $("#area").val();
        var series = $("#series").val();
        productTable = new Datatables();
        productTable.setAjaxParam("series",series);
        productTable.init({
            src : $("#dt_basic_product"),
            loadingMessage: message["datatable.loading"],
            scrollX: true,
            dataTable : {
                bStateSave: false,
                ajax: { url: "panel/rest/list?area=" + area },
                columns   : [
//                    DTColumnKit.order,
                    {data:"productSeries",title:message["product.panel.list.name"]},
                    {data:"partNo",title:message["product.panel.list.partNo"],
                        render: function (data, type, row, meta) {
                            return '<a class="operate" data-product-id="'+row.id+'">' + data + '</a>';
                        }},
                    {data:"productConfiguration",title:message["product.panel.list.configuration"]},
                    {data:"certification",title:message["product.panel.list.certification"]},
//                    {data:"state",title:message["product.panel.list.state"]},
                    {data:"featured",title:message["product.panel.list.featured"],
                        render: function (data, type, row, meta) {
                            if (data === 0) {
                                return '是';
                            } else {
                                return '否';
                            }
                        }},
                    {data:"executionTime",title:message["product.panel.list.executionTime"],
                        render: function (data, type, row, meta) {
                            if (data === null) {
                                return data;
                            } else {
                                return (new Date(data)).Format("yyyy-MM-dd");
                            }
                        }},
                    {data:"productType",title:message["product.panel.list.type"]},
                    {data:"productStatus",title:message["product.panel.list.status"],
                        render: function (data, type, row, meta) {
                            if (row.code == 1) {
                                return "<span class='text-color-green'>"+data+"</span>";
                            } else {
                                return "<span class='text-color-yellow'>"+data+"</span>";
                            }
                        }},
                    {data:"color",title:message["product.panel.list.color"]}
                ],
                drawCallback:function(){ }
            }
        });



        $('article').on('click','.operate',function () {
            var id = $(this).data("product-id");
            var url = "/sp/product/view";
            var $second = $('#second');
            var $first = $('#first');
            showLoading();
            $.ajax({
                url:url,
                type:'GET',
                data:{id:id},
                success:function (data) {
                    $first.hide();
                    $second.show();
                    $second.html(data);
                    hideLoading();
                }

            });
        });

    });
    //时间的格式化
    Date.prototype.Format = function (fmt) { //author: meizz 
        var o = {
            "M+": this.getMonth() + 1,
            //月份 
            "d+": this.getDate(),
            //日 
            "h+": this.getHours(),
            //小时 
            "m+": this.getMinutes(),
            //分 
            "s+": this.getSeconds(),
            //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3),
            //季度 
            "S": this.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }
</script>

<style>
    .btn-xs{
        margin-right: 15px;
        margin-top: 5px;
    }
    .smart-form fieldset {
        padding: 20px 14px 5px;
        height: 50px;
    }
    .jarviswidget .widget-body {
        min-height: 50px;
    }
    tr a{
        text-decoration: underline;
        cursor: pointer;
    }
</style>
</#compress>