package interview;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/12
 * @Version: 1.0.0
 * @Description: 2两个用户甲和乙一共去银行存款5次，每次存100，两人各存了多少钱？(用线程做)
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * Duenboa            2017/6/12            00000001         创建文件
 */
public class BankSaveMoney {

    public volatile static Integer totalMoney = 500;
    static int Anum = 0;
    static int Bnum = 0;

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (totalMoney >= 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (totalMoney) {
                        if (totalMoney >= 100) {
                            totalMoney = totalMoney - 100;
                            Anum++;
                            System.out.println("甲存入100, 剩余:" + totalMoney);
                        }
                    }
                }
            }
        });

        final Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while (totalMoney >= 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (totalMoney) {
                        if (totalMoney >= 100) {
                            totalMoney = totalMoney - 100;
                            Bnum++;
                            System.out.println("乙存入100, 剩余:" + totalMoney);
                        }
//                        Thread.interrupted();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("甲: " + 100 * Anum);
        System.out.println("乙: " + 100 * Bnum);
    }


}
