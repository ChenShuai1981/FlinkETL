package com.caselchen.flink;

import lombok.AllArgsConstructor;
import lombok.ToString;

@lombok.Data
@ToString
@AllArgsConstructor
public class Data {
    private long timestamp;
    private String event;
    private String uuid;
}
