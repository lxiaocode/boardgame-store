package com.lxiaocode.boardgame.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author lixiaofeng
 * @date 2020/11/17 下午6:45
 * @blog http://www.lxiaocode.com/
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object deleted = getFieldValByName("deleted", metaObject);

        Date date = new Date();
        if (createTime == null){
            strictInsertFill(metaObject, "createTime", Date.class, date);
        }
        if (updateTime == null){
            strictInsertFill(metaObject, "updateTime", Date.class, date);
        }
        if (deleted == null){
            strictInsertFill(metaObject, "deleted", Integer.class, 0);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);
        // 下面的方法，如果属性有值则不覆盖
        //strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
