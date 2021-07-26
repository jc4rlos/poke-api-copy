package com.pokemon.pokedex.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * <b>Class</b>:PokemonPatchNameDto <br/>.
 *
 * @author Carlos
 */
@Data
@Builder
public class PokemonPatchNameDto {
  private String name;
  private Long id;
}
