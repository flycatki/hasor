/*
 * Copyright 2008-2009 the original author or authors.
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
package net.hasor.jdbc.transaction.support;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import net.hasor.jdbc.datasource.SavepointManager;
import net.hasor.jdbc.datasource.local.ConnectionHolder;
/**
 * 
 * @version : 2014-1-18
 * @author ������ (zyc@byshell.org)
 */
public class TransactionObject {
    private ConnectionHolder holder     = null;
    private DataSource       dataSource = null;
    public TransactionObject(ConnectionHolder holder, DataSource dataSource) {
        this.holder = holder;
        this.dataSource = dataSource;
    }
    public ConnectionHolder getHolder() {
        return this.holder;
    }
    public DataSource getDataSource() {
        return dataSource;
    }
    public SavepointManager getSavepointManager() {
        return this.getHolder();
    };
    /**�ع����ع�֮����Ҫ���¿�������*/
    public void rollback() throws SQLException {
        this.holder.getConnection().rollback();
        this.holder.getConnection().setAutoCommit(true);
    }
    /**�ݽ����ݽ�֮����Ҫ���¿�������*/
    public void commit() throws SQLException {
        this.holder.getConnection().commit();
        this.holder.getConnection().setAutoCommit(true);
    }
    public boolean hasTransaction() throws SQLException {
        return this.holder.hasTransaction();
    };
    public void beginTransaction() throws SQLException {
        Connection conn = this.holder.getConnection();
        boolean autoMark = conn.getAutoCommit();
        if (autoMark == true)
            conn.setAutoCommit(false);//������autoCommit����Ϊfalse������Ϊ�ֶ��ݽ�����
    }
}