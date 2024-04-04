package com.mayak.chuckchuck.utill;

import com.mayak.chuckchuck.exception.ErrorCode.CommonErrorCode;
import com.mayak.chuckchuck.exception.RestApiException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class OpenApiManager {

    @Value("${openApi.illCode.url}")
    private String openApiBaseUrl;
    @Value("${openApi.illCode.secretKey}")
    private String openApiKey;
    private final String keyParam = "?serviceKey=";
    private final String queryParam = "&sickType=2&medTp=1&diseaseType=SICK_CD&searchText=";
    private String makeOpenApiUrl(String illCode) {
        return openApiBaseUrl+keyParam+openApiKey+queryParam+illCode;
    }

    //openApi를 통해 진단명 파싱
    public String getIllNamethroughOpenApi(String illCode) {
        try {
            String url = makeOpenApiUrl(illCode);

            System.out.println("url 생성 "+url);

            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            con.connect();

            BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            StringBuffer st = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                st.append(line);
            }
            System.out.println("st.toString() : "+st.toString());

            JSONObject xmlJSONObj = XML.toJSONObject(st.toString());
            // response 아래에 있는 body 객체의 items 값을 추출
            JSONObject response = xmlJSONObj.getJSONObject("response");
            JSONObject body = response.getJSONObject("body");
            System.out.println("xmlJSONObj : "+xmlJSONObj);

            // items 값을 추출
            Object itemsValue = body.get("items");
            // items가 문자열인 경우
            if (itemsValue instanceof String)  return null;
            // items가 JSONObject인 경우
            else if (itemsValue instanceof JSONObject) {
                //결과값이 있으면 아이템을 꺼냄
                JSONObject items = body.getJSONObject("items");
                // items 아래에 있는 item 객체의 sickNm 값을 추출
                JSONObject item = items.getJSONObject("item");
                String sickNmValue = item.getString("sickNm");
                return sickNmValue;
            } else {
                return "";
            }
        } catch (IOException e) {
            throw new RestApiException(CommonErrorCode.OPENAPI_RESPONSE_ERROR);
        }
    }
}
