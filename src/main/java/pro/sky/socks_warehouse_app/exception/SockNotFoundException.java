package pro.sky.socks_warehouse_app.exception;

public class SockNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Носки с такими параметрами не найдены!";
    }
}

