package com.wut.self.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wut.common.model.entity.UserInterfaceInfo;

import java.util.List;


/**
 * @author Administrator
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
 * @createDate 2023-07-24 18:01:38
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    /**
     * 用户接口调用信息验证
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 获取某个用户剩余的接口调用次数
     */
    Integer getInterfaceResidueNum(long userId, long interfaceId);

    /**
     * 显示调用次数最多的几个接口记录
     * interfaceInfoId
     * totalNum
     */
    List<UserInterfaceInfo> listTopInvokeInterface();
}
