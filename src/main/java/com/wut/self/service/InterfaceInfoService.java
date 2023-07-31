package com.wut.self.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wut.common.model.entity.UserInterfaceInfo;
import com.wut.self.model.vo.InterfaceInfoVO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2023-07-15 20:40:42
 */
public interface InterfaceInfoService extends IService<com.wut.common.model.entity.InterfaceInfo> {

    /**
     * 接口信息校验
     */
    void validInterfaceInfo(com.wut.common.model.entity.InterfaceInfo interfaceInfo, boolean add);

    /**
     * 根据接口id列表显示所有的接口信息
     */
    List<InterfaceInfoVO> listTopInvokeInterfaceInfo(List<UserInterfaceInfo> userInterfaceInfos);
}
