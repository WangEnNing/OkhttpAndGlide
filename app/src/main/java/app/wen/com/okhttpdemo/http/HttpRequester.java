package app.wen.com.okhttpdemo.http;

import org.apache.http.conn.ConnectTimeoutException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

import app.wen.com.okhttpdemo.utils.FormFile;

/**
 * Copyright 2014-2015 Secken, Inc. All Rights Reserved.
 *
 * @author lizhangfeng
 * @version V1.2.3
 * @Description: 网络请求之HttpURLConnection
 * @date 2015-6-5 下午12:27:15
 */
public class HttpRequester {
    private static int REQUEST_TIME_OUT = 10 * 1000;

    /**
     * 上传图片
     *
     * @param sensetime_url
     * @param params
     * @param formFile
     * @return
     */
    public static String post(String sensetime_url, Map<String, String> params, FormFile formFile) {
        String result = "";
        String PREFIX = "--";
        String LINEND = "\r\n";
        String CHARSET = "UTF-8";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        params.clear();
//        FakeX509TrustManager.allowAllSSL();
        try {
            URL url = new URL(sensetime_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(REQUEST_TIME_OUT);
            conn.setReadTimeout(REQUEST_TIME_OUT);
            conn.setChunkedStreamingMode(0);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
            // 首先组拼文本类型的参数
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }
            DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(sb.toString().getBytes());

            /**
             * 下面是传图
             */
            StringBuilder sb1 = new StringBuilder();
            sb1.append(PREFIX);
            sb1.append(BOUNDARY);
            sb1.append(LINEND);
            sb1.append("Content-Disposition: form-data; name=\"" + formFile.inputStreamKey + "\"; filename=\""
                    + formFile.fileName + "\"" + LINEND);
            sb1.append("Content-Type: image/jpg; charset=" + CHARSET + LINEND);
            sb1.append(LINEND);
            outStream.write(sb1.toString().getBytes());
            outStream.write(formFile.dates);
            outStream.write(LINEND.getBytes());
            // 请求结束标志
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
            outStream.write(end_data);
            outStream.flush();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return result;
            }
            InputStream in = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in, CHARSET);
            BufferedReader bufReader = new BufferedReader(isReader);
            result = bufReader.readLine();
            conn.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {// 连接超时
            e.printStackTrace();

            return result;
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    /**
     * 普通请求网络
     *
     * @param sensetime_url
     * @param map
     * @return
     */
    public static String post(String sensetime_url, Map<String, String> map) {
        String CHARSET = "UTF-8";
        String result = "";
        try {
            URL url = new URL(sensetime_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// 提交模式
            conn.setReadTimeout(REQUEST_TIME_OUT);// 读取超时 单位毫秒
            conn.setConnectTimeout(REQUEST_TIME_OUT);// 连接超时
            conn.setUseCaches(false);
            conn.setDoOutput(true);// 是否输入参数
            StringBuffer params = new StringBuffer();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            byte[] bypes = params.toString().substring(1, params.length()).getBytes();
            conn.getOutputStream().write(bypes);
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return null;
            }
            InputStream in = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(in, CHARSET);
            BufferedReader bufReader = new BufferedReader(isReader);
            result = bufReader.readLine();
            conn.disconnect();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }// 输入参数
        return result;
    }

}
