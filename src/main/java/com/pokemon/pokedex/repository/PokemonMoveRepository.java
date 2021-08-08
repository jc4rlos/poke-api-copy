package com.pokemon.pokedex.repository;

import com.pokemon.pokedex.model.entity.PokemonMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class</b>:PokemonMoveRepository <br/>.
 *
 * @author Carlos
 */
@Repository
public interface PokemonMoveRepository extends JpaRepository<PokemonMove, Long> {
}
