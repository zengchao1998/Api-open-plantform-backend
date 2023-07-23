package com.wut.self.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zeng1998
 * @CreateTime 2023-07-23  16:27
 * @Description 接口状态枚举值
 */
public enum InterfaceInfoStatusEnum {

    OFFLINE("关闭", 0),
    ONLINE("上线", 1);

    private final String description;
    private final Integer value;

    InterfaceInfoStatusEnum(String description, int value) {
        this.description = description;
        this.value = value;
    }

    /**
     * 获取值列表
     */
    public static List<Integer> getCodes() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    public int getValue() {return value;}

    public String getDescription() {return description;}
}
