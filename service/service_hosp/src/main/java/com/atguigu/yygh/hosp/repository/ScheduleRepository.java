package com.atguigu.yygh.hosp.repository;

import com.atguigu.yygh.model.hosp.Schedule;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author
 * @date 2022/5/2 16:40
 */
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, DateTime dateTime);
}
