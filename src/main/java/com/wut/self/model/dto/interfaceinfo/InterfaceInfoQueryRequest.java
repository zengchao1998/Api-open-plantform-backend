package com.wut.self.model.dto.interfaceinfo;

import com.wut.self.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询接口信息
 *
 * @author zeng1998
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 接口host
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

    /**
     * 接口状态(0-关闭,1-开启)
     */
    private Integer status;

    /**
     * 创建人
     */
    private Long userId;
}