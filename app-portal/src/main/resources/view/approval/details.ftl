<#compress>
<div class="widget-body">
    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="customer.item.name"></dic>:
        </label>
        <label class="col col-10 padding7">
        ${(offer.customerName)!}
        </label>
    </fieldset>

    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="portal.dashboard.customerLevel"></dic>:
        </label>
        <label class="col col-4 padding7">
        ${(offer.levelName)!}
        </label>
        <label class="col col-2 text-align-right padding">
            <dic data-dic="quotes.offer.list.seriesName"></dic>:
        </label>
        <label class="col col-4 padding7">
        ${(offer.seriesName)!}
        </label>
    </fieldset>
    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="portal.dashboard.total"></dic>:
        </label>
        <label class="col col-4 padding7">
        ${(offer.cost)!}
        </label>
        <label class="col col-2 text-align-right padding">
            <dic data-dic="portal.dashboard.discount"></dic>:
        </label>
        <label class="col col-4 padding7">
        ${(offer.totalDiscount)!}
        </label>
    </fieldset>
    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="portal.dashboard.netReceipts"></dic>:
        </label>
        <label class="col col-4 padding7">
        ${((offer.cost)*((offer.totalDiscount)/100))!}
        </label>
        <label class="col col-2 text-align-right padding">
            <dic data-dic="portal.approval.username"></dic>:
        </label>
        <label class="col col-4 padding7">
        ${(offer.creater)!}
        </label>
    </fieldset>

    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="offer.approval.opinion"></dic>:
        </label>
        <label class="col col-10 textarea">
            <textarea rows="3" class="custom-scroll" id="opinion" readonly>
                ${(approval.opinion)!}
            </textarea>
        </label>
    </fieldset>
</div>
<style>
    .padding {
        padding-top: 7px;
        padding-right: 0 !important;
    }
    .new-fieldset {
        height: auto !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
        padding-top: 5px !important;
    }
</style>
<script>
    $("dic").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
</script>
</#compress>