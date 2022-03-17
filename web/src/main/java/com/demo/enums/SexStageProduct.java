package com.demo.enums;

public enum SexStageProduct {

    MAN_PRODUCT("男性产品购物者"),
    WAMAN_PRODUCT("女性产品购物者")

    ;
    private String imageName;
    SexStageProduct(String imageName) {
        this.imageName = imageName;
    }

    public static SexStageProduct getSexStageProduct(String name) {
        for (SexStageProduct item : SexStageProduct.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
    public String getImageName() {
        return imageName;
    }
}
