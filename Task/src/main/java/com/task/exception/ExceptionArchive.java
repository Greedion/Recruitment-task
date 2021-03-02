package com.task.exception;

public  class ExceptionArchive {

    /**
     * GlobalExceptionHandler
     */
    public final static String GEH_VALIDATION_FAILED = "Validation Failed.";

    /**
     * CheckGenderService
     */
    public final static String CGS_INVALID_MATCH_EXCEPTION = "Invalid 'match' exception.";
    public final static String CGS_INVALID_VARIANT_EXCEPTION = "Invalid 'variant' exception.";
    public final static String CGS_ILLEGAL_STATE_EXCEPTION = "Unexpected value: ";
    public final static String CGS_NUMBER_FORMAT_EXCEPTION = "Variant contains illegal characters: {}";

    /**
     * GetFileContentService
     */
    public final static String GFCS_STREAM_EXCEPTION = "Stream close exception: ";
    public final static String GFCS_STREAM_EXCEPTION_LOGGER = "Stream close exception: {}";

    /**
     * DataAccess
     */
    public final static String DA_WRONG_FILE_PATH_EXCEPTION = "Wrong file path exception.";
    public final static String DA_WRONG_FILE_PATH_EXCEPTION_LOGGER = "Wrong file path exception: {}";
    public final static String DA_WRONG_FILE_PATH_EXCEPTION_WITH_ERROR_MESSAGE = "Wrong file path exception: {}";

    /**
     * DataAccessFactory
     */
    public final static String DAF_WRONG_FILE_PATH_EXCEPTION_LOGGER = "Wrong file path exception: {}";
    public final static String DAF_WRONG_FILE_PATH_EXCEPTION = "Wrong file patch exception: ";
}
