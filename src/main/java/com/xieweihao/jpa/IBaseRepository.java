package com.xieweihao.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<T, PK extends Serializable> extends JpaRepository<T, PK>, JpaSpecificationExecutor<T>{

}
