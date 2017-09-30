/**
 * Copyright 2017 Sun Jian
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crazysunj.multitypeadapter.entity;

import java.util.List;

/**
 * 给异步AdapterHelper封装实体类
 * Created by sunjian on 2017/3/31.
 */
public final class HandleBase<T> {
    private List<T> newData;
    private T header;
    private T footer;
    private int refreshType;
    private int type;

    public HandleBase(List<T> newData, T header, T footer, int type, int refreshType) {
        this.newData = newData;
        this.header = header;
        this.footer = footer;
        this.type = type;
        this.refreshType = refreshType;
    }

    public List<T> getNewData() {
        return newData;
    }

    public T getNewHeader() {
        return header;
    }

    public T getNewFooter() {
        return footer;
    }

    public int getType() {
        return type;
    }

    public int getRefreshType() {
        return refreshType;
    }
}
