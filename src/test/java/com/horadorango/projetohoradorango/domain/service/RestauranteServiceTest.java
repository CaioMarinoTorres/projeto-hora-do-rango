package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.api.converter.RestauranteConverter;
import com.horadorango.projetohoradorango.api.dto.usuario.UsuarioResponse;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteRequest;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteResponse;
import com.horadorango.projetohoradorango.api.dto.restaurante.RestauranteUpdateRequest;
import com.horadorango.projetohoradorango.domain.entity.Usuario;
import com.horadorango.projetohoradorango.domain.entity.Restaurante;
import com.horadorango.projetohoradorango.domain.exeption.DataConflictException;
import com.horadorango.projetohoradorango.domain.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class RestauranteServiceTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteConverter converter;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private RestauranteService service;

    private RestauranteUpdateRequest updateRequest;
    private RestauranteResponse response;
    private RestauranteRequest request;
    private Restaurante entity;

    @BeforeEach
    void setUp() {
        String nome = "Teste teste";
        String cnpj = "123.456.789-00";
        String cpf = "123.456.789-00";
        long id = 1L;

        Usuario usuario = Usuario.builder()
                .nome(nome)
                .cpf(cpf)
                .id(id)
                .build();

        UsuarioResponse usuarioResponse = UsuarioResponse.builder()
                .nome(nome)
                .cpf(cpf)
                .id(id)
                .build();

        response = RestauranteResponse.builder()
                .proprietario(usuarioResponse)
                .nome(nome)
                .cnpj(cnpj)
                .id(id)
                .build();

        request = RestauranteRequest.builder()
                .proprietarioId(id)
                .nome(nome)
                .cnpj(cnpj)
                .build();

        updateRequest = RestauranteUpdateRequest.builder()
                .nome(nome)
                .proprietarioId(id)
                .build();

        entity = Restaurante.builder()
                .usuario(usuario)
                .nome(nome)
                .cnpj(cnpj)
                .id(id)
                .build();
    }

    //<editor-fold desc="Metodo Save">

    @Test
    @DisplayName("Deve salvar restaurante")
    void deveSalvarRestaurante() {
        when(usuarioService.findById(entity.getUsuario().getId())).thenReturn(entity.getUsuario());
        when(repository.findByCnpj(entity.getCnpj())).thenReturn(Optional.empty());
        when(converter.toResponse(entity)).thenReturn(response);
        when(converter.toEntity(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        RestauranteResponse result = service.save(request);

        verify(usuarioService, times(1)).findById(request.getProprietarioId());
        verify(repository, times(1)).findByCnpj(entity.getCnpj());
        verify(converter, times(1)).toResponse(entity);
        verify(converter, times(1)).toEntity(request);
        verify(repository, times(1)).save(entity);

        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    @DisplayName("Deve lançar exceção quando CNPJ já existir")
    void deveLancarExcecaoQuandoCnpjJaExistir() {
        when(repository.findByCnpj(entity.getCnpj())).thenReturn(Optional.of(entity));
        when(converter.toEntity(request)).thenReturn(entity);

        assertThrows(DataConflictException.class, () -> service.save(request));
        verify(repository, times(0)).save(entity);
    }

    //</editor-fold>

    //<editor-fold desc="Metodo Update">

    @Test
    @DisplayName("Deve atualizar o nome do restaurante quando o proprietário for o mesmo")
    void deveAtualizarONomeDoRestaurante() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(converter.toResponse(entity)).thenReturn(response);

        RestauranteResponse result = service.update(updateRequest, entity.getId());

        verify(repository, times(1)).findById(entity.getId());
        verify(converter, times(1)).toResponse(entity);
        verify(repository, times(1)).save(entity);

        assertEquals(response, result);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve deletar restaurante com sucesso")
    void deveDeletarRestaurante() {

    }

    //</editor-fold>

}