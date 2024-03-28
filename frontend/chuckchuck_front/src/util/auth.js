export function getAccessToken() {
  // 로컬 스토리지 또는 쿠키에서 액세스 토큰 가져오기
  const accessToken = localStorage.getItem("accessToken");

  // 액세스 토큰이 존재하고 유효한지 확인 (선택적)
  //   if (accessToken && !isAccessTokenExpired(accessToken)) {
  //     return accessToken;
  //   }

  return accessToken;
}

export function getRefreshToken() {
  // 로컬 스토리지 또는 쿠키에서 리프레시 토큰 가져오기
  const refreshToken = localStorage.getItem("refreshToken");

  // 리프레시 토큰이 존재하는지 확인
  return refreshToken;
}

// (선택적) 액세스 토큰의 유효 시간을 검사하는 함수
function isAccessTokenExpired(accessToken) {
  // 로직 구현 (예: JWT 토큰의 exp 필드 검사)
  // ...
}
