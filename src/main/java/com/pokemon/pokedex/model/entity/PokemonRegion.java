package com.pokemon.pokedex.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Class</b>: PokemonRegion <br/>.
 *
 * @author Carlos <br/>
 */

@Entity()
@Table(name = "pokemon_regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PokemonRegion {

  @Id
  @Column
  private Long id;

  @Column(name = "pokemon_id")
  private Long pokedexId;

  private String region;

  private String generation;

}
