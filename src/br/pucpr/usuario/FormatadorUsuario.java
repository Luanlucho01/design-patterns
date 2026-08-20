package br.pucpr.usuario;

final class FormatadorUsuario {

    private static final int LARGURA_NOME = 20;
    private static final int LIMITE_TRUNCAMENTO = 17;
    private static final int TAMANHO_CPF = 11;

    private FormatadorUsuario() {
    }

    static String formatarId(Long id) {
        return id != null ? id.toString() : "0";
    }

    static String formatarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return "NÃO INFORMADO";
        }
        if (nome.length() > LARGURA_NOME) {
            return nome.substring(0, LIMITE_TRUNCAMENTO) + "...";
        }
        return nome;
    }

    static String formatarEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "INVALIDO";
        }
        return email;
    }

    static String formatarCpf(String cpf, boolean mascarar) {
        if (cpf == null || cpf.length() != TAMANHO_CPF) {
            return "CPF INVALIDO";
        }
        if (mascarar) {
            return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
        }
        return cpf.substring(0, 3) + "."
                + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-"
                + cpf.substring(9, 11);
    }
}
