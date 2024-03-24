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
//@Transactional  필요없는 어노테이션이기에 제거
@RequiredArgsConstructor
public class PharmacyService {
    /**
     * 타이레놀 보유 약국 조회
     *
     * @author: 차현철
     * @param: String city, String area, Long pillId
     * @return: PharmacyDto[]
     */
    public PharmacyDto[] tylenolSearch(String city, String area) {
        JSONArray markers = null;
        try {
            // URL 설정
            String url = "https://www.tylenol.co.kr/system/ajax";

            // POST 요청을 보내기 위한 폼 데이터 구성
            Connection.Response response = Jsoup.connect(url)
                    .method(Connection.Method.POST) // POST 메소드 사용
                    .ignoreContentType(true) // JSON 등의 컨텐츠 타입을 받기 위함
                    // form 데이터 설정
                    .data("page_url", "finding-store")
                    .data("form_build_id", "form-L4ndx5L6Pqmes1WPIIJexIgPv8XdSsxvNS_XZCxbark")
                    .data("form_id", "jjbos_apac_storefinder_form")
                    .data("_triggering_element_name", "op")
                    .data("_triggering_element_value", "Address Filter Search")
                    .data("address_filter_1", city)
                    .data("address_filter_2", area)
                    .timeout(5000)
                    .execute();

            JSONArray jsonArray = new JSONArray(response.body());
            JSONObject jsonObject = jsonArray.getJSONObject(4);
            JSONObject settings = jsonObject.getJSONObject("settings");
            markers = settings.getJSONObject("optico_locator").getJSONArray("markers");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (markers == null || markers.isEmpty()) return new PharmacyDto[0];

        PharmacyDto[] pharmacyArr = new PharmacyDto[markers.length()];
        for (int i = 0; i < markers.length(); i++) {
            String title = (String) markers.getJSONObject(i).get("title");
            BigDecimal lat = (BigDecimal) markers.getJSONObject(i).get("lat");
            BigDecimal lng = (BigDecimal) markers.getJSONObject(i).get("lng");
            String address = (String) markers.getJSONObject(i).get("address");
            pharmacyArr[i] = new PharmacyDto(title, lat, lng, address);
        }

        return pharmacyArr;
    }
}
