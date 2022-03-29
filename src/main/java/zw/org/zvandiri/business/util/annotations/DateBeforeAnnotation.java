//package zw.org.zvandiri.business.util.annotations;
//
//
//
//import zw.org.zvandiri.business.util.annotations.validations.DateAfterValidation;
//import zw.org.zvandiri.business.util.annotations.validations.DateBeforeValidation;
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
//@Constraint(validatedBy = DateBeforeValidation.class)
//public @interface DateBeforeAnnotation {
//
//    String message() default "Date has to be in the past!";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//
//}
