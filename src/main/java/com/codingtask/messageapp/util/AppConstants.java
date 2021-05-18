package com.codingtask.messageapp.util;

public final class AppConstants {

    public static final String KAFKA_MESSAGE_TOPIC = "kafka_message_topic";
    public static final String KAFKA_GROUP_ID = "kafka_group_id";

    public static final String INPUT_CANNOT_BE_NULL_OR_EMPTY = "Invalid input! Value cannot be null or empty!";
    public static final String SAME_NAME_ALREADY_EXISTS = "An item with the same name already exists!";
    public static final String OPERATION_NOT_ALLOWED = "Operation not allowed!";
    public static final String NO_SUCH_VALUE_FOUND_IN_DATABASE = "No such value found in database!";
    public static final String INCORRECT_HTTP_METHOD_TYPE = "Incorrect method type! Please choose the correct Http method type.";
    public static final String INVALID_REQUEST_BODY = "Request body is empty or not specified!";
    public static final String INTERNAL_EXCEPTION = "Something went wrong! Please check whether the request headers or other parameters are specified correctly.";
    public static final String INVALID_OR_MISSING_PATH_VARIABLE = "The path variable is invalid or missing!";

    public static final String ERROR_CODE_601 = "601";
    public static final String ERROR_CODE_602 = "602";
    public static final String ERROR_CODE_603 = "603";
    public static final String ERROR_CODE_604 = "604";
    public static final String ERROR_CODE_605 = "605";

    private AppConstants() {
    }
}
