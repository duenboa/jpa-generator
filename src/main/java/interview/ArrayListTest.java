package interview;

import java.util.ArrayList;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/19
 * @Version: 1.0.0
 * :
 * @see
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(10);
        Object o = arrayList.get(-1);
        System.out.println(o);
    }
}
