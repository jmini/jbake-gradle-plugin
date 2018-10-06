/*
 * Copyright 2014-2018 the original author or authors.
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
package org.jbake.gradle.impl

import org.jbake.gradle.JBakeInitProxy

class JBakeInitProxyImpl implements JBakeInitProxy {
    Class delegate

    def init

    @Override
    void prepare() {
        init = delegate.newInstance()
    }

    @Override
    void setConfig(Object config) {
        init.config = config
    }

    @Override
    void init(String type, File outputDir) {
        try {
            init.run(outputDir, type)
            println("Base folder structure successfully created.")
        } catch (final Exception e) {
            final String msg = "Failed to initialise structure: " + e.getMessage()
            throw new IllegalStateException(msg, e)
        }
    }
}
