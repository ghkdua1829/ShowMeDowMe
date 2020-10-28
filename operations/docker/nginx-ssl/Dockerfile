FROM ubuntu:latest

RUN apt-get update

# Install Git
RUN apt-get install -y git

# Install letsencrypt.
RUN apt-get install -y build-essential
RUN cd /home
RUN git clone https://github.com/letsencrypt/letsencrypt
RUN cd /home/letsencrypt/
RUN ./letsencrypt-auto --help
RUN ./letsencrypt-auto certonly --standalone -d k3a301.p.ssafy.io

COPY nginx.conf /etc/nginx/nginx.conf

# Install Nginx.
RUN apt-get install -y nginx
RUN echo "\ndaemon off;" >> /etc/nginx/nginx.conf
RUN chown -R www-data:www-data /var/lib/nginx

# Define mountable directories.
VOLUME ["/etc/nginx/sites-enabled", "/etc/nginx/certs", "/etc/nginx/conf.d", "/var/log/nginx", "/var/www/html"]

# Define working directory.
WORKDIR /etc/nginx

# Define default command.
CMD ["nginx"]

# Expose ports.
EXPOSE 80
EXPOSE 443