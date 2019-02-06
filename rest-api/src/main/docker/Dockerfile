FROM java:8
ADD core-api.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Xmx1024M","-jar","/app.jar"]
