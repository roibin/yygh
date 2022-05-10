package com.atguigu.yygh.task.scheduled;

import com.atguigu.common.rabbit.constant.MqConst;
import com.atguigu.common.rabbit.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022/5/9 19:15
 */
@Component
@EnableScheduling
public class ScheduleTask {

    @Autowired
    private RabbitService rabbitService;

    //每天的八点执行方法，就医提醒
    //cron表达式，设置执行间隔
    //0 0 8 * * ?
    @Scheduled(cron = "0/30 * * * * ?")
    public void taskPatient() {
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_TASK,MqConst.ROUTING_TASK_8,"");
    }
}
