package com.atguigu.hospital.service;

import com.atguigu.hospital.model.OrderInfo;
import com.atguigu.yygh.vo.order.OrderQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author
 * @date 2022/5/10 9:23
 */
public interface OrderService extends IService<OrderInfo> {
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    Map<String,Object> show(Long orderId);
}
