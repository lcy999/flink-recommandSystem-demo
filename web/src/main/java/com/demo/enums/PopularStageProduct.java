package com.demo.enums;

public enum PopularStageProduct {

    HIGH_POPULAR(0.7),
    MIDDLE_POPULAR(0.5),
    LOW_POPULAR(0.2)

    ;

    private double popularParam;

    PopularStageProduct(double popularParam) {
        this.popularParam = popularParam;
    }


}
