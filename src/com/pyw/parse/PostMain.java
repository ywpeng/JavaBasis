package com.pyw.parse;



import com.pyw.parse.util.FileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pyw
 * @create 2019-07-12 23:31
 */
public class PostMain {
    //对 http://wenshu.court.gov.cn/ 的抓取测试
    // 1.尝试获取 cookie中的内容

//    // 2.获取翻页数据
//    NameValuePair[] params = {
//            new NameValuePair("Param", "案件类型:行政案件"),
//            new NameValuePair("Index", "2"),
//            new NameValuePair("Page", "10"),
//            new NameValuePair("Order", "法院层级"),
//            new NameValuePair("Direction", "asc"),
//            new NameValuePair("vl5x", "40dacdeccffa17a3a4f1ab81"),
//            new NameValuePair("number", "0.5249479885797912")
//            // ,   new NameValuePair("guid", "bfc55cdf-c41f-7a3cbb35-2eeb0d3033b7")
//    };




//    FileUtil.doPostTest("http://wenshu.court.gov.cn/List/ListContent", params);

    public static void main(String[] args) {
//       //1.通过get方式获取首页
//        System.out.println("======== 1.通过get方式获取首页");
//        String vjkl5 = FileUtil.doGetTest("http://wenshu.court.gov.cn/List/List?sorttype=1&conditions=searchWord+1++%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6+%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E5%88%91%E4%BA%8B%E6%A1%88%E4%BB%B6");
//        //String vjkl5 = "1be5faca94ffa8ea94013e18d49e1ee114132b89";
//        String vl5x = "e2619bdd804712c572ba24c3";
//        String guid = "4d2df0cf-ff8c-6129c5b9-c6ec231bca5c";
//        //2.获取验证码
//        System.out.println("======== 2.获取验证码");
//        FileUtil.doYzmTest("http://wenshu.court.gov.cn/ValiCode/CreateCode/?guid=" + guid);

//        //3.验证一下呗屏蔽的方法看能不能捡个漏
//        System.out.println("======== 3.验证一下呗屏蔽的方法看能不能捡个漏");
//        Map params0 = new HashMap();
//        params0.put("guid",guid);
//        FileUtil.doPostTest("http://wenshu.court.gov.cn/ValiCode/GetCode",params0,"");
//
//        //4.获取每一页的数据
//        System.out.println("======== 4.获取每一页的数据");
//        //构建 post 的请求参数
//        double yzmD = Math.random();
//        String yzm = Double.toString(yzmD);
//        Map params = new HashMap();
//        params.put("Param","案件类型:行政案件");
//        params.put("Index",4);
//        params.put("Page","10");
//        params.put("Order","法院层级");
//        params.put("Direction","asc");
//        params.put("vl5x",vl5x);
//        params.put("number",yzm);
//        params.put("guid",guid);
//
//        String pageUrl  = "http://wenshu.court.gov.cn/List/ListContent";
//        FileUtil.doPostTest(pageUrl,params,vjkl5);
    }

}
