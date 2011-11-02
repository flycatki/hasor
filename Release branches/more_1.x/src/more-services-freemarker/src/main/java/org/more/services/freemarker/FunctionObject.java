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
package org.more.services.freemarker;
import java.util.List;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
/**
 * 
 * @version : 2011-9-16
 * @author 赵永春 (zyc@byshell.org)
 */
public abstract class FunctionObject implements TemplateMethodModel {
    public Object exec(List arg0) throws TemplateModelException {
        // TODO 未来实现Freemarker的方法调用。
        return this.call(arg0);
    }
    public abstract Object call(List<?> params);
}