package com.atguigu.springboot.serv;
  
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
 
 
public class HttpClientUtil {
	public static final String urlString = "http://tpck.gzmzb.com/Home/Candidate/13?voteList=1";  //先登录保存cookie
	public static final String urlString2 = "http://......";
	public static String sessionId = "";
	
	public void doGet(String urlStr) throws IOException{
		String key = "";
		String cookieVal = "";
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect(); //到此步只是建立与服务器的tcp连接，并未发送http请求
		/** 
		 * 设置cookie
		 */
		if(!sessionId.equals("")){
			connection.setRequestProperty("Cookie", sessionId);
		}
		//String cookie = connection.getHeaderField("Set-Cookie");
        //System.out.println("read over" + cookie);
		for(int i=1;(key=connection.getHeaderField(i))!=null;i++){
			cookieVal = connection.getHeaderField(i);
			cookieVal = cookieVal.substring(0,cookieVal.indexOf(";")>-1?cookieVal.indexOf(";"):cookieVal.length()-1);
			sessionId = sessionId + cookieVal + ";";
		}
		
		//直到getInputStream()方法调用请求才真正发送出去
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=br.readLine()) != null){
			sb.append(line);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
		connection.disconnect();
	}
	
	/**
	 * 发送带参数的POST的HTTP请求
	 * 
	 * @param reqUrl
	 *            HTTP请求URL
	 * @param parameters
	 *            参数映射表
	 * @return HTTP响应的字符串
	 */
	public static String doPostCookie(String reqUrl, Map parameters,String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry element = (Entry) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(),"gb2312"));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
//			System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(HttpRequestProxy.connectTimeOut));// （单位：毫秒）jdk1.4换成这个,连接超时
//			System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(HttpRequestProxy.readTimeOut)); // （单位：毫秒）jdk1.4换成这个,读操作超时
			
			url_con.setConnectTimeout(500000);//（单位：毫秒）jdk 1.5换成这个,连接超时
			url_con.setReadTimeout(1000000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
			url_con.setRequestProperty("Accept-Charset", "gb2312");
			url_con.setRequestProperty("contentType", "gb2312");
			System.out.println("sessionId:"+sessionId);
			url_con.setRequestProperty("cookie", sessionId);
			
			url_con.setDoOutput(true);
			url_con.setDoInput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,"gb2312"));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = tempStr.toString();
			System.out.println("responseContent:"+responseContent);
			rd.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return decodeUnicode(responseContent);
	}
	
	 
	/**
     * 
     * unicode 转换成 中文
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
	
	public void doPost(String urlStr) throws IOException{
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true); //设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
		connection.setDoInput(true); // 设置连接输入流为true
		connection.setRequestMethod("POST"); // 设置请求方式为post
		connection.setUseCaches(false); // post请求缓存设为false
		connection.setInstanceFollowRedirects(true); //// 设置该HttpURLConnection实例是否自动执行重定向
		// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
        // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();
		
		// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
		DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
        String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()方法  为字符串进行编码           
        dataout.writeBytes(parm); 
        dataout.flush();
        dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)           
        // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder(); // 用来存储响应数据           
        // 循环读取流,若不到结尾处
        while ((line = bf.readLine()) != null) {
            sb.append(bf.readLine());
        }
        bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
        connection.disconnect(); // 销毁连接
        System.out.println(sb.toString()); 
	}
	 
}