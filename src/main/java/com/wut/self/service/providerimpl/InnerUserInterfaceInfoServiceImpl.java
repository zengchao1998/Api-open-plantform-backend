package com.wut.self.service.providerimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wut.common.model.entity.UserInterfaceInfo;
import com.wut.common.service.InnerUserInterfaceInfoService;
import com.wut.self.common.ErrorCode;
import com.wut.self.exception.BusinessException;
import com.wut.self.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @Author zeng1998
 * @CreateTime 2023-07-28  10:00
 * @Description TODO
 */
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeInterfaceCounts(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("interface_info_id", interfaceInfoId);
        updateWrapper.eq("user_id", userId);
        updateWrapper.setSql("residue_num = residue_num - 1, total_num = total_num + 1");
        return userInterfaceInfoService.update(updateWrapper);
    }

    @Override
    public void isLegitimateCall(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 如果当前用户调用接口的记录存在，则直接判断是否能够调用
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("interface_info_id", interfaceInfoId);
        queryWrapper.eq("user_id", userId);
        UserInterfaceInfo record = userInterfaceInfoService.getOne(queryWrapper);
        if (record == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "当前用户无可用调用次数");
        }
        Integer residueNum = record.getResidueNum();
        if(residueNum <= 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "当前用户无可用调用次数");
        }
    }
}
