package com.pokemon.pokedex.business.impl;

import com.pokemon.pokedex.business.PokemonService;
import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.mappers.PokemonMapper;
import com.pokemon.pokedex.model.dto.PokemonGetDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.dto.PokemonSaveDto;
import com.pokemon.pokedex.model.entity.Pokemon;
import com.pokemon.pokedex.model.entity.PokemonEvolution;
import com.pokemon.pokedex.repository.PokemonEvolutionRepository;
import com.pokemon.pokedex.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <b>Class</b>:PokemonServiceImpl <br/>.
 *
 * @author Carlos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService {

  private final PokemonRepository pokemonRepository;
  private final PokemonEvolutionRepository pokemonEvolutionRepository;

  @Override
  public PokemonGetDto savePokemon(final PokemonSaveDto pokemonSaveDto) {
    final Optional<PokemonEvolution> pokemonEvolution = pokemonEvolutionRepository.findByPokemonName(pokemonSaveDto.getName());
    if (pokemonEvolution.isPresent()) {
      pokemonSaveDto.setEvolution(pokemonEvolution.get().getEvolution());
    }
    final Pokemon pokemon = pokemonRepository.save(PokemonMapper.pokemonSaveDtoToPokemon(pokemonSaveDto));
    return PokemonMapper.pokemonToPokemonGetDto(pokemon);
  }

  @Override
  public List<PokemonGetDto> findAllPokemons() {
    return pokemonRepository.findAll()
            .stream().map(PokemonMapper::pokemonToPokemonGetDto)
            .collect(Collectors.toList());
  }

  @Override
  public PokemonGetDto deletePokemonById(final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(pokemon -> {
              pokemonRepository.delete(pokemon);
              return PokemonMapper.pokemonToPokemonGetDto(pokemon);
            })
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonGetDto findPokemonById(final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(PokemonMapper::pokemonToPokemonGetDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonGetDto updatePokemon(final PokemonSaveDto pokemonSaveDto, final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(pokemon -> PokemonMapper.pokemonSaveDtoToPokemon(pokemonSaveDto))
            .map(pokemonRepository::save)
            .map(PokemonMapper::pokemonToPokemonGetDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonGetDto patchNamePokemon(final PokemonPatchNameDto pokemonPatchNameDto, final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(pokemon -> {
              pokemon.setName(pokemonPatchNameDto.getName());
              return pokemonRepository.save(pokemon);
            })
            .map(PokemonMapper::pokemonToPokemonGetDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonGetDto patchFavoritePokemon(final PokemonPatchFavoriteDto pokemonPatchFavoriteDto,
                                            final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(pokemon -> {
              pokemon.setFavorite(pokemonPatchFavoriteDto.isFavorite());
              return pokemonRepository.save(pokemon);
            })
            .map(PokemonMapper::pokemonToPokemonGetDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }
}
