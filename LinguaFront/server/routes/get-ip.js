export default defineEventHandler(({node}) => {
    // node.req.headers 확인
    if (!node.req || !node.req.headers) {
        console.error('node.req.headers is undefined');
        return {
            error: 'Request headers are not available'
        };
    }

    return {
        ip: node.req.headers['x-forwarded-for'] || node.req.socket.remoteAddress
    };
});