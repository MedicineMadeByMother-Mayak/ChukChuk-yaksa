const CACHE_NAME = "app-static-v2"; // 버전을 변경하여 업데이트를 관리
const assetsToCache = [
  "/manifest.webmanifest",
  "/icons/icon-192x192.png",
  "/icons/icon-256x256.png",
  "/icons/icon-384x384.png",
  // ... 캐시하고 싶은 다른 자산들
];

// 설치 이벤트
self.addEventListener("install", (event) => {
  console.log("[Service Worker] Install Event processing");

  event.waitUntil(
    caches.open(CACHE_NAME).then((cache) => {
      console.log("[Service Worker] Caching all: app shell and content");
      return cache.addAll(assetsToCache);
    })
  );
});

// 활성화 이벤트: 오래된 캐시를 정리
self.addEventListener("activate", (event) => {
  console.log("[Service Worker] Activate event");

  event.waitUntil(
    caches
      .keys()
      .then((cacheNames) => {
        return Promise.all(
          cacheNames.map((cacheName) => {
            if (cacheName !== CACHE_NAME) {
              console.log("[Service Worker] Deleting old cache:", cacheName);
              return caches.delete(cacheName);
            }
          })
        );
      })
      .then(() => {
        console.log(
          "[Service Worker] Claiming clients for version",
          CACHE_NAME
        );
        return self.clients.claim();
      })
  );
});

// 패치 이벤트
self.addEventListener("fetch", (event) => {
  // 'chrome-extension' 스키마와 'GET' 메소드가 아닌 요청은 무시
  if (!event.request.url.startsWith("http") || event.request.method !== "GET") {
    return fetch(event.request);
  }

  event.respondWith(
    caches.match(event.request).then((response) => {
      return (
        response ||
        fetch(event.request).then((response) => {
          return caches.open(CACHE_NAME).then((cache) => {
            cache.put(event.request, response.clone());
            return response;
          });
        })
      );
    })
  );
});
