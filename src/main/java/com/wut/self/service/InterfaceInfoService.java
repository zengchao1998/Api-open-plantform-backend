package com.wut.self.service;

import com.wut.self.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-07-15 20:40:42
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 接口信息校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
