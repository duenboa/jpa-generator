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
public class Worker extends Employee {

    private BigDecimal days; //工作时间天数
    private BigDecimal perDaySalary; //工作单日薪水

    @Override
    public BigDecimal computeSalary() {
        if (days.compareTo(BigDecimal.ZERO) > 0 && perDaySalary.compareTo(BigDecimal.ZERO) > 0) {
            return days.multiply(perDaySalary);
        }
        return BigDecimal.ZERO;
    }

    @Override
    Employee work() {
        return this;
    }

    public BigDecimal getDays() {
        return days;
    }

    public void setDays(BigDecimal days) {
        this.days = days;
    }

    public BigDecimal getPerDaySalary() {
        return perDaySalary;
    }

    public void setPerDaySalary(BigDecimal perDaySalary) {
        this.perDaySalary = perDaySalary;
    }
}
