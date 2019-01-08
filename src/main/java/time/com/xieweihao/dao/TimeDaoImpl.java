package com.xieweihao.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xieweihao.entity.Time;
import com.xieweihao.jpa.BaseDaoImpl;
import com.xieweihao.jpa.Page;

@Repository
public class TimeDaoImpl extends BaseDaoImpl<Time, Long>{

	public Page<Map<String,Object>> searchUserByUserName(String userName,Integer page,Integer pageSize){
		
		List<Object> list = new ArrayList<Object>();
		StringBuilder sqlBuild = new StringBuilder("SELECT u.* FROM user u "
				+"JOIN user_x_event ue ON ue.user_id = u.id "
				+"JOIN event e ON e.id = ue.event_id "
				+"JOIN event_x_time et ON et.event_id = e.id "
				+"JOIN time t ON t.id = et.time_id "
				+"WHERE 1=1 ");
		StringBuilder sqlCount = new StringBuilder("SELECT 1 FROM user u "
				+"JOIN user_x_event ue ON ue.user_id = u.id "
				+"JOIN event e ON e.id = ue.event_id "
				+"JOIN event_x_time et ON et.event_id = e.id "
				+"JOIN time t ON t.id = et.time_id "
				+"WHERE 1=1 ");
		if(userName == null){
			list.add(0);
		}else{
			sqlBuild.append(" and username = ? ");
			sqlCount.append(" and username = ? ");
			list.add(userName);
		}
		sqlBuild.append("GROUP BY u.id ORDER BY u.id DESC ");
		sqlCount.append("GROUP BY u.id ORDER BY u.id DESC ");
		return this.findPageMapList(new Page<Map<String, Object>>(page, pageSize), sqlBuild.toString(), sqlCount.toString(), list.toArray());
	}
	
	public List<Map<String, Object>> searchEventAndTimeByEventId(Long eventId){
		StringBuilder sql = new StringBuilder("select t.id tid,t.duration,et.id etid from time t "
				+ "JOIN event_x_time et ON et.time_id = t.id "
				+ "where 1=1 ");
		
		List<Object> list=new ArrayList<Object>();
		if(eventId != null){
			sql.append("and et.event_id = ? ");
			list.add(eventId);
		}
		sql.append(" GROUP BY t.id ");
		return this.findMapListNoPage(sql.toString(), list.toArray());
	}
	
}
