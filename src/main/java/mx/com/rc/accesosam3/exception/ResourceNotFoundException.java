package mx.com.rc.accesosam3.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final Long serialVersionUid = 1L;

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
