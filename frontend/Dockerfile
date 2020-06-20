FROM node:12

COPY . /frontend
WORKDIR /frontend

RUN yarn install

ENTRYPOINT ["yarn", "serve"]
