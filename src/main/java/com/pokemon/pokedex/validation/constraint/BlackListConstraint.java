package com.pokemon.pokedex.validation.constraint;

import com.pokemon.pokedex.validation.validator.BlackListValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <b>Class</b>:BlackListConstraint <br/>.
 *
 * @author Carlos
 */

@Documented
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank
@NotNull
@Constraint(validatedBy = BlackListValidator.class)
public @interface BlackListConstraint {

  /**
   * message .
   * @return String
   */
  String message() default "{pokemon.blackList}";

  /**
   * groups .
   * @return ?
   */
  Class<?>[] groups() default { };

  /**
   * payload .
   * @return ?
   */
  Class<? extends Payload>[] payload() default { };
}
