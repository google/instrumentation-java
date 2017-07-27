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

package io.opencensus.tags;

import io.opencensus.common.Function;
import io.opencensus.common.Functions;
import io.opencensus.tags.Tag.TagBoolean;
import io.opencensus.tags.Tag.TagLong;
import io.opencensus.tags.Tag.TagString;

final class TagContextUtils {
  private TagContextUtils() {}

  static void addTagToBuilder(Tag tag, TagContextBuilder builder) {
    tag.match(
        new AddTagString(builder),
        new AddTagLong(builder),
        new AddTagBoolean(builder),
        Functions.<Void>throwAssertionError());
  }

  private static class AddTagString implements Function<TagString, Void> {
    private final TagContextBuilder builder;

    AddTagString(TagContextBuilder builder) {
      this.builder = builder;
    }

    @Override
    public Void apply(TagString tag) {
      builder.set(tag.getKey(), tag.getValue());
      return null;
    }
  }

  private static class AddTagLong implements Function<TagLong, Void> {
    private final TagContextBuilder builder;

    AddTagLong(TagContextBuilder builder) {
      this.builder = builder;
    }

    @Override
    public Void apply(TagLong tag) {
      builder.set(tag.getKey(), tag.getValue());
      return null;
    }
  }

  private static class AddTagBoolean implements Function<TagBoolean, Void> {
    private final TagContextBuilder builder;

    AddTagBoolean(TagContextBuilder builder) {
      this.builder = builder;
    }

    @Override
    public Void apply(TagBoolean tag) {
      builder.set(tag.getKey(), tag.getValue());
      return null;
    }
  }
}
