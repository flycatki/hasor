/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.plugins.datasource.factory;
import javax.sql.DataSource;
import net.hasor.core.Environment;
import net.hasor.core.Hasor;
import net.hasor.core.XmlNode;
import net.hasor.plugins.datasource.DataSourceFactory;
import org.apache.commons.dbcp.BasicDataSource;
/**
 * 
 * @version : 2013-9-16
 * @author ������(zyc@hasor.net)
 */
public class DbcpFactory implements DataSourceFactory {
    public DataSource createDataSource(Environment env, XmlNode configElement) throws Throwable {
        BasicDataSource dataSource = new BasicDataSource();
        //
        String driverString = configElement.getOneChildren("driver").getText();//<driver>com.microsoft.sqlserver.jdbc.SQLServerDriver</driver>
        String urlString = configElement.getOneChildren("url").getText();//<url>jdbc:sqlserver://10.200.15.100;DatabaseName=NOE_ESTUDY</url>
        String userString = configElement.getOneChildren("user").getText();//<user>sa</user>
        String pwdString = configElement.getOneChildren("password").getText();//<password>abc123!@#</password>
        int poolMaxSize = 200;
        //
        Hasor.logInfo("DBCP Pool Info maxSize is ��%s�� driver is ��%s�� jdbcUrl is��%s��", poolMaxSize, driverString, urlString);
        //
        dataSource.setDriverClassName(driverString);
        dataSource.setUrl(urlString);
        dataSource.setUsername(userString);
        dataSource.setPassword(pwdString);
        dataSource.setMaxActive(poolMaxSize);
        dataSource.setInitialSize(1);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select 'ok' as msg");
        return dataSource;
    }
}