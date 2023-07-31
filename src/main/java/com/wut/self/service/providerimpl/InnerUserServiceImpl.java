package com.wut.self.service.providerimpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wut.common.model.entity.User;
import com.wut.common.service.InnerUserService;
import com.wut.self.common.ErrorCode;
import com.wut.self.exception.BusinessException;
import com.wut.self.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @Author zeng1998
 * @CreateTime 2023-07-28  09:59
 * @Description TODO
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Api签名为空");
        }
        // 根据ak查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("access_key", accessKey);
        return userMapper.selectOne(queryWrapper);
    }
}
