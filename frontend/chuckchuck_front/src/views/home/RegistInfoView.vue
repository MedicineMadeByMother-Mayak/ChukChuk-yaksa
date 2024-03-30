<!-- 개인정보등록 페이지 -->
<template>
  <div class="basic-background">
    <img src="@/assests/icon/logo.svg" />
    <div class="noti-text"><strong>개인정보를 등록해주세요!</strong></div>
    <div class="regist-info">
      <div class="regist-info-box">
        <div class="info-container">
          <div class="box">
            <div class="row">
              <div class="column">
                <div class="column-content">
                  <div class="column-content-title">이름</div>
                  <div><input /></div>
                </div>
              </div>
              <div class="column">
                <div class="column-content">
                  <div class="column-content-title">혈액형</div>
                  <div><input /></div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="column">
                <div class="column-content">
                  <div class="column-content-title">생년월일</div>
                  <div><input /></div>
                </div>
              </div>
              <div class="column">
                <div class="column-content">
                  <div class="column-content-title">성별</div>
                  <div><input /></div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="column">
                <div class="column-content">
                  <div class="column-content-title">키/몸무게</div>
                  <div><input /></div>
                </div>
              </div>
              <div class="column">
                <div class="column-content">
                  <div style="visibility: hidden">_</div>
                  <div><input /></div>
                </div>
              </div>
            </div>
          </div>
          <!-- 수정 start -->
          <div>
            <button class="next-button">다음으로</button>
          </div>
          <!-- 수정 end -->
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.basic-background {
  position: relative;
  text-align: center;
  background: linear-gradient(to right, #3183ff 0%, #86e7ee 100%);
  color: black;
  margin-bottom: 10px;
  height: 100vh; /* 부모 요소의 높이를 화면 전체로 설정 */
}

.basic-background img {
  margin: 70px 0 0 0;
}

.noti-text {
  color: white;
  margin: 25px 0 10px 0;
}

.regist-info {
  display: flex;
  justify-content: center;
  align-items: center;
}

.regist-info-box {
  height: 300px;
  width: 300px;
  background-color: #F9F9F9;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.info-container {
  display: flex;
  justify-content: center;
  align-items: top;
  height: 100%;
  width: 100%;
  flex-direction: column;
}

.box {
  width: 100%;
  height: 80%;
  display: flex;
  flex-wrap: wrap;
}

.row {
  width: 100%;
  height: 33.33%;
  display: flex;
}

.column {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: start;
  flex-direction: column;
}

.column-content {
  margin: 0 0 0 20px;
}

.column-content-title {
  margin: 0 0 5px 5px;
  display: flex;
  justify-content: start;
}

.column input {
    width: 90px;
    height: 20px;
    background-color: white;
    border: 1px solid lightgray;
    border-radius: 5px;
    padding: 5px;
    outline: none; /* 클릭시 border 제거 */
}

.dot {
  width: 2px;
  height: 2px;
  background-color: blue;
  margin: auto;
}

.next-button {
  height: 22px;
  width: 160px;
  background-color: #242291;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin: 15px 0 0 0;
}
</style>
