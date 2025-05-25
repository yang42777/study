package com.example.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ecommerce.dto.MainOrderDto;
import com.example.ecommerce.entity.MainOrder;
import com.example.ecommerce.entity.Result;
import com.example.ecommerce.exception.BizCodeException;
import com.example.ecommerce.service.IMainOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zfy
 * @since 2025-05-20
 */
@RestController
@RequestMapping("/order")
public class MainOrderController {

    @Autowired
    private IMainOrderService orderService;


    @PostMapping("/submit")
    public Result<Map<String, String>> createOrder(@RequestBody MainOrderDto mainOrderDto, HttpServletRequest request) throws BizCodeException {
        String qrCodeUrl = orderService.submitOrder(request.getAttribute("user_id") != null ? (Long) request.getAttribute("user_id") : 0, mainOrderDto.getProductId(), mainOrderDto.getQuantity(), mainOrderDto.getPaymentMethod());
        Map<String, String> result = new HashMap<>();
        result.put("url", qrCodeUrl);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public MainOrder getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<MainOrder> getUserOrders(@PathVariable Long userId) {
        return orderService.list(new QueryWrapper<MainOrder>().eq("user_id", userId));
    }

    @PutMapping("/{id}/status")
    public boolean updateStatus(@PathVariable Long id, @RequestParam Byte status) {
        return orderService.updateOrderStatus(id, status);
    }

    @PutMapping("/{id}/cancel")
    public boolean cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

}
