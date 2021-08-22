package com.pokemon.pokedex.model.dto;

import com.pokemon.pokedex.validation.constraint.BlackListConstraint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Class</b>:PokemonDto <br/>.
 *
 * @author Carlos
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonDto {
  private Long id;
  private Long pokedexId;
  @BlackListConstraint
  private String name;
  private Integer order;
  private Integer cp;
  private Integer hp;
  private BigDecimal weight;
  private BigDecimal height;
  private String type;
  private String gender;
  private Integer candy;
  private Integer starDust;
  private String image;
  private boolean favorite;
  private boolean shiny;
  private String evolution;
  private Integer requiredCandies;
  @Builder.Default
  private List<PokemonMoveDto> moves = new ArrayList<>();
}
