package com.recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.recommender.db.Joinee;

public interface JoineeRepository extends JpaRepository<Joinee, Integer>
{

}
