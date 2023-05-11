# controle-financeiro-sop
projeto teste para controle financeiro da SOP

O Controle financeiro da SOP é realizado de forma resumida por três Entidades de negocio:

1. DESPESA 
   -> Que agrega informações referente aos processos financeiros da instituição.

2. EMPENHO 
   -> Que garante o comprometimento do governo em executar a divida referente as despesas.

3. PAGAMENTO 
   -> Que efetiva a execução da dividas da instituição.

Uma DESPESA deve conter no minimo as seguintes informações:

- Número de protocolo
- Tipo de Despesa
- Data do Protocolo
- Data de vencimento
- Credor da Despesa
- Descrição da Despesa
- Valor da Despesa

Um EMPENHO deve conter no minimo as seguintes informações:

- Ano do Empenho
- Número do Empenho
- Data do Empenho
- Valor do Empenho
- Observação

Um PAGAMENTO deve conter no minimo as seguintes informações:
- Ano do Pagamento
- Número do Pagamento
- Data do Pagamento
- Valor do Pagamento
- Observação

Para efeito de modelagem, deve-se saber que:
1. Uma Despesa pode ter nenhum ou vários Empenhos.
2. Um Empenho podem ter nenhum ou varios Pagamentos. 
3. Um Pagamento obrigatoriamente pertence a um Empenho.
4. Um Empenho obrigatoriamente pertence a uma Despesa.

A DESPESA deve ainda conter um controle da situação através dos Status Abaixo:

1. Aguardando Empenho 
   -> Despesa Registrada, mas nenhum empenho associado.

2. Parcialmente Empenhada 
   -> Despesa onde a soma dos valores empenhados é inferior ao valor da despesa

3. Aguardando Pagamento 
   -> Despesa onde a soma dos valores empenhados é igual ao valor da despesa. Mas não possui registro de pagamento.

4. Parcialmente Paga 
   -> Despesa onde a soma dos valores pagos é inferior ao valor da despesa

5. paga 
   -> Despesa onde a soma dos valores pagos ao valor da despesa

Mais alguns requisitos a serem considerados
- O Número do protocolo da despesa deve ser unico.
- Os atributos: (Número e Ano) do Empenho Não pode ser registrado mais de uma vez.
- Os atributos: (Número e Ano) do Pagamento Não pode ser registrado mais de uma vez.
- Não deve ser permitido deletar um Empenho que tenha ao menos um Pagamento associado.
- Não deve ser permitido deletar uma Despesa que tenha ao menos um Empenho assoaciado.

Com base nas Informações acimas: 

- Deve ser criado a Modelagem UML que achar necessaria.
- Deve ser criado o script de banco de dados para os objetos de banco de dados 

- Desenvolva uma pequena aplicação que permita:

1. Entrada de dados referente a: DESPESA, EMPENHO, PAGAMENTO.
2. Consultas Relação de Despesa usando filtros de: (Data protocolo, Tipo Despesa, Credor )
3. Consultas Relação de Empenho usando filtros de: (Periodo de Data de empenho)
4. Consultas Relação de Pagamento usando filtros de: (Periodo de Data de Pagamento)

Requisitos Técnicos:
- A aplicação deve ser desenvolvida para ambiente WEB e/ou Mobile:
- Uso de Banco de Dados relacional (Preferivel Postgres)
