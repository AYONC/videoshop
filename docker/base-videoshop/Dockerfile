FROM ubuntu:16.04

# install essential tools
RUN apt-get update

# RUN apt-get install -y curl wget vim python-software-properties software-properties-common
RUN apt-get install -y curl wget vim

# Install Oracle JDK 8u74
RUN mkdir -p /usr/local/java && \
    cd /tmp && \
    curl -L -O -H "Cookie: oraclelicense=accept-securebackup-cookie" -k "http://download.oracle.com/otn-pub/java/jdk/8u151-b12/e758a0de34e24606bca991d704f6dcbf/jdk-8u151-linux-x64.tar.gz" && \
    tar xf jdk-8u151-linux-x64.tar.gz -C /usr/local/java && \
    rm -f jdk-8u151-linux-x64.tar.gz

# Define commonly used JAVA_HOME variable
# Add /srv/java and jdk on PATH variable
ENV JAVA_HOME=/usr/local/java/jdk1.8.0_151
ENV PATH=${PATH}:${JAVA_HOME}/bin
