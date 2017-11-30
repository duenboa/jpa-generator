package interview.slavery;

import java.math.BigDecimal;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/12
 * @Version: 1.0.0
 * @Description:
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * Duenboa            2017/6/12            00000001         创建文件
 */
public class Saleman extends Employee {

    private BigDecimal baseSalary; //底薪
    private BigDecimal saleAmount; //销售额
    private static BigDecimal saleScale = new BigDecimal("0.1"); //销售额提成率


    @Override
    public BigDecimal computeSalary() {
        if (saleAmount.compareTo(BigDecimal.ZERO) > 0) {
            return this.baseSalary.add(saleAmount.multiply(new BigDecimal(0.1)));
        }
        return baseSalary;
    }

    @Override
    Employee work() {
        return this;
    }

    public BigDecimal getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(BigDecimal baseSalary) {
        this.baseSalary = baseSalary;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public static BigDecimal getSaleScale() {
        return saleScale;
    }

    public static void setSaleScale(BigDecimal saleScale) {
        Saleman.saleScale = saleScale;
    }
}
