// 번역을 수행한 문장 수, 단어 수, 글자 수 수집 및 사용자 IP 수집
export default defineNuxtPlugin(nuxtApp => {
    const logger = async (message) => {
        try {
            const response = await fetch('/api/stat', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(message)
            });
            console.log(`[LOG]: `,message)

            if (!response.ok) {
                console.error('[ERROR] logger', response.statusText);
            }
        } catch (error) {
            console.error('[ERROR] logger', error);
        }
    }

    return ({
        provide: {
            logger
        }
    })
})