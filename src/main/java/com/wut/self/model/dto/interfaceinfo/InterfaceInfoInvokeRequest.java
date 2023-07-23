package com.wut.self.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zeng1998
 * @CreateTime 2023-07-23  23:29
 * @Description 接口调用参数
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户传入的请求参数
     */
    private String userRequestParams;
}
