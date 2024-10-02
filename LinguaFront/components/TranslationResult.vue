<template>
  <div class="result-area">
    <LanguageSelect :lang="to" :handleSelectedLanguageChange="handleSelectedLanguageChange" :read-only="readOnly" />
    <div class="translation-result">
      {{ result }}
    </div>
    <button @click="$emit('play-audio', {lang: to, text: result, to})">발음듣기</button>
    <audio v-if="audio" class="invisible audio-controls">
      <source :src="audio" type="audio/ogg">
      <source :src="audio" type="audio/mpeg">
      Your browser does not support the audio tag.
    </audio>
    <button @click="$emit('copy-to-clipboard', result)">복사하기</button>
    <button v-if="!readOnly" @click="$emit('handle-click-save')">즐겨찾기</button>
  </div>
</template>

<script>
import LanguageSelect from "~/components/LanguageSelect.vue";

export default {
  components: {
    LanguageSelect
  },
  props: {
    result: {
      type: String,
      default: '',
    },
    to: {
      type: String,
      default: 'ko',
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

  data() {
    return {
    };
  },
  methods: {
    handleSelectedLanguageChange(selectedLanguage) {
      this.$emit('update-to', selectedLanguage);
    }
  }
};
</script>

<style scoped>
.translation-result {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  min-height: 200px;
}
</style>