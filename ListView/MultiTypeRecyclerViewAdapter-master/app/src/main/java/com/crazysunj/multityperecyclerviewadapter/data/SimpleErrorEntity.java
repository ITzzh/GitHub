package com.crazysunj.multityperecyclerviewadapter.data;

import com.crazysunj.multitypeadapter.entity.DefaultMultiHeaderEntity;
import com.crazysunj.multitypeadapter.helper.RecyclerViewAdapterHelper;

import java.util.UUID;

/**
 * description
 * <p>
 * Created by sunjian on 2017/5/16.
 */

public class SimpleErrorEntity extends DefaultMultiHeaderEntity {

    private long id;
    private int type;

    public SimpleErrorEntity(int type) {
        this.id = UUID.nameUUIDFromBytes(("error_" + type).getBytes()).hashCode();
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type - RecyclerViewAdapterHelper.ERROR_TYPE_DIFFER;
    }

    @Override
    public long getId() {
        return id;
    }
}
