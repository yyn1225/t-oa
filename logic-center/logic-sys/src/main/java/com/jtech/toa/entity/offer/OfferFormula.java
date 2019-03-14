package com.jtech.toa.entity.offer;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p></p>
 *
 * @author dongdong.bian
 * @version 1.0
 * @date 2018/8/3 6:47
 * @since JDK 1.8
 */
@Data
public class OfferFormula {
    private Long id;
    private Long product;
    private String brand;
    private String typeName;
    private String model;
    private String unit;
    private BigDecimal count2;
    private Integer type2;
    private String spel2;
    private BigDecimal count3;
    private Integer type3;
    private String spel3;
    private BigDecimal count4;
    private Integer type4;
    private String spel4;
    private BigDecimal count5;
    private Integer type5;
    private String spel5;
    private Long offerSpare;

    public Formula buildFormula() {
        Formula formula = new Formula();
        formula.setPanel(this.product);

        formula.setCountTwo(this.count2);
        formula.setTypeTwo(this.type2);
        formula.setSpelTwo(this.spel2);

        formula.setCountThree(this.count3);
        formula.setTypeThree(this.type3);
        formula.setSpelThree(this.spel3);

        formula.setCountFour(this.count4);
        formula.setTypeFour(this.type4);
        formula.setSpelFour(this.spel4);

        formula.setCountFive(this.count5);
        formula.setTypeFive(this.type5);
        formula.setSpelFive(this.spel5);
        return formula;
    }
}
