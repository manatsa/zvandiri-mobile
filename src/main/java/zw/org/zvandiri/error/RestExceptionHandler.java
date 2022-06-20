package zw.org.zvandiri.error;


import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import zw.org.zvandiri.business.domain.User;
import zw.org.zvandiri.business.domain.util.UserType;
import zw.org.zvandiri.business.service.CatDetailService;
import zw.org.zvandiri.business.service.UserService;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    UserService userService;
    @Autowired
    CatDetailService catDetailService;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        User user=userService.getCurrentUser();
        System.err.println("\n\nUser :: "+user.getUserName()+" <=> District:"+(user.getUserLevel()!=null?user.getDistrict(): catDetailService.getByEmail(user.getUserName()).getPrimaryClinic().getDistrict().getName())+" >>>>>>> BAD REQUEST ISSUED >>>>>>> Message :: "+ex.getMessage());
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }



    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //other exception handlers below

}