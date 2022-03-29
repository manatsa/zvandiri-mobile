//package zw.org.zvandiri.business.util.annotations.validations;
//
//
//
//import zw.org.zvandiri.business.util.annotations.DateAfterAnnotation;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.util.Date;
//
///**
// * @author manatsachinyeruse@gmail.com
// */
//
//
//public class DateBeforeValidation implements ConstraintValidator<DateAfterAnnotation, Date> {
//    @Override
//    public void initialize(DateAfterAnnotation dateAfterAnnotation) {
//
//    }
//
//    @Override
//    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
//        return date.before(new Date());
//    }
//}
