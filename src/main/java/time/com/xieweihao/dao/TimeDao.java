package com.xieweihao.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xieweihao.entity.Time;
import com.xieweihao.jpa.IBaseRepository;

@Repository
public interface TimeDao extends IBaseRepository<Time, Long>{

	@Query(value="SELECT t.* FROM time t JOIN event_x_time et ON et.time_id = t.id JOIN user_x_event ue ON ue.event_id = et.event_id WHERE ue.user_id = ? GROUP BY t.id",nativeQuery=true)
	List<Time> findTimesByUserId(Long userId);
}
