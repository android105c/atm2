package com.etone.atm.entity;

import android.content.ContentValues;

import com.etone.atm.DAO.ExpendDAO;
import com.etone.atm.Utils.Utils;

import java.util.Date;

import lombok.Data;

/**
 * Created by Maggie on 2016/5/12.
 */

@Data
public class Expend {
    int _id;
    Date date;
    String info;
    int amount;

    public Expend(String cdate, String info, int amount) {
        this.amount = amount;

        this.date = Utils.stringToDate(cdate);
        this.info = info;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(ExpendDAO.FIELD_DATE, Utils.dateToString(date));
        values.put(ExpendDAO.FIELD_INFO, info);
        values.put(ExpendDAO.FIELD_AMOUNT, amount);
        return values;
    }
}
