<!-- 처방내역 -->
<template>
  <Wave
    title="진단내역"
    height="30px"
    Link="usermedicalinfo"
    style="z-index: 0"
  />

  <div style="z-index: 9999; margin-top: 80px">
    총 {{ diagnosishistorydata.count }}건의 진단내역이 있습니다.
  </div>
  <hr style="margin: 10px" />

  <div v-for="data in diagnosishistorydata.result">
    <Accordian
      style="margin: 10px"
      :title="formatDate(data.diagnosisDate, 'YYYY/MM/DD 진단내역')"
    >
      <div>
        <TableForm
          :tableData="[
            ['진단일', formatDate(data.diagnosisDate, 'YYYY/MM/DD')],
            ['병원명', data.hospitalName],
            ['질병코드', data.illCode],
            ['질병명', data.illName],
            ['소견', data.opinion],
          ]"
        ></TableForm>
      </div>
    </Accordian>
  </div>
</template>

<script setup>
import Accordian from "@/views/user_medical_info/components/Accordian.vue";
import { ref, onMounted, nextTick, onUnmounted } from "vue";
import { instance } from "@/util/mainAxios";
import Wave from "@/common/Wave.vue";
import TableForm from "@/common/Form/TableForm.vue";
import dayjs from "dayjs";

const diagnosishistorydata = ref({
  result: [],
  count: 0,
  page: 1,
  itemsPerPage: 5, // 페이지 당 항목 수
  hasNext: true,
});

// 데이터를 불러오는 함수
async function fetchData() {
  try {
    const response = await instance.get("/record/diagnosis", {
      params: { page: diagnosishistorydata.value.page },
    });
    // 첫 페이지 로딩 시 전체 count를 설정
    if (diagnosishistorydata.value.page === 1) {
      diagnosishistorydata.value.count = response.data.count;
    }
    // 결과를 기존 데이터에 추가
    diagnosishistorydata.value.result.push(...response.data.result);
    // 페이지 번호 증가
    const totalLoadedItems =
      diagnosishistorydata.value.page * diagnosishistorydata.value.itemsPerPage;
    diagnosishistorydata.value.hasNext =
      totalLoadedItems < diagnosishistorydata.value.count;
    diagnosishistorydata.value.page++;
  } catch (error) {
    console.error("API 데이터를 불러오는데 실패했습니다.", error);
  }
}

// fetchData 호출 후 문서 높이 체크 및 추가 로드 처리
async function checkAndFetchData() {
  await fetchData();

  await nextTick(); // DOM 업데이트 대기

  const { clientHeight, scrollHeight } = document.documentElement;
  if (scrollHeight <= clientHeight && diagnosishistorydata.value.hasNext) {
    // 문서 전체 높이가 뷰포트 높이 이하이고, 추가 데이터가 있으면 재귀적으로 호출
    checkAndFetchData();
  }
}

// 스크롤 이벤트 핸들러
function handleScroll() {
  const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
  if (
    scrollHeight - (scrollTop + clientHeight) < 1 &&
    diagnosishistorydata.value.hasNext
  ) {
    checkAndFetchData();
  }
}

onMounted(() => {
  window.addEventListener("scroll", handleScroll);
  checkAndFetchData(); // 초기 데이터 로딩 및 높이 체크
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

function formatDate(date, format = "YYYY/MM/DD") {
  return dayjs(date).format(format);
}
</script>

<style scoped></style>
