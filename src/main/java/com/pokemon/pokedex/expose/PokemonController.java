package com.pokemon.pokedex.expose;

import com.pokemon.pokedex.business.PokemonService;
import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.model.dto.PokemonGetDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.dto.PokemonSaveDto;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Class</b>:PokemonController <br/>.
 *
 * @author Carlos
 */
@RestController
@RequestMapping("/pokemons")
@RequiredArgsConstructor
public class PokemonController {
  private final PokemonService pokemonService;

  /**
   * savePokemon .
   *
   * @param pokemonSaveDto .
   * @return PokemonGetDto
   */
  @PostMapping
  public ResponseEntity<PokemonGetDto> savePokemon(@RequestBody final PokemonSaveDto pokemonSaveDto) {
    final PokemonGetDto pokemonGetDto = pokemonService.savePokemon(pokemonSaveDto);
    return new ResponseEntity<>(pokemonGetDto, HttpStatus.CREATED);
  }

  /**
   * findAllPokemons .
   *
   * @return List of PokemonGetDto
   */
  @GetMapping
  public ResponseEntity<List<PokemonGetDto>> findAllPokemons() {
    final List<PokemonGetDto> pokemons = pokemonService.findAllPokemons();
    return new ResponseEntity<>(pokemons, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PokemonGetDto> findPokemonById(@PathVariable("id") final Long id) throws PokemonNotFoundException {
    return new ResponseEntity<>(pokemonService.findPokemonById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PokemonGetDto> updatePokemon(@RequestBody final PokemonSaveDto pokemonSaveDto,
                                                     @PathVariable("id") final Long id) throws PokemonNotFoundException {
    final PokemonGetDto pokemonGetDto = pokemonService.updatePokemon(pokemonSaveDto, id);
    return new ResponseEntity<>(pokemonGetDto, HttpStatus.OK);
  }

  @PatchMapping("/update-name/{id}")
  public ResponseEntity<PokemonGetDto> patchNamePokemon(@RequestBody final PokemonPatchNameDto pokemonPatchNameDto,
                                                        @PathVariable("id") final Long id) throws PokemonNotFoundException {
    final PokemonGetDto pokemonGetDto = pokemonService.patchNamePokemon(pokemonPatchNameDto, id);
    return new ResponseEntity<>(pokemonGetDto, HttpStatus.OK);
  }

  @PatchMapping("/make-favorite/{id}")
  public ResponseEntity<PokemonGetDto> patchFavoritePokemon(@RequestBody final PokemonPatchFavoriteDto pokemonPatchFavoriteDto,
                                                            @PathVariable("id") final Long id) throws PokemonNotFoundException {
    final PokemonGetDto pokemonGetDto = pokemonService.patchFavoritePokemon(pokemonPatchFavoriteDto, id);
    return new ResponseEntity<>(pokemonGetDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PokemonGetDto> deletePokemonById(@PathVariable("id") final Long id) throws PokemonNotFoundException {
    return new ResponseEntity<>(pokemonService.deletePokemonById(id), HttpStatus.OK);
  }

}
