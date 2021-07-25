FROM openjdk:11
ARG JAR_FILE=target/*.jar
ENV AWS_ACCESS_KEY=AKIAI5ZQLUBZKBPJUPCA
ENV AWS_ACCESS_SECRET=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
ENV AWS_BUCKET=ad-generator-jp
ENV MYSQL_HOST=db-ad-generator-1.cyydetuhq9uj.sa-east-1.rds.amazonaws.com
ENV MYSQL_KEY=P3V3xC7R
ENV MYSQL_USER=admin
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
