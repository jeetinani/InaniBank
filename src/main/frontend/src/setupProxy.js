
const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/api',  // backend API endpoint
    createProxyMiddleware({
      target: 'http://localhost:8081',  //Spring backend server
      changeOrigin: true,
    })
  );
};
