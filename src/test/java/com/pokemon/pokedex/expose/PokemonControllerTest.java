package com.pokemon.pokedex.expose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.pokedex.business.PokemonService;
import com.pokemon.pokedex.model.dto.PokemonGetDto;
import com.pokemon.pokedex.model.dto.PokemonPatchFavoriteDto;
import com.pokemon.pokedex.model.dto.PokemonPatchNameDto;
import com.pokemon.pokedex.model.dto.PokemonSaveDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PokemonService pokemonService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createPokemon_whenPostMethod() throws Exception {
    final PokemonSaveDto pokemonSaveDto =getPokemonSaveDto();
    final PokemonGetDto pokemonGetDto =getPokemonGetDto();
    when(pokemonService.savePokemon(any(PokemonSaveDto.class))).thenReturn(pokemonGetDto);

    mockMvc.perform(post("/pokemons")
            .content(objectMapper.writeValueAsString(pokemonSaveDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name",is(pokemonGetDto.getName())));
  }

  @Test
  void findAllPokemons_whenGetMethod() throws Exception {
    final List<PokemonGetDto> pokemons =List.of(getPokemonGetDto());
    when(pokemonService.findAllPokemons()).thenReturn(pokemons);

    mockMvc.perform(get("/pokemons")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(1)))
            .andExpect(jsonPath("$[0].name",is("pokemon")));
  }

  @Test
  void findPokemonById_whenGetMethod() throws Exception {
    final PokemonGetDto pokemon =getPokemonGetDto();
    when(pokemonService.findPokemonById(anyLong())).thenReturn(pokemon);

    mockMvc.perform(get("/pokemons/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemon.getName())));
  }

  @Test
  void updatePokemon_whenPutMethod() throws Exception {
    final PokemonSaveDto pokemonSaveDto =getPokemonSaveDto();
    final PokemonGetDto pokemonGetDto =getPokemonGetDto();
    when(pokemonService.updatePokemon(any(PokemonSaveDto.class),anyLong())).thenReturn(pokemonGetDto);

    mockMvc.perform(put("/pokemons/1")
            .content(objectMapper.writeValueAsString(pokemonSaveDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemonGetDto.getName())));
  }

  @Test
  void patchNamePokemon_whenPatchMethod() throws Exception {
    final PokemonPatchNameDto pokemonPatchNameDto =getPokemonPatchNameDto();
    final PokemonGetDto pokemonGetDto =getPokemonGetDto();
    when(pokemonService.patchNamePokemon(any(PokemonPatchNameDto.class),anyLong())).thenReturn(pokemonGetDto);

    mockMvc.perform(patch("/pokemons/update-name/1")
            .content(objectMapper.writeValueAsString(pokemonPatchNameDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemonGetDto.getName())));
  }

  @Test
  void patchFavoritePokemon_whenPatchMethod() throws Exception {
    final PokemonPatchFavoriteDto pokemonPatchFavoriteDto =getPokemonPatchFavoriteDto();
    final PokemonGetDto pokemonGetDto =getPokemonGetDto();
    when(pokemonService.patchFavoritePokemon(any(PokemonPatchFavoriteDto.class),anyLong())).thenReturn(pokemonGetDto);

    mockMvc.perform(patch("/pokemons/make-favorite/1")
            .content(objectMapper.writeValueAsString(pokemonPatchFavoriteDto))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemonGetDto.getName())));
  }

  @Test
  void deletePokemonById_whenDeleteMethod() throws Exception {
    final PokemonGetDto pokemon =getPokemonGetDto();
    when(pokemonService.deletePokemonById(anyLong())).thenReturn(pokemon);

    mockMvc.perform(delete("/pokemons/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name",is(pokemon.getName())));
  }


  private PokemonPatchFavoriteDto getPokemonPatchFavoriteDto(){
    return PokemonPatchFavoriteDto.builder().favorite(true).id(1L).build();
  }

  private PokemonPatchNameDto getPokemonPatchNameDto(){
    return PokemonPatchNameDto.builder().name("pokemon").build();
  }

  private PokemonSaveDto getPokemonSaveDto() {
    return PokemonSaveDto.builder()
            .name("pokemon").build();
  }

  private PokemonGetDto getPokemonGetDto() {
    return PokemonGetDto.builder()
            .name("pokemon").build();
  }
}