package com.gabriel.schedulingapi.business.mapper;

import com.gabriel.schedulingapi.controller.dto.in.SchedulingRecordInput;
import com.gabriel.schedulingapi.controller.dto.out.SchedulingRecordOutput;
import com.gabriel.schedulingapi.infra.entities.Scheduling;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ISchedulingMapper {
    Scheduling forEntity(SchedulingRecordInput schedulingRecordInput);
    SchedulingRecordOutput forDto(Scheduling scheduling);

    @Mapping(target = "sendDateTime", expression = "java(LocalDateTime.now())")
    @Mapping(target = "notificationStatus", expression = "java(NotificationStatus.CANCELLED)")
    Scheduling forCancelledEntity(Scheduling scheduling);
}
