FROM node:16-alpine AS build
WORKDIR /app

ARG REACT_APP_BACKEND_URL
ARG REACT_APP_GATEWAY_URL

RUN echo $REACT_APP_GATEWAY_URL
RUN echo $REACT_APP_BACKEND_URL


ENV REACT_APP_BACKEND_URL=$REACT_APP_BACKEND_URL
ENV REACT_APP_GATEWAY_URL=$REACT_APP_GATEWAY_URL

COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine
# Copy the React build files to the NGINX public directory
COPY --from=build /app/build /usr/share/nginx/html
# Expose port 80 for the web server
EXPOSE 80
# Start NGINX
CMD ["nginx", "-g", "daemon off;"]