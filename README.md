# Dropwizard Support for InfluxDB

[![Build Status](https://travis-ci.org/mbialon/dropwizard-metrics-influxdb.svg?branch=master)](https://travis-ci.org/mbialon/dropwizard-metrics-influxdb)

I’ve created a [Dropwizard](http://www.dropwizard.io/) Metrics reporter for [InfluxDB](https://influxdb.com/). It supports [Dropwizard](http://www.dropwizard.io/) v0.9.x and [InfluxDB](https://influxdb.com/) v0.8.x.

## Usage and configuration:

```yaml
metrics:
  reporters:
    - type: influxdb
      frequency: 10 seconds
      host: localhost
      port: 8086
      database: web
      username: admin
      password: admin
      skipIdle: false
```
