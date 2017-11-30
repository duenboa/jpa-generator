package interview.topk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/16
 * @Version: 1.0.0
 * : 海量字符串频次 求topk
 * <pre>
 * * 例题：搜索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255字节。
 * 假设目前有一千万个记录（这些查询串的重复度比较高，虽然总数是1千万，但如果除去重复后，不超过3百万个。
 * 一个查询串的重复度越高，说明查询它的用户越多，也就是越热门。），请你统计最热门的10个查询串，要求使用的内存不能超过1G。
 * 本题的解法思路有几种，本文只提供其中一种实现，即HashMap+堆
 * 第一步：
 * Hash统计，创建一个key为查询字串，value为次数的Hash表，插入记录时，如果已经存在该记录，则把该记录的value+1,否则插入一个value为1的记录。
 * 整个过程的时间复杂度为log(n);
 * 第二步：
 * 维护一个大小为K的小顶堆，最先存储的是先从Hash表中遍历到的K的记录，继续遍历，每次把得到的元素与对顶元素比较，如果大于则调整堆，小于则跳过继续。
 * 整个过程时间包括，建堆和多次更新堆的时间，整体的时间复杂度为nlog(k);
 * </pre>
 * @see interview.topk.MyTopK 求给的海量数组中最大值的topk
 */
public class FrequenceTopK {


    public static void main(String[] args) {
        String[] arr = new String[]{"a", "a", "a", "a", "a", "a", "c", "a", "b", "b", "b", "b", "b", "d", "dd", "da", "ad", "ad", "ac", "ac", "ac", "dc", "dd", "ads", "da", "dd", "a", "c", "dd", "d", "d", "da", "ad", "d", "ad", "da", "a"};
        Map.Entry<String, Integer>[] topk = topk(arr, 5);
        System.out.println(Arrays.toString(topk));
    }


    private static Map.Entry<String, Integer>[] topk(String[] arr, int k) {
        Map<String, Integer> map = toMap(arr);

        // 先将指定top个数的元素丢进数组,构造小顶堆
        Map.Entry<String, Integer>[] topk = new Map.Entry[k];
        String[] unusefulKeys = new String[k];
        int mark = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (mark < k) {
                topk[mark] = entry;
                unusefulKeys[mark] = entry.getKey();
                mark++;
            } else {
                break;
            }
        }

        //添加到初始的小顶堆中的数据在大的map中需要剔除.避免重复.
        for (String key : unusefulKeys) {
            map.remove(key);
        }

        MinHeap minHeap = new MinHeap(topk);

        //所有值都与堆根比较,把堆中的最小值替换掉.
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            Integer root = minHeap.getRoot().getValue();
            // 与小顶堆的根比较.
            if (value > root) {
                minHeap.setRoot(entry);
            }
        }

        return minHeap.getData();
    }

    private static Map<String, Integer> toMap(String[] arr) {
        Map<String, Integer> map = new HashMap();
        for (String s : arr) {
            if (map.get(s) == null) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        return map;
    }


    /****** 静态内部类 小顶堆结构 ***********/
    private static class MinHeap {
        Map.Entry<String, Integer>[] data;

        public Map.Entry<String, Integer>[] getData() {
            return data;
        }


        public MinHeap(Map.Entry<String, Integer>[] mapArr) {
            data = mapArr;
            buildHeap();
        }

        private void buildHeap() {
            int haveChildIndex = (data.length >> 2) - 1; //对于使用数组构造成的完全二叉树来说, 数组长度/2 -1 以上的节点都有子节点.
            for (int i = 0; i < haveChildIndex; i++) {
                heapify(i);
            }
        }

        private void heapify(int i) {
            int l = (i + 1) << 2 - 1; //左子节点 这种算法是完全二叉树性质
            int r = (i + 1) << 2; //右子节点
            int smallest = i; //角标
            int length = data.length;

            if (l < length && data[l].getValue() < data[smallest].getValue()) {
                smallest = l;
            }

            if (r < length && data[r].getValue() < data[smallest].getValue()) {
                smallest = r;
            }

            if (smallest != i) {
                Map.Entry<String, Integer> tmp = data[i];
                data[i] = data[smallest];
                data[smallest] = tmp;

                heapify(smallest);
            }
        }

        private Map.Entry<String, Integer> getLeft(int i) {
            return data[(i + 1) << 2 - 1];
        }

        private Map.Entry<String, Integer> getRight(int i) {
            return data[(i + 1) << 2];
        }

        public Map.Entry<String, Integer> getRoot() {
            return data[0]; //小顶堆第一个元素就是根. 不需要找parent
        }


        public void setRoot(Map.Entry<String, Integer> newRoot) {
            data[0] = newRoot;
            heapify(0); //改变了根, 根以下节点都需要进行调整.
        }

    }

}
