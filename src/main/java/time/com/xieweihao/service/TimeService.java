package com.xieweihao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xieweihao.dao.EventDao;
import com.xieweihao.dao.TimeDao;
import com.xieweihao.dao.TimeDaoImpl;
import com.xieweihao.dao.UserDao;
import com.xieweihao.entity.Event;
import com.xieweihao.entity.Time;
import com.xieweihao.jpa.Page;

@Service
@Transactional
public class TimeService {
	@Autowired TimeDaoImpl timeDaoImpl;
	@Autowired TimeDao timeDao;
	@Autowired EventDao eventDao;
	@Autowired UserDao userDao;
	
	/**
	 * 查找用户
	 * @param userName
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<Map<String,Object>> searchUserByUserName(String userName,Integer page,Integer pageSize){
		if(userName==null)
			return null;
		
		return timeDaoImpl.searchUserByUserName(userName, page, pageSize);
	}
	

	public List<Event> findEventsByUserId(Long userId){
		return eventDao.findEventsByUserId(userId);
	}
	
	public List<Time> findTimesByUserId(Long userId){
		return timeDao.findTimesByUserId(userId);
	}
	
	public List<Map<String,Object>> searchEventAndTimeByEventId(Long eventId){
		return timeDaoImpl.searchEventAndTimeByEventId(eventId);
	}
}
