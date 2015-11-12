/*
 * Copyright (c) 2015, Sastrion Sp. z o.o.
 * All rights reserved.
 *
 * Author: Marcin Białoń
 */

package net.bialon.dropwizard.metrics.influxdb;

import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class InfluxDBReporterFactoryTest {
    @Test
    public void isDiscoverable() throws Exception {
        assertThat(new DiscoverableSubtypeResolver().getDiscoveredSubtypes(), hasItem(InfluxDBReporterFactory.class));
    }
}