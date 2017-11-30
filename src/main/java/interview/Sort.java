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
 * <p>
 * <p>
 * 总结：
 * 一、稳定性:
 * 　   稳定：冒泡排序、插入排序、归并排序和基数排序
 * 　　不稳定：选择排序、快速排序、希尔排序、堆排序
 * 二、平均时间复杂度
 * 　　O(n^2):直接插入排序，简单选择排序，冒泡排序。
 * 　　在数据规模较小时（9W内），直接插入排序，简单选择排序差不多。当数据较大时，冒泡排序算法的时间代价最高。性能为O(n^2)的算法基本上是相邻元素进行比较，基本上都是稳定的。
 * 　　O(nlogn):快速排序，归并排序，希尔排序，堆排序。
 * 　　其中，快排是最好的， 其次是归并和希尔，堆排序在数据量很大时效果明显。
 * 三、排序算法的选择
 * 　　1.数据规模较小
 * 　　（1）待排序列基本序的情况下，可以选择直接插入排序；
 * 　　（2）对稳定性不作要求宜用简单选择排序，对稳定性有要求宜用插入或冒泡
 * 　　2.数据规模不是很大
 * 　　（1）完全可以用内存空间，序列杂乱无序，对稳定性没有要求，快速排序，此时要付出log（N）的额外空间。
 * 　　（2）序列本身可能有序，对稳定性有要求，空间允许下，宜用归并排序
 * 　　3.数据规模很大
 * 　　（1）对稳定性有求，则可考虑归并排序。
 * 　　（2）对稳定性没要求，宜用堆排序
 * 　　4.序列初始基本有序（正序），宜用直接插入，冒泡
 * @History: 变更记录
 * <author>           <time>             <version>        <desc>
 * Duenboa            2017/6/13            00000001         创建文件
 */
public class Sort {

    public static void main(String[] args) {
        directInsertSort();
        dichotomySort();
        selectSort();
        quickSort();
        heapSort();
        conflation();
        hillSort();
        bubbleSort();


    }
//------------------------------------------------------------------------------------------------------------------------------- 直接插入排序

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
        for (int i = 1; i < len; i++) {
            int e = arr[i]; //要被插的
            int j;

            //迭代此元素以前的元素(已经有序了), 依次与当前元素做判断,
            for (j = i - 1; j >= 0; j--) {
                if (e < arr[j]) { //大于当前元素  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 右移动
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
                arr[j] = e; //循环结束找到属于自己的位置,则进行设值.
            }
        }
        System.out.println("排序之后：" + Arrays.toString(arr));
    }

//------------------------------------------------------------------------------------------------------------------------------- 二分法插入排序

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

            for (int j = i - 1; j >= l; j--) { // left到j-1之间的元素向  !!!!!!!!!!!!!!右移动.
                arr[j + 1] = arr[j];
            }

            if (l != i) {
                arr[l] = e;
            }
        }
        System.out.println("排序之后：" + Arrays.toString(arr));
    }

//------------------------------------------------------------------------------------------------------------------------------- 选择排序

    /**
     * 每趟从待排序的记录序列中选择关键字最小的记录放置到已排序表的最前位置，直到全部排完。
     * <p>
     * ①简单的选择排序
     * 　　1、基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
     * <p>
     * <p>
     * 简单选择排序是不稳定的排序
     * 时间复杂度: O(n2)
     */
    public static void selectSort() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：" + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int mark = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    mark = j;
                }
            }
            arr[mark] = arr[i];     // ============ 交换位置
            arr[i] = min;
        }

        System.out.println("排序之后：" + Arrays.toString(arr));
    }


//------------------------------------------------------------------------------------------------------------------------------- 快速排序

    /**
     * ②快速排序
     *
     * @link http://www.cnblogs.com/0201zcr/p/4763806.html
     * <p>
     * <p>
     * 　　基本思想：通过一趟排序将待排序记录分割成独立的两部分，其中一部分记录的关键字均比另一部分关键字小，则分别对这两部分继续进行排序，直到整个序列有序。
     * <p>
     * 分析：
     * <p>
     * 快速排序是不稳定的排序。
     * 　　快速排序的时间复杂度为O(nlogn)。
     * 　　快速排序是通常被认为在同数量级（O(nlog2n)）的排序方法中平均性能最好的。 当n较大时使用快排比较好，当序列基本有序时用快排反而不好。
     * 但若初始序列按关键码有序或基本有序时，快排序反而蜕化为冒泡排序。
     * 为改进之，通常以“三者取中法”来选取基准记录，即将排序区间的两个端点与中点三个记录关键码居中的调整为支点记录。快速排序是一个不稳定的排序方法。
     */
    public static void quickSort() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：" + Arrays.toString(arr));
        quickSortRecursive(arr, 0, arr.length - 1);
        System.out.println("排序之后：" + Arrays.toString(arr));
    }

    /**
     * 分治递归排序
     *
     * @param numbers 带排序数组
     * @param low     开始位置
     * @param high    结束位置
     */
    private static void quickSortRecursive(int[] arr, int l, int h) {
        if (l < h) { // 头尾指针不重合才保存递归
            int middle = getMiddle(arr, l, h); //将numbers数组根据轴进行一分为二
            quickSortRecursive(arr, l, middle - 1);   //对低字段表进行递归排序
            quickSortRecursive(arr, middle + 1, h); //对高字段表进行递归排序
        }
    }

    /**
     * 查找出中轴（默认是最低位low l）的在arr数组排序后所在位置
     * <p>
     * 把整个序列看做一个数组，把第零个位置看做中轴，和最后一个比，如果比它小交换，比它大不做任何处理；交换了以后再和小的那端比，比它小不交换，比他大交换。
     * 这样循环往复，一趟排序完成，左边就是比中轴小的，右边就是比中轴大的，然后再用分治法，分别对这两个独立的数组进行排序。
     *
     * @param arr 带查找数组
     * @param l   开始位置
     * @param h   结束位置
     * @return 中轴所在位置
     */
    public static int getMiddle(int[] arr, int l, int h) {
        int temp = arr[l]; //数组的第一个作为中轴
        while (l < h) {
            while (l < h && arr[h] >= temp) { //从右往左找, 找到小于等于轴值的为止,将这个小值丢到l处.
                h--;
            }
            arr[l] = arr[h];//比中轴小的记录移到低端
            while (l < h && arr[l] <= temp) {//从左往右找, 找到大于等于轴值的为止,将这个大值丢到h处.
                l++;
            }
            arr[h] = arr[l]; //比中轴大的记录移到高端
        }
        arr[l] = temp; //此时l与h为==关系. 将l存入temp轴值.
        System.out.println("======== " + Arrays.toString(arr));
        return l; // 返回中轴的位置
    }

    //-------------------------------------------------------------------------------------------------------------------- 堆排序
    public static void heapSort() {
    }

    //-------------------------------------------------------------------------------------------------------------------- 归并排序
    public static void conflation() {
    }

    //-------------------------------------------------------------------------------------------------------------------- 希尔排序
    public static void hillSort() {
    }

    //-------------------------------------------------------------------------------------------------------------------- 冒泡排序
    public static void bubbleSort() {
    }

}
