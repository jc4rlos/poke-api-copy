package com.pokemon.pokedex.exception;

/**
 * <b>Class</b>:PokemonNotFoundException <br/>.
 *
 * @author Carlos
 */
public class PokemonNotFoundException extends RuntimeException {

  public PokemonNotFoundException(Long id) {
    super("Could not find pokemon ".concat(String.valueOf(id)));
  }

}
