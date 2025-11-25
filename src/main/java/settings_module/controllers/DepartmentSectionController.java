package settings_module.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import settings_module.dto.response.dto.DepartmentSectionResponseDto;
import settings_module.dto.response.dto.PayloadResponse;
import settings_module.service.DepartmentSectionService;
import settings_module.util.CommonMessages;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department_section")
@RequiredArgsConstructor
public class DepartmentSectionController {

    private final DepartmentSectionService service;

    @GetMapping("/departments")
    public ResponseEntity<PayloadResponse<List<DepartmentSectionResponseDto>>> getAllDepartments() {
        try {
            List<DepartmentSectionResponseDto> departments = service.getAll()
                    .stream()
                    .collect(Collectors.groupingBy(
                            DepartmentSectionResponseDto::getDeptNo,
                            Collectors.mapping(DepartmentSectionResponseDto::getName, Collectors.toList())
                    ))
                    .entrySet()
                    .stream()
                    .map(e -> DepartmentSectionResponseDto.builder()
                            .deptNo(e.getKey())
                            .name(e.getValue().get(0))
                            .build())
                    .toList();

            if (departments.isEmpty()) {
                return respond(HttpStatus.NOT_FOUND, CommonMessages.RECORD_LIST_NOT_FOUND,
                        PayloadResponse.MessageStatus.FAILURE, departments);
            }

            return respond(HttpStatus.OK, CommonMessages.RECORDS_RETRIEVED_SUCCESSFULLY,
                    PayloadResponse.MessageStatus.SUCCESS, departments);
        } catch (Exception ex) {
            return handleException(ex);
        }
    }

    @GetMapping("/departments/{deptNo}/sections")
    public ResponseEntity<PayloadResponse<List<DepartmentSectionResponseDto>>> getSectionsByDepartment(
            @PathVariable Integer deptNo) {
        try {
            List<DepartmentSectionResponseDto> sections = service.getAll()
                    .stream()
                    .filter(ds -> ds.getDeptNo().equals(deptNo))
                    .collect(Collectors.toList());

            if (sections.isEmpty()) {
                return respond(HttpStatus.NOT_FOUND, CommonMessages.RECORD_LIST_NOT_FOUND,
                        PayloadResponse.MessageStatus.FAILURE, sections);
            }

            return respond(HttpStatus.OK, CommonMessages.RECORDS_RETRIEVED_SUCCESSFULLY,
                    PayloadResponse.MessageStatus.SUCCESS, sections);
        } catch (Exception ex) {
            return handleException(ex);
        }
    }

    @GetMapping("/departments/{deptNo}/sections/{sectionNo}")
    public ResponseEntity<PayloadResponse<DepartmentSectionResponseDto>> getSection(
            @PathVariable Integer deptNo, @PathVariable Integer sectionNo) {
        try {
            return service.getAll()
                    .stream()
                    .filter(ds -> ds.getDeptNo().equals(deptNo) && ds.getSectionNo().equals(sectionNo))
                    .findFirst()
                    .map(section -> respond(HttpStatus.OK, CommonMessages.RECORD_FOUND,
                            PayloadResponse.MessageStatus.SUCCESS, section))
                    .orElseGet(() -> respond(HttpStatus.NOT_FOUND,
                            CommonMessages.RECORD_NOT_FOUND + deptNo + "-" + sectionNo,
                            PayloadResponse.MessageStatus.FAILURE,
                            null));
        } catch (Exception ex) {
            return handleException(ex);
        }
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

    private <T> ResponseEntity<PayloadResponse<T>> handleException(Exception ex) {
        if (ex instanceof ResponseStatusException responseStatusException) {
            HttpStatus status = resolveStatus(responseStatusException.getStatusCode());
            String reason = responseStatusException.getReason() != null
                    ? responseStatusException.getReason()
                    : CommonMessages.INTERNAL_SERVER_ERROR;
            return respond(status, reason, PayloadResponse.MessageStatus.FAILURE, null);
        }
        return respond(HttpStatus.INTERNAL_SERVER_ERROR, CommonMessages.INTERNAL_SERVER_ERROR,
                PayloadResponse.MessageStatus.FAILURE, null);
    }

    private HttpStatus resolveStatus(HttpStatusCode statusCode) {
        if (statusCode instanceof HttpStatus status) {
            return status;
        }
        HttpStatus resolved = HttpStatus.resolve(statusCode.value());
        return resolved != null ? resolved : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}