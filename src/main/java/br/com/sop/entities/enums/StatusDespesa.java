package br.com.sop.entities.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum StatusDespesa {
    AGUARDANDO_EMPENHO(1),
    // — > Despesa Registrada, mas nenhum empenho associado.
    PARCIALMENTE_EMPENHADA(2),
    // — > Despesa onde a soma dos valores empenhados é inferior ao valor da despesa
    AGUARDANDO_PAGAMENTO(3),
    // — > Despesa onde a soma dos valores empenhados é igual ao valor da despesa. Mas não possui registro de pagamento.
    PARCIALMENTE_PAGA(4),
    // — > Despesa onde a soma dos valores pagos é inferior ao valor da despesa
    PAGA(5);

    // — > Despesa onde a soma dos valores pagos ao valor da despesa

    private Integer status;

    StatusDespesa(Integer status) {
        this.status = status;
    }

    public static StatusDespesa statusDespesa(Integer status) {
        if (status == null) {
            return null;
        }
        return Arrays
                .stream(StatusDespesa.values())
                .filter(tipo -> tipo.getStatus().equals(status))
                .findFirst()
//                .get();
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + status));
    }

}
