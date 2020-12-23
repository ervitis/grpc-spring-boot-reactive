FROM docker.io/openjdk:11-slim

RUN apt update && \
    apt install -y wget && \
    apt clean

WORKDIR /opt/app

RUN wget -O jmx_prometheus_javaagent.jar https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.14.0/jmx_prometheus_javaagent-0.14.0.jar
RUN wget -O config.yaml https://raw.githubusercontent.com/prometheus/jmx_exporter/master/example_configs/tomcat.yml

ENV ARTIFACT_NAME="server-agregator-1.0.0.jar"

COPY target/* .

ENV JAVA_OPTS="-javaagent:/opt/app/jmx_prometheus_javaagent.jar=7071:/opt/app/config.yaml -XX:MaxDirectMemorySize=100M -XX:MaxPermSize=256M -XX:ReservedCodeCacheSize=48M -Xss64M -Xmx128M -Xms32M"
ENTRYPOINT exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /${ARTIFACT_NAME}