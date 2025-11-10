package com.project.AIPoweredBRD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.AIPoweredBRD.entity.Scenario;


@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long>{

	public Scenario findByBRDName(String name);
	
	
	@Query("SELECT s FROM Scenario s WHERE LOWER(s.extractedText) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	public List<Scenario> findByExactSearch(@Param("keyword")String keyword);

	
}
