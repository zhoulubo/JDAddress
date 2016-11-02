package com.scofield.address;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

/**
 * 数据库操作入口
 * @author allen@tronsis.com
 * @date 7/6/2016 12:06 PM
 */
public class DBUtil {
    public static final String DB_NAME = "area.db";
    private static LiteOrm mLiteOrm;

    public static LiteOrm getInstance(Context context){
        if (mLiteOrm == null) {
            DataBaseConfig config = new DataBaseConfig(context);
            config.dbName = DB_NAME;
            config.dbVersion = 1;
            mLiteOrm = LiteOrm.newSingleInstance(config);
        }
        return mLiteOrm;
    }

    public void deleteDB(Context context){
        if(mLiteOrm == null){
            mLiteOrm = LiteOrm.newSingleInstance(context, DB_NAME);
        }
        mLiteOrm.deleteDatabase();
    }
}
