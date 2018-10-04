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

import io.opencensus.common.ExperimentalApi;
import java.util.List;

/**
 * Creates and manages your application's set of metrics. The default implementation of this creates
 * a {@link io.opencensus.metrics.export.MetricProducer} and registers it to the global {@link
 * io.opencensus.metrics.export.MetricProducerManager}.
 *
 * @since 0.17
 */
@ExperimentalApi
public abstract class MetricRegistry {
  /**
   * Build a new long gauge to be added to the registry.
   *
   * <p>Must be called only once.
   *
   * @param name the name of the metric.
   * @param description the description of the metric.
   * @param unit the unit of the metric.
   * @param labelKeys the list of label keys.
   * @since 0.17
   */
  @ExperimentalApi
  public abstract LongGaugeMetric addLongGaugeMetric(
      String name, String description, String unit, List<LabelKey> labelKeys);

  /**
   * Build a new double gauge to be added to the registry.
   *
   * <p>Must be called only once.
   *
   * @param name the name of the metric.
   * @param description the description of the metric.
   * @param unit the unit of the metric.
   * @param labelKeys the list of label keys.
   * @since 0.17
   */
  @ExperimentalApi
  public abstract DoubleGaugeMetric addDoubleGaugeMetric(
      String name, String description, String unit, List<LabelKey> labelKeys);

  static MetricRegistry newNoopMetricRegistry() {
    return new NoopMetricRegistry();
  }

  private static final class NoopMetricRegistry extends MetricRegistry {

    @Override
    public LongGaugeMetric addLongGaugeMetric(
        String name, String description, String unit, List<LabelKey> labelKeys) {
      return LongGaugeMetric.getNoopLongGaugeMetric(name, description, unit, labelKeys);
    }

    @Override
    public DoubleGaugeMetric addDoubleGaugeMetric(
        String name, String description, String unit, List<LabelKey> labelKeys) {
      return DoubleGaugeMetric.getNoopDoubleGaugeMetric(name, description, unit, labelKeys);
    }
  }
}
