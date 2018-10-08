/*
 * Copyright 2018, OpenCensus Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.opencensus.metrics;

import io.opencensus.common.ToDoubleFunction;
import io.opencensus.common.ToLongFunction;
import io.opencensus.metrics.LongGaugeMetric.Point;
import java.util.ArrayList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit tests for {@link MetricRegistry}. */
@RunWith(JUnit4.class)
public class MetricRegistryTest {
  @Rule public ExpectedException thrown = ExpectedException.none();

  private final MetricRegistry metricRegistry =
      MetricsComponent.newNoopMetricsComponent().getMetricRegistry();

  @Test
  public void addDoubleGauge_NullName() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addDoubleGaugeMetric(null, "description", "1", new ArrayList<LabelKey>());
  }

  @Test
  public void addDoubleGauge_NullDescription() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addDoubleGaugeMetric("name", null, "1", new ArrayList<LabelKey>());
  }

  @Test
  public void addDoubleGauge_NullUnit() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addDoubleGaugeMetric("name", "description", null, new ArrayList<LabelKey>());
  }

  @Test
  public void addDoubleGauge_NullLabels() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addDoubleGaugeMetric("name", "description", "1", null);
  }

  @Test
  public void addDoubleGauge_NoopPoint() {
    DoubleGaugeMetric doubleGaugeMetric =
        metricRegistry.addDoubleGaugeMetric("name", "description", "1", new ArrayList<LabelKey>());
    DoubleGaugeMetric.Point dp = doubleGaugeMetric.addTimeSeries(new ArrayList<LabelValue>());
    dp.inc();
    dp.inc(12);
    dp.set(12);
    dp.dec(12);
    dp.dec();

    doubleGaugeMetric.getDefaultTimeSeries();
    doubleGaugeMetric.addTimeSeries(
        new ArrayList<LabelValue>(),
        null,
        new ToDoubleFunction<Object>() {
          @Override
          public double applyAsDouble(Object value) {
            return 10.0;
          }
        });
  }

  @Test
  public void addLongGauge_NullName() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addLongGaugeMetric(null, "description", "1", new ArrayList<LabelKey>());
  }

  @Test
  public void addLongGauge_NullDescription() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addLongGaugeMetric("name", null, "1", new ArrayList<LabelKey>());
  }

  @Test
  public void addLongGauge_NullUnit() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addLongGaugeMetric("name", "description", null, new ArrayList<LabelKey>());
  }

  @Test
  public void addLongGauge_NullLabels() {
    thrown.expect(NullPointerException.class);
    metricRegistry.addLongGaugeMetric("name", "description", "1", null);
  }

  @Test
  public void addLongGauge_NoopPoint() {
    LongGaugeMetric longGaugeMetric =
        metricRegistry.addLongGaugeMetric("name", "description", "1", new ArrayList<LabelKey>());
    Point dp = longGaugeMetric.addTimeSeries(new ArrayList<LabelValue>());
    dp.inc();
    dp.inc(12);
    dp.set(12);
    dp.dec(12);
    dp.dec();

    longGaugeMetric.getDefaultTimeSeries();
    longGaugeMetric.addTimeSeries(
        new ArrayList<LabelValue>(),
        null,
        new ToLongFunction<Object>() {
          @Override
          public long applyAsLong(Object value) {
            return 10;
          }
        });
  }
}
