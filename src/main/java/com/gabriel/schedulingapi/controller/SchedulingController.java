package com.gabriel.schedulingapi.controller;

import com.gabriel.schedulingapi.business.SchedulingService;
import com.gabriel.schedulingapi.controller.dto.in.SchedulingRecordInput;
import com.gabriel.schedulingapi.controller.dto.out.SchedulingRecordOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    @PostMapping
    public ResponseEntity<SchedulingRecordOutput> create(@RequestBody SchedulingRecordInput schedulingRecordInput) {
        return ResponseEntity.ok(schedulingService.create(schedulingRecordInput));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingRecordOutput> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(schedulingService.findById(id));
    }
}
