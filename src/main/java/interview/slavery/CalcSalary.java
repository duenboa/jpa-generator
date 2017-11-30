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
public class CalcSalary {

    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.setName("m");
        manager.setSex("man");
        manager.setSalary(BigDecimal.valueOf(10000));


        Worker worker = new Worker();
        worker.setSex("man");
        worker.setName("w");
        worker.setDays(BigDecimal.valueOf(10));
        worker.setPerDaySalary(BigDecimal.valueOf(200));

        Saleman saleman = new Saleman();
        saleman.setName("s");
        saleman.setSex("woman");
        saleman.setBaseSalary(BigDecimal.valueOf(3000));
        saleman.setSaleAmount(BigDecimal.valueOf(100000));

        Employee e = maxSalaryEmployee(manager, worker, saleman);
        System.out.println(e);
        BigDecimal salary = e.computeSalary();
        System.out.println("max : " + salary.doubleValue());


    }

    /**
     * //    1 某公司写一个工资计算程序，普通工人worker按照工作天数计算，销售Saleman底薪加上提成，提成为销售额的0.1倍，
     * //            *  经理manager是固定工资。
     * //            *要求设计抽象类Employee，含有员工共同属性姓名，性别，抽象方法computeSalary()计算工资，work()方法访问共同属性;
     * //    * 并且用三元运算算出工资最多的薪水是多少(未使用三元运算不得分)。
     * //
     * //            2两个用户甲和乙一共去银行存款5次，每次存100，两人各存了多少钱？(用线程做)
     * //
     * //            3 往集合中添加10个1-100(包含100)之间不重复的随机整数,要求这10个随机整数必须为奇数并且是5的倍数,
     * //            * 然后按从大到小排序后写入到当前工程项目下的number.txt文本中,格式如下
     * //    * 例如:number.txt内容: 95, 85, 75, 65, 55, 45, 35, 25, 15, 5
     *
     * @param manager
     * @param worker
     * @param saleman
     * @return
     */
    private static Employee maxSalaryEmployee(Manager manager, Worker worker, Saleman saleman) {
        BigDecimal mAmt = manager.computeSalary();
        System.out.println("manager: " + mAmt.doubleValue());
        BigDecimal wAmt = worker.computeSalary();
        System.out.println("worker: " + wAmt.doubleValue());
        BigDecimal sAmt = saleman.computeSalary();
        System.out.println("saleman: " + sAmt.doubleValue());
        return mAmt.compareTo(wAmt) > 0 ? (mAmt.compareTo(sAmt) > 0 ? manager : saleman) : (wAmt.compareTo(sAmt) > 0 ? worker : saleman);
    }


}
