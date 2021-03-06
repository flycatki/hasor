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
package net.hasor.freemarker.servlet;
import net.hasor.context.ModuleSettings;
import net.hasor.context.anno.DefineModule;
import net.hasor.freemarker.support.FreemarkerSupportModule;
import net.hasor.servlet.AbstractWebHasorModule;
import net.hasor.servlet.WebApiBinder;
import net.hasor.servlet.anno.support.ServletAnnoSupportModule;
/**
 * 模板服务 
 * @version : 2013-4-8
 * @author 赵永春 (zyc@byshell.org)
 */
@DefineModule(displayName = "TemplatePlatformListener", description = "org.hasor.view.template软件包功能支持。")
public class TemplateSupportModule extends AbstractWebHasorModule {
    public void configuration(ModuleSettings info) {
        info.beforeMe(ServletAnnoSupportModule.class);//在hasor-servlet启动之前
        info.beforeMe(FreemarkerSupportModule.class);//在FreemarkerSupportModule启动之前
        //freemarker依赖检查
        try {
            Thread.currentThread().getContextClassLoader().loadClass("freemarker.template.Configuration");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**初始化.*/
    public void init(WebApiBinder apiBinder) {
        TempSettings tempSettings = new TempSettings();
        tempSettings.onLoadConfig(apiBinder.getInitContext().getSettings());
        apiBinder.getGuiceBinder().bind(TempSettings.class).toInstance(tempSettings);
        //
        if (tempSettings.isEnable() == false)
            return;
        //
        String[] suffix = tempSettings.getSuffix();
        if (suffix != null)
            for (String suf : suffix)
                apiBinder.serve(suf).with(FmHttpServlet.class);
    }
}