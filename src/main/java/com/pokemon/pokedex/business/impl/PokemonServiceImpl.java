package com.pokemon.pokedex.business.impl;

import com.pokemon.pokedex.business.PokemonService;
import com.pokemon.pokedex.exception.PokemonNotFoundException;
import com.pokemon.pokedex.mappers.PokemonMapper;
import com.pokemon.pokedex.model.dto.PokemonDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
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
import org.springframework.transaction.annotation.Transactional;

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
  public List<PokemonDto> findAllPokemons(final String orderByColumn, final boolean ascending) {
    return pokemonRepository.findAllByOrderByCreatedAtAndCp(orderByColumn,ascending)
            .stream().map(PokemonMapper::pokemonToPokemonDto)
            .collect(Collectors.toList());
  }

  @Override
  public PokemonDto savePokemon(final PokemonDto pokemonDto) throws PokemonNotFoundException {
    final Optional<PokemonEvolution> pokemonEvolution = pokemonEvolutionRepository.findById(pokemonDto.getPokedexId());
    if (pokemonEvolution.isPresent()) {
      pokemonDto.setEvolution(pokemonEvolution.get().getEvolution());
      pokemonDto.setRequiredCandies(pokemonEvolution.get().getCandyAmount());
    }
    final Pokemon pokemon = pokemonRepository.save(PokemonMapper.pokemonDtoToPokemon(pokemonDto));
    return PokemonMapper.pokemonToPokemonDto(pokemon);
  }

  @Override
  @Transactional
  public PokemonDto deletePokemonById(final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(pokemon -> {
              pokemonRepository.delete(pokemon);
              pokemonRepository.updatePokemonCandies(pokemon.getCandy(), pokemon.getPokedexId());
              return PokemonMapper.pokemonToPokemonDto(pokemon);
            })
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonDto findPokemonById(final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(PokemonMapper::pokemonToPokemonDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonDto updatePokemon(final PokemonDto pokemonDto, final Long id) throws PokemonNotFoundException {
    final Optional<PokemonEvolution> pokemonEvolution = pokemonEvolutionRepository.findById(pokemonDto.getPokedexId());
    if (pokemonEvolution.isPresent()) {
      pokemonDto.setEvolution(pokemonEvolution.get().getEvolution());
      pokemonDto.setRequiredCandies(pokemonEvolution.get().getCandyAmount());
    }
    return pokemonRepository.findById(id)
            .map(pokemon -> PokemonMapper.pokemonDtoToPokemon(pokemonDto))
            .map(pokemonRepository::save)
            .map(PokemonMapper::pokemonToPokemonDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonDto patchNamePokemon(final PokemonPatchNameDto pokemonPatchNameDto, final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(pokemon -> {
              pokemon.setName(pokemonPatchNameDto.getName());
              return pokemonRepository.save(pokemon);
            })
            .map(PokemonMapper::pokemonToPokemonDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }

  @Override
  public PokemonDto patchFavoritePokemon(final PokemonPatchFavoriteDto pokemonPatchFavoriteDto,
                                         final Long id) throws PokemonNotFoundException {
    return pokemonRepository.findById(id)
            .map(pokemon -> {
              pokemon.setFavorite(pokemonPatchFavoriteDto.isFavorite());
              return pokemonRepository.save(pokemon);
            })
            .map(PokemonMapper::pokemonToPokemonDto)
            .orElseThrow(() -> new PokemonNotFoundException(id));
  }
}
