# Use NGINX as the base image to serve static files
FROM nginx:stable-alpine

# Copy the React build files to the NGINX public directory
COPY build /usr/share/nginx/html

# Expose port 80 for the web server
EXPOSE 80

# Start NGINX
CMD ["nginx", "-g", "daemon off;"]