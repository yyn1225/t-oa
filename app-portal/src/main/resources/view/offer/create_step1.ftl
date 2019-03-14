<#compress>
<div id="create-step1">
    <div class="col-xs-12 col-sm-2 col-md-2 col-lg-2"></div>
    <article class="col-xs-12 col-sm-7 col-md-7 col-lg-7 sortable-grid ui-sortable">
        <div class="jarviswidget jarviswidget-sortable">
            <header role="heading">
                <span class="widget-icon"><i class="fa fa-cube"></i> </span>
                <h2><strong><i><dic data-dic="offer.create.choose"></dic></i></strong></h2>
            </header>
            <div role="content" style="display: block;">
                <div class="widget-body">
                    <div class="tab-content produce-edit-data">
                        <div class="row">
                            <div class="col-sm-6 col-md-6">
                                <input type="text" id="select-series" style="width: 95%;margin-left: 11px;" />
                                <form class="smart-form" style="margin-top: 8px;">
                                    <header style="padding-top: 0;margin-top: 0;"><dic data-dic="offer.create.certification"></dic></header>
                                    <fieldset tag="cert"></fieldset>
                                </form>
                                <form class="smart-form" style="margin-top: 8px;">
                                    <header style="padding-top: 0;margin-top: 0;"><dic data-dic="product.panel.list.configuration"></dic></header>
                                    <fieldset role="config"></fieldset>
                                </form>
                                <form class="smart-form" style="margin-top: 8px;">
                                    <header style="padding-top: 0;margin-top: 0;"><dic data-dic="product.panel.list.status"></dic></header>
                                    <fieldset role="state"></fieldset>
                                </form>
                            </div>
                            <div class="col-sm-6 col-md-6" style="margin-top: 20px;">
                                <div class="swiper-container">
                                    <div class="swiper-wrapper"></div>
                                    <div class="swiper-pagination"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="quotes.offer.list.customer"></dic></label>
                            <label class="col col-md-3">
                                <div class="input-group colorpicker-demo2 colorpicker-element" style="width: 100%">
                                    <div class='input-group input-group-sm' style="width: 100%">
                                        <input id="select_customer" class="input-autosuggest" />
                                        <div class='input-group-btn'>
                                            <button class='btn btn-default' type='button' style='height: 32px;' onclick="addNewCustomer()">
                                                <i class='fa fa-list'></i>
                                            </button>
                                        </div>
                                    </div>
                                    <#--<span class="input-group-addon"><a class="btn btn-xs btn-primary" style="float:right" onclick="addNewCustomer()"><dic data-dic="header.list.button.add"></dic></a></span>-->
                                </div>
                            </label>
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="quotes.offer.list.priceArea"></dic></label>
                            <label class="col col-md-3">
                                <select id="price_area" class="select2">
                                    <#if department??>
                                        <#list department as item>
                                            <option value="${(item.id)!}">${(item.name)!}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </label>
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="quotes.offer.list.payment"></dic></label>
                            <label class="col col-md-3">
                                <input id="select_payment" class="input-autosuggest" style="width: 100%" />
                            </label>
                        </div>
                        <div class="row">
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.company"></dic></label>
                            <label class="col col-md-3">
                                <select id="select_trader" class="select2" style="width: 100%">
                                    <#if tradeCompany??>
                                        <#list tradeCompany as item>
                                        <option value="${(item.code)!}">${(item.name)}</option>
                                        </#list>
                                    </#if>
                                </select>
                            </label>
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.monetaryUnit"></dic></label>
                            <label class="col col-md-3">
                                <select id="money-type" class="select2" style="width: 100%">
                                    <option value="CNY" data-key="￥">CNY(￥)</option>
                                    <#list rates as rate>
                                        <option value="${rate.code}" data-key="${rate.currency}">${rate.code!}(${rate.currency})</option>
                                    </#list>
                                </select>
                            </label>
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="quotes.offer.list.waitingDate"></dic></label>
                            <label class="col col-md-3">
                                <input id="valid-date" value="45" style="width: 100%"/>
                            </label>
                        </div>
                        <div class="row">
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.sizeUnit"></dic></label>
                            <label class="col col-md-3">
                                <select id="length-type" class="select2" style="width: 100%">
                                    <option value="1" data-dic="offer.create.meters"></option>
                                    <option value="2" data-dic="offer.create.foot"></option>
                                </select>
                            </label>
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="offer.create.boundary"></dic></label>
                            <label class="col col-md-3">
                                <select id="size-type" class="select2" style="width: 100%">
                                    <option value="1" data-dic="offer.create.not"></option>
                                    <option value="2" data-dic="offer.create.yes"></option>
                                    <option value="3" data-dic="offer.create.left"></option>
                                    <option value="4" data-dic="offer.create.top"></option>
                                </select>
                            </label>
                            <label class="col col-md-1 padding7 no-left no-right"><dic data-dic="quotes.offer.list.guarantee"></dic></label>
                            <label class="col col-md-3">
                                <select name="guarantee" class="select2" style="width: 100%">
                                    <option value="2" data-dic="portal.my.twoYears"></option>
                                    <option value="3" data-dic="portal.my.threeYears"></option>
                                    <option value="4" data-dic="portal.my.fourYears"></option>
                                    <option value="5" data-dic="portal.my.fiveYears"></option>
                                </select>
                            </label>
                        </div>
                        <div class="row">
                            <div class="col col-md-10"></div>
                            <label class="col col-md-1" style="text-align: right">
                                <button class="btn btn-default btn-success" type="button" id="next_btn">
                                    <dic data-dic="button.next"></dic>
                                </button>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>

    <#--模态框  客户-->
    <div class="modal fade in" id="dialog_table_customer" tabindex="-1" role="dialog">
        <div class="modal-dialog" style="width: 900px;">
            <input type="hidden" value="0" id="spare-type">
            <div class="modal-content">
                <div class="modal-header">
                    <h4><dic data-dic="portal.dashboard.myCustomer"></dic></h4>
                </div>
                <div class="modal-body no-padding">
                    <form class="smart-form">
                        <fieldset>
                            <div id="choose_spare_dialog_customer" class="custom-scroll table-responsive" style="max-height:450px; overflow-y: scroll;">

                            </div>
                        </fieldset>
                        <footer>
                            <button type="button" id="spare-choose_customer" class="btn btn-primary"><dic data-dic="offer.create.choose"></button>
                            <button type="button" class="btn btn-default" data-dismiss="modal"><dic data-dic="button.cancel"></dic></button>
                        </footer>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<div id="create-step2" style="display: none;"></div>

<script type="text/javascript" src="../../static/js/swiper/swiper.min.js"></script>
<script type="text/javascript" src="../../static/js/plugin/bootstrap-autosuggest/autosuggest.js"></script>
<script type="text/javascript">
    $("dic").each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
    $("#select-series").attr('data-placeholder', message["offer.create.select"]);
    $("option[data-dic]").each(function(){
        $(this).html(message[$(this).data("dic")]);
    });
    pageSetUp();
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        slidesPerView: 1,
        paginationClickable: true,
        spaceBetween: 30,
        loop: true,
        autoplay: 2500,
        autoplayDisableOnInteraction: false
    });

    var dataList=${series}||[];
    var seriesList=[];
    dataList.forEach(function(parent){
        var seriesItem = {text:parent.text,id:parent.id,children:[],disabled: true};
        parent.children.forEach(function(item){
            seriesItem.children.push({id:item.id,text:item.text});
        });
        seriesList.push(seriesItem);
    });
    $("#select-series").select2({
        data:seriesList
    });

    $("#select-series").on("change", function () {
        showLoading();
        var value=$(this).select2("val");
        $.ajax({
            type:"get",
            url:"/offer/series/products",
            data:{series:value},
            success:function(data){
                swiper.removeAllSlides();
                for(var image in data.images){
                    swiper.prependSlide('<div class="swiper-slide" style="width: 250px;height:250px;"><img src="'+data.images[image].urlThum+'" style="width: 100%;height: 100%"/></div>');
                }
                setTimeout(function(){
                    swiper.startAutoplay();
                },500);
                rebuildFilter(data.cert||[],data.config||[],data.special||[]);
                hideLoading();
            },
            error:function(){
                hideLoading();
                alert(message["http.response.error"],3);
            }
        });
    });

    $("#next_btn").off("click").click(function(){
       if($("#select-series").select2("val")){
           if(!$("#select_customer").val()){
               alert(message["offer.create.step1.customer"],2);
               return;
           }
           if(!$("#price_area").select2("val")){
               if($("#price_area option").length==0){
                   alert(message["offer.create.step1.admin"],2);
               }else{
                   alert(message["offer.create.step1.area"],2);
               }
               return;
           }
           if(!$("#valid-date").val()){
               alert(message["offer.create.step1.validdate"],2);
               return;
           }

           if(!$('#select_payment').val()){
               alert(message['offer.error.msg.paymentNotNULL'],2);
               return;
           }

           var certs=$("[data-role='cert'].btn-warning");
           var certValues=[];
           for (var i=0;i<certs.length;i++){
               var value=$(certs[i]).data("value");
               if(!!value){
                   certValues.push(value);
               }
           }

           var params = {
               series:$("#select-series").select2("val"),
               cert:certValues.join(","),
               config:$("[data-role='config'].btn-warning").data("value"),
               state:$("[data-role='state'].btn-warning").data("value"),
               customer:$("#select_customer").data("id"),
               priceArea:$("#price_area").select2("val"),
               length:$("#length-type").select2("val"),
               money:$("#money-type").select2("val")
           };

           $.ajax({
               type:"get",
               url:"/offer/create/step2",
               data:{
                   params:JSON.stringify(params)
               },
               success: function (html) {
                   $("#create-step1").hide();
                   $("#create-step2").html(html);
                   $("#create-step2").show();
               },error: function (err) {
                   console.log(err);
                   alert(message["http.response.error"],3);
               }
           });
       }else{
           alert(message["quote.empty.product"],2);
       }
    });

    $('#select_customer').change(function () {
        $(this).data('id','');
    });

    $("#valid-date").spinner({
        step:1,
        min:1,
        max:999,
        change: function () {
            $("#accept-days").html($("#valid-date").val());
        }
    });

    /**
     * 重置产品过滤选项
     */
    function rebuildFilter(cert,config,special){
        var certFilter = $(".smart-form fieldset[tag='cert']");
        certFilter.empty();
        certFilter.append("<a class='btn btn-warning btn-sm' data-role='cert' data-value=''>Standard</a>");
        $.each(cert, function (index,item) {
            var button = "<a class='btn btn-default btn-sm' data-role='cert' data-value='"+item.code+"'>"+item.name+"</a>";
            certFilter.append(button);
        });


        var configFilter = $(".smart-form fieldset[role='config']");
        configFilter.empty();
        configFilter.append("<a class='btn btn-warning btn-sm' data-role='config' data-value=''>"+message["offer.create.step1.config.global"]+"</a>");
        $.each(config, function (index,item) {
            var button = "<a class='btn btn-default btn-sm' data-role='config' data-value='"+item.id+"'>"+item.remark+"</a>";
            configFilter.append(button);
        });

        var stateFilter = $(".smart-form fieldset[role='state']");
        stateFilter.empty();
        stateFilter.append("<a class='btn btn-warning btn-sm' data-role='state' data-value=''>"+message["offer.create.step1.status.0"]+"</a>");
        $.each(special, function (index,item) {
            var button = "<a class='btn btn-default btn-sm' data-role='state' data-value='"+item+"'>"+message["offer.create.step1.status."+item]+"</a>";
            stateFilter.append(button);
        });
    }

    $("#create-step1 .smart-form fieldset[role]").on("click","a.btn-sm",function(){
        if($(this).hasClass("btn-warning")){
            return false;
        }
        $(this).parent().find("a.btn-warning").removeClass("btn-warning").addClass("btn-default");
        $(this).removeClass("btn-default").addClass("btn-warning");
    });

    $("#create-step1 .smart-form fieldset[tag]").on("click","a.btn-sm",function () {
        if($(this).hasClass("btn-default")){
            $(this).removeClass("btn-default").addClass("btn-warning");
        }else{
            $(this).removeClass("btn-warning").addClass("btn-default");
        }
    });

    if($("#price_area").select2("val")){
        loadUnit($("#price_area").select2("val"));
    }


    $("#price_area").change(function () {
        loadUnit($("#price_area").select2("val"));
    });


    /*客户选择*/
    $("#spare-choose_customer").off("click").click(function(e){
        e.preventDefault();
        var checkboxs=$("#choose_spare_dialog_customer table input:checkbox:checked");
        if(!checkboxs || checkboxs.length==0){
            alert(message["offer.create.spare.empty"]);
            return false;
        }else if(checkboxs.length > 1){
            alert(message["offer.create.spare.empty"]);
            return false;
        }

        checkboxs.each(function(){
            var tr=$(this).parents("tr:first");
            var data={
                name:$(tr).data("name")
            };
            $("#select_customer").val(data.name);
        });

        $("#dialog_table_customer").modal("hide");
        $("#choose_spare_dialog_customer").html("");
    });

    function addNewCustomer(){
        var name=$("#name").val();
        var type = $("#type").val();
        var location=$("#location").val();
        var phone = $("#phone").val();

        showLoading();
        $.ajax({
            url: "/customer/customer/list",
            data: {
                name: name,
                type: type,
                location:location,
                phone:phone
            },
            type: "get",
            success: function (data) {
                $("#choose_spare_dialog_customer").html(data);
                $("#dialog_table_customer").modal("show");
                hideLoading();
            }
        });
    }

    function loadUnit(area) {
        $.ajax({
            url:"/offer/area/unit",
            type:"get",
            data:{area:area},
            success:function (data) {
                if(data){
                    $("#money-type").select2("val",data);
                }
            }
        });
    }

    var evt = function () {
        return{
            /**
             * 获取数据序列化
             */
            findProduceData: function () {
                var certs=$("[data-role='cert'].btn-warning");
                var certValues=[];
                for (var i=0;i<certs.length;i++){
                    var value=$(certs[i]).data("value");
                    if(!!value){
                        certValues.push(value);
                    }
                }
                if(certValues.length==0){
                    certValues.push("Basics");
                }

                var produceEditData = $('.produce-edit-data');
                var produceName = produceEditData.find('#s2id_select-series').select2('data');
                var data = {
                    productName: produceName ? produceName.text : '',
                    cert: certValues.join("+"),
                    config: produceEditData.find('[role="config"] a.btn-warning').html(),
                    state: produceEditData.find('[role="state"] a.btn-warning').html(),
                    customer: produceEditData.find('#select_customer').val(),
                    area: produceEditData.find('#price_area').select2('data').text,
                    payment: produceEditData.find('#select_payment').val(),
                    trader: produceEditData.find('#select_trader').select2('val'),
                    moneyType: produceEditData.find('#money-type').select2('data').text,
                    validDate: produceEditData.find('#valid-date').val(),
                    lengthType: produceEditData.find('#length-type').select2('data').text,
                    sizeType: produceEditData.find('#size-type').select2('val'),
                    autogen: produceEditData.find('[name="guarantee"]').select2('data').text
                };
                return data;
            },
            initForm: function () {
                //客户
                $('#select_customer').autosuggest({
                    url: '/api/customer/customer/autosuggest',
                    queryParamName: 'name',
                    minLength: 1,
                    onSelect: function (option) {
                        $('#select_customer').data('id',$(option).data('id'));
                    }
                });
                $.ajax({
                    url: '/api/dict/find/category',
                    data: {category:'payment_mode'},
                    type: "GET",
                    success: function (res) {
                        //付款方式'
                        console.log(res);
                        $('#select_payment').data('list',res);
                        $('#select_payment').autosuggest({
                            dataList:res,
                            local:true,
                            minLength:1
                        });
                    }
                });
            },
            /**
             * 数组处理成map（给下拉款使用）
             */
            list2map: function (list) {
                var map = {};
                list.forEach(function (item) {
                    map[item.id] = item.value;
                });
                return map;
            }
        }
    }();

    evt.initForm();
</script>

<link rel="stylesheet" href="../../static/js/swiper/swiper.min.css"/>
<link rel="stylesheet" href="../../static/js/plugin/bootstrap-autosuggest/autosuggest.css"/>
<style>
    .stepshow.activate {
        right: 0 !important;
        box-shadow: -11px 12px 23px rgba(0, 0, 0, .2);
        padding: 5px 10px 10px;
    }

    .stepshow {
        position: absolute;
        top: 5px;
        right: 0;
        width: 380px;
        z-index: 902;
        background: #F1DA91;
    }

    .param-table td.text-align-right{
        border-right: 1px dashed #00000033;
    }

    .stepshow.activate > span {
        left: -30px;
    }

    .stepshow > span {
        position: absolute;
        right: 0;
        top: 0;
        display: block;
        height: 30px;
        width: 30px;
        border-radius: 5px 0 0 5px;
        background: #F1DA91;
        padding: 2px;
        text-align: center;
        line-height: 28px;
        cursor: pointer;
    }

    .smart-form fieldset[role] a.btn-sm,.smart-form fieldset[tag] a.btn-sm{
        margin-right: 5px;
    }

    .jarviswidget{
        margin:0 0 10px;
    }

    .smart-form fieldset{
        padding-top:2px;
    }

    .smart-form fieldset a{
        margin-bottom:2px;
        margin-top:2px;
    }

    .smart-form header{
        font-size:14px;;
    }

    .swiper-container,.swiper-slide{
        min-height:255px;
    }

    .smart-form header{
        padding-bottom:0;
        font-size:15px;
        color: #828282;
    }

    .table td{
        border-top:1px dashed rgba(0,0,0,.2) !important;
    }

    .table{
        width: 90%;
    }

    .table  .no-border td{
        border-top:0 !important;
    }

    .smart-form section label{
        padding-top:5px;;
    }

    .table>tbody>tr>td{
        padding: 5px 10px !important;
    }

    header{
        cursor: auto !important;
    }

    .ui-spinner-button.ui-spinner-down.ui-corner-br{
        margin-bottom: 0;
        background-color: #ababab;
    }

    .ui-spinner-button.ui-spinner-up.ui-corner-tr{
        margin-top: 0;
        background-color: #ababab;
        padding-top: 2px;
    }

    .padding7{
        padding-top:7px;
    }

    .input-group .input-group-addon{
        padding-left: 3px;
        padding-right: 0;
        border: 0;
        background-color: #fff;
        padding-top:3px;
        width: 25px;
    }

    .long-label{
        padding-right: 0 !important;
        padding-left: 0 !important;
        width: 11.3% !important;
    }

    .short-label{
        padding-left: 3px !important;
        width: 5.3% !important;
        padding-right: 0 !important;
    }

    .no-left{
        padding-left:0 !important;
    }

    .no-right{
        padding-right:0 !important;
    }

    .no-left.no-right{
        text-align: right;
    }

    .smart-form .ui-widget-content .ui-spinner-input{
        padding-left: 3px;
    }

    .select2-hidden-accessible{
        display: none;
    }

    .widget-body .smart-form fieldset .col.col-3{
        padding-left:0 !important;
    }

    .right-span{
        float: right;
        padding-top: 7px;
        padding-right: 25px;
        color: #ff5757;
    }

    #choose_spare_dialog_customer label i{
        margin-top:-3px;
    }

    .fa.fa-trash-o{
        font-size: 17px;
        padding-top:7px;
        cursor: pointer;
    }

    .margin-left-10{
        margin-left: 10px;
    }

    .free,[tag="free_part"] [price]{
        text-decoration: line-through;
    }
    .input-autosuggest{
        width: 100%;
        height: 32px;
        border: 1px solid #c9ccd0;
        padding: 0 7px;
    }

    .form-control{
        height:29px;
    }

    .smart-form fieldset+fieldset{
        border-top: 0;
    }

    .drop-panel{
        -webkit-text-fill-color: #da0000;
        display: inline-block;
        cursor: pointer;
        margin-top: -9px;
    }
    .product-info-look .col-md-4{
        padding-right: 0;
    }
    .pading-seven{
        padding-bottom: 7px;
    }
    .panel_certification,.panel_state{
        font-weight: 700;
    }
    /*文字限制一行显示*/
    .text-nowrap{
        white-space: nowrap;
    }
    #package-form .list-group-item{
        padding: 10px 15px;
    }

    .choose_spare_table{
        table-layout: fixed;
    }
    /*#choose_spare_list_ul .checkbox i{*/
        /*width: 13px;*/
        /*height: 13px;*/
    /*}*/
    #choose_spare_list_ul .show-grid{
        margin: 0;
    }
    #choose_spare_list_ul tr:hover{
        background-color: #efefef;
    }
    #choose_spare_list_ul table{
        width: 99%;
    }
    #choose_spare_list_ul tbody tr td{
        text-overflow: ellipsis; /* for IE */
        -moz-text-overflow: ellipsis; /* for Firefox,mozilla */
        overflow: hidden;
        white-space: nowrap;
        text-align: left;
        height: 22px;
    }
    #choose_spare_list_ul table th .first-check{
        width: 30px;
        height: 30px;
        padding: 0;
        margin-left: 5px;
    }
    #choose_spare_list_ul table th:first-child{
    }
    .font-diy{
        font-size: 10px;
    }

    section.spare_search{
        z-index: 999999999999;
        background-color: #FFF;
        padding-left: 0;
        height: 52px;
        position: absolute;
    }
</style>
</#compress>