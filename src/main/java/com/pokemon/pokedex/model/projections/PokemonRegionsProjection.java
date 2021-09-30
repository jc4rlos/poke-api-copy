package com.pokemon.pokedex.model.projections;

import org.springframework.beans.factory.annotation.Value;

/**
 * <b>Class</b>: PokemonRegionsProjection <br/>.
 *
 * @author Carlos <br/>
 */
public interface PokemonRegionsProjection {
  Long getId();

  @Value("#{target.pokedex_id}")
  Long getPokedexId();

  String getName();

  String getType();

  String getRegion();

  String getGeneration();

}
