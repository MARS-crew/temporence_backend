package mars.temporence.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * It is to proceed the {@code XSS} security
 */
public final class RequestWrapper extends HttpServletRequestWrapper {

    /**
     * @param servletRequest a {@link HttpServletRequest}
     */
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    /**
     * @param parameter the parameter name should be looked value
     * @return array of string values
     */
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return new String[0];
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return cleanXSS(value);
    }

    /**
     * It is to remove the {@code XSS} injection
     * 
     * @param inputVal
     * @return corrected value
     */
    private String cleanXSS(String inputVal) {
        String value = inputVal;
//        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
//        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\""); // NOSONAR
        value = value.replaceAll("script", "");
        return value;
    }
}
