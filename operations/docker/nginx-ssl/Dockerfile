FROM nginx:1.9.9

RUN mkdir /etc/nginx/ssl/

COPY default.conf /etc/nginx/conf.d/

CMD ["nginx", "-g", "daemon off;"]