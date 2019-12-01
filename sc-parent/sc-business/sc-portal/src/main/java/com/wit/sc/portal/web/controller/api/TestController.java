package com.wit.sc.portal.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.wit.sc.common.domain.dto.ResultDto;
import com.wit.sc.common.domain.vo.MethodRecordVo;
import com.wit.sc.common.domain.vo.PageArgs;
import com.wit.sc.portal.web.service.MethodRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author ctw
 * @Project： springcloud
 * @Package: com.wit.sc.portal.web.controller.api
 * @Description:
 * @date 2019/12/1 14:10
 */
@RestController
public class TestController {

    @Value("${custom: }")
    public String custom;

    /**
     * 测试简单接口
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public ResultDto getProduct(@PathVariable String id) {
        String result = "产品id : " + id;
        return ResultDto.successT(result);
    }

    /**
     * 测试oauth
     * eg:http://localhost:8000/order/1/maple?access_token=d808c748-71e8-4fc7-9287-8ba892c902a6
     * 1、http://localhost:9030/oauth/token?grant_type=password&username=user_1&password=123456&scope=server&client_id=client_2&client_secret=123456
     * 2、http://localhost:8000/order/1?access_token=a6a50c2c-97cc-4deb-be56-14e0c6a6300c
     * @param id
     * @return
     */
    @GetMapping("/order/{id}/{name}")
    public String getOrder(@PathVariable String id, @PathVariable String name) {
        return "order id : " + id;
    }

    /**
     * 测试配置中心修改属性，动态刷新
     */
    @Value("${spring.datasource.driver-class-name}")
    String classDriver;

    @GetMapping("driver")
    public String profile() {
        return classDriver;
    }

    @Autowired
    MethodRecordService methodRecordService;


    /**
     * 测试方法切面
     * @param methodRecordVo
     * @param pageArgs
     * @return
     */
    @GetMapping("/method/page")
    public ResultDto methodRecordPage(MethodRecordVo methodRecordVo, PageArgs pageArgs) {
        PageInfo<MethodRecordVo> pageInfo = methodRecordService.page(methodRecordVo, pageArgs);
        return ResultDto.successT(pageInfo);
    }

    //@Autowired
    //private DistributedLockHandler distributedLockHandler;

    /**
     * 测试redis分布式锁
     * @return
     */
    /*@GetMapping("redisDistributeLock")
    public String redisDistributeLock(){
        Lock lock=new Lock("ctw","min");
        if(distributedLockHandler.tryLock(lock)){
            try {
                //为了演示锁的效果，这里睡眠5000毫秒
                System.out.println("执行方法");
                Thread.sleep(5000);
            }catch (Exception e){
                e.printStackTrace();
            }
            distributedLockHandler.releaseLock(lock);
        }
        return "hello world!";
    }*/
}
