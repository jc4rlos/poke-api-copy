package com.pokemon.pokedex.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * <b>Class</b>:ErrorResponseDto <br/>.
 *
 * @author Carlos
 */
@Getter
@Setter
@Builder
public class ErrorResponseDto {
  private String message;
  private HttpStatus httpStatus;
}
