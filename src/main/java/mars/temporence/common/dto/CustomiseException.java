package mars.temporence.common.dto;


import org.apache.commons.lang3.StringUtils;

/**
 * Customize the {@link Exception}.
 */
public class CustomiseException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    private final int statusCode;
    private final String localizationCode;
    private final String internalMessage;

    /**
     * Instantiates a new customise exception.
     *
     * @param message
     *            the message
     */
    public CustomiseException(String message) {
        super(message);
        this.statusCode = 0;
        this.localizationCode = StringUtils.EMPTY;
        this.internalMessage = StringUtils.EMPTY;
    }

    /**
     * @param statusCode
     *            the http status code
     * @param message
     *            the error message
     */
    public CustomiseException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.localizationCode = StringUtils.EMPTY;
        this.internalMessage = StringUtils.EMPTY;
    }

    /**
     * @param statusCode
     *            the http status code
     * @param localizationCode
     *            the {@code localizationCode} is to map or localize
     * @param message
     *            the error message
     */
    public CustomiseException(int statusCode, String localizationCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.localizationCode = localizationCode;
        this.internalMessage = StringUtils.EMPTY;
    }

    /**
     * 
     * @param statusCode
     *            the http status code
     * @param localizationCode
     *            the {@code localizationCode} is to map or localize
     * @param message
     *            the error message
     * @param internalMessage
     *            the system error message is to trace error
     */
    public CustomiseException(int statusCode, String localizationCode, String message, String internalMessage) {
        super(message);
        this.statusCode = statusCode;
        this.localizationCode = localizationCode;
        this.internalMessage = internalMessage;
    }

    /**
     * Instantiates a new customise exception.
     *
     * @param cause
     *            the cause
     */
    public CustomiseException(Throwable cause) {
        super(cause);
        this.statusCode = 0;
        this.localizationCode = StringUtils.EMPTY;
        this.internalMessage = StringUtils.EMPTY;
    }

    /**
     * Instantiates a new customise exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public CustomiseException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 0;
        this.localizationCode = StringUtils.EMPTY;
        this.internalMessage = StringUtils.EMPTY;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return the localizationCode
     */
    public String getLocalizationCode() {
        return localizationCode;
    }

    /**
     * @return the internalMessage
     */
    public String getInternalMessage() {
        return internalMessage;
    }

}
