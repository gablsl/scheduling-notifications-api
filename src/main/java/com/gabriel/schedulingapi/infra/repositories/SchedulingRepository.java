package com.gabriel.schedulingapi.infra.repositories;

import com.gabriel.schedulingapi.infra.entities.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {}
