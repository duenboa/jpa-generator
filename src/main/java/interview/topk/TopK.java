package interview.topk;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/15
 * @Version: 1.0.0
 * @Description:
 * @History: 变更记录 作者：叉叉哥   转载请注明出处：http://blog.csdn.net/xiao__gui/article/details/8687982
 * <pre>
 * 堆有几个重要操作：
 * BuildHeap：将普通数组转换成堆，转换完成后，数组就符合堆的特性：所有父节点的值小于或等于两个子节点的值。
 * Heapify(int i)：当元素i的左右子树都是小根堆时，通过Heapify让i元素下降到适当的位置，以符合堆的性质。
 * 回到上面的取TopK问题上，用最小堆的解决方法就是：先去源数据中的K个元素放到一个长度为K的数组中去，再把数组转换成最小堆。
 * 再依次取源数据中的K个之后的数据和堆的根节点（数组的第一个元素）比较，根据最小堆的性质，
 * 根节点一定是堆中最小的元素，如果小于它，则直接pass，大于的话，就替换掉跟元素，并对根元素进行Heapify，直到源数据遍历结束。
 * </pre>
 * @see interview.topk.MinHeap 依赖
 */
public class TopK {

    public static void main(String[] args) {
        // 源数据
        int[] data = {56, 275, 12, 6, 45, 478, 41, 1236, 456, 12, 546, 45, 7878, 45, 78, 451, 354, 68132, 16, 498, 432, 16, 46, 84, 251, 34, 84, 13651, 6, 48, 461, 3, 13, 5416, 513};

        // 获取Top5
        int k = 3;
        int[] top5 = topK(data, k);

        for (int i = 0; i < k; i++) {
            System.out.println(top5[i]);
        }
    }

    // 从data数组中获取最大的k个数
    private static int[] topK(int[] data, int k) {
        // 先取K个元素放入一个数组topk中
        int[] topk = new int[k];
        for (int i = 0; i < k; i++) {
            topk[i] = data[i];
        }

        // 转换成最小堆
        MinHeap heap = new MinHeap(topk);

        // 从k开始，遍历data
        for (int i = k; i < data.length; i++) {
            int root = heap.getRoot();

            // 当数据大于堆中最小的数（根节点）时，替换堆中的根节点，再转换成堆
            if (data[i] > root) {
                heap.setRoot(data[i]);
            }
        }

        return topk;
    }

}
