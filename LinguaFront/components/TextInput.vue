<template>
  <div class="result-area">
    <LanguageSelect :lang="from" :detect-language="true" :handleSelectedLanguageChange="handleSelectedLanguageChange" :read-only="readOnly" />
    <input class="text-input" :value="inputText" @input="handleInputText" :readonly="readOnly" placeholder="번역할 내용을 입력하세요">
    <button @click="$emit('play-audio', {lang: from, text: inputText, from})">발음듣기</button>
    <audio v-if="audio" class="invisible audio-controls">
      <source :src="audio" type="audio/ogg">
      <source :src="audio" type="audio/mpeg">
      Your browser does not support the audio tag.
    </audio>
    <button @click="$emit('copy-to-clipboard', inputText)">복사하기</button>
  </div>
</template>

<script>
import LanguageSelect from "~/components/LanguageSelect.vue";

export default {
  components: {
    LanguageSelect
  },
  props: {
    from: {
      type: String,
      default: 'ko',
    },
    inputText: {
      type: String,
      default: '',
    },
    readOnly: {
      type: Boolean,
      default: false,
    },
    audio: {
      type: String,
      default: '',
    }
  },
  methods: {
    handleSelectedLanguageChange(selectedLanguage) {
      this.$emit('update-from', selectedLanguage);
    },
    handleInputText({target:{value}}) {
      this.$emit('update-input-text',value)
    }
  }
};
</script>

<style>
.result-area {
  display: flex;
  flex-direction: column;
}

.text-input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  min-height: 200px;
}

@media (min-width: 768px) {
  .result-area {
    width: 50%;
  }
}
</style>