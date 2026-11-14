# Proffy Mobile — Fluxo "Esqueci minha senha"

- **Nome completo:** Luís Otávio Dias
- **Turma:** Sistemas de Informação — 6º Período

## Sobre a Implementação

A validação do campo de e-mail na `ForgotPasswordActivity` foi implementada com o utilitário compartilhado `EmailValidator` (baseado em `Patterns.EMAIL_ADDRESS` e também reutilizado na `RegisterStepTwoActivity` para evitar lógica duplicada). Por meio de `addTextChangedListener` e `setOnFocusChangeListener`, o formato do e-mail é validado em tempo real: quando o campo está vazio ou inválido, o `TextInputLayout` exibe a borda e a mensagem de erro apropriada e o botão permanece desabilitado em cinza com o texto `"Entrar"`; quando o e-mail é válido, o erro é removido e o botão é habilitado em verde com o texto `"Enviar"`. Ao tocar em `"Enviar"`, a aplicação inicia a `FinallyActivity` via `Intent` com os textos de redefinição enviada, e o botão `"Voltar ao login"` retorna à `LoginActivity` utilizando `FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK` para limpar a pilha de navegação.
