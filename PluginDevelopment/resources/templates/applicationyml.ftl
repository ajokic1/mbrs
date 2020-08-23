server:
  port: ${port?string["0"]}
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
    url: ${databaseUrl}
    username: ${databaseUsername}
    password: ${databasePassword}