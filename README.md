# OpenMQ Spring Boot Starter
[![Build Status](https://travis-ci.org/levry/spring-imq.svg?branch=master)](https://travis-ci.org/levry/spring-imq)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=github.levry.imq.spring&metric=alert_status)](https://sonarcloud.io/dashboard?id=github.levry.imq.spring)

Starter enables a jms interaction with [openMQ broker](https://javaee.github.io/openmq)

### How to connect the project

```groovy
repositories {
    jcenter()
}
```

```groovy
compile 'com.github.levry:imq-spring-boot-starter:+'
```

### How to use

OpenMQ configuration is controlled by properties in `imq.*`.
For example, declare in application.yml:

```yaml
imq:
  host: localhost
  port: 7676
  username: user
  password: secret
```

See for a jms messaging in [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-messaging.html#boot-features-jms)
