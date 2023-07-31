package com.wut.self.controller;

import com.wut.common.model.entity.UserInterfaceInfo;
import com.wut.self.annotation.AuthCheck;
import com.wut.self.common.BaseResponse;
import com.wut.self.common.ResultUtils;
import com.wut.self.constant.UserConstant;
import com.wut.self.model.vo.InterfaceInfoVO;
import com.wut.self.service.InterfaceInfoService;
import com.wut.self.service.UserInterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zeng1998
 * @CreateTime 2023-07-28  15:19
 * @Description 统计分析功能
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    InterfaceInfoService interfaceInfoService;

    @Resource
    UserInterfaceInfoService userInterfaceInfoService;

    @GetMapping("/top/invoke/interface")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<UserInterfaceInfo> userInterfaceInfos = userInterfaceInfoService.listTopInvokeInterface();
        List<InterfaceInfoVO> topList = interfaceInfoService.listTopInvokeInterfaceInfo(userInterfaceInfos);
        return ResultUtils.success(topList);
    }

}
