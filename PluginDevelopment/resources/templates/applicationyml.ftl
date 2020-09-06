server:
  port: ${port}
spring:
  application:
    name: ${projectName}
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        hbm2ddl:
          import_files_sql_extractor: org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor
    show-sql: true
  jackson:
    serialization:
      fail-on-empty-beans: false
  datasource:
    url: jdbc:mysql://${databaseHost}:${databasePort}/${databaseName}?createDatabaseIfNotExist=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
    username: ${databaseUsername}
    password: ${databasePassword}