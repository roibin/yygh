package com.atguigu.yygh.order.service;

import java.util.Map;

/**
 * @author
 * @date 2022/5/8 20:27
 */
public interface WeixinService {
    //生成微信付款二维码
    Map createNative(Long orderId);

    Map<String, String> queryPayStatus(Long orderId);

    /***
     * 退款
     * @param orderId
     * @return
     */
    Boolean refund(Long orderId) throws Exception;

}
