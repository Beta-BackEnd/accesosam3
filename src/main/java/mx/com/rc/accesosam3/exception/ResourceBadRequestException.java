package mx.com.rc.accesosam3.exception;

public class ResourceBadRequestException extends RuntimeException {

    private static final Long serialVersionUid = 1L;

    public ResourceBadRequestException(String msg){
        super(msg);
    }
}
