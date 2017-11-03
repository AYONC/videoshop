FROM 805839038026.dkr.ecr.ap-northeast-2.amazonaws.com/base-videoshop:latest

ARG version

RUN mkdir -p /usr/local/videoshop
ADD build/libs/videoshop-${version}-SNAPSHOT.jar /usr/local/videoshop/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/videoshop/app.jar"]
