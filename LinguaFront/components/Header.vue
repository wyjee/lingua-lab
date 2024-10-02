<template>
  <header class="header">
    <nav>
      <ul>
        <li class="logo blind">
          <NuxtLink to="/">
            홈
          </NuxtLink>
        </li>
        <li>언어 번역</li>
        <li>파일 번역</li>
        <li>영상 자막 번역</li>
        <li>영상 자막 편집</li>
        <li>DMT 번역 소개</li>
        <li>API</li>
        <li>요금제</li>
        <li>
          <NuxtLink to="/bookmark">
            즐겨찾기
          </NuxtLink>
        </li>
        <li>언어 변경</li>
        <li>로그인</li>
      </ul>
    </nav>
  </header>
</template>

<script>
</script>

<style scoped>
.header {
  width: 100%;
}

nav ul {
  display: flex;
  justify-content: space-between;
  list-style-type: none;
  align-items: center;
  padding: 20px;
}

nav ul li {
  cursor: pointer;
}

.logo {
  width: 120px;
  height: 39px;
  padding: 0px 54px 0px 0px;
}

.logo a:before {
  content: "";
  display: block;
  background: url('assets/icons/logo.png') no-repeat;
  width: 120px;
  height: 39px;
  float: left;
  z-index: 1;
  cursor: pointer;
}

@media (min-width: 768px) {
  .header {
    max-width: 760px;
    margin: 0 auto;
  }

  nav ul {
  }
}
</style>
