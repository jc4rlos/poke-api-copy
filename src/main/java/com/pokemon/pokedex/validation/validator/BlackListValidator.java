package com.pokemon.pokedex.validation.validator;

import com.pokemon.pokedex.validation.constraint.BlackListConstraint;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <b>Class</b>:BlackListValidator <br/>.
 *
 * @author Carlos
 */
public class BlackListValidator implements ConstraintValidator<BlackListConstraint,String> {

  private static final String[] BLACK_LIST = new String[] {"arbok","meowth","weezing"};


  @Override
  public boolean isValid(String pokemonName, ConstraintValidatorContext context) {
    return !Arrays.asList(BLACK_LIST).contains(pokemonName);
  }
}
