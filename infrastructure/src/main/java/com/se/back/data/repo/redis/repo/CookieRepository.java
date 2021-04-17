package com.se.back.data.repo.redis.repo;

import com.se.back.data.repo.redis.mapper.CookieMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mgong
 */
@Repository
public interface CookieRepository extends CrudRepository<CookieMapper, String> {
}
