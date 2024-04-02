<template>
  <div class="header-background">
    <!-- <img src="@/assests/img/main_page_background.svg" /> -->
    <img src="@/assests/img/Rectangle.svg" />
    <!-- <img src="@/assests/img/Pil.png" /> -->
  </div>
  <div class="basic-background">
    <div class="basic-content">
      <div class="hello">
        <div class="hello-section">
          <img
            src="@/assests/icon/main_page/main_icon.svg"
            style="border-radius: 30px"
          />
          <div class="hello-text">안녕하세요, {{ store.userName }}님!</div>
        </div>
        <img class="hello-pic" src="@/assests/img/Pil.png" />
      </div>
      <div class="temp">
        <div class="icon-container">
          <div class="box">
            <div
              class="row"
              style="border-bottom: 0.5px solid rgb(231, 231, 231)"
            >
              <div
                class="column"
                style="border-right: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/pillpic"
                  ><img src="@/assests/icon/main_page/find_pill_by_pic.svg"
                /></a>
              </div>
              <div
                class="column"
                style="
                  border-left: 0.5px solid rgb(231, 231, 231);
                  border-right: 0.5px solid rgb(231, 231, 231);
                "
              >
                <a href="/ocrlist"
                  ><img src="@/assests/icon/main_page/take_pic.svg"
                /></a>
              </div>
              <div
                class="column"
                style="border-left: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/pill"
                  ><img src="@/assests/icon/main_page/pill_search.svg"
                /></a>
              </div>
            </div>
            <div class="row" style="border-top: 0.5px solid rgb(231, 231, 231)">
              <div
                class="column"
                style="border-right: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/pharmacy"
                  ><img src="@/assests/icon/main_page/pharmacy_search.svg"
                /></a>
              </div>
              <div
                class="column"
                style="
                  border-left: 0.5px solid rgb(231, 231, 231);
                  border-right: 0.5px solid rgb(231, 231, 231);
                "
              >
                <a href="/user-pill-effect"
                  ><img src="@/assests/icon/main_page/effect_record.svg"
                /></a>
              </div>
              <div
                class="column"
                style="border-left: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/take-list"
                  ><img src="@/assests/icon/main_page/take_pill_management.svg"
                /></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 수정 start -->
    <div class="middle-content">
      <img src="@/assests/icon/main_page/noti_background.svg" />
    </div>
    <!-- 수정 end -->
  </div>
  <!-- <div>
    <a href="/ocrlist">약봉투/진단서 촬영 메인페이지 (/ocrlist)</a>
  </div>
  <div>
    <a href="/ocrlist/diagnosisfilming"
      >약봉투 촬영 (/ocrlist/diagnosisfilming)</a
    >
  </div>
  <div>
    <a href="/ocrlist/diagnosisresult"
      >약봉투 촬영 결과 (/ocrlist/diagnosisresult)</a
    >
  </div>
  <div>
    <a href="/ocrlist/pillbagfilming">진단서 촬영 (/ocrlist/pillbagfilming)</a>
  </div>
  <div>
    <a href="/ocrlist/pillbagresult"
      >진단서 촬영 결과 (/ocrlist/pillbagresult)</a
    >
  </div>
  <div>
    <a href="/pharmacy">약국 검색 메인페이지 (/map)</a>
  </div>
  <div>
    <a href="/pharmacy">약국 검색창 페이지 (/map/pharmacysearch)</a>
  </div>
  <div>
    <a href="/pillpic">약 사진으로 검색 (/pillpic)</a>
  </div>
  <div>
    <a href="/pillsearch">약 이름으로 검색 (/pillsearch)</a>
  </div>
  <div>
    <a href="/pillsearch/pillsearchdetail"
      >약 상세정보 페이지 (/pillsearch/pillsearchdetail)</a
    >
  </div>
  <div>
    <a href="/setting">설정 (/setting)</a>
  </div>
  <div>
    <a href="/take-list">복용리스트 (/take-list)</a>
  </div>
  <div>
    <a href="/usermedicalinfo">문진표 (/usermedicalinfo)</a>
  </div>
  <div>
    <a href="/usermedicalinfo/pillbag">처방내역 (/usermedicalinfo/pillbag)</a>
  </div>
  <div>
    <a href="/usermedicalinfo/diagnosishistory"
      >진단내역 (/usermedicalinfo/diagnosishistory)</a
    >
  </div>
  <div>
    <a href="/test">테스트 (/test)</a>
  </div>
  <div>
    <a href="/userpilleffect">약효기록 (/userpilleffect)</a>
  </div>
  <div>
    <a href="/userpilleffect/effectdetail"
      >약효기록 상세정보 페이지 (/userpilleffect/effectdetail)</a
    >
  </div>
  <div>
    <a href="/login">로그인 화면 (/login)</a>
  </div>
  <div>
    <a href="/loading">로딩 화면 (/loading)</a>
  </div>
  <div>
    <a href="/registinfo">개인정보등록 페이지 (/registinfo)</a>
  </div> -->
  <!-- Nav-bar용 -->
  <div style="height: 85px; background-color: #f9f9f9"></div>
</template>

<script setup>
import { userStore } from "@/stores/user";
const store = userStore();
store.getUserInfo(); // 유저 이름 가져오기 위함
</script>

<style scoped>
.header-background {
  height: 400px;
  width: 320px;
  position: relative;
  text-align: center;
  color: black;
  margin-top: -110px; /*위로 올려 원 일부를 가리는 용도 */
  margin-bottom: -257px; /*밑에 사진 삼키는 용도 */
  border-radius: 500%;
  display: flex;
  justify-items: center;
  align-items: center;
}

.header-background img {
  width: 320px;
  height: 600px;
  margin-top: 100px;
  border-radius: 100px;
}

.basic-background {
  background-color: #f9f9f9;
  height: 100vh; /* 100vb가 아닌 100vh로 수정 */
  display: flex;
  justify-content: top; /* 가로 가운데 정렬 */
  flex-direction: column; /* 요소들을 세로로 정렬 */
}

.basic-content {
  margin: 90px 0 0 0;
}

.hello {
  display: flex;
  justify-content: left; /* 가로 가운데 정렬 */
  align-items: center;
  position: relative; /* 상대 위치 설정 */
  z-index: 5; /* 다른 요소 위에 표시되도록 설정 */
  margin: 0 0 10px 20px;
}

.hello-section {
  z-index: 7; /* 다른 요소 위에 표시되도록 설정 */
}
.hello-text {
  color: white;
  margin: 0 0 0 10px;
  font-size: 1rem;
  font-weight: 600;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* 그림자 설정 */
}

.hello-pic {
  width: 155px;
  margin: -50px 0px -18px 0px;
}

.icon-container {
  width: 270px; /* 가로 50px */
  height: 180px; /* 높이 80px */
}

.box {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  border-radius: 10px; /* 바깥 모서리에 border-radius 적용 */
  border: 1px solid rgb(231, 231, 231);
  position: relative; /* 상대 위치 설정 */
  z-index: 3; /* 다른 요소 위에 표시되도록 설정 */
  background-color: white;
  box-shadow: 0px 5px 5px 0px rgba(0, 0, 0, 0.4); /* 아래쪽에만 그림자 추가 */
  /* margin: 43px 0 50px 0; */
}

.row {
  width: 100%;
  height: 50%;
  display: flex;
}

.column {
  width: 33.333%;
  height: 100%;

  display: flex;
  justify-content: center;
  align-items: center;
}

.temp {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.middle-content {
  display: flex;
  justify-content: center;
  align-items: center;
  max-width: 270px;
  margin: 80px auto 0; /* 상단 여백을 조절하기 위해 auto로 수정 */
  text-align: center; /* 내부 요소를 가운데 정렬 */
}

.middle-content img {
  max-width: 270px;
  border-radius: 20px;
}

.middle-content-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  z-index: 1;
  color: white;
  justify-items: left;
}
</style>
