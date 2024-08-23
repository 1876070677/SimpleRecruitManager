# DEMO
https://se.dobby.kr

# CONFIGURATION
#### Make a XML File in following path; /src/main/webapp/META-INF/context.xml.
#### Write your database information.
#### Example)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/">
    <Resource
            name="jdbc/mysql"
            type="javax.sql.DataSource"
            auth="Container"
            driverClassName="com.mysql.cj.jdbc.Driver"
            url="jdbc:mysql://{host}/{db_name}?serverTimezone=UTC"
            username="{username}"
            password="{password}"
            maxActive="20"
            maxWait="3000"
    />
</Context>
```

# INSTALLATION
#### 1) Enter a command below.
```shell
mvn package
```
#### 2) install docker and docker-compose.
#### 3) Enter a command below.
```shell
docker-compose up -d
```
#### 4) Enter "http://localhost:9005" into your web browser.
