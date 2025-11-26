package settings_module.util;

public final class CommonMessages {

    private CommonMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String REQUIRED_FIELD = " is required";
    public static final String RECORD_SAVED_SUCCESS = "Record created successfully";
    public static final String RECORD_DELETED_SUCCESS = "Record deleted successfully";
    public static final String RECORD_UPDATED_SUCCESS = "Record updated successfully";
    public static final String ALREADY_EXIST = " already exists. You cannot add duplicate ";
    public static final String ALREADY_EXIST_ONE = " already exists under this.";
    public static final String ALREADY_DELETED = "This record is already deleted.";
    public static final String REFERENCE_ELSEWHERE = "This record is referenced elsewhere. Are you sure you want to delete it";
    public static final String LIMIT_EXCEED = "You can't exceed the character length";

    public static final String RECORD_NOT_FOUND = "Record not found with the given identifier: ";
    public static final String INTERNAL_SERVER_ERROR = "An unexpected error occurred. Please try again later";
    public static final String RECORDS_RETRIEVED_SUCCESSFULLY = "Records retrieved successfully";
    public static final String RECORD_FOUND = "Record found successfully";
    public static final String RECORD_LIST_NOT_FOUND = "Record list not found";
    public static final String DEFAULT_FOUND = "Assign a new default record before modifying the existing default record";
    public static final String RECORD_USAGE_FOUND = "Usage record retrieved successfully";
    public static final String RECORD_CANNOT_DELETE = "Record cannot be deleted";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String EMPLOYEE_NUMBER_REQUIRED = "Employee number is required";
    public static final String EMPLOYEE_NUMBER_ALREADY_EXISTS = "Employee number already exists";
    public static final String EMPLOYEE_NUMBER_MISMATCH = "Employee number does not match request path";
    public static final String EMPLOYEE_MUST_BE_ACTIVE_ON_CREATE = "New employees must be active on creation";

    public static final String SELECT_CHECKBOX = "Users should mark at least one check box";
    public static final String RECORD_SAVED_FAILED = "Failed to save record";
    public static final String RECORD_DELETED_FAILED = "Failed to delete record";
    public static final String RECORD_UPDATED_FAILED = "Failed to update record";
    public static final String EMAIL_SENT_SUCCESS = "Email sent successfully";
    public static final String INVALID_DEPARTMENT_SECTION = "Invalid department or section selection";

    public static final String RECORD_REMOVED_SUCCESS = "Record removed successfully";
    public static final String CONCURRENT_ERROR = "The record is currently being edited by another user. Please try again later.";
    public static final String DEFAULT_EMAIL_EMPTY = "Default email not defined.";
    public static final String DEFAULT_EMAIL_CONFIGURATION_NOT_FOUND = "No email address is associated with this user or the email is not configured.";
}