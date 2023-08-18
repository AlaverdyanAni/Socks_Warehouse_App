package pro.sky.socks_warehouse_app.exception;

public class OperationException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Операция не найдена!";
    }
}
