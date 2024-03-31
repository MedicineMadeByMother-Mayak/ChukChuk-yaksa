<template>
  <HeaderForm title="약효기록" height="120px">
    <div class="header-content">
      <SearchBar
        class="SearchBar"
        height="30px"
        width="300px"
        iconWidth="15px"
        fontSize="12px"
        marginLeft="15%"
      ></SearchBar>
    </div>
    <div class="buttons">
      <div>
        <button class="tab-button" @click="setCategoryflagData(0)">전체</button
      ><button class="tab-button" @click="setCategoryflagData(1)">효과</button
      ><button class="tab-button" @click="setCategoryflagData(2)">중단</button
      ><button class="tab-button" @click="setCategoryflagData(3)">부작용</button>
      </div>
      <div>
        <div>
          <button class="navy-button"><font-awesome-icon :icon="['fas', 'circle-plus']" style="color: #ffffff;" /> 기록추가</button>
        </div>
      </div>
    </div>
  </HeaderForm>
  <div class="pill-content">
    <div v-for="(pillData, index) in filteredPills" :key="`pill-data-${index}`" class="pill-info">
      <PillContent 
        v-model="categoryflag" 
        :pillId="pillData.pillId" 
        :pillName="pillData.name"
        :imageURL="pillData.imageURL"
        :company="pillData.company"
        :categories="pillData.categories"
        :tags="pillData.tags" />
    </div>
  </div>
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import PillContent from "./components/Content.vue";
import SearchBar from "@/common/SearchBar.vue";
import { ref, computed  } from "vue";

const categoryflag = ref(true);
const tabnum = ref(0);

function setCategoryflagData(num) {
  categoryflag.value = 0 == num;
  tabnum.value = num
}

const filteredPills = computed(() => {
  return dumyData.value.pills.filter(pill => {
    return pill.categories.some(category => category.categoryId === tabnum.value);
  });
});

function setTabflag(categoryDatas) {
  categoryDatas.forEach((categoryData, index) => {
    if (categoryData.categoryId == tabnum.value) {
      return true
    }
    return false
  })
}

const dumyData = ref({
	page : 1,          // 페이지 번호
	pills : [            //(사용자가 등록한 약 목록)
		{
			pillId : 1,    //약 id값
			name : "타이레놀",   //약 이름
			imageURL : "@/assests/img/tempPill.png", //약 이미지 URI
			type : "항히스타민제",    //약 분류
			company : "싸피사랑약국",  // 제조사
			categories : [    //대분류 (전체, 부작용, 중단, 효과)
        {
          categoryId : 0, //대분류 id
          categoryName : "전체"   //대분류 이름
        },
        {
          categoryId : 1, //대분류 id
          categoryName : "부작용"   //대분류 이름
        }
			],
			tags : [         //태그 리스트(String List)
				{
					tagId : 0, //태그 id값
					tagName : "알러지 진정" //태그 이름
				}
			]
		},
    {
			pillId : 1,    //약 id값
			name : "프라닥사캡슐",   //약 이름
			imageURL : "@/assests/img/tempPill.png", //약 이미지 URI
			type : "항히스타민제",    //약 분류
			company : "싸피사랑약국",  // 제조사
			categories : [    //대분류 (전체, 부작용, 중단, 효과)
        {
          categoryId : 0, //대분류 id
          categoryName : "전체"   //대분류 이름
        },
        {
          categoryId : 2, //대분류 id
          categoryName : "중단"   //대분류 이름
        }
			],
			tags : [         //태그 리스트(String List)
				{
					tagId : 0, //태그 id값
					tagName : "알러지 진정" //태그 이름
				}
			]
		}
	]
})
</script>

<style scoped>
.pill-info {
  margin: 5px 10px;
  align-items: center;
}

.header-content {
  display: flex;
  justify-content: center;
  align-items: center;
}

.tab-button {
  font-family: Cafe24SsurroundAir;
  font-size: 11px;
  border: none;
  background-color: white;
  width: 55px;
  height: 21px;
  padding: 3px 10px;
  margin: 0px;
  cursor: pointer;
  clip-path: polygon(10% 0, 90% 0, 100% 100%, 0% 100%);
  caret-color: transparent;
  border-top-left-radius: 5%;
  position: relative;
  overflow: hidden;
}

.buttons {
  width: 320px;
  display: flex;
  justify-content: space-between;
  margin: 0px 0px;
  position: absolute;
  left: 0;
  bottom: 0;
}

.navy-button {
  background-color: #242291;
  color: #ffffff;
  font-weight: bold;
  width: 65px;
  height: 21px;
  border-radius: 5px;
  padding: 3px;
  font-size: 11px;
  border: none;
  cursor: pointer;
}

.icon {
  display: flex;
  margin-left: auto;
  padding: 0 15px 0 0;
}
</style>
