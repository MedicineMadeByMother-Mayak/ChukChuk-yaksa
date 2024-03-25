<template>
  <div class="user-madical-info">
    <div class="header">
      <h3>문진표</h3>
      <p>본인의 약국에 프로필을 보여주고 더 정확한 진단을 받아보세요.</p>
      <div class="profile-picture">
        <img src="../../assests/img/woman.jpg" alt="프로필 사진" />
      </div>
    </div>

    <div class="profile-container">
      <h3>김민중(여)</h3>
      <hr style="margin: 8px 18px" />
    </div>

    <div class="container">
      <h3 style="margin: 5px 0px">개인정보</h3>
      <div class="content">
        <div class="info-card">
          <div class="info-item">
            <p class="info-title">생년월일</p>
            <p class="info-content">1995.10.02</p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">혈액형</p>
            <p class="info-content">A RH+</p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">키/몸무게</p>
            <p class="info-content">180cm/ 75kg</p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">BMI</p>
            <p class="info-content">22.5</p>
          </div>
        </div>
        <div class="buttons-container">
          <button class="button gradient-blue">
            <strong>처방내역 확인</strong>
            <p><strong>모든 약봉투를 확인</strong></p>
          </button>
          <button class="button gradient-purple">
            <strong>진단내역 확인</strong>
            <p><strong>모든 진단서를 확인</strong></p>
          </button>
        </div>
      </div>
    </div>

    <div class="appointment-section">
      <div style="margin: 5px 0px">
        <strong
          style="
            font-size: 18.72px;
            font-family: Arial, Helvetica, sans-serif;
            margin: 5px 0px;
          "
        >
          병력 </strong
        ><span style="font-size: 10px"
          >(다음과 같은 질병으로 진단을 받았어요.)</span
        >
      </div>
      <div class="appointment-details">
        <span>2023.03.05</span>
        <span>JOO</span>
        <span>국민기(금성)</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfilePage",
  //   여기에 컴포넌트 데이터, 메서드 등을 추가하세요.
};
</script>

<style scoped>
/* 그라데이션 */
.header {
  color: black;
  text-align: center;
  background: linear-gradient(to left, #86e7ee, #3183ff);
  height: 148px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.header > * {
  position: relative;
  top: 33px;
}

.user-madical-info {
  font-family: "Arial", sans-serif;
}

div p {
  font-size: 10px;
  margin: 5px;
}

/* 프로필 컨테이너 조정 */
.profile-container {
  text-align: center; /* 텍스트 중앙 정렬 */
  position: relative;
  padding-top: 50px; /* 프로필 사진 높이만큼 패딩 추가 */
  margin-top: -50px; /* 헤더와 겹치도록 마진 설정 */
}

.profile-picture {
  position: relative;
}

.profile-picture img {
  width: 100px; /* 사진 크기 조정 */
  height: 100px;
  border-radius: 50%;
  border: 4px solid white;
  position: relative; /* 상위 요소(.profile-container)에 대해 상대적 위치 지정 */
  z-index: 2; /* 다른 요소 위로 올림 */
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}

.container {
  font-family: "Arial", sans-serif;
  width: 100%;
  padding: 18px;
  box-sizing: border-box;
}

.content {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.info-card,
.buttons-container {
  width: 48%;
}

.info-card {
  display: flex;
  justify-content: center;
  flex-direction: column;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 10px;
  box-sizing: border-box;
}

.info-card hr {
  width: 100%; /* 가로선 전체 너비를 사용하도록 설정 */
  border: 0;
  height: 1px; /* 선의 높이 */
  background-color: #eaeaea; /* 선의 색상 */
  margin: 3px 0; /* 위아래 여백 */
}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
}

.info-title {
  font-weight: bold;
  font-size: 10px;
  color: #666;
}

.info-content {
  font-size: 10px;
  color: #333;
}

.buttons-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 5px;
}

.button {
  width: 100%;
  height: 100%;
  padding: calc(
    50% - 44px
  ); /* Adjust padding to make the height approximately 50% */
  margin-bottom: 0px; /* Spacing between buttons */
  border-radius: 20px;
  border: none;
  color: white;
  cursor: pointer;
  font-size: 14px;
}

.gradient-blue {
  background: linear-gradient(145deg, #a0b7ef, #d5f2e8);
}

.gradient-purple {
  background: linear-gradient(145deg, #a0b7ef, #e3b8fb);
}

/* Additional media query to handle button height on smaller screens */
@media (max-width: 768px) {
  .button {
    padding: 10px; /* Reduce padding on smaller screens */
  }
}

.appointment-section {
  padding: 18px;
}

.appointment-details {
  display: flex;
  justify-content: space-between;
  background-color: white;
  border-radius: 15px;
  padding: 18px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}
</style>
