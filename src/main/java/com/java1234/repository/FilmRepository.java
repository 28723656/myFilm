package com.java1234.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java1234.entity.Film;

/**
 *  电影dao接口
 * @author xr
 *
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {

	
}
