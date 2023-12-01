package mars.temporence.global.exception;

public class ForbiddenException extends Exception {

    public ForbiddenException() {
        super("접근 권한이 없습니다.");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
