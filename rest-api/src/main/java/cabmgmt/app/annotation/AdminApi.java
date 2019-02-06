package cabmgmt.app.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * annotation for Administration API 
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface AdminApi {

}
