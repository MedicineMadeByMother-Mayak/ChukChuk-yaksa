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
              <div class="column-left">
                <div class="column-content">
                  <div class="column-content-title">이름</div>
                  <div><input v-model="userName" /></div>
                </div>
              </div>
              <div class="column-right">
                <div class="column-content">
                  <div class="column-content-title">혈액형</div>
                  <div><input v-model="bloodType" /></div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="column-left">
                <div class="column-content">
                  <div class="column-content-title">생년월일</div>
                  <div><input v-model="birth" type="date" /></div>
                </div>
              </div>
              <div class="column-right">
                <div class="column-content">
                  <div class="column-content-title">성별</div>
                  <div>
                    <select v-model="sex" class="styled-select">
                      <option value="FEMALE" class="styled-option">여자</option>
                      <option value="MALE" class="styled-option">남자</option>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="column-left">
                <div class="column-content">
                  <div class="column-content-title">키</div>
                  <div><input v-model="height" /></div>
                </div>
              </div>
              <div class="column-right">
                <div class="column-content">
                  <div class="column-content-title">몸무게</div>
                  <div><input v-model="weight" /></div>
                </div>
              </div>
            </div>
          </div>
          <div>
            <button class="next-button" @click="updateUserInfo">
              다음으로
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { userStore } from "@/stores/user";

const store = userStore();

const userName = ref("");
const bloodType = ref("");
const birth = ref("");
const sex = ref("");
const height = ref("");
const weight = ref("");

onMounted(async () => {
  await store.getUserInfo();

  userName.value = store.userName;
  bloodType.value = store.bloodType;
  birth.value = store.birth;
  sex.value = store.sex;
  height.value = store.height;
  weight.value = store.weight;
});

const updateUserInfo = () => {
  store.updateUserInfo(
    userName.value,
    bloodType.value,
    birth.value,
    sex.value,
    height.value,
    weight.value
  );
};
</script>

<style scoped>
.basic-background {
  position: relative;
  text-align: center;
  background: linear-gradient(to right, #3183ff 0%, #86e7ee 100%);
  color: black;
  margin-bottom: 10px;
  height: 100%; /* 부모 요소의 높이를 화면 전체로 설정 */
}

.basic-background img {
  margin: 70px 0 0 0;
}

.noti-text {
  font-size: 0.9rem;
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
  background-color: #f9f9f9;
  border-radius: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.info-container {
  display: flex;
  justify-content: center;
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

.column-left {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 0 0 0 12px;
}

.column-right {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 0 12px 0 0;
}

.column-content-title {
  margin: 0 0 5px 5px;
  display: flex;
  justify-content: start;
}

input {
  width: 100px;
  height: 20px;
  background-color: white;
  border: 1px solid lightgray;
  border-radius: 5px;
  padding: 5px;
  outline: none; /* 클릭시 border 제거 */
}

.styled-select {
  width: 110px;
  height: 30px;
  background-color: white;
  border: 1px solid lightgray;
  border-radius: 5px;
  padding: 5px;
  outline: none;
  margin-top: 5px;
}

.styled-option {
  padding: 5px;
  background-color: white;
  color: black;
}

.dot {
  width: 2px;
  height: 2px;
  background-color: blue;
  margin: auto;
}

.next-button {
  width: 160px;
  background-color: #242291;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin: 15px 0 0 0;
  padding: 5px 0px;
  font-weight: 900;
}
</style>
