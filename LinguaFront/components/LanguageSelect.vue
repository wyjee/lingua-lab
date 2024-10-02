<template>
  <select class="language-select" :value="lang" @input="handleSelectedLanguageChange($event.target.value)" :disabled="readOnly">
    <option v-if="detectLanguage">언어감지</option>
    <option v-for="language in languages" :key="language.code" :value="language.code">
      {{ language.name }}
    </option>
  </select>
</template>

<script>
export default {
  props: {
    detectLanguage: {
      type: Boolean,
      default: false,
    },
    handleSelectedLanguageChange: {
      type: Function,
      default: () => {}
    },
    lang: {
      type: String,
      default: 'ko',
    },
    readOnly: {
      type: Boolean,
      default: false,
    }
  },
  data() {
    return {
      languages: [
        { code: 'ko', name: '한국어' },
        { code: 'en', name: '영어' },
        { code: 'zh-CN', name: '중국어 (간체)' },
        { code: 'ja', name: '일본어' },
        { code: 'de', name: '독일어' },
        { code: 'fr', name: '프랑스어' },
        { code: 'it', name: '이탈리아어' },
        { code: 'es', name: '스페인어' },
        { code: 'pt', name: '포르투갈어' },
        { code: 'ru', name: '러시아어' },
        { code: 'vi', name: '베트남어' },
      ],
      selectedLanguage: 'ko', // 기본 언어 설정
    };
  },
};
</script>

<style scoped>
.language-select {
  display: block;
  padding: 10px;
  outline: none;
  border: 1px solid #ccc;
  border-radius: 4px;
  text-align: center;
  background: url('assets/icons/icon_arrow_down.png') no-repeat 65% 50%;

  /* select 드롭다운 화살표 제거 */
  -moz-appearance:none; /* Firefox */
  -webkit-appearance:none; /* Safari and Chrome */
  appearance:none;
  cursor: pointer;
}
</style>