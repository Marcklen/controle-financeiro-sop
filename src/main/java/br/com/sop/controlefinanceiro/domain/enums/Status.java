package br.com.sop.controlefinanceiro.domain.enums;

public enum Status {

	AGUARDANDO_EMPENHO(1, "AGUARDANDO_EMPENHO"),
	PARCIALMENTE_EMPENHADA(2, "PARCIALMENTE EMPENHADA"),
	AGUARDANDO_PAGAMENTO(3, "AGUARDANDO_PAGAMENTO"),
	PARCIALMENTE_PAGA(4, "PARCIALMENTE_PAGA"),
	PAGA(5, "PAGA");
	
	private Integer codigo;
	private String descricao;

	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Status x : Status.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status Inválido");
	}
}