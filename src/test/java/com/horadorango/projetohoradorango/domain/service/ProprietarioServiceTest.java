package com.horadorango.projetohoradorango.domain.service;

import com.horadorango.projetohoradorango.api.converter.ProprietarioConverter;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioRequest;
import com.horadorango.projetohoradorango.api.dto.proprietario.ProprietarioResponse;
import com.horadorango.projetohoradorango.domain.entity.Proprietario;
import com.horadorango.projetohoradorango.domain.exeption.DataConflictException;
import com.horadorango.projetohoradorango.domain.exeption.DataNotFoundException;
import com.horadorango.projetohoradorango.domain.repository.ProprietarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProprietarioServiceTest {

    @Mock
    private ProprietarioRepository repository;

    @Mock
    private ProprietarioConverter converter;

    @InjectMocks
    private ProprietarioService service;

    private ProprietarioResponse response;
    private ProprietarioRequest request;
    private Proprietario entity;

    @BeforeEach
    void setUp() {
        String nome = "Nazário da Silva";
        String cpf = "123.456.789-00";
        long id = 1L;

        response = ProprietarioResponse.builder()
                .nome(nome)
                .cpf(cpf)
                .id(id)
                .build();

        request = ProprietarioRequest.builder()
                .nome(nome)
                .cpf(cpf)
                .build();

        entity = Proprietario.builder()
                .nome(nome)
                .cpf(cpf)
                .id(id)
                .build();
    }

    //<editor-fold desc="Metodo Save">

    @Test
    @DisplayName("Deve salvar proprietário quando CPF não existir")
    void deveSalvarProprietarioQuandoCpfNaoExistir() {
        Proprietario propBeforeSave = Proprietario.builder()
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .build();

        when(repository.findByCpf(entity.getCpf())).thenReturn(Optional.empty());
        when(converter.toResponse(entity)).thenReturn(response);
        when(converter.toEntity(request)).thenReturn(propBeforeSave);
        when(repository.save(propBeforeSave)).thenReturn(entity);

        ProprietarioResponse response = service.save(request);

        verify(repository, times(1)).findByCpf(entity.getCpf());
        verify(repository, times(1)).save(propBeforeSave);
        assertEquals(this.response, response);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Deve lançar exceção quando CPF já existir")
    void deveLancarExcecaoQuandoCpfJaExistir() {
        when(converter.toEntity(request)).thenReturn(entity);
        when(repository.findByCpf(entity.getCpf())).thenReturn(Optional.of(entity));

        assertThrows(DataConflictException.class, () -> service.save(request));
        verify(repository, times(0)).save(entity);
    }

    //</editor-fold>

    //<editor-fold desc="Metodo Update">

    @Test
    @DisplayName("Deve atualizar proprietário quando o proprietario existir")
    void deveAtualizarProprietario()
    {
        ProprietarioRequest proprietarioUpdate = ProprietarioRequest.builder()
                .nome("Nome Atualizado")
                .build();

        Proprietario proprietarioAfterSave = Proprietario.builder()
                .nome(proprietarioUpdate.getNome())
                .cpf(entity.getCpf())
                .id(entity.getId())
                .build();

        response.setNome(proprietarioUpdate.getNome());

        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(converter.toResponse(proprietarioAfterSave)).thenReturn(response);
        when(repository.save(proprietarioAfterSave)).thenReturn(proprietarioAfterSave);

        ProprietarioResponse response = service.update(proprietarioUpdate, entity.getId());

        verify(repository, times(1)).findById(entity.getId());
        verify(repository, times(1)).save(entity);
        assertEquals(this.response.getNome(), response.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando proprietário não existir")
    void deveLancarExcecaoQuandoProprietarioNaoExistir() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> service.update(request, entity.getId()));
        verify(repository, times(0)).save(entity);
    }

    //</editor-fold>

    //<editor-fold desc="Metodo Delete">

    @Test
    @DisplayName("Deve deletar proprietário com sucesso")
    void deveDeletarProprietario() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        service.delete(entity.getId());

        verify(repository, times(1)).deleteById(entity.getId());
        verify(repository, times(1)).findById(entity.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quando proprietário não existir ao deletar")
    void deveLancarExcecaoQuandoProprietarioNaoExistirAoDeletar() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> service.delete(entity.getId()));
        verify(repository, times(0)).deleteById(entity.getId());
    }

    //</editor-fold>

    //<editor-fold desc="Metodos Find">

    @Test
    @DisplayName("Deve retornar uma lista de proprietários")
    void deveRetornarUmaListaDeProprietarios() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(converter.toResponse(List.of(entity))).thenReturn(List.of(response));

        service.findAll();

        verify(repository, times(1)).findAll();
        verify(converter, times(1)).toResponse(List.of(entity));
    }

    @Test
    @DisplayName("Deve retornar um proprietário por ID")
    void deveRetornarUmProprietarioPorId() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));

        service.findById(entity.getId());

        verify(repository, times(1)).findById(entity.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção quando proprietário não existir ao buscar por ID")
    void deveLancarExcecaoQuandoProprietarioNaoExistirPorId() {
        when(repository.findById(entity.getId())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> service.findById(entity.getId()));
        verify(repository, times(1)).findById(entity.getId());
    }

    @Test
    @DisplayName("Deve retornar um proprietário response por ID")
    void deveRetornarUmProprietarioResponsePorId() {
        when(repository.findById(entity.getId())).thenReturn(Optional.of(entity));
        when(converter.toResponse(entity)).thenReturn(response);

        service.findByIdResponse(entity.getId());

        verify(repository, times(1)).findById(entity.getId());
        verify(converter, times(1)).toResponse(entity);
    }

    //</editor-fold>

    //<editor-fold desc="Metodos Private">

    @Test
    @DisplayName("Deve retornar verdadeiro quando proprietário EXISTIR por CPF")
    void deveRetornarVerdadeiroQuandoProprietarioExistirPorCpf() {
        when(repository.findByCpf(entity.getCpf())).thenReturn(Optional.of(entity));

        Optional<Proprietario> proprietarioOptional = repository.findByCpf(entity.getCpf());

        assertTrue(proprietarioOptional.isPresent());
    }

    @Test
    @DisplayName("Deve retornar falso quando proprietario NÃO EXISTIR por CPF")
    void deveRetornarFalsoQuandoProprietarioNaoExistirPorCpf() {
        when(repository.findByCpf(entity.getCpf())).thenReturn(Optional.empty());

        Optional<Proprietario> proprietarioOptional = repository.findByCpf(entity.getCpf());

        assertTrue(proprietarioOptional.isEmpty());
    }

    //</editor-fold>

}