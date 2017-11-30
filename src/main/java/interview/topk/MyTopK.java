package interview.topk;

import java.util.Arrays;

/**
 * @Copyright (C), 2000-9999 DuenBoa
 * @Author: DuenBoa on 2017/6/15 23:51
 * @Desc interview   topk.
 * <pre>
 * 海量数据求指定个数的最大值.
 * </pre>
 * @See
 */
public class MyTopK {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 2, 2, 5, 5, 4, 6, 46, 78, 6, 54, 32, 54, 6, 123, 46, 431, 34, 621, 3654, 315, 4561, 32, 4444444, 55555555, 999999};
        int[] topk = topk(arr, 3);
        System.out.println(Arrays.toString(topk));
    }

    public static int[] topk(int[] arr, int k) {
        int[] topArr = new int[k];
        for (int i = 0; i < k; i++) {
            topArr[i] = arr[i];
        }

        MyMinHeap minHeap = new MyMinHeap(topArr);
        for (int i = k; i < arr.length; i++) {
            int root = minHeap.getRoot();
            if (arr[i] > root) {
                minHeap.setRoot(arr[i]);
            }
        }
        return topArr;
    }


    /**** 内部类 构造小顶堆的 ****/
    private static class MyMinHeap {
        int[] data;

        //将数据构造成小顶堆
        public MyMinHeap(int[] arr) {
            data = arr;
            buildHeap();
        }

        private void buildHeap() {
            int haveChildMaxIndex = data.length / 2 - 1; //完全二叉树中 length/2-1以上的所有节点才有子节点.
            for (int i = 0; i <= haveChildMaxIndex; i++) {
                heapify(i);
            }
        }

        /*** 将堆的儿子节点都递归进行堆化处理 ***/
        private void heapify(int i) {
            int l = (i + 1) << 1 - 1; //如果当前节点是0的话 : 左节点角标 = (0+1)*2 - 1 = 1
            int r = (i + 1) << 1; // 如果当前节点是0的话 : 右节点角标 = (0+1) *2 = 2
            int smallest = i; //当前节点, 当前左子,当前右子 三者中最小值的角标.
            int length = data.length;

            //------------------------------------------------------- 当前节点,左子节点, 右子节点, 取最小的值所在节点的角标
            if (l < length && data[l] < data[smallest]) {
                smallest = l;
            }
            if (r < length && data[r] < data[smallest]) {
                smallest = r;
            }

            //当前节点非最小节点, 节点值交换, 递归将下面的节点堆化
            if (smallest != i) {
                swap(i, smallest);
                heapify(smallest);
            }

        }


        private void swap(int i, int j) {
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }

        private int getRoot() {
            return data[0];
        }

        /**** 根节点被替换之后, 需要调整堆内元素的顺序,保持完全二叉树的性质 ****/
        private void setRoot(int newRoot) {
            data[0] = newRoot;
            heapify(0);
        }
    }
}
