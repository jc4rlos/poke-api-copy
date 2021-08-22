package com.pokemon.pokedex.validation.validator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackListValidatorTest {

  @Test
  void should_return_false_if_not_blacklisted() {
    BlackListValidator blackListValidator = new BlackListValidator();
    boolean isValid = blackListValidator.isValid("arbok",null);
    assertThat(isValid).isEqualTo(false);
  }

  @Test
  void should_return_true_if_blacklisted() {
    BlackListValidator blackListValidator = new BlackListValidator();
    boolean isValid = blackListValidator.isValid("pokemon",null);
    assertThat(isValid).isEqualTo(true);
  }
}