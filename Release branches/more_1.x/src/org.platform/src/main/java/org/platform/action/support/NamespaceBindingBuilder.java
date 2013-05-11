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
package org.platform.action.support;
/***
 * 
 * @version : 2013-5-11
 * @author 赵永春 (zyc@byshell.org)
 */
public interface ActionBinder {
    /**绑定一个命名空间*/
    public NamespaceBindingBuilder bindPath(String path);
    //
    //
    public static interface NamespaceBindingBuilder {
        public ActionBindingBuilder bindAction(String actionName)
        //public NamespaceBindingBuilder bindPath(String namespacePath)
    }
    
    
    public ActionBindingBuilder bindAction(String actionName)
    //public NamespaceBindingBuilder bindPath(String namespacePath)
}



