package com.gabriel.schedulingapi.business;

import com.gabriel.schedulingapi.business.mapper.ISchedulingMapper;
import com.gabriel.schedulingapi.controller.dto.in.SchedulingRecordInput;
import com.gabriel.schedulingapi.controller.dto.out.SchedulingRecordOutput;
import com.gabriel.schedulingapi.infra.exceptions.NotFoundException;
import com.gabriel.schedulingapi.infra.repositories.SchedulingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulingService {
    private final SchedulingRepository schedulingRepository;
    private final ISchedulingMapper schedulingMapper;

    public SchedulingRecordOutput create(SchedulingRecordInput schedulingRecordInput) {
        return schedulingMapper.forDto(
                schedulingRepository.save(
                        schedulingMapper.forEntity(
                                schedulingRecordInput)));
    }

    public SchedulingRecordOutput findById(Long id) {
        return schedulingMapper.forDto(schedulingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id not found")));
    }
}
