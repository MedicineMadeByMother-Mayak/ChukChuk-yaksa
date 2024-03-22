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
    private final String queryParam = "&numOfRows=1&pageNo=1&sickType=2&medTp=1&diseaseType=SICK_CD&searchText=";
    private String makeOpenApiUrl(String illCode) {
        return openApiBaseUrl+keyParam+openApiKey+queryParam+"069";
    }

    //openApi를 통해 진단명 파싱
    public void getIllNamethroughOpenApi(String illCode) {
        try {
            String url = makeOpenApiUrl(illCode);
            System.out.println("url : "+url);


            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

            con.connect();

            BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            StringBuffer st = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                st.append(line);
            }

            JSONObject xmlJSONObj = XML.toJSONObject(st.toString());
            String jsonPrettyPrintString = xmlJSONObj.toString(4);
            System.out.println(jsonPrettyPrintString);


        } catch (IOException e) {
            throw new RestApiException(CommonErrorCode.OPENAPI_RESPONSE_ERROR);
        }
    }
}
