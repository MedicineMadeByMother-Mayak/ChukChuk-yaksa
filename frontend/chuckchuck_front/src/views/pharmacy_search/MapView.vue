<template>
  <div class="view-container">
    <div id="map"></div>
    <div id="list" :style="{ marginTop: `${heightNum}px` }">
      <div
        class="line-box"
        v-touch:swipe.bottom="listDown"
        v-touch:swipe.up="listUp"
      >
        <img
          :src="mapLine"
          alt=""
          v-touch:swipe.bottom="listDown"
          v-touch:swipe.up="listUp"
        />
      </div>
      <p>{{ formattedDate }} 기준, 판매중인 약국입니다.</p>
      <div class="pharmacy-list">
        <PharmacyCard
          v-for="pharmacy in resultList"
          :key="pharmacy.address"
          :title="pharmacy.title"
          :address="pharmacy.address"
          :end="pharmacy.end"
          :status="pharmacy.status"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
const { VITE_KAKAO_KEY, VITE_DATA_KEY } = import.meta.env;
import PharmacyCard from "@/common/PharmacyCard.vue";
import mapLine from "@/assests/img/mapLine.svg";
import { instance } from "@/util/mainAxios";
import axios from "axios";
const pillId = sessionStorage.getItem("pillId");
const map = ref(null);
// 슬라이드를 위한 margin
const heightNum = ref(550);
// 사용자 위도
const lat = ref(36.355328);
// 사용자 경도
const long = ref(127.298295);
// 사용자 도시
const city = ref("대전");
// 사용자 지역
const area = ref("유성구");
// 마커를 위한 배열
const positions = [];
// 결과목록
const resultList = ref([]);

const now = new Date();
const year = now.getFullYear(); // 현재 년도
const month = now.getMonth() + 1; // 월 (0부터 시작하므로 1을 더함)
const day = now.getDate(); // 일

// 오늘의 날짜
const formattedDate = `${year}/${month < 10 ? `0${month}` : month}/${
  day < 10 ? `0${day}` : day
}`;

// 오늘의 요일 1:월, 2:화, 3:수, 4:목, 5:금, 6:토, 7:일
const today = now.getDay() === 0 ? 7 : now.getDay();

// 카카오 맵 API를 위한 초기 설정 script
const script = document.createElement("script");
script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${VITE_KAKAO_KEY}&libraries=services`;
document.head.appendChild(script);
script.onload = function () {
  saveLocal();
  kakao.maps.load(initMap);
};
function saveLocal() {
  // 사용자 위치 구하기
  navigator.geolocation.getCurrentPosition(showYourLocation, showErrorMsg, {
    enableHighAccuracy: true,
    timeout: 10000,
  });
}

function showYourLocation(position) {
  // 성공했을때 실행
  lat.value = position.coords.latitude;
  long.value = position.coords.longitude;
}

function showErrorMsg(error) {
  // 실패했을때 실행
  switch (error.code) {
    case error.PERMISSION_DENIED:
      alert("위치 정보 활용을 동의해주시기 바랍니다.");
      break;

    // default:
    //   alert("알 수 없는 오류로 추후 다시 시도해주시기 바랍니다.");
    //   break;
  }
}

async function searchPharmacy() {
  const { data } = await instance.get("/pharmacy-search", {
    params: {
      city: city.value,
      area: area.value,
      pillId: pillId,
      page: 1,
    },
  });

  return data;
}

// 카카오 맵 추가 설정
async function initMap() {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(lat.value, long.value),
    level: 3,
  };

  map.value = new kakao.maps.Map(container, options);

  const geocoder = new kakao.maps.services.Geocoder();
  const places = new kakao.maps.services.Places();

  await new Promise((resolve, reject) => {
    geocoder.coord2RegionCode(long.value, lat.value, function (result, status) {
      if (status === kakao.maps.services.Status.OK) {
        city.value = result[0].region_1depth_name;
        if (city.value === "대전광역시") {
          city.value = "대전";
        }
        area.value = result[0].region_2depth_name;
      }
      resolve();
    });
  });

  searchPharmacy().then((data) => {
    data.pharmacies.forEach((pharmacy) => {
      places.keywordSearch(pharmacy.title, placesCallback, {
        location: new kakao.maps.LatLng(lat.value, long.value),
        radius: 5000,
        size: 1,
        sort: kakao.maps.services.SortBy.DISTANCE,
      });
    });
  });
}

const placesCallback = async function (result, status) {
  if (status === kakao.maps.services.Status.OK) {
    await new Promise((resolve, reject) => {
      positions.push({
        title: result[0].place_name,
        latlng: new kakao.maps.LatLng(result[0].y, result[0].x),
      });

      axios
        .get(
          "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire",
          {
            params: {
              serviceKey: VITE_DATA_KEY,
              Q0: city.value,
              Q1: area.value,
              QT: today,
              QN: result[0].place_name,
            },
          }
        )
        .then((response) => {
          const hours = now.getHours();
          const minutes = now.getMinutes();
          const currentTime = parseInt(
            `${hours < 10 ? "0" : ""}${hours}${
              minutes < 10 ? "0" : ""
            }${minutes}`
          );

          try {
            if (`dutyTime${today}s` in response.data.response.body.items) {
              const start =
                response.data.response.body.items.item[`dutyTime${today}s`];
              const end =
                response.data.response.body.items.item[`dutyTime${today}c`];
              const status =
                currentTime >= start && currentTime <= end ? true : false;

              resultList.value.push({
                title: result[0].place_name,
                address: result[0].address_name,
                end: end.toString(),
                status: status,
              });
            } else {
              // 휴무로 인해 영업시간이 없음
              resultList.value.push({
                title: result[0].place_name,
                address: result[0].address_name,
                end: null,
                status: false,
              });
            }
          } catch (error) {
            // api key 인식 못함, 일단 휴무로 처리
            resultList.value.push({
              title: result[0].place_name,
              address: result[0].address_name,
              end: null,
              status: false,
            });
          }
        });
      resolve();
    });
  }

  // 마커 찍기
  createMaker();
};

function createMaker() {
  const imageSrc =
    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

  // 마커 이미지의 이미지 크기 입니다
  const imageSize = new kakao.maps.Size(24, 35);

  // 마커 이미지를 생성합니다
  const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

  for (let i = 0; i < positions.length; i++) {
    // 마커를 생성합니다
    const marker = new kakao.maps.Marker({
      map: map.value, // 마커를 표시할 지도
      position: positions[i].latlng, // 마커를 표시할 위치
      title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });
  }
}

// 목록 내리기
const listDown = () => {
  heightNum.value = 550;
};

// 목록 올리기
const listUp = () => {
  heightNum.value = 44;
};
</script>

<style scoped>
.view-container {
  width: 100%;
  height: 100%;
  position: relative;
  overflow: hidden;
}

#map {
  width: 100%;
  height: 100%;
}

#list {
  top: 0;
  content: "";
  width: 100%;
  height: 100%;
  background-color: white;
  position: absolute;
  border-radius: 36px 36px 0 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  z-index: 1;
  transition: margin-top 0.5s ease;
}

.line-box {
  margin: 8px 0 20px 0;
  width: 100%;
  display: flex;
  justify-content: center;
}

#list > p {
  margin: 0 0 20px 0;
}

.pharmacy-list {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 13px;
  overflow: scroll;
  padding-bottom: 43%;
}
</style>
