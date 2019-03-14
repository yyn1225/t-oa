<form class="smart-form">
        <fieldset>
            <div class="row">
                <label class="col col-1 text-align-right padding">
                    <dic data-dic="org.user.list.name"></dic>
                    :
                </label>
                <label class="col col-2 input">
                    <input type="text" id="name_assistant_query"/>
                </label>
                <label class="col col-1 text-align-right padding">
                    <dic data-dic="org.user.list.workno"></dic>
                    :</label>
                <label class="col col-2 input">
                    <input type="text" id="workno_assistant_query"/>
                </label>
                <label class="col col-2 text-align-right padding">
                    <dic data-dic="org.user.list.phone"></dic>
                    :
                </label>
                <label class="col col-2 input">
                    <input type="text" id="phone_assistant_query"/>
                </label>
                <label class="col col-2 text-align-right">
                    <a class="btn btn-primary query-btn" id="btn_assistant_query">
                        <dic data-dic="header.list.button.search"></dic>
                    </a>
                    </a>
                </label>
            </div>
        </fieldset>
    </form>
<table id="dt_basic_assistant" class="table table-striped table-bordered table-hover"
       width="90%">
</table>

</div>

<script type="text/javascript">
    var dtn;
    $(document).ready(function () {
        $("[data-dic]").each(function () {
            $(this).html(message[$(this).data("dic")]);
        });

        dtn = new Datatables();
        dtn.init({
            src: $("#dt_basic_assistant"),
            loadingMessage: message["datatable.loading"],
            dataTable: {
                bStateSave: false,
                ajax: {url: "user/rest/assistantList"},
                columns: [
                    DTColumnKit.checkboxone,
                   // DTColumnKit.order,
                    {data: "username", title: message["org.user.list.name"], width: "100px"},
                    {data: "workNo", title: message["org.user.list.workno"], width: "100px"},
                    {data: "phone", title: message["org.user.list.phone"]},
                    {data: "languageDefault", title: message["org.user.list.language"], width: "80px"},
                    {
                        data: "areaName", title: message["org.user.list.area"], width: "70px",
                        render: function (data, type, row) {
                            if (row.area === 0) {
                                return message["organization.item.form.center"];
                            }
                            return data;
                        }
                    },
                    {data: "area", "visible": false},
                    {
                        data: "status", title: message["org.user.list.status"], width: "50px",
                        render: function (data, type, full) {
                            if (full.deleteFlag == 1) {
                                return "<span class='text-color-red'>" + message["body.list.deleted"] + "</span>"
                            }
                            if (data === 0) {
                                return "<span class='text-color-red'>" + message["body.list.invalid"] + "</span>";
                            } else {
                                return "<span class='text-color-green'>" + message["body.list.effective"] + "</span>";
                            }
                        }
                    }
                ],
                columnDefs: [{
                    orderable: false,
                    targets: [0, 1, 2, 3, 4, 5, 6]
                }],
                drawCallback: function () {
                }
            }
        });

        $("#btn_assistant_query").click(function () {
            showLoading();
            dtn.addAjaxParam('name', $('#name_assistant_query').val());
            dtn.addAjaxParam('workno', $('#workno_assistant_query').val());
            dtn.addAjaxParam('phone', $('#phone_assistant_query').val());
            dtn.submitFilter();
            hideLoading();
        });


    });

    $('#dt_basic_assistant').on('click', 'input:checkbox[name="dt_checkbox"]', function () {

        var tableCheckbox = $("#dt_basic_assistant input[name='dt_checkbox']:visible");

        tableCheckbox.prop('checked', false);

        $(this).prop('checked', true);

    });

</script>

<style>
    .padding {
        padding-top: 7px;
        padding-left: 0 !important;
        padding-right: 0 !important;
    }

    .modal-header {
        padding: 9px;
    }
</style>