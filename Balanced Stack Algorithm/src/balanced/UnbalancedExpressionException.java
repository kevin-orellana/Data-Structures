package balanced;
/**
 * Created by kfo21 on 6/16/2017.
 */
public class UnbalancedExpressionException extends Exception {
    public UnbalancedExpressionException(String message){
        super();
        System.out.println(message);
    }
    public UnbalancedExpressionException(){
        super();
    }
}
