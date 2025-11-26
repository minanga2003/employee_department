package settings_module.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import settings_module.dto.requestDto.EmployeeRequestDto;
import settings_module.dto.responseDto.EmployeeResponseDto;
import settings_module.dto.responseDto.PayloadResponse;
import settings_module.service.EmployeeService;
import settings_module.util.CommonMessages;
import settings_module.util.ResponseBuilder;
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
            return ResponseBuilder.build(HttpStatus.NOT_FOUND, CommonMessages.RECORD_LIST_NOT_FOUND,
                    PayloadResponse.MessageStatus.FAILURE, employees);
        }
        return ResponseBuilder.build(HttpStatus.OK, CommonMessages.RECORDS_RETRIEVED_SUCCESSFULLY,
                PayloadResponse.MessageStatus.SUCCESS, employees);
    }

    @GetMapping("/{empNo}")
    public ResponseEntity<PayloadResponse<EmployeeResponseDto>> getOne(@PathVariable Long empNo) {
        EmployeeResponseDto employee = service.getOne(empNo);
        return ResponseBuilder.build(HttpStatus.OK, CommonMessages.RECORD_FOUND,
                PayloadResponse.MessageStatus.SUCCESS, employee);
    }

    @PostMapping
    public ResponseEntity<PayloadResponse<EmployeeResponseDto>> create(@RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto employee = service.create(dto);
        return ResponseBuilder.build(HttpStatus.CREATED, CommonMessages.RECORD_SAVED_SUCCESS,
                PayloadResponse.MessageStatus.SUCCESS, employee);
    }

    @PutMapping("/{empNo}")
    public ResponseEntity<PayloadResponse<EmployeeResponseDto>> update(@PathVariable Long empNo,
                                                                       @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto employee = service.update(empNo, dto);
        return ResponseBuilder.build(HttpStatus.OK, CommonMessages.RECORD_UPDATED_SUCCESS,
                PayloadResponse.MessageStatus.SUCCESS, employee);
    }

    @DeleteMapping("/{empNo}")
    public ResponseEntity<PayloadResponse<Boolean>> delete(@PathVariable Long empNo) {
        boolean deleted = service.delete(empNo);
        return ResponseBuilder.build(HttpStatus.OK, CommonMessages.RECORD_DELETED_SUCCESS,
                PayloadResponse.MessageStatus.SUCCESS, deleted);
    }
}
