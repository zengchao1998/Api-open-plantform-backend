package com.wut.self.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author zeng1998
 * @CreateTime 2023-07-28  15:20
 * @Description 接口信息的响应包装类
 */
@Data
public class InterfaceInfoVO implements Serializable {

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
     * 接口地址
     */
    private String url;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 调用次数
     */
    private Integer totalNum;

    private static final long serialVersionUID = 1L;
}
