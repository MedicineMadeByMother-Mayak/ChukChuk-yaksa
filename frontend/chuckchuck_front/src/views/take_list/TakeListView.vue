
<style>
  .carousel {
    width: 320px;
    overflow: hidden;
    position: relative;
  }
  .carousel ul {
    list-style-type: none;
    width: 100%;
    margin: 0;
    padding: 0;
    display: flex;
    transition: transform 0.5s ease;
  }
  .carousel li {
    flex: 0 0 100%;
    width: 100%;
  }
  .carousel img {
    width: 320px;
    flex: 0 0 100%;
    width: 100%;
  }
  .carousel-buttons {
    display: flex;
    justify-content: center;
    margin-top: 10px;
  }
  .carousel-buttons button {
    background-color: #ddd;
    color: #333;
    padding: 8px 16px;
    border: none;
    cursor: pointer;
    width: 100px;
    height: 30px;
    z-index: 999;
    margin-left: 10px;
  }
</style>

<template>
<div class="carousel">
  <ul>
    <li>
      <img src="https://via.placeholder.com/728x90.png" alt="Image 1" />
    </li>
    <li>
      <img src="https://via.placeholder.com/728x90.png" alt="Image 2" />
    </li>
    <li>
      <img src="https://via.placeholder.com/728x90.png" alt="Image 3" />
    </li>
  </ul>
  <div class="carousel-buttons">
    <button class="prev-button">Prev</button>
    <button class="next-button">Next</button>
  </div>
</div>
</template>

<script>
  function carousel() {
    const carouselContainer = document.querySelector(".carousel");
    const carouselList = carouselContainer.querySelector("ul");
    const carouselItems = carouselList.querySelectorAll("li");
    const carouselWidth = carouselContainer.offsetWidth;
    const totalItems = carouselItems.length;
    let currentIndex = 0;

    // 초기 위치 설정
    carouselList.style.transform = `translateX(-${
      currentIndex * carouselWidth
    }px)`;

    // 이전 버튼 클릭 시
    function prev() {
      currentIndex = (currentIndex - 1 + totalItems) % totalItems;
      carouselList.style.transform = `translateX(-${
        currentIndex * carouselWidth
      }px)`;
    }

    // 다음 버튼 클릭 시
    function next() {
      currentIndex = (currentIndex + 1) % totalItems;
      carouselList.style.transform = `translateX(-${
        currentIndex * carouselWidth
      }px)`;
    }

    // 이전/다음 버튼 이벤트 리스너 추가
    const prevButton = carouselContainer.querySelector(".prev-button");
    prevButton.addEventListener("click", prev);

    const nextButton = carouselContainer.querySelector(".next-button");
    nextButton.addEventListener("click", next);
  }

  // DOM이 로드된 후에 Carousel을 초기화합니다.
  window.addEventListener("DOMContentLoaded", carousel);
</script>
  