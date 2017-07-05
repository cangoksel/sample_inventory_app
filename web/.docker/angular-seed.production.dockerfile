FROM node:6.6

# prepare a com.github.cangoksel.user which runs everything locally! - required in child images!
RUN useradd --com.github.cangoksel.user-group --create-home --shell /bin/false app

ENV HOME=/home/app
WORKDIR $HOME

ENV APP_NAME=angular-seed

# before switching to com.github.cangoksel.user we need to set permission properly
# copy all files, except the ignored files from .dockerignore
COPY . $HOME/$APP_NAME/
RUN chown -R app:app $HOME/*

USER app
WORKDIR $HOME/$APP_NAME

RUN npm install
