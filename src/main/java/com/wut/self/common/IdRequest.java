package com.wut.self.common;

import lombok.Data;

import java.io.Serializable;

/**
 * id 请求类
 */
@Data
public class IdRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
}