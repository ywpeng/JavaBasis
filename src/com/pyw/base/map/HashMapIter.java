package com.pyw.base.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author pyw
 *  2019-06-24 22:55
 */
public class HashMapIter {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("1","zhangsan"); //复制一行的快捷键：ctrl + d
        map.put("2","lisi");
        map.put("3","wangwu3");
        map.put("4","wangwu4");
        map.put("5","wangwu5");



        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key=" + key + " value="+ map.get(key));
        }

        //第二种：entrySet.iter
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }

    }
}
