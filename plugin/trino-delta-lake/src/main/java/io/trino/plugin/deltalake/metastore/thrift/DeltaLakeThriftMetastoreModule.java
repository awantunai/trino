/*
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
package io.trino.plugin.deltalake.metastore.thrift;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.airlift.configuration.AbstractConfigurationAwareModule;
import io.trino.plugin.deltalake.HideNonDeltaLakeTables;
import io.trino.plugin.hive.metastore.thrift.ThriftMetastoreModule;

public class DeltaLakeThriftMetastoreModule
        extends AbstractConfigurationAwareModule
{
    @Override
    protected void setup(Binder binder)
    {
        install(new ThriftMetastoreModule());
    }

    // TODO support delta.hide-non-delta-lake-tables with thrift metastore (https://github.com/trinodb/trino/issues/12014)
    @Provides
    @Singleton
    @HideNonDeltaLakeTables
    public boolean provideHideNonDeltaLakeTables()
    {
        return false;
    }
}
