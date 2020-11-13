package com.ssafy.SMDM.service;

import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class NaverShoppingService {

    static String getString(String input, String data) // API에서 필요한 문자 자르기.
    {
        String[] dataSplit = data.split("{" + input + "}");
        String[] dataSplit2 = dataSplit[1].split("\"" + input + "\"");
        return dataSplit2[0];
    }



    public List<String> findCategory(String findcategory){
        String clientId = "k_IClWbMNYXQW9cnZrHb";// 애플리케이션 클라이언트 아이디값";
        String clientSecret = "OWRYXDDQHm";// 애플리케이션 클라이언트 시크릿값";\
        String s1 = new String();
        String s2 = new String();

        List<String> li = new ArrayList<>();
        int display = 1; // 검색결과갯수. 최대100개
        try {
            String text = URLEncoder.encode(findcategory, "utf-8");
            String apiURL = "https://openapi.naver.com/v1/search/shop.json?query=" + text + "&display=" + display + "&";

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            con.disconnect();
            String data = sb.toString();
            String[] array;
            array = data.split("\"");
            String[] category = new String[display];
            int k = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals("category1")) {
                    s1= array[i + 2];
                }
                if (array[i].equals("category3")) {
                    s2= array[i + 2];
                }

            }
            li.add(s1);
            li.add(s2);

        } catch (Exception e) {
            System.out.println(e);
        }
        return li;

    }
}
