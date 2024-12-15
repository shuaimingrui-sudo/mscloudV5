package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value="/pay/add")
    @Operation(summary = "插入",description = "插入支付流水")
     public String addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return "成功插入记录，返回值："+i;
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水")
    public Integer deletePay(@PathVariable("id")Integer id){
        return payService.delte(id);
    }

    @PutMapping(value="/pay/update")
    @Operation(summary = "更新",description = "更新支付流水")
    public String updatePay(@RequestBody PayDTO payDTO){
           Pay pay = new Pay();
           BeanUtils.copyProperties(payDTO,pay);
           int i = payService.update(pay);
           return "成功修改记录，返回值"+i;
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "查询",description = "查询支付流水")
    public Pay getById(@PathVariable("id")Integer id){
        return payService.getById(id);
    }

/*    @GetMapping(value = "/pay/get/{id}")
    public List<Pay> getAll(){
        return payService.getAll();
    }*/
}
