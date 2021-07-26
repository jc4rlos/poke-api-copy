package com.pokemon.pokedex.expose.advice;

import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.model.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <b>Class</b>:PokemonAdvice <br/>.
 *
 * @author Carlos
 */
@ControllerAdvice
public class PokemonAdvice {

  /**
   * pokemonNotFoundHandler .
   * @param exception .
   * @return ErrorResponseDto
   */
  @ResponseBody
  @ExceptionHandler(PokemonNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseDto pokemonNotFoundHandler(PokemonNotFoundException exception) {
    return ErrorResponseDto.builder()
            .message(exception.getMessage())
            .httpStatus(HttpStatus.NOT_FOUND)
            .build();
  }
}
