package com.pokemon.pokedex.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <b>Class</b>:PokemonMapper <br/>.
 *
 * @author Carlos
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PokemonQueries {

  public static final String POKEMON_REGION_PROJECTION_QUERY = "SELECT p.id,p.pokedex_id,p.name,p.[type],pr.region,pr.generation "
          + "FROM [dbo].[pokemons] p INNER JOIN [dbo].[pokemon_regions] pr  ON p.pokedex_id = pr.pokedex_id WHERE p.name LIKE  :name ";

}
