package com.xieweihao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xieweihao.jpa.IdEntity;
/**
 * @author xieweihao
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "event")
public class Event extends IdEntity implements java.io.Serializable{

	private String eventName;

	@Column(name = "event_name")
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
}
