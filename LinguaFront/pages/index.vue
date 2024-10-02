<template>
  <main class="main">
    <div class="translation_area flex-row">
      <div class="icon_box">
        <button class="icon_two_way blind" @click="handleClickSwitchLanguage">switch language</button>
      </div>
      <TextInput :from="from" :inputText="inputText" :audio="textInputAudio" @update-from="updateFrom" @update-input-text="updateInputText" @play-audio="playAudio" @copy-to-clipboard="copyToClipboard"/>
      <TranslationResult :to="to" :result="translatedText" :audio="translationResultAudio" @update-to="updateTo" @handle-click-save="handleClickSave" @handle-input-change="handleInputChange" @play-audio="playAudio" @copy-to-clipboard="copyToClipboard"/>
    </div>
  </main>
</template>
<script>
import LanguageSelect from "~/components/LanguageSelect.vue";
import TextInput from '~/components/TextInput.vue';
import TranslationResult from '~/components/TranslationResult.vue';

import { useTranslationStore } from '~/stores/translation.js';

export default {
  components: {
    LanguageSelect,
    TextInput,
    TranslationResult
  },
  watch: {
    from() {
      this.handleInputChange();
    },
    to() {
      this.handleInputChange();
    },
    inputText() {
      this.handleInputChange();
    }
  },
  computed: {
    isSameLanguage() {
      return this.from === this.to;
    }
  },
  data() {
    return {
      ip: '',
      inputText: '',
      translatedText: '',
      from: 'ko',
      to: 'ko',
      timeoutId: '',
      textInputAudio: null,
      translationResultAudio: null,
      wordCount: 0,
      characterCount: 0,
      sentenceCount: 0,
    };
  },
  async mounted() {
    const response = await $fetch('/get-ip');
    this.ip = response.ip;
  },
  methods: {
    updateInputText(value) {
      this.inputText = value;
    },
    handleInputChange(event) {
      const text = (event && event.target.value) || this.inputText;
      if (!text) return
      if (this.isSameLanguage) return this.translatedText = text;
      if (this.timeoutId) clearTimeout(this.timeoutId);

      const self = this;
      const params = {
        from: this.from,
        to: this.to,
        text,
      }
      this.timeoutId = setTimeout(async () => await self.requestTranslation(params).then((result)=>{
        self.requestAudio({lang: result.from, text: result.text, from: true}) // 번역 대상 텍스트 사운드
        self.requestAudio({lang: result.to, text: result.translatedText, to: true}) // 번역된 텍스트 사운드
        return result
      }).then(({text})=>{
        self.handleLog(text) // 로그 수집
      }).catch((error)=>console.error('[ERROR]', error)), 2500);
    },
    async requestTranslation(params) {
      // 번역 API
      try {
        const response = await $fetch('/api/translate-text', {
          method: 'POST',
          body: JSON.stringify(params),
        });

        this.translatedText = response[0].translations;
        return ({
          ...params,
          translatedText: response[0].translations
        })
      } catch (error) {
        console.error('[ERROR]', error);
      } finally {
        clearTimeout(this.timeoutId);
        this.timeoutId = null;
      }
    },
    playAudio(params) {
      if(!params.lang || !params.text) return;
      const translationStore = useTranslationStore()

      if (params.from) {
        this.textInputAudio = translationStore.$state.fromData.audioSrc
        this.translationResultAudio = null

        const audio = new Audio(this.textInputAudio);
        audio.play();
      }
      if (params.to) {
        this.textInputAudio = null
        this.translationResultAudio = translationStore.$state.toData.audioSrc

        const audio = new Audio(this.translationResultAudio);
        audio.play();
      }
    },
    async requestAudio({lang, text, from, to}) {
      const translationStore = useTranslationStore()
      await translationStore.requestTextToSpeech({lang, text, from, to})
    },
    updateFrom(from) {
      this.from = from;
    },
    updateTo(to) {
      this.to = to;
    },
    handleClickSwitchLanguage() {
      const temp = this.from;
      this.from = this.to;
      this.to = temp;
      this.initializeTranslation();
    },
    initializeTranslation() {
      const translationStore = useTranslationStore()
      translationStore.init()
      this.handleInputText('');
      this.translatedText = '';
    },
    async handleClickSave() {
      const {from, to, inputText, translatedText} = this;
      if (!inputText || !translatedText) return;

      const params = {
        fromLanguage: from,
        toLanguage: to,
        inputText, translatedText
      };
      const {response} = await $fetch('/api/history', {
        method: 'POST',
        body: JSON.stringify(params),
      });
      return response
    },
    async copyToClipboard(text) {
      try {
        await navigator.clipboard.writeText(text);
        alert('클립보드에 복사되었습니다.');
      } catch (error) {
        console.error('[ERROR]', error);
      }
    },
    countWords(text) {
      const words = text.split(/\s+/);
      this.wordCount = words.length;
      this.characterCount = text.length;
      this.sentenceCount = text.split(/[.?!]+/).length;
      return ({
        wordCount: this.wordCount,
        characterCount: this.characterCount,
        sentenceCount: this.sentenceCount
      });
    },
    handleLog(text) {
      const result = this.countWords(text);

      const { $logger } = useNuxtApp();
      $logger({...result, ip: this.ip})
    }
  }
}
</script>

<style scoped>
.translation_area {
  position: relative;
}

.icon_box {
  display: block;
  position: absolute;
  top: 10px;
  left: calc(50% - 10px);
}

.icon_two_way:before {
  content: "";
  display: block;
  background: url('assets/icons/icon_two_way.png') no-repeat;
  width: 23px;
  height: 23px;
  float: left;
  z-index: 1;
}
</style>
