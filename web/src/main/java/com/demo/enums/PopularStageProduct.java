package com.demo.enums;

public enum PopularStageProduct {

    HIGH_POPULAR(0.7,1.0, "潮流狂热者"),
    MIDDLE_POPULAR(0.5,0.7,"潮流忠实者"),
    LOW_POPULAR(0.2,0.5,"潮流追求者")

    ;

    private double popularParamUp;
    private double popularParamDown;
    private String imageName;

    PopularStageProduct(double popularParamUp,double popularParamDown,String imageName) {
        this.popularParamUp = popularParamUp;
        this.popularParamDown = popularParamDown;
        this.imageName = imageName;
    }

    public static PopularStageProduct getPopularStageProduct(double rate) {
        for (PopularStageProduct item : PopularStageProduct.values()) {
            if (rate > item.popularParamUp && rate <= item.popularParamDown) {
                return item;
            }
        }
        return null;
    }

    public static PopularStageProduct getPopularStageProduct(String name) {
        for (PopularStageProduct item : PopularStageProduct.values()) {
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
