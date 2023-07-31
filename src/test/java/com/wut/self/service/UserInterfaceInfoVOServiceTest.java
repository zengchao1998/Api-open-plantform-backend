package com.wut.self.service;

import com.wut.common.service.InnerUserInterfaceInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author zeng1998
 * @CreateTime 2023-07-24  21:41
 * @Description 用户接口调用服务测试
 */
@SpringBootTest
public class UserInterfaceInfoVOServiceTest {

    @Resource
    InnerUserInterfaceInfoService interfaceInfoService;

    @Test
    void testInvokeInterfaceCounts() {
        boolean result = interfaceInfoService.invokeInterfaceCounts(1L, 1L);
        System.out.println(result);
    }

}
