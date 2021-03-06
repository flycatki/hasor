/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
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
package org.more.webui.render.inputs;
import org.more.webui.components.UIInput;
import org.more.webui.context.ViewContext;
/**
 * 将输入组建渲染成input[type=checkbox]。
 * <br><b>客户端模型</b>：UIInput（UIInput.js）
 * @version : 2012-5-18
 * @author 赵永春 (zyc@byshell.org)
 */
public class CheckboxInputRender<T extends UIInput> extends AbstractInputRender<T> {
    @Override
    public InputType getInputType(ViewContext viewContext, T component) {
        return InputType.checkbox;
    }
}