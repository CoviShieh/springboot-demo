package com.xieweihao.dao;

import org.springframework.stereotype.Repository;

import com.xieweihao.entity.User;
import com.xieweihao.jpa.IBaseRepository;

@Repository
public interface UserDao extends IBaseRepository<User, Long>{

}
