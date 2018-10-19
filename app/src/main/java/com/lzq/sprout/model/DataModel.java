package com.lzq.sprout.model;

import com.lzq.sprout.base.BaseModel;

public class DataModel {
    public static BaseModel requestModel(String token){
        BaseModel model = null;
        try {
            model = (BaseModel) Class.forName(token).newInstance();
        } catch (Exception e) {
        }
        return model;
    }
}
