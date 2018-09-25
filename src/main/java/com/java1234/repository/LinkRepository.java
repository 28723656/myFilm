package com.java1234.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java1234.entity.Link;

/**
 *  友情链接dao接口
 * @author xr
 *
 */
public interface LinkRepository extends JpaRepository<Link, Integer> {

	
}
