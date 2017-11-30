package interview;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
public class NumberToText {

    //3 往集合中添加10个1-100(包含100)之间不重复的随机整数,要求这10个随机整数必须为奇数并且是5的倍数,
    //       * 然后按从大到小排序后写入到当前工程项目下的number.txt文本中,格式如下

    public static void main(String[] args) {

        //构造
        Set numSet = new HashSet(10);
        while (numSet.size() < 10) {
            int i = new Random().nextInt(100);
            if (i % 2 != 0 && i % 5 == 0) {
                numSet.add(i);
            }
        }
        System.out.println("构造完成后: " + numSet);

        Integer[] nums = new Integer[numSet.size()];
        numSet.toArray(nums);

        for (int i = 0; i < nums.length; i++) {
            int tmp;
            for (int i1 = 0; i1 < i - 1; i1++) {
                if (nums[i] > nums[i1]) {
                    tmp = nums[i];
                    nums[i] = nums[i1];
                    nums[i1] = tmp;
                }
            }
        }

        String s = Arrays.toString(nums);
        System.out.println("排序后：" + s);

        URL pathUrl = NumberToText.class.getResource("");
        String path = pathUrl.getPath();
        File file = new File(path + "\\" + "number.txt");
        System.out.println("file : " + file);
        OutputStream outputStream = null;
        try {
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (outputStream != null) {
                byte[] bytes = s.getBytes();
                try {
                    outputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
