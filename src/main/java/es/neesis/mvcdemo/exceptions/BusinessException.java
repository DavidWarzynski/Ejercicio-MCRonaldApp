package es.neesis.mvcdemo.exceptions;

public class BusinessException extends Exception {
    public BusinessException(){
    }

    public BusinessException(String mensaje){
        super(mensaje);
    }

    public  BusinessException(Throwable causa){
        super(causa);
    }

    public  BusinessException(String mensaje,Throwable causa){
        super(mensaje,causa);
    }
}
