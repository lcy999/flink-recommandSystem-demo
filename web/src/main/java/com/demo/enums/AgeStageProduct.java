package com.demo.enums;

public enum AgeStageProduct{

    CHILDREN_PRODUCT(1,19),
    YOUNG_PRODUCT(19,41),
    OLD_PRODUCT(41,200)

    ;
    private int upAge;
    private int downAge;
    private AgeStageProduct(int upAge,int downAge){
        this.upAge = upAge;
        this.downAge = downAge;
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


}
