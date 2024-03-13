package com.weaponlin.skynet;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        CloseableHttpResponse response = null;
        try {
            //1.获得一个httpclient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //2.生成一个get请求
            HttpGet httpget = new HttpGet("http://127.0.0.1:8080/user/list");

            //3.执行get请求并返回结果
            response = httpclient.execute(httpget);
            System.out.println(response.getEntity().toString());
            //4.处理结果
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
    }
}
