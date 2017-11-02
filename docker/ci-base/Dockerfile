FROM 805839038026.dkr.ecr.ap-northeast-2.amazonaws.com/base-videoshop:latest


# pre-install gradle & dependencies
RUN mkdir -p /tmp/gradle-temp/gradle
ADD ./build.gradle /tmp/gradle-temp/
ADD ./gradle /tmp/gradle-temp/gradle/
ADD ./gradlew /tmp/gradle-temp/
WORKDIR /tmp/gradle-temp/
RUN ./gradlew dependencies && rm -rf /tmp/gradle-temp

# install awscli
RUN sudo apt install python3-pip
RUN pip3 install awscli
