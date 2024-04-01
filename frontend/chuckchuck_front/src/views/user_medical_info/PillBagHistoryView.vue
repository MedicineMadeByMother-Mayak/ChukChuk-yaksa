<template>
  <Wave title="처방내역" height="30px" Link="usermedicalinfo" />

  <div style="margin: 5px 10px">
    총 {{ pillbaghistorydata.count }}건의 처방내역이 있습니다.
  </div>
  <hr style="margin: 10px" />

  <div v-for="data in pillbaghistorydata.result">
    <Accordian
      style="margin: 10px"
      :title="formatDate(data.receipt.buildDate, 'YYYY/MM/DD 처방내역')"
    >
      <li>
        <strong>영수증</strong>
        <TableForm
          style="margin-top: 10px"
          :tableData="[
            ['약국정보', data.receipt.pharmacyName],
            ['조제일자', formatDate(data.receipt.buildDate, 'YYYY/MM/DD')],
            ['수납금액', data.receipt.cost],
          ]"
        ></TableForm>
      </li>
      <li style="margin: 10px 0px">
        <strong>복약안내</strong>
        <div v-for="pillData in data.guide" style="margin-top: 10px">
          <PillBagContent
            :pillName="pillData.pillName"
            :type="pillData.type"
            :capacity="pillData.capacity"
          />
        </div>
      </li>
    </Accordian>
  </div>
</template>

<script setup>
import Accordian from "@/views/user_medical_info/components/Accordian.vue";
import PillBagContent from "@/common/PillInfo.vue";
import { ref, onMounted, nextTick, onUnmounted } from "vue";
import { instance } from "@/util/mainAxios";
import Wave from "@/common/Wave.vue";
import TableForm from "@/common/Form/TableForm.vue";
import dayjs from "dayjs";

const pillbaghistorydata = ref({
  result: [],
  count: 0,
  page: 1,
  itemsPerPage: 5, // 페이지 당 항목 수
  hasNext: true,
});

// 데이터를 불러오는 함수
async function fetchData() {
  try {
    const response = await instance.get("/record/pill-bag", {
      params: { page: pillbaghistorydata.value.page },
    });
    // 첫 페이지 로딩 시 전체 count를 설정
    if (pillbaghistorydata.value.page === 1) {
      pillbaghistorydata.value.count = response.data.count;
    }
    // 결과를 기존 데이터에 추가
    pillbaghistorydata.value.result.push(...response.data.result);
    // 페이지 번호 증가
    const totalLoadedItems =
      pillbaghistorydata.value.page * pillbaghistorydata.value.itemsPerPage;
    pillbaghistorydata.value.hasNext =
      totalLoadedItems < pillbaghistorydata.value.count;
    pillbaghistorydata.value.page++;
  } catch (error) {
    console.error("API 데이터를 불러오는데 실패했습니다.", error);
  }
}

// fetchData 호출 후 문서 높이 체크 및 추가 로드 처리
async function checkAndFetchData() {
  await fetchData();

  await nextTick(); // DOM 업데이트 대기

  const { clientHeight, scrollHeight } = document.documentElement;
  if (scrollHeight <= clientHeight && pillbaghistorydata.value.hasNext) {
    // 문서 전체 높이가 뷰포트 높이 이하이고, 추가 데이터가 있으면 재귀적으로 호출
    checkAndFetchData();
  }
}

// 스크롤 이벤트 핸들러
function handleScroll() {
  const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
  if (
    scrollHeight - (scrollTop + clientHeight) < 1 &&
    pillbaghistorydata.value.hasNext
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
