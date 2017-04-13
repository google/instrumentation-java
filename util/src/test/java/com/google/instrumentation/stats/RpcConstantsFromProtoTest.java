/*
 * Copyright 2016, Google Inc.
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

package com.google.instrumentation.stats;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Tests for RpcConstantsFromProto.
 */
@RunWith(JUnit4.class)
public class RpcConstantsFromProtoTest {
  @Test
  public void testConstants() {
    assertThat(RpcConstantsFromProto.RPC_CLIENT_ERROR_COUNT).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_CLIENT_ROUNDTRIP_LATENCY).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_CLIENT_REQUEST_BYTES).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_CLIENT_RESPONSE_BYTES).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_CLIENT_UNCOMPRESSED_REQUEST_BYTES).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_CLIENT_UNCOMPRESSED_RESPONSE_BYTES).isNotNull();

    assertThat(RpcConstantsFromProto.RPC_SERVER_ERROR_COUNT).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_SERVER_REQUEST_BYTES).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_SERVER_RESPONSE_BYTES).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_SERVER_LATENCY).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_SERVER_UNCOMPRESSED_REQUEST_BYTES).isNotNull();
    assertThat(RpcConstantsFromProto.RPC_SERVER_UNCOMPRESSED_RESPONSE_BYTES).isNotNull();
  }

  @Test(expected = AssertionError.class)
  public void testConstructor() {
    new RpcConstantsFromProto();
  }
}
