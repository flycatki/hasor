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
package org.more.core.classcode;
/**
* 过滤器链，生成的新类中所有AOP代理方法都会调用该类的doInvoke以触发方法调用动作。
* 通过doInvoke方法进行AOP过滤器链的调用。
* <br/><br/>在开发过程中开发人员不会碰触该类实例对象。
* Date : 2009-10-30
* @author 赵永春
*/
public class ImplAOPFilterChain implements AOPFilterChain {
    private AOPInvokeFilter thisFilter      = null; //表示过滤器链的当前过滤器。
    private AOPFilterChain  nextFilterChain = null; //过滤器链的下一个过滤器。
    /** */
    public ImplAOPFilterChain(AOPInvokeFilter thisFilter, AOPFilterChain nextFilterChain) {
        this.thisFilter = thisFilter;
        this.nextFilterChain = nextFilterChain;
    }
    @Override
    public Object doInvokeFilter(Object target, AOPMethods methods, Object[] args) throws Throwable {
        if (this.nextFilterChain != null)
            return this.thisFilter.doFilter(target, methods, args, this.nextFilterChain);
        else
            return methods.getMethod().invoke(target, args);
    }
    /** 该方法是生成的类中方法受到AOP代理时候调用。 */
    public Object doInvoke(Object target, String methodDesc, Object[] args) {
        try {
            ClassEngine ce = (ClassEngine) target.getClass().getClassLoader();
            return this.thisFilter.doFilter(target, ce.aopMethods.get(methodDesc), args, this.nextFilterChain);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}