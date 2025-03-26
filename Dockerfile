# Use Node.js for building
FROM node:18-alpine AS build
WORKDIR /numberconversion-ui

# Copy package files and install dependencies
COPY package.json package-lock.json ./
RUN npm install --frozen-lockfile

# Copy the rest of the files and build
COPY . .
RUN npm run build

# Use a lightweight Nginx image for final deployment
FROM nginx:alpine
COPY --from=build /numberconversion-ui/build /usr/share/nginx/html

# Expose port and run Nginx
EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"]

