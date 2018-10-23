package com.wgc.auth.repository;

import com.wgc.auth.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
}
