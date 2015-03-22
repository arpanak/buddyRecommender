package com.recommender.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.recommender.db.Configuration;
import com.recommender.db.Configuration.CONFIGURATION_TYPE;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer>
{
	Configuration findOneByConfigurationType(CONFIGURATION_TYPE configurationType);
}
