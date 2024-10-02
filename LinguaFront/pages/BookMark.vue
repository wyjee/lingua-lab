<template>
  <main class="main">
  <h2>저장된 번역</h2>
      <div class="language-filter">
        <select v-model="selectedPair">
          <option v-for="pair in sortedPairs" :key="pair.from + pair.to" :value="pair">
            {{ pair.from.name }} -> {{ pair.to.name }}
          </option>
        </select>
      </div>
      <div class="translation-list">
        <div v-for="tr in translations" class="bookmark_area flex-row">
          <TextInput :from="tr.fromLanguage" :input-text="tr.inputText" :read-only="true" @play-audio="playAudio" @copy-to-clipboard="copyToClipboard"/>
          <TranslationResult :to="tr.toLanguage" :result="tr.translatedText" :read-only="true" @play-audio="playAudio" @copy-to-clipboard="copyToClipboard"/>
        </div>
      </div>
  </main>
</template>
<script>
import TextInput from "~/components/TextInput.vue";
import TranslationResult from "~/components/TranslationResult.vue";
import {useTranslationStore} from "~/stores/translation.js";
export default {
  components: {
    TextInput,
    TranslationResult
  },
  computed: {
    pairs() {
      const translationPairs = [];
      for (let i = 0; i < this.languages.length; i++) {
        for (let j = 0; j < this.languages.length; j++) {
          if (i !== j) { // from - to 언어가 같을 경우 제외
            translationPairs.push({ from: this.languages[i], to: this.languages[j] });
          }
        }
      }
      return translationPairs;
    },
    sortedPairs() {
      // 사용자 언어가 포함된 쌍과 그 외 쌍 분리
      const userLanguagePairs = this.pairs.filter(pair => pair.from === this.userLanguage);
      const otherPairs = this.pairs.filter(pair => pair.from !== this.userLanguage);

      // 사용자 언어 쌍 정렬
      userLanguagePairs.sort((a, b) => this.supportedLanguages.indexOf(a.to) - this.supportedLanguages.indexOf(b.to));

      // 다른 언어 쌍 정렬
      otherPairs.sort((a, b) => {
        const fromIndexA = this.supportedLanguages.indexOf(a.from);
        const fromIndexB = this.supportedLanguages.indexOf(b.from);
        return fromIndexA - fromIndexB || this.supportedLanguages.indexOf(a.to) - this.supportedLanguages.indexOf(b.to);
      });

      return [...userLanguagePairs, ...otherPairs];
    },
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
      translations: [],
      selectedPair: {},
      userLanguage: 'ko', // 사용자 언어
      supportedLanguages: ['ko', 'en', 'zh', 'ja', 'de', 'fr', 'es', 'pt', 'it', 'ru', 'vi'] // 지원 언어 목록
    }
  },
  mounted(){
    this.getTranslations();
  },
  methods: {
    async getTranslations() {
      const response = await $fetch("/api/history");
      this.translations = response;
    },
    async playAudio(params) {
      if(!params.lang || !params.text) return;
      const translationStore = useTranslationStore()
      const self = this;

      await this.requestAudio(params).then((result) => {
        if (params.from) {
          self.textInputAudio = translationStore.$state.fromData.audioSrc
          self.translationResultAudio = null

          const audio = new Audio(self.textInputAudio);
          audio.play();
        }
        if (params.to) {
          self.textInputAudio = null
          self.translationResultAudio = translationStore.$state.toData.audioSrc

          const audio = new Audio(self.translationResultAudio);
          audio.play();
        }
      })
    },
    async requestAudio({lang, text, from, to}) {
      const translationStore = useTranslationStore()
      await translationStore.requestTextToSpeech({lang, text, from, to})
    },
    async copyToClipboard(text) {
      try {
        await navigator.clipboard.writeText(text);
        alert('클립보드에 복사되었습니다.');
      } catch (error) {
        console.error('[ERROR]', error);
      }
    }
  }
}
</script>
<style scoped>
.bookmark_area {
  padding: 0px 0px 20px 0px;
}
</style>