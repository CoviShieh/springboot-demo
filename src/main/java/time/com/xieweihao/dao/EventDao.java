package com.xieweihao.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.xieweihao.entity.Date;
import com.xieweihao.entity.Event;
import com.xieweihao.jpa.IBaseRepository;

public interface EventDao extends IBaseRepository<Event, Long>{

	@Query(value="SELECT e.* FROM event e JOIN date_x_event de ON de.event_id = e.id JOIN date d ON d.id = de.date_id WHERE d.datetime = ? GROUP BY e.id ",nativeQuery=true)
	List<Event> findByDatetime(String datetime);

	@Query(value="SELECT e.* FROM event e JOIN event_x_time et ON et.event_id = e.id WHERE et.id=?1 and e.event_name=?2 ",nativeQuery=true)
	Event findByXidAndEventName(Long xid,String eventName);

	Event findByEventName(String eventName);

}
