package com.crazysunj.multityperecyclerviewadapter.expand;

import com.crazysunj.multitypeadapter.entity.MultiHeaderEntity;

/**
 * description
 * <p>
 * Created by sunjian on 2017/7/5.
 */

public interface OpenCloseItem extends MultiHeaderEntity {
    int getFlag();

    String getTitle();
}
