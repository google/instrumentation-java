/*
 * Copyright 2017, Google Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.opencensus.internal;

import io.opencensus.common.Function;

public final class InternalFunctions {
  private InternalFunctions() {}

  private static final Function<Object, Void> THROW_ASSERTION_ERROR =
      new Function<Object, Void>() {
        @Override
        public Void apply(Object ignored) {
          throw new AssertionError();
        }
      };

  /**
   * A {@code Function} that always ignores its argument and throws an {@link AssertionError}.
   *
   * @return a {@code Function} that always ignores its argument and throws an {@code
   *     AssertionError}.
   */
  public static <T> Function<Object, T> throwAssertionError() {
    // It is safe to cast a producer of Void to anything, because Void is always null.
    @SuppressWarnings("unchecked")
    Function<Object, T> function = (Function<Object, T>) THROW_ASSERTION_ERROR;
    return function;
  }
}
