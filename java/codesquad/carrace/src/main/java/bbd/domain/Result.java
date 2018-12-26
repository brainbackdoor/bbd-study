package bbd.domain;

public class Result {
    private boolean status;
    private String errorMessage;
    private Cars cars;

    private Result(boolean status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
    private Result(boolean status, Cars cars) {
        this.status = status;
        this.cars = cars;
    }
    public boolean isStatus() {
        return this.status;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public Cars getCars() {
        return this.cars;
    }
    public void setCars(Cars cars) {
        this.cars = cars;
    }
    
    public static Result ok(Cars cars) {
        return new Result(true, cars);
    }
    public static Result error(String errorMessage) {
        return new Result(false, errorMessage);
    }
}
