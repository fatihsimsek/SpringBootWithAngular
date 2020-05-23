FROM node:12.7-alpine AS build
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

### STAGE 2: Run ###
FROM nginx:1.17.1-alpine
COPY --from=build /app/dist/AngularDemo /usr/share/nginx/html