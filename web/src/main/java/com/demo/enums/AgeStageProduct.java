package com.demo.enums;

public enum AgeStageProduct{

    CHILDREN_PRODUCT(1,19,"孩子产品购物者"),
    YOUNG_PRODUCT(19,41,"年轻人产品购物者"),
    OLD_PRODUCT(41,200,"老年人产品购物者")

    ;
    private int upAge;
    private int downAge;
    private String imageName;
    private AgeStageProduct(int upAge,int downAge,String imageName){
        this.upAge = upAge;
        this.downAge = downAge;
        this.imageName = imageName;
    }

    public static AgeStageProduct getAgeStageProduct(int age) {
        for (AgeStageProduct item : AgeStageProduct.values()) {
            if (age >= item.upAge && age < item.downAge) {
                return item;
            }
        }
        return null;
    }

    public static AgeStageProduct getAgeStageProduct(String name) {
        for (AgeStageProduct item : AgeStageProduct.values()) {
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
