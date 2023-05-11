package br.com.sop.service;

import br.com.sop.entities.DespesaEntity;
import br.com.sop.entities.EmpenhoEntity;
import br.com.sop.entities.dtos.in.DespesaCreateDTO;
import br.com.sop.entities.dtos.out.DespesaDTO;
import br.com.sop.entities.enums.StatusDespesa;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.repositories.DespesaRepository;
import br.com.sop.services.DespesaService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DespesaServiceTest {

    @InjectMocks
    private DespesaService despesaService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private DespesaRepository despesaRepository;

    @Before
    public void init() {
        // Configurações do ObjectMapper
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(despesaService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCriarDespesa() throws RegraDeNegocioException {
        //SETUP
        DespesaCreateDTO despesaCreateDTO = new DespesaCreateDTO();
        despesaCreateDTO.setCredor_despesa("CREDOR");
        despesaCreateDTO.setDescricao_despesa("DESCRICAO QUALQUER");
        despesaCreateDTO.setValor_despesa(1000d);
        despesaCreateDTO.setData_protocolo(LocalDate.parse("2020-01-01"));
        despesaCreateDTO.setData_vencimento(LocalDate.parse("2020-01-10"));
        despesaCreateDTO.setNumero_protocolo(123456);

        DespesaEntity despesaEntityMock = getDespesaEntityMock();
        when(despesaRepository.save(any())).thenReturn(despesaEntityMock);
        //ACT
        DespesaDTO despesaDTO = despesaService.criarDespesa(despesaCreateDTO);
        //ASSERT
        assertNotNull(despesaDTO);
        assertEquals(1, despesaDTO.getId_despesa());
        assertEquals(despesaEntityMock.getCredor_despesa(), despesaDTO.getCredor_despesa());
        assertEquals(despesaEntityMock.getDescricao_despesa(), despesaDTO.getDescricao_despesa());
        assertEquals(despesaEntityMock.getValor_despesa(), despesaDTO.getValor_despesa());
        assertEquals(despesaEntityMock.getData_protocolo(), despesaDTO.getData_protocolo());
        assertEquals(despesaEntityMock.getData_vencimento(), despesaDTO.getData_vencimento());
        assertEquals(despesaEntityMock.getNumero_protocolo(), despesaDTO.getNumero_protocolo());
        assertEquals(despesaEntityMock.getTipo_despesa(), despesaDTO.getTipo_despesa());
    }

    @Test
    public void deveListarDespesasComSucesso() {
        //SETUP
        List<DespesaEntity> despesasMockadas = List.of(getDespesaEntityMock(), getDespesaEntityMock());
        //ACT
        when(despesaRepository.findAll()).thenReturn(despesasMockadas);
        //ASSERT
        assertNotNull(despesasMockadas);
        assertEquals(2, despesasMockadas.size());
    }

    @Test
    public void deveListarDespesasComFiltroDataProtocoloComSucesso() {
        //SETUP
        LocalDate dataProtocoloInicio = LocalDate.parse("2023-05-09");
        LocalDate dataProtocoloFim = LocalDate.parse("2023-05-09");
        List<DespesaEntity> despesasMockadas = List.of(getDespesaEntityMock(), getDespesaEntityMock());
        //ACT
        when(despesaRepository.findByData_protocoloBetween(any(), any())).thenReturn(despesasMockadas);
        //ASSERT
        assertNotNull(despesasMockadas);
        assertEquals(2, despesasMockadas.size());
        assertEquals(dataProtocoloInicio, despesasMockadas.get(0).getData_protocolo());
        assertEquals(dataProtocoloFim, despesasMockadas.get(1).getData_protocolo());
    }

    @Test
    public void deveListarDespesasComFiltroStatusComSucesso() {
        //SETUP
        StatusDespesa statusDespesa = StatusDespesa.AGUARDANDO_EMPENHO;
        List<DespesaEntity> despesasMockadas = List.of(getDespesaEntityMock(), getDespesaEntityMock());
        //ACT
        when(despesaRepository.findByTipo_despesaExists(any())).thenReturn(despesasMockadas);
        //ASSERT
        assertNotNull(despesasMockadas);
        assertEquals(2, despesasMockadas.size());
        assertEquals(statusDespesa, despesasMockadas.get(0).getTipo_despesa());
    }

    @Test
    public void deveListarDespesasComFiltroCredorComSucesso() {
        //SETUP
        String credor = "CREDOR";
        List<DespesaEntity> despesasMockadas = List.of(getDespesaEntityMock(), getDespesaEntityMock());
        //ACT
        when(despesaRepository.findDistinctByCredor_despesaContainingIgnoreCase(any())).thenReturn(despesasMockadas);
        //ASSERT
        assertNotNull(despesasMockadas);
        assertEquals(2, despesasMockadas.size());
        assertEquals(credor, despesasMockadas.get(0).getCredor_despesa());
    }

    @Test
    public void deveBuscarDespesaPorId() throws RegraDeNegocioException {
        //SETUP
        when(despesaRepository.findById(anyInt())).thenReturn(Optional.of(getDespesaEntityMock()));
        //ACT
        DespesaEntity despesa = despesaService.buscarDespesaPorId(1);
        //ASSERT
        assertNotNull(despesa);
        assertEquals(1, despesa.getId_despesa());
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarIdNaoEncontrado() throws RegraDeNegocioException {
        //SETUP
        Integer idProcurado = 5;
        //ACT
        despesaService.buscarDespesaPorId(idProcurado);
    }

    private static DespesaEntity getDespesaEntityMock() {
        DespesaEntity despesaMockada = new DespesaEntity();
        despesaMockada.setId_despesa(1);
        despesaMockada.setCredor_despesa("CREDOR");
        despesaMockada.setDescricao_despesa("DESCRICAO QUALQUER");
        despesaMockada.setValor_despesa(1000d);
        despesaMockada.setNumero_protocolo(123456);
        despesaMockada.setData_protocolo(LocalDate.parse("2023-05-09"));
        despesaMockada.setData_vencimento(LocalDate.parse("2023-05-10"));
        despesaMockada.setTipo_despesa(StatusDespesa.AGUARDANDO_EMPENHO);

        Set<EmpenhoEntity> empenhos = new HashSet<>();
        despesaMockada.setEmpenhos(empenhos);

        return despesaMockada;
    }
}
