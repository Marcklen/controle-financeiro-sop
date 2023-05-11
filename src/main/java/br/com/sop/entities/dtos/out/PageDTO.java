package br.com.sop.entities.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// classe para retornar os dados de uma página
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {

    private Long totalElementos;
    private Integer quantidadePaginas;
    private Integer pagina;
    private Integer tamanho;
    private List<T> elementos;
}
