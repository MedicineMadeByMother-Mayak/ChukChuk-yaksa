package com.mayak.chuckchuck.service;

import com.mayak.chuckchuck.dto.PharmacyDto;
import com.mayak.chuckchuck.exception.ErrorResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class PharmacyService {
    public PharmacyDto[] tylenolSearch(String city, String area, Long pillId) {
        try {
            // URL 설정
            String url = "https://www.tylenol.co.kr/system/ajax";

            // POST 요청을 보내기 위한 폼 데이터 구성
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.POST) // POST 메소드 사용
                    .ignoreContentType(true) // JSON 등의 컨텐츠 타입을 받기 위함
                    // form 데이터 설정
                    .data("page_url", "finding-store")
                    .data("form_build_id", "form-n3t8drzz-DgrJW1SDfYrPFLoI3WDxpX-7ECiBzg8LJE")
                    .data("form_id", "jjbos_apac_storefinder_form")
                    .data("_triggering_element_name", "op")
                    .data("_triggering_element_value", "Address Filter Search")
                    .data("address_filter_1", city)
                    .data("address_filter_2", area)
                    .execute();

            JSONArray jsonArray = new JSONArray(response.body());
            JSONObject jsonObject = jsonArray.getJSONObject(4);
            JSONObject settings = jsonObject.getJSONObject("settings");
            JSONArray markers = settings.getJSONObject("optico_locator").getJSONArray("markers");

            PharmacyDto[] parmacyArr = new PharmacyDto[markers.length()];
            for (int i = 0; i < markers.length(); i++) {
                System.out.println(markers.getJSONObject(i).get("lat").getClass());
                String title = (String) markers.getJSONObject(i).get("title");
                BigDecimal lat = (BigDecimal) markers.getJSONObject(i).get("lat");
                BigDecimal lng = (BigDecimal) markers.getJSONObject(i).get("lng");
                String address = (String) markers.getJSONObject(i).get("address");
                parmacyArr[i] = new PharmacyDto(title, lat, lng, address);
            }

            return parmacyArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
