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
public class Manager extends Employee {

    private BigDecimal salary;

    @Override
    public BigDecimal computeSalary() {
        return this.salary;
    }

    @Override
    Employee work() {
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
