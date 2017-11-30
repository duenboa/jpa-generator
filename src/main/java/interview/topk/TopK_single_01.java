package interview.topk;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Copyright (C), 2012-2020 上海好屋网信息技术有限公司
 * @Author: DengBenbo 20160289
 * @Date: 2017/6/15
 * @Version: 1.0.0
 * @Description: 海量数据求top K
 * 例题：搜索引擎会通过日志文件把用户每次检索使用的所有检索串都记录下来，每个查询串的长度为1-255字节。
 * 假设目前有一千万个记录（这些查询串的重复度比较高，虽然总数是1千万，但如果除去重复后，不超过3百万个。
 * 一个查询串的重复度越高，说明查询它的用户越多，也就是越热门。），请你统计最热门的10个查询串，要求使用的内存不能超过1G。
 * 本题的解法思路有几种，本文只提供其中一种实现，即HashMap+堆
 * 第一步：
 * Hash统计，创建一个key为查询字串，value为次数的Hash表，插入记录时，如果已经存在该记录，则把该记录的value+1,否则插入一个value为1的记录。
 * 整个过程的时间复杂度为log(n);
 * 第二步：
 * 维护一个大小为K的小顶堆，最先存储的是先从Hash表中遍历到的K的记录，继续遍历，每次把得到的元素与对顶元素比较，如果大于则调整堆，小于则跳过继续。
 * 整个过程时间包括，建堆和多次更新堆的时间，整体的时间复杂度为nlog(k);
 */
public class TopK_single_01 {

    //每行封装成一个记录，便于以后处理其他信息，如时间等等
    static class Record {
        private String searchKey;

        public String getSearchKey() {
            return searchKey;
        }

        public void setSearchKey(String s) {
            this.searchKey = s;
        }

        public Record() {

        }
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        //URL resource = Class.class.getResource("");
        //String path = resource.getPath();
        File f = new File("d://topkTest.txt");
        BufferedReader reader = null;
        int k = 3;//测试用例记录较少，这里指定为3
        Record record = new Record();
        try {
            reader = new BufferedReader(new FileReader(f));
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                record.setSearchKey(tmp);
                insert(record, map);
            }
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry e = (Map.Entry) iter.next();
                System.out.println(e.getKey() + " " + e.getValue());
            }
            Map.Entry[] result = getTopKRecord(map, k);
            for (Map.Entry<String, Integer> e : result) {
                System.out.println(e.getKey() + " " + e.getValue());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Hash表统计次数
    private static void insert(Record record, Map<String, Integer> map) {
        String url = record.getSearchKey();
        if (map.containsKey(url)) {
            map.put(url, map.get(url) + 1);
        } else
            map.put(url, 1);
    }

    //维持一个大小为k的最小堆
    public static Map.Entry[] getTopKRecord(Map<String, Integer> map, int k) {
        int i = 0;
        Iterator iter = map.entrySet().iterator();
        Map.Entry[] elements = new Map.Entry[k];
        while (iter.hasNext()) {
            Map.Entry e = (Map.Entry) iter.next();
            if (i <= k - 1) {
                elements[i] = e;
                if (i == k - 1) {
                    buildMinHeap(elements);
                }
                i++;
            } else {
                insertHeap(e, elements);
            }
        }
        return elements;
    }

    private static void insertHeap(Map.Entry<Object, Integer> n, Map.Entry<Object, Integer>[] heap) {
        if ((int) n.getValue() > (int) heap[0].getValue()) {
            heap[0] = n;
            minHeap(heap, 0, heap.length);
        }

    }

    private static void buildMinHeap(Map.Entry[] heap) {
        int i = heap.length / 2 - 1;
        for (; i >= 0; i--) {
            minHeap(heap, i, heap.length);
        }
    }

    private static void minHeap(Map.Entry<Object, Integer>[] heap, int i, int length) {
        int left, right, min;
        Map.Entry temp;
        left = 2 * i + 1;
        right = 2 * i + 2;
        min = i;
        if (left <= length - 1 && (int) heap[left].getValue() < (int) heap[i].getValue()) {
            min = left;
        }
        if (right <= length - 1 && (int) heap[right].getValue() < (int) heap[min].getValue()) {
            min = right;
        }
        if (min != i) {
            temp = heap[i];
            heap[i] = heap[min];
            heap[min] = temp;
            minHeap(heap, min, length);
        }
    }

}
