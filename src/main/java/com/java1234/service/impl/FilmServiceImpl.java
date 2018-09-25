package com.java1234.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.entity.Film;
import com.java1234.repository.FilmRepository;
import com.java1234.service.FilmService;

/**
 * 电影service接口实现类
 * @author xr
 *
 */

@Service("filmService")
public class FilmServiceImpl implements FilmService {
	
	@Resource
	private FilmRepository filmRepository;

	@Override
	public void save(Film film) {
		filmRepository.save(film);
	}

	
	
}
