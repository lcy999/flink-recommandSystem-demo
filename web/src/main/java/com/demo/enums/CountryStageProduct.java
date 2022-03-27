package com.demo.enums;

public enum CountryStageProduct {

    EXTERNAL_COUNTRY("进口爱好者"),
    INNER_COUNTRY("国产支撑者")

    ;

    private String imageName;

    CountryStageProduct(String imageName) {
        this.imageName = imageName;
    }


    public static CountryStageProduct getCountryStageProduct(String name) {
        for (CountryStageProduct item : CountryStageProduct.values()) {
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
