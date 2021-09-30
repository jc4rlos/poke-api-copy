package com.pokemon.pokedex.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class</b>:PokemonRegionsDto <br/>.
 *
 * @author Carlos
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonRegionsDto {
  private Long id;
  private Long pokedexId;
  private String name;
  private String type;
  private String region;
  private String generation;
}
