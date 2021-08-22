package com.pokemon.pokedex.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Class</b>:ErrorResponseDto <br/>.
 *
 * @author Carlos
 */
@Getter
@Setter
@Builder
public class ErrorResponseDto {
  private Object errors;
}
