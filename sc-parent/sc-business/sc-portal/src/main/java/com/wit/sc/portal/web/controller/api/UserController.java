package com.wit.sc.portal.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.wit.sc.common.api.feign.BookServiceFeigns;
import com.wit.sc.common.domain.dto.ResultDto;
import com.wit.sc.common.domain.vo.MethodRecordVo;
import com.wit.sc.common.domain.vo.PageArgs;
import com.wit.sc.portal.web.service.MethodRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author ctw
 * @Project： sc-parent
 * @Package: com.wit.sc.portal.web.controller
 * @Description:
 * @date 2019/6/11 23:58
 */
@RestController
public class UserController {

    public final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${custom: }")
    public String custom;

    @Autowired
    MethodRecordService methodRecordService;

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

    @Value("${spring.datasource.driver-class-name}")
    String classDriver;

    @GetMapping("driver")
    public String profile() {
        return classDriver;
    }
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

    @Autowired
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

    @Resource
    BookServiceFeigns bookServiceFeigns;

    @Autowired
    HttpSession session;

    /**
     * 建侧服务见session共享和feign服务间调用
     * @return
     */
    @GetMapping("getAllBooks")
    public String getAllBooks() {
        String sessionId = session.getId();
        session.setAttribute("sessionId", sessionId);
        String books = bookServiceFeigns.getAllBooks();
        return books;
    }
}
