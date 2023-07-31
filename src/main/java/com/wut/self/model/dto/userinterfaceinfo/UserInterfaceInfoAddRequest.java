package com.wut.self.model.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建用户接口调用关系
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调用用户 id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer residueNum;

    /**
     * 0-正常,1-禁用
     */
    private Integer status;

}