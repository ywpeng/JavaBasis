package com.pyw.parse.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;

import java.net.URL;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.io.FileHandler;

/**
 * 文件处理工具类。目前网页抓取使用较多
 * @author zhaocg
 *
 */
public class FileUtil {


//	/**
//	 * 传入url并使用post方式获取source
//	 *
//	 * @param path
//	 * @param parametersBody
//	 * @param connectionTimeOut
//	 * @param soTimeOut
//	 * @param charsetName
//	 * @return
//	 * @throws HttpException
//	 * @throws IOException
//	 * @throws KettleException
//	 */
//	public static Source getSourceByPost(String path, NameValuePair[]  parametersBody, int connectionTimeOut, int soTimeOut, String charsetName) throws HttpException,
//			IOException {
//		try{
//			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
////			//获得本机IP Add By HuangJun 2012-08-20
////			InetAddress addr = InetAddress.getLocalHost();
////			String ip=addr.getHostAddress().toString().trim();
////			//本机使用192.168.12网段需要设置客户端代理IP才能抓取
////			if(ip.contains("192.168.12")){
////				httpClient.getHostConfiguration().setProxy("10.2.7.10", 8080);
//
//
//			HttpPost getMethod  = new PostMethod(path);
//			HttpClientParams params = httpClient.getParams();
//			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeOut);
//			httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeOut);
//		    params.setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5");
//		    //httpClient.setParams(params);
//		    getMethod.setRequestBody(parametersBody);;
//			int statusCode = httpClient.executeMethod(getMethod );
//			if (statusCode != HttpStatus.SC_OK) {
//				throw new KettleException("访问网络出错.网址：" + path );
//			}
//			InputStream is = getMethod .getResponseBodyAsStream();
//			Source source = new Source(new InputStreamReader(is, charsetName));
//			is.close();
//			source.getSource().clearCache();
//			getMethod.releaseConnection();
//			return source;
//		}catch(InterruptedIOException  e){
//			throw new KettleException("访问网络出错.网址：" + path ,e);
//		}
//	}

	public static void doPostTest(String url, Map params,String vjkl5) {

		CookieStore cookieStore = new BasicCookieStore();
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
//		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.build();

		// 创建Post请求
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Host","wenshu.court.gov.cn");
		httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
		//设置 cookie
		BasicClientCookie cookie = new BasicClientCookie("vjkl5", vjkl5);
//		cookie.setVersion(0);
//		cookie.setDomain("/pms/");   //设置范围
//		cookie.setPath("/");
		if (!"".equals(vjkl5)) {
			cookieStore.addCookie(cookie);
		}

		// 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");

		//设置post 参数
		String paramStr = JSONObject.toJSONString(params);
		StringEntity stringEntity = new StringEntity(paramStr,"utf-8");
		httpPost.setEntity(stringEntity);

		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Post请求
			response = httpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();

			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试一下验证码的使用
	 * @param url
	 * @return
	 */
	public static String doYzmTest(String url) {
		//第一次请求时获取的 cookie 中的内容
		String vjkl5 = null;

		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CookieStore cookieStore = new BasicCookieStore();
		//CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpClient  httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		// 创建Get请求
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Host","wenshu.court.gov.cn");
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Get请求
			response = httpClient.execute(httpGet);

			//请求后获取 cookie
			List<Cookie> cookies = cookieStore.getCookies();

			for (int i = 0; i < cookies.size(); i++) {
				if (cookies.get(i).getName().equals("vjkl5")) {
					vjkl5 = cookies.get(i).getValue();
					System.out.println(" 获取到的 cookie 的 vjkl5：" + vjkl5 );
				}
			}
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				//System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
				InputStream inputStream = new GzipDecompressingEntity(responseEntity).getContent();
				OutputStream out = new FileOutputStream(new File("G:\\newfile.png"));
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				inputStream.close();
				out.flush();
				out.close();
				System.out.println("Check file G:\\newfile.png");
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return vjkl5;
	}

	public static String doGetTest(String url) {
		//第一次请求时获取的 cookie 中的内容
		String vjkl5 = null;

		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CookieStore cookieStore = new BasicCookieStore();
		//CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpClient  httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		// 创建Get请求
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Host","wenshu.court.gov.cn");
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Get请求
			response = httpClient.execute(httpGet);

			//请求后获取 cookie
			List<Cookie> cookies = cookieStore.getCookies();

			for (int i = 0; i < cookies.size(); i++) {
				if (cookies.get(i).getName().equals("vjkl5")) {
					vjkl5 = cookies.get(i).getValue();
					System.out.println(" 获取到的 cookie 的 vjkl5：" + vjkl5 );
				}
			}
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			System.out.println("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				System.out.println("响应内容长度为:" + responseEntity.getContentLength());
				System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return vjkl5;
	}


//	public static String getYzm() {
//		//new一个URL对象
//		URL url;
//
//		String s="";
//		try {
//			url = new URL("http://172.16.30.226:8099/bms/checkcode.do?0.9858807739801705");
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			//设置请求方式为"GET"
//			conn.setRequestMethod("GET");
//			//超时响应时间为5秒
//			conn.setConnectTimeout(5 * 1000);
//			//通过输入流获取图片数据
//			InputStream inStream = conn.getInputStream();
//			//得到图片的二进制数据，以二进制封装得到数据，具有通用性
//			byte[] data = readInputStream(inStream);
//			//new一个文件对象用来保存图片，默认保存当前工程根目录
//			File imageFile = new File("D:/BeautyGirl.jpg");
//			//创建输出流
//			FileOutputStream outStream = new FileOutputStream(imageFile);
//			//写入数据
//			outStream.write(data);
//			//关闭输出流
//			outStream.close();
//
//			Runtime rt = Runtime.getRuntime();
//			rt.exec("cmd.exe /C  tesseract.exe D:\\BeautyGirl.jpg  D:\\ddd\\yzm -1 ");
//			Thread.sleep(1000);
//			File file = new File("D:\\ddd\\yzm.txt");
//			if(file.exists()) {
//				FileHandler fh = new FileHandler();
//				s = fh.readAsString(file).trim();
//				System.out.println("========="+s);
//			} else {
//				System.out.print("yzm.txt不存在");
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return s;
//		//打开链接
//
//	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while( (len=inStream.read(buffer)) != -1 ){
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}

}
