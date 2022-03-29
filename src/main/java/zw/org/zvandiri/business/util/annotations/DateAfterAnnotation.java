//package zw.org.zvandiri.business.util.annotations;
//
//
//
//import zw.org.zvandiri.business.util.annotations.validations.DateAfterValidation;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.*;
//
///**
// * @author manatsachinyeruse@gmail.com
// */
//
//
//@Target({ElementType.FIELD, ElementType.PARAMETER})
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Constraint(validatedBy = DateAfterValidation.class)
//public @interface DateAfterAnnotation {
//
//    String message() default "Date has to be in the future!";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
