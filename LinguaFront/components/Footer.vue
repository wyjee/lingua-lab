<template>
  <footer class="footer">
    <div class="footer-content">
      <p class="company-name">디엠티랩스</p>
      <p class="contact-info">
        고객센터: help@dmtlabs.co.kr | 02-794-5333
      </p>
      <p class="address">
        주소: (04386) 서울특별시 용산구 한강대로40길 18, 404호 (한강로2가 144-2 다이빌딩)
      </p>
      <p class="copyright">
        Copyright © 2021 DMT Labs All Rights Reserved.
      </p>
      <div class="terms">
        <a href="#">이용약관</a> | <a href="#">개인정보방침</a>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  width: 100%;
  background-color: #f0f0f0;
  text-align: center;
  padding: 20px;
}

.footer-content {
  margin: 0 auto;
}

.company-name {
  font-weight: bold;
  margin-bottom: 10px;
}

.contact-info, .address, .copyright {
  margin-bottom: 5px;
}

.terms {
  margin-top: 10px;
}

@media (min-width: 768px) {
  .footer-content {
    max-width: 760px;
  }
}
</style>