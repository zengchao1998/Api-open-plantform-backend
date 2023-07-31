package com.wut.self.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 添加接口信息
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 接口Host
     */
    private String host;

    /**
     * 接口地址
     */
    private String path;

    /**
     * 请求类型GET/POST
     */
    private String method;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;
}