package com.example.farmingproject.repository;

import com.example.farmingproject.domain.Work;
import org.springframework.data.repository.CrudRepository;

public interface WorkRepository extends CrudRepository<Work, Integer> {

    Long countById(int id);
}
