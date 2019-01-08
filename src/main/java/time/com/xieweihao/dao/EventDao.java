package com.xieweihao.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.xieweihao.entity.Event;
import com.xieweihao.jpa.IBaseRepository;

public interface EventDao extends IBaseRepository<Event, Long>{

	@Query(value="SELECT e.* FROM event e JOIN user_x_event ue ON ue.event_id = e.id WHERE ue.user_id = ? GROUP BY e.id ",nativeQuery=true)
	List<Event> findEventsByUserId(Long userId);
}
