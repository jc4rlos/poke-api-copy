package com.pokemon.pokedex.model.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * <b>Class</b>:PokemonPatchFavoriteDto <br/>.
 *
 * @author Carlos
 */
@Getter
@Builder
public class PokemonPatchFavoriteDto {
  private Long id;
  private boolean favorite;
}
