/*
   Copyright 2016 Immutables Authors and Contributors

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.immutables.generator;

import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;

public final class ExtensionLoader {
  private ExtensionLoader() {}

  private static final Splitter RESOURCE_SPLITTER = Splitter.on("\n")
      .omitEmptyStrings()
      .trimResults();

  public static ImmutableSet<String> findExtensions(String resource) {
    List<String> annotations = Lists.newArrayList();

    ClassLoader classLoader = ExtensionLoader.class.getClassLoader();
    try {
      Enumeration<URL> resources = classLoader.getResources(resource);
      while (resources.hasMoreElements()) {
        URL nextElement = resources.nextElement();
        String lines = Resources.toString(nextElement, StandardCharsets.UTF_8);
        annotations.addAll(RESOURCE_SPLITTER.splitToList(lines));
      }
    } catch (IOException cannotReadFromClasspath) {
      // we ignore this as we did or best effort
      // and there are no plans to halt whole compilation
    }
    return FluentIterable.from(annotations).toSet();
  }
}
