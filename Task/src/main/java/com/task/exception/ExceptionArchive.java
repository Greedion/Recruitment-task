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

}
