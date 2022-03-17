package com.demo.enums;

public enum SpeedOrderStageProduct {

    HIGH_SPEED(0.7,1.0,"下单圣手"),
    MIDDLE_SPEED(0.4,0.7,"下单国手"),
    LOW_SPEED(0.1,0.4,"下单快手")
    ;
    private double speedUp;
    private double speedDown;
    private String imageName;

    SpeedOrderStageProduct(double speedUp, double speedDown,String imageName) {
        this.speedDown = speedDown;
        this.speedUp = speedUp;
        this.imageName = imageName;
    }

    public static SpeedOrderStageProduct getSpeedOrderStageProduct(double rate) {
        for (SpeedOrderStageProduct item : SpeedOrderStageProduct.values()) {
            if (rate > item.speedUp && rate <= item.speedDown) {
                return item;
            }
        }
        return null;
    }

    public String getImageName() {
        return imageName;
    }
    public static SpeedOrderStageProduct getSpeedOrderStageProduct(String name) {
        for (SpeedOrderStageProduct item : SpeedOrderStageProduct.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
