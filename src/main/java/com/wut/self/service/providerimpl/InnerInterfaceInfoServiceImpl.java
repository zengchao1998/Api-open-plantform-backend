package com.wut.self.service.providerimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wut.common.model.entity.InterfaceInfo;
import com.wut.common.service.InnerInterfaceInfoService;
import com.wut.self.common.ErrorCode;
import com.wut.self.exception.BusinessException;
import com.wut.self.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;


/**
 * @Author zeng1998
 * @CreateTime 2023-07-28  10:00
 * @Description TODO
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo isExistInterface(String host, String requestPath, String method) {
        if(StringUtils.isAnyBlank(requestPath)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不合法");
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("host", host);
        queryWrapper.eq("path", requestPath);
        queryWrapper.eq("method", method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
