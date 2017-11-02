FROM 805839038026.dkr.ecr.ap-northeast-2.amazonaws.com/base-videoshop:latest

RUN mkdir -p /tmp/gradle-temp/gradle

# pre-install gradle & dependencies
ADD ./build.gradle /tmp/gradle-temp/
ADD ./gradle /tmp/gradle-temp/gradle/
#ADD ./gradle/wrapper/gradle-wrapper.jar /tmp/gradle-temp/gradle/wrapper/
#ADD ./gradle/wrapper/gradle-wrapper.properties /tmp/gradle-temp/gradle/wrapper/
ADD ./gradlew /tmp/gradle-temp/
WORKDIR /tmp/gradle-temp/
RUN find . -name '*'
RUN ./gradlew dependencies && rm -rf /tmp/gradle-temp
