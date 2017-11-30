package util;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/8
 * @Version: 1.0.0
 * @Description:
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * Duenboa            2017/6/8            00000001         创建文件
 */
public class Test {

    public static void main(String[] args) {

        String title = "`1234567890-=~!@#$%^&*()_+qwertyuiop[]QWERTYUIOP{}\\|asdfghjkl;'ASDFGHJKL::\"zxcvbnm,./ZXCVBNM<>?dyy团+分170608A（合资）";
        title.replaceAll("[\\w]|[\\u4E00-\\u9FA5]", "");
        String s = clearNotWordChar(title);
        System.out.println(s);

    }

    public static String clearNotWordChar(String s) {
        if (s == null || "".equals(s.trim())) {
            return "";
        }
        StringBuilder buf = new StringBuilder("");
        char[] chars = s.toCharArray();
        if (chars != null) {
            for (Character c : chars) {
                if (c.toString().matches("[\\w]|[\\u4E00-\\u9FA5]")) {
                    buf.append(c);
                }
            }
        }
        return buf.toString();
    }



//    1 某公司写一个工资计算程序，普通工人worker按照工作天数计算，销售Saleman底薪加上提成，提成为销售额的0.1倍，
//            *  经理manager是固定工资。
//            *要求设计抽象类Employee，含有员工共同属性姓名，性别，抽象方法computeSalary()计算工资，work()方法访问共同属性;
//    * 并且用三元运算算出工资最多的薪水是多少(未使用三元运算不得分)。
//
//            2两个用户甲和乙一共去银行存款5次，每次存100，两人各存了多少钱？(用线程做)
//
//            3 往集合中添加10个1-100(包含100)之间不重复的随机整数,要求这10个随机整数必须为奇数并且是5的倍数,
//            * 然后按从大到小排序后写入到当前工程项目下的number.txt文本中,格式如下
//    * 例如:number.txt内容: 95, 85, 75, 65, 55, 45, 35, 25, 15, 5

    public static Object calcMoney(){


        return null;
    }
}
