package cn.lcy.netty;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class fry {

    /**
     * 国家
     */
    private String country;

    /**
     * 配送费 元/kg
     */
    private BigDecimal price;

    /**
     * 挂号费 元
     */
    private BigDecimal ghPrice;

    /**
     * 配送优惠 元/kg
     */
    private BigDecimal downPrice;

    /**
     * 挂号费优惠 元
     */
    private BigDecimal downGhPrice;


    /**
     * 实际首重金额  美元
     */
    private BigDecimal actorGhPrice;

    /**
     * 实际配送费 美元
     */
    private BigDecimal actorPrice;
}
