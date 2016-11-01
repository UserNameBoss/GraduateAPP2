package com.example.yangxiaolong.graduateapp.domain;

/**
 * Created by yangxiaolong on 2016/11/1.
 */
public class Userss {

    private ListUserContent02 listUserContent02;

    public Userss(ListUserContent02 listUserContent02) {
        this.listUserContent02 = listUserContent02;
    }

    public ListUserContent02 getListUserContent02() {
        return listUserContent02;
    }

    public void setListUserContent02(ListUserContent02 listUserContent02) {
        this.listUserContent02 = listUserContent02;
    }

    @Override
    public String toString() {
        return "Userss{" +
                "listUserContent02=" + listUserContent02 +
                '}';
    }
}
