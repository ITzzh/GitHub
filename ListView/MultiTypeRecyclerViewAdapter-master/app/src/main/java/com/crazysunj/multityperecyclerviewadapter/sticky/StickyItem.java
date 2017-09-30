package com.crazysunj.multityperecyclerviewadapter.sticky;

import com.crazysunj.multitypeadapter.entity.MultiHeaderEntity;

/**
 * Created by sunjian on 2017/3/30.
 */

public interface StickyItem extends MultiHeaderEntity {
    void setHeaderId(long headerId);

    void setStickyName(String stickyName);

    String getStickyName();
}
