<#compress>
<div class="widget-body">
    <fieldset class="new-fieldset">
        <input type="hidden" id="transferId" value="${((offerTransfer.id?c)!)}">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="offer.item.transfer.series"></dic>:
        </label>
        <label class="col col-4 input padding-l">
        ${(offerTransfer.seriesName)!}
        </label>

        <label class="col col-2 text-align-right padding">
            <dic data-dic="offer.list.transfer.bidder"></dic>:
        </label>
        <label class="col col-4 input padding-l">
        ${(offerTransfer.bidder)!}
        </label>
    </fieldset>

    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="customer.item.name"></dic>:
        </label>
        <label class="col col-4 input padding-l">
        ${(offerTransfer.customerName)!}
        </label>
    </fieldset>
    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="offer.list.transfer.toAddress"></dic>:
        </label>
        <label class="col col-4 input padding-l">
        ${(offerTransfer.toAddress)!}
        </label>
    </fieldset>
    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="offer.list.transfer.transport"></dic>:
        </label>
        <label class="col col-4 input padding-l">
        ${(offerTransfer.transport)!}
        </label>
    </fieldset>

    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding">
            <dic data-dic="offer.list.transfer.size"></dic>:
        </label>
        <label class="col col-4 input padding-l">
        ${(offerTransfer.size)!}
        </label>
    </fieldset>

    <fieldset class="new-fieldset">
        <label class="col col-2 text-align-right padding" style="padding-top: 7px">
            <dic data-dic="offer.item.transfer.unit"></dic>:
        </label>
        <label class="col col-4 input padding-l">
            ${moneyUnit!}
        </label>
        <label class="col col-2 text-align-right padding" style="padding-top: 7px">
            <dic data-dic="offer.item.transfer.cost"></dic>:
        </label>
        <label class="col col-3 input">
            <input type="text" class="spinners" value="${(offerTransfer.cost?c)!}" id="cost">
        </label>
    </fieldset>
</div>
<style>
    .padding {
        padding-right: 0 !important;
        padding-bottom: 10px !important;
    }
    .new-fieldset {
        border-bottom: 1px dashed #cccccc !important;
        height: auto !important;
        padding-left: 0 !important;
        padding-right: 0 !important;
        padding-top: 5px !important;
    }
</style>
<script>
    $("[data-dic]").each(function () {
        $(this).html(message[$(this).data("dic")]);
    });
    $(".spinners").spinner({
        step: 1,
        textFormat: "n"
    });
</script>
</#compress>