package com.java1234.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java1234.entity.Film;
import com.java1234.service.FilmService;
import com.java1234.util.DateUtil;

@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {
	
	@Resource
	private FilmService filmService;
	
	@Value("${imageFilePath}")
	private String imageFilePath;
	
	
	@RequestMapping("/save")
	public Map<String,Object> save(Film film,@RequestParam("imageFile") MultipartFile file) throws Exception{
		if(!file.isEmpty()){
			//获取文件名
			String fileName = file.getOriginalFilename();
			// 获取文件的后缀
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			// 组成一个新的文件名,保证不重复  日期＋后缀名
			String newFileName = DateUtil.getCurrentDateStr()+suffixName;
			// 把文件拷贝到  给定路径,加图片名称
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));
			
			film.setImageName(newFileName);
		}
		film.setPublishDate(new Date());
		Map<String,Object> resultMap = new HashMap<>();
		filmService.save(film);
		resultMap.put("success", true);
		
		
		return resultMap;
	}

	@RequestMapping("/ckeditorUpload")
	public String ckeditorUpload( @RequestParam("upload") MultipartFile file,String CKEditorFuncNum ) throws Exception {
		
		//获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		
		// 组成一个新的文件名,保证不重复  日期＋后缀名
		String newFileName = DateUtil.getCurrentDateStr()+suffixName;
		// 把文件拷贝到  给定路径,加图片名称
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));
		
		//  回选图片,就是上传了一张图片后,可以看到上传的图片
		StringBuffer sb=new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/static/filmImage/"+ newFileName + "','')");
		sb.append("</script>");
		
		return sb.toString();
	}
}
