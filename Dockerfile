FROM dockerfile/java:oracle-java7
MAINTAINER Shohei Miyashita <kei@kamasu.jp>

# copy project filesroot
ADD ./ /root

# run play
EXPOSE 40002
WORKDIR /root

CMD ./target/universal/stage/bin/miyatintools -Dhttp.port=80 -J-Xms128M -J-Xmx512m -J-server