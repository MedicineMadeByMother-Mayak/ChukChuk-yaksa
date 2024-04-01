R
<!-- 개인정보등록 페이지 -->
<template>
  <div class="basic-background" @click="closeModal">
    <div class="regist-info">
      <div class="regist-info-box" @click.stop>
        <div class="regist-info-box-container">
          <div class="info-container">
            <div class="box">
              <div
                style="
                  display: flex;
                  justify-content: center;
                  align-items: center;
                  width: 100%;
                  font-size: 15px;
                "
              >
                개인정보 수정
              </div>
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
                        <option value="FEMALE" class="styled-option">
                          여자
                        </option>
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
            <div style="display: flex; justify-content: center">
              <button class="save-button" @click="updateUserInfo">Save</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { userStore } from "@/stores/user";

const modalflag = defineModel();

const store = userStore();

const userName = ref("");
const bloodType = ref("");
const birth = ref("");
const sex = ref("");
const height = ref("");
const weight = ref("");

function closeModal() {
  modalflag.value = false;
}

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
  z-index: 9999;
  margin: 0px;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: end;
  justify-content: center;
  background-color: rgba(0, 0, 0, 0.5);
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
  height: 340px;
  width: 320px;
  background-color: #f9f9f9;
  border-radius: 20px;
  position: relative;
  top: 20px;
}

.regist-info-box-container {
  height: 340px;
  width: 320px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.info-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 340px;
  width: 320px;
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
  caret-color: transparent;
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

.save-button {
  height: 22px;
  width: 160px;
  background-color: #242291;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin: 5px 0 0 0;
}
</style>
