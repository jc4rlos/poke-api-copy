package com.pokemon.pokedex.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class</b>:PokemonPageDto <br/>.
 *
 * @author Carlos
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonPageDto {
  private Long totalItems;
  private Integer totalPages;
  private Integer currentPage;
  private List<PokemonDto> pokemons;
}
