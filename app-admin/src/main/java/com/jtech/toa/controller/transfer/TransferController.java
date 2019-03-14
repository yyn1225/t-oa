package com.jtech.toa.controller.transfer;

import com.jtech.toa.model.dto.offer.OfferTransferDto;
import com.jtech.toa.model.sap.SapRate;
import com.jtech.toa.service.offer.IOfferTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @since JDK 1.8
 */
@Controller
public class TransferController {

    private final IOfferTransferService offerTransferService;

    @Autowired
    public TransferController(IOfferTransferService offerTransferService) {
        this.offerTransferService = offerTransferService;
    }

    @GetMapping("/transfer")
    public String index() {
        return "transfer/list";
    }

    @GetMapping("/transfer/item")
    public String item(Model model, int id) {
        OfferTransferDto offerTransfer = offerTransferService.selectOneById(id);
        model.addAttribute("offerTransfer", offerTransfer);
        if (offerTransfer.getMoneyUnit() != null) {
            model.addAttribute("moneyUnit", new SapRate().getKeyCode().get(offerTransfer.getMoneyUnit()));
        }
        return "transfer/item";
    }
}
