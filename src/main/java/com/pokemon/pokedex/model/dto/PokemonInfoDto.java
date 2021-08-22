package com.pokemon.pokedex.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class</b>:PokemonInfoDto <br/>.
 *
 * @author Carlos
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonInfoDto {
  private Long id;
  private String name;
}
