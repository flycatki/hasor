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
package org.more.submit;
import java.lang.reflect.Field;
import org.more.core.error.DefineException;
import org.more.core.error.ExistException;
import org.more.util.StringConvert;
/**
 * 该类是{@link ActionContext}接口的一个基本实现。该类只是实现了findAction方法。其子类可以管理actionbean对象的创建和生命周期。
 * 此外AbstractActionContext类还在findAction方法中实现了注解的支持。
 * @version 2010-7-26
 * @author 赵永春 (zyc@byshell.org)
 */
public abstract class AbstractActionContext implements ActionContext {
    public ActionInvoke findAction(String actionName, String invoke) throws ExistException {
        if (this.containsAction(actionName) == false)
            throw new ExistException("找不到名称为[" + actionName + "]的对象");
        //
        boolean isAction = true;
        //-----------1.注解配置
        Class<?> actionType = this.getActionType(actionName);
        Action act = actionType.getAnnotation(Action.class);
        if (act == null || act.isAction() == false)
            isAction = false;
        //-----------2.元信息属性配置
        if (isAction == false) {
            Object obj = this.getActionProperty(actionName, "isAction");
            if (obj != null)
                isAction = StringConvert.parseBoolean(obj.toString());
        }
        //-----------3.字段配置, public boolean isAction=true;
        try {
            Field field = actionType.getField("isAction");
            if (field.getType() == Boolean.class)
                isAction = (Boolean) field.get(null);
        } catch (Exception e) {}
        //-----------
        if (isAction == false)
            throw new DefineException("名称为[" + actionName + "]的对象不是一个Action对象");
        return this.getAction(actionName, invoke);
    }
    /**该方法指出当{@link AbstractActionContext}类的具体查找{@link ActionInvoke}的方式。*/
    protected abstract ActionInvoke getAction(String actionName, String invoke);
}