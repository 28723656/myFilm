package com.java1234.service;

import java.util.List;

import com.java1234.entity.Link;

/**
 * 友情链接service接口
 * @author xr
 *
 */
public interface LinkService {

	/**
	 * 分页查询友情链接
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Link> list(Integer page,Integer pageSize);
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public Long getCount();
	
	/**
	 * 添加或修改友情链接
	 * @param link
	 */
	public void save(Link link);
	
	/**
	 * 根据id删除友情链接
	 * @param id
	 */
	public void delete(Integer id);
} 
