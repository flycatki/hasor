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
package org.more.hypha.xml.tags.el;
import org.more.hypha.xml.XmlDefineResource;
import org.more.hypha.xml.tags.Tag_Abstract;
/**
 * ���ڽ���el-config�����ռ��ǩ���������࣬��Ҫ�������ֲ�ͬ�����ռ䡣
 * @version : 2011-4-22
 * @author ������ (zyc@byshell.org)
 */
public abstract class EL_NS extends Tag_Abstract {
    public static final String ELConfigList = "org.more.hypha.el.EL_LIST";
    public EL_NS(XmlDefineResource configuration) {
        super(configuration);
    };
};