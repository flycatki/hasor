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
package net.hasor.web.startup;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import net.hasor.core.AppContext;
import net.hasor.core.Hasor;
import net.hasor.core.context.AbstractAppContext;
import net.hasor.web.binder.SessionListenerPipeline;
import net.hasor.web.context.AnnoWebAppContext;
import org.more.util.ContextClassLoaderLocal;
/**
 * 
 * @version : 2013-3-25
 * @author 赵永春 (zyc@hasor.net)
 */
public class RuntimeListener implements ServletContextListener, HttpSessionListener {
    public static final String                             AppContextName          = AppContext.class.getName();
    private AbstractAppContext                             appContext              = null;
    private SessionListenerPipeline                        sessionListenerPipeline = null;
    private static ContextClassLoaderLocal<ServletContext> LocalServletContext     = new ContextClassLoaderLocal<ServletContext>();
    private static ContextClassLoaderLocal<AppContext>     LocalAppContext         = new ContextClassLoaderLocal<AppContext>();
    /*----------------------------------------------------------------------------------------------------*/
    protected AbstractAppContext createAppContext(ServletContext sc) throws Throwable {
        return new AnnoWebAppContext("hasor-config.xml", sc);
    }
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.创建AppContext
        try {
            this.appContext = this.createAppContext(servletContextEvent.getServletContext());
            this.appContext.start();
            LocalServletContext.set(servletContextEvent.getServletContext());
            LocalAppContext.set(this.appContext);
        } catch (Throwable e) {
            Hasor.logError("createAppContext error.\n%s", e);
            if (e instanceof RuntimeException)
                throw (RuntimeException) e;
            throw new RuntimeException(e);
        }
        //2.获取SessionListenerPipeline
        this.sessionListenerPipeline = this.appContext.getInstance(SessionListenerPipeline.class);
        this.sessionListenerPipeline.init(this.appContext);
        Hasor.logInfo("sessionListenerPipeline created.");
        //3.放入ServletContext环境。
        Hasor.logInfo("ServletContext Attribut : " + AppContextName + " -->> " + Hasor.logString(this.appContext));
        servletContextEvent.getServletContext().setAttribute(AppContextName, this.appContext);
        this.sessionListenerPipeline.contextInitialized(servletContextEvent);
    }
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        this.sessionListenerPipeline.contextDestroyed(servletContextEvent);
        this.appContext.stop();
    }
    public void sessionCreated(HttpSessionEvent se) {
        this.sessionListenerPipeline.sessionCreated(se);
    }
    public void sessionDestroyed(HttpSessionEvent se) {
        this.sessionListenerPipeline.sessionDestroyed(se);
    }
    //
    /**获取{@link ServletContext}*/
    public static ServletContext getLocalServletContext() {
        return LocalServletContext.get();
    }
    //
    /**获取{@link AppContext}*/
    public static AppContext getLocalAppContext() {
        return LocalAppContext.get();
    }
}