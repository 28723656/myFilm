package com.java1234.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java1234.entity.Link;
import com.java1234.service.LinkService;
/**
 * 友情链接controller类
 * @author xr
 *
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {
	
	@Resource
	private LinkService linkService;
	
	/**
	 * 友情链接分页列表
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public Map<String ,Object> list(@RequestParam(value="page",required=false) Integer page,
		@RequestParam(value="rows",required=false) Integer rows	) throws Exception{
		List<Link> linkList = linkService.list(page-1, rows);
		Long total = linkService.getCount();
		Map<String ,Object> resultMap = new HashMap<>();
		resultMap.put("rows", linkList);
		resultMap.put("total", total);
		return resultMap;
	}
	
	/**
	 * 添加或者修改友情链接
	 * @param link
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public Map<String ,Object> save(Link link) throws Exception{
		 Map<String ,Object> resultMap = new HashMap<>();
		 linkService.save(link);
		 resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 删除友情链接
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestParam(value="ids") String ids) throws Exception{
		String[] idsStr = ids.split(",");
		 Map<String ,Object> resultMap = new HashMap<>();
		 for (String id : idsStr) {
			 linkService.delete(Integer.parseInt(id));
		}
		 resultMap.put("success", true);
		return resultMap;
	}

}
