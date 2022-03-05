package com.demo.domain;

import lombok.Data;

/**
 * @author XINZE
 */
@Data
public class HLogEntity {

    private String id;
    private String userId;
    private String productId;
    private String time;
    private String action;

}
