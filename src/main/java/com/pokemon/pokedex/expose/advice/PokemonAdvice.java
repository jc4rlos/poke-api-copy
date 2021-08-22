package com.pokemon.pokedex.expose.advice;

import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.model.dto.ErrorResponseDto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
   *
   * @param exception .
   * @return ErrorResponseDto
   */
  @ResponseBody
  @ExceptionHandler(PokemonNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponseDto> pokemonNotFoundHandler(PokemonNotFoundException exception) {
    return new ResponseEntity<>(ErrorResponseDto.builder()
            .errors(exception.getMessage())
            .build(), HttpStatus.NOT_FOUND);
  }

  /**
   * handleValidationExceptions .
   *
   * @param ex .
   * @return Map
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> handleValidationExceptions(
          MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult()
            .getFieldErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

    return new ResponseEntity<>(ErrorResponseDto.builder()
            .errors(errors)
            .build(), HttpStatus.BAD_REQUEST);
  }

}
