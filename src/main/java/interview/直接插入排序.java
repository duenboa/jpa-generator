package interview;

import java.util.Arrays;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/13
 * @Version: 1.0.0
 * @Description: 内排序有可以分为以下几类：
 * 　　(1)、插入排序：直接插入排序、二分法插入排序、希尔排序。
 * 　　(2)、选择排序：简单选择排序、堆排序。
 * 　　(3)、交换排序：冒泡排序、快速排序。
 * 　　(4)、归并排序
 * 　　(5)、基数排序
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * Duenboa            2017/6/13            00000001         创建文件
 */
public class 直接插入排序 {

    public static void main(String[] args) {
        directInsertSort();
        dichotomySort();

    }


    /**
     * ①直接插入排序（从后向前找到合适位置后插入）
     * ＜p>
     * 基本思想：每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后），直到全部插入排序完为止。
     * 总结:
     * 直接插入排序是稳定的排序。关于各种算法的稳定性分析可以参考http://www.cnblogs.com/Braveliu/archive/2013/01/15/2861201.html
     * 文件初态不同时，直接插入排序所耗费的时间有很大差异。
     * 若文件初态为正序，则每个待插入的记录只需要比较一次就能够找到合适的位置插入，故算法的时间复杂度为O(n)，这时最好的情况。
     * 若初态为反序，则第i个待插入记录需要比较i+1次才能找到合适位置插入，故时间复杂度为O(n2)，这时最坏的情况。
     */
    public static void directInsertSort() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};

        System.out.println("排序之前：" + Arrays.toString(arr));
        int len = arr.length;
        for (int i = 1; i < len; i++) { // 当前元素从第二个开始循环
            int e = arr[i]; //要被插的元素
            int j;

            //迭代此元素以前的元素(已经有序了), 依次与当前元素做判断,
            for (j = i - 1; j >= 0; j--) {
                //大于当前元素的都做后移动操作. 否则循环停止.
                if (e < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
                arr[j] = e; //循环结束找到属于自己的位置,则进行设值.
            }
        }
        System.out.println("排序之后：" + Arrays.toString(arr));
    }


    /**
     * ②二分法插入排序（按二分法找到合适位置插入）
     * <p></p>
     * 基本思想：二分法插入排序的思想和直接插入一样，只是找合适的插入位置的方式不同，这里是按二分法找到合适的位置，可以减少比较的次数。
     * <p></p>
     * 总结:
     * 二分法插入排序也是稳定的
     * 二分插入排序的比较次数与待排序记录的初始状态无关，仅依赖于记录的个数。
     * 当n较大时，比直接插入排序的最大比较次数少得多。但大于直接插入排序的最小比较次数。
     * 算法的移动次数与直接插入排序算法的相同，最坏的情况为n2/2，最好的情况为n，平均移动次数为O(n2)。
     */
    public static void dichotomySort() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：" + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            int e = arr[i];
            int l = 0;
            int r = i - 1;
            int mid = 0;

            while (l <= r) { //比较mid, 计算最总匹配元素的为位置,取left
                mid = (l + r) / 2;
                if (e < arr[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            for (int j = i - 1; j >= l; j--) { // left到j-1之间的元素向右移动.
                arr[j + 1] = arr[j];
            }

            if (l != i) {
                arr[l] = e;
            }
        }
        System.out.println("排序之后：" + Arrays.toString(arr));

    }
}
