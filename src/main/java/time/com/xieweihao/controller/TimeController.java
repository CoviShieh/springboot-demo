package com.xieweihao.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xieweihao.entity.Event;
import com.xieweihao.entity.Time;
import com.xieweihao.jpa.Page;
import com.xieweihao.service.TimeService;
import com.xieweihao.utils.JsonUtil;

@RestController
public class TimeController {
	@Autowired TimeService timeService;
	
	@GetMapping("searchUserEvent")
	public String searchUserEvent(String userName,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "20")Integer limit){
		
		Integer page=offset/limit+1;
		Integer pageSize=limit;
		try{
			Page<Map<String,Object>> list= timeService.searchUserByUserName(userName, page, pageSize);
			JSONArray jarray = new JSONArray();
			if(list != null){
				for(Map<String,Object> map :list.getList()){
					JSONObject user = new JSONObject();
					user.put("id", map.get("id"));
					user.put("username", map.get("username"));
					
					JSONArray eventsArray = new JSONArray();
					
					List<Event> listEvents = timeService.findEventsByUserId(user.getLong("id"));
					if(listEvents == null){
						break;
					}
					for(Event e : listEvents){
						//拿到 事件时间关联表 的id 和 对应的时间
						List<Map<String,Object>> eventAndTime = timeService.searchEventAndTimeByEventId(e.getId());	//一个事件对应一个时间段
						JSONObject eventjson = new JSONObject();
						Map<String, Object> time = eventAndTime.get(0);
						eventjson.put("id", time.get("etid"));
						eventjson.put("event", e.getEventName());
						eventjson.put("duration", time.get("duration"));
						eventsArray.add(eventjson);
					}
					user.put("events", eventsArray);
					jarray.add(user);
				}
			}
			//组装数据
			String data = JsonUtil.getInstance().putData("rows", jarray)
					 .putData("total", list.getTotal_count())
					 .putData("pageSize", pageSize)
					 .putData("currentPage", list.getCurrent_page())
					 .pushData();
			 
			 return JsonUtil.getInstance().putData("ret", 1).putData("msg", "查询成功").putData("data", jarray).pushData();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * sInput {
						"id": 1,
						"username": "1530696130@qq.com",
						"events": [{
						    "id": 1, // 事件时间关联表的id
							"event": "读书",
							"time": "3"
						}, {
							"id": 2,
							"event": "睡觉",
							"time": "6"
						}]
					}
	 * @param sInput
	 * @return
	 */
	@RequestMapping(value = {"/setTime","/setTime.action"})
	@ResponseBody
	public String SaveOrUpdateTime(@RequestBody String sInput){
		
		
		return null;
	}
}
