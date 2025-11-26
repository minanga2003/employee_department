package settings_module.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import settings_module.dto.requestDto.EmployeeRequestDto;
import settings_module.dto.response.dto.EmployeeResponseDto;
import settings_module.dto.response.dto.PayloadResponse;
import settings_module.service.EmployeeService;
import settings_module.util.CommonMessages;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<PayloadResponse<List<EmployeeResponseDto>>> getAll() {
        List<EmployeeResponseDto> employees = service.getAll();
        if (employees.isEmpty()) {
            return respond(HttpStatus.NOT_FOUND, CommonMessages.RECORD_LIST_NOT_FOUND,
                    PayloadResponse.MessageStatus.FAILURE, employees);
        }
        return respond(HttpStatus.OK, CommonMessages.RECORDS_RETRIEVED_SUCCESSFULLY,
                PayloadResponse.MessageStatus.SUCCESS, employees);
    }

    @GetMapping("/{empNo}")
    public ResponseEntity<PayloadResponse<EmployeeResponseDto>> getOne(@PathVariable Long empNo) {
        EmployeeResponseDto employee = service.getOne(empNo);
        return respond(HttpStatus.OK, CommonMessages.RECORD_FOUND,
                PayloadResponse.MessageStatus.SUCCESS, employee);
    }

    @PostMapping
    public ResponseEntity<PayloadResponse<EmployeeResponseDto>> create(@RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto employee = service.create(dto);
        return respond(HttpStatus.CREATED, CommonMessages.RECORD_SAVED_SUCCESS,
                PayloadResponse.MessageStatus.SUCCESS, employee);
    }

    @PutMapping("/{empNo}")
    public ResponseEntity<PayloadResponse<EmployeeResponseDto>> update(@PathVariable Long empNo,
                                                                       @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto employee = service.update(empNo, dto);
        return respond(HttpStatus.OK, CommonMessages.RECORD_UPDATED_SUCCESS,
                PayloadResponse.MessageStatus.SUCCESS, employee);
    }

    @DeleteMapping("/{empNo}")
    public ResponseEntity<PayloadResponse<Boolean>> delete(@PathVariable Long empNo) {
        boolean deleted = service.delete(empNo);
        return respond(HttpStatus.OK, CommonMessages.RECORD_DELETED_SUCCESS,
                PayloadResponse.MessageStatus.SUCCESS, deleted);
    }

    private <T> ResponseEntity<PayloadResponse<T>> respond(HttpStatus status,
                                                           String message,
                                                           PayloadResponse.MessageStatus messageStatus,
                                                           T data) {
        return ResponseEntity.status(status)
                .body(PayloadResponse.<T>builder()
                        .status_code(status.value())
                        .message(message)
                        .message_status(messageStatus)
                        .data(data)
                        .build());
    }
}
