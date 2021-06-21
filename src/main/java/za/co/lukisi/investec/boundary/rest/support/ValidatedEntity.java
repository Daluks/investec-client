package za.co.lukisi.investec.boundary.rest.support;

import javax.ws.rs.NameBinding;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Provider
@NameBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatedEntity {
}
