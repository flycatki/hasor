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
package org.hasor.web.controller;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
/***
 * 
 * @version : 2013-5-11
 * @author ������ (zyc@byshell.org)
 */
public interface ActionBinder {
    /**��һ�������ռ�*/
    public NameSpaceBindingBuilder bindNameSpace(String path);
    /**�󶨽��������*/
    public ResultProcessBindingBuilder bindResultProcess(Class<? extends Annotation> annoType);
    /**������һ�������ռ�*/
    public static interface NameSpaceBindingBuilder {
        public String getNameSpace();
        /**ע��һ��Action����action�󶨵������ϡ�*/
        public ActionBindingBuilder bindActionClass(Class<?> targetClass);
        /**ע��һ��Action����action�󶨵������ϡ�*/
        public ActionBindingBuilder bindActionMethod(Method targetMethod);
    }
    /**������action��ִ��Ŀ��*/
    public static interface ActionBindingBuilder {
        /**��action�󶨵�Http�����ϡ�*/
        public ActionBindingBuilder onHttpMethod(String httpMethod);
        /**�����÷��ص�MimeType��*/
        public ActionBindingBuilder returnMimeType(String mimeType);
        /**restful����ӳ��*/
        public void mappingRestful(String restfulMapping);
        /**Action�����Ķ���*/
        public void toInstance(Object targetAction);
    }
    /**�󶨽��������*/
    public static interface ResultProcessBindingBuilder {
        /**ResultProcess*/
        public void toType(Class<? extends ResultProcess> resultProcess);
        /**ResultProcess*/
        public void toInstance(ResultProcess targetProcess);
    }
}