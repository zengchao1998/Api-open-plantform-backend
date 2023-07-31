package com.wut.self.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wut.common.model.entity.UserInterfaceInfo;
import com.wut.self.common.ErrorCode;
import com.wut.self.exception.BusinessException;
import com.wut.self.mapper.UserInterfaceInfoMapper;
import com.wut.self.service.UserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Administrator
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2023-07-24 18:01:38
*/
@Service
@Slf4j
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Resource
    UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if(userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer residueNum = userInterfaceInfo.getResidueNum();
        // 创建时所有参数必须不能为空
        if(add) {
            if(userId == null || interfaceInfoId == null || totalNum == null || residueNum == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        // 具体的参数校验
        if(userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户id不符合规范");
        }
        if(interfaceInfoId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口id不符合规范");
        }
        if(totalNum < 0 || residueNum < 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "调用次数不符合规范");
        }
    }

    @Override
    public Integer getInterfaceResidueNum(long userId, long interfaceId) {
        if(userId <= 0 || interfaceId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数错误");
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("interface_id", interfaceId);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(queryWrapper);
        if(userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "没有申请接口调用次数");
        }
        return userInterfaceInfo.getResidueNum();
    }

    @Override
    public List<UserInterfaceInfo> listTopInvokeInterface() {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("interface_info_id", "SUM(total_num) AS total_num")
                .groupBy("interface_info_id")
                .orderByDesc("total_num")
                .last("LIMIT 3");
        return userInterfaceInfoMapper.selectList(queryWrapper);
    }
}




