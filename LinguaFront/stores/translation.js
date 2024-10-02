import { defineStore } from 'pinia'

export const useTranslationStore = defineStore('translation', {
    state: () => ({
        fromData: {
            id: null,
            lang: null,
            text: null,
            audioSrc: null,
            from: true
        },
        toData: {
            id: null,
            lang: null,
            text: null,
            audioSrc: null,
            to: true
        }
    }),
    getters: {
        getFromData(state) {
            return state.fromData;
        },
        getToData(state) {
            return state.toData;
        },
    },
    actions: {
        async requestTextToSpeech({lang, text, from, to}) {
            const lan = lang === 'ja' ? 'jp' : lang;
            const {audio} = await $fetch('/api/tts', {
                method: 'POST',
                body: JSON.stringify({
                    id: '000001', // "000001"로 고정 (요청번호 - 사용하지 않음)
                    sr: 8000, // 8000 으로 고정 (음성품질 - 사용하지 않음)
                    lang: lan, text
                }),
            })
            const data = {
                lang,
                text,
                audioSrc: `data:audio/wav;base64,${audio}`,
            };

            if (from) return this.fromData = {...data, from: true};
            if (to) return this.toData = {...data, to: true};
        },
        init(state) {
            state.fromData = {
                id: null,
                lang: null,
                text: null,
                audioSrc: null,
                from: true
            }
            state.toData = {
                id: null,
                lang: null,
                text: null,
                audioSrc: null,
                to: true
            }
        }
    },
})