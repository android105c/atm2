package com.etone.atm.DAO;

import com.etone.atm.Utils.MyApplication;
import com.etone.atm.Utils.MyDBHelper;
import com.etone.atm.entity.Expend;

/**
 * Created by Maggie on 2016/5/13.
 */
public class ExpendDAO  {

    private static final String TABLE_NAME = "exp";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_DATE = "cdate";
    public static final String FIELD_INFO = "info";
    public static final String FIELD_AMOUNT = "amount";

    public static int insert(Expend expend) {
        return (int) MyDBHelper.getInstance(MyApplication.getContext()).getWritableDatabase().insert(TABLE_NAME, null, expend.getContentValues());

    }
}
