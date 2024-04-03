// service-worker.js

self.addEventListener("install", function (event) {
  event.waitUntil(
    caches.open("your-cache-name").then(function (cache) {
      return cache.addAll([
        "/manifest.webmanifest",
        "/icons/icon-192x192.png",
        "/icons/icon-256x256.png",
        "/icons/icon-384x384.png",
        // ... 캐시하고 싶은 다른 자산들
      ]);
    })
  );
});

// 여기에 fetch 이벤트 리스너 등 다른 서비스 워커 로직을 추가할 수 있습니다.
self.addEventListener("fetch", function (e) {
  e.respondWith(
    caches.match(e.request).then(function (r) {
      console.log("[Service Worker] Fetching resource: " + e.request.url);
      return (
        r ||
        fetch(e.request).then(function (response) {
          return caches.open("danbi").then(function (cache) {
            console.log(
              "[Service Worker] Caching new resource: " + e.request.url
            );
            cache.put(e.request, response.clone());
            return response;
          });
        })
      );
    })
  );
});