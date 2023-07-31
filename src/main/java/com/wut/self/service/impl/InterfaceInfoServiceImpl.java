package com.wut.self.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wut.common.model.entity.UserInterfaceInfo;
import com.wut.self.common.ErrorCode;
import com.wut.self.exception.BusinessException;
import com.wut.self.mapper.InterfaceInfoMapper;
import com.wut.self.model.vo.InterfaceInfoVO;
import com.wut.self.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wut.self.constant.CommonConstant.GET_METHOD;
import static com.wut.self.constant.CommonConstant.POST_METHOD;

/**
* @author Administrator
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-07-15 20:40:42
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, com.wut.common.model.entity.InterfaceInfo>
    implements InterfaceInfoService {

    @Resource
    InterfaceInfoMapper interfaceInfoMapper;

    /**
     * 验证输入的接口信息
     * @param interfaceInfo 接口信息对象
     * @param add 所有参数是否不能为空
     */
    @Override
    public void validInterfaceInfo(com.wut.common.model.entity.InterfaceInfo interfaceInfo, boolean add) {
        if(interfaceInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String method = interfaceInfo.getMethod();
        String host = interfaceInfo.getHost();
        String path = interfaceInfo.getPath();
        String url = host + path;
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        // 创建时所有参数必须不能为空
        if(add) {
            if(StringUtils.isAnyBlank(name, description, method, url, requestHeader, responseHeader)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        // 具体的参数要求
        if(StringUtils.isNotBlank(name) && name.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名称过长");
        }
        if(StringUtils.isNotBlank(description) && description.length() > 2000) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }
        if(StringUtils.isNotBlank(method) && !(method.equals(GET_METHOD) || method.equals(POST_METHOD))) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求方法不符合规范");
        }
        if(StringUtils.isNotBlank(url) && !url.matches("^(https?|http)://" +
                "(localhost:\\d{4,}|[^\\s/.]+\\.[^\\s/.]+)/([^\\s/]+/)+.*$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "URL格式错误");
        }
    }


    @Override
    public List<InterfaceInfoVO> listTopInvokeInterfaceInfo(List<UserInterfaceInfo> userInterfaceInfos) {
        if(userInterfaceInfos == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        Map<Long, List<UserInterfaceInfo>> idAndTotalNumMap = userInterfaceInfos.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<com.wut.common.model.entity.InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", idAndTotalNumMap.keySet());
        List<com.wut.common.model.entity.InterfaceInfo> interfaceInfos = interfaceInfoMapper.selectList(queryWrapper);
        if(interfaceInfos == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "系统内部错误");
        }
        // 添加统计次数信息
        return interfaceInfos.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            interfaceInfoVO.setTotalNum(idAndTotalNumMap.get(interfaceInfo.getId()).get(0).getTotalNum());
            return interfaceInfoVO;
        }).collect(Collectors.toList());
    }
}




