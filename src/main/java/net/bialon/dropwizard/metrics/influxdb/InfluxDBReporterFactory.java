package net.bialon.dropwizard.metrics.influxdb;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.metrics.BaseReporterFactory;
import metrics_influxdb.InfluxdbHttp;
import metrics_influxdb.InfluxdbReporter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * A factory for {@link InfluxDBReporterFactory} instances.
 */
@JsonTypeName("influxdb")
public class InfluxDBReporterFactory extends BaseReporterFactory {
    private String prefix;

    @NotEmpty
    private String host = "localhost";

    @Range(min = 1, max = 49151)
    private int port = 8086;

    private String database;

    private String username;

    private String password;

    private boolean skipIdle = false;

    @JsonProperty
    public String getPrefix() {
        return prefix;
    }

    @JsonProperty
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(int port) {
        this.port = port;
    }

    @JsonProperty
    public String getDatabase() {
        return database;
    }

    @JsonProperty
    public void setDatabase(String database) {
        this.database = database;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty
    public boolean isSkipIdle() {
        return skipIdle;
    }

    @JsonProperty
    public void setSkipIdle(boolean skipIdle) {
        this.skipIdle = skipIdle;
    }

    public ScheduledReporter build(MetricRegistry registry) {
        try {
            InfluxdbHttp influxdb = new InfluxdbHttp(
                    host,
                    port,
                    database,
                    username,
                    password);
            return InfluxdbReporter.forRegistry(registry)
                    .convertDurationsTo(getDurationUnit())
                    .convertRatesTo(getRateUnit())
                    .filter(getFilter())
                    .prefixedWith(getPrefix())
                    .skipIdleMetrics(isSkipIdle())
                    .build(influxdb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
