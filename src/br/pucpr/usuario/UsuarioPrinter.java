package br.pucpr.usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioPrinter {

    private static final int LARGURA_TABELA = 74;
    private static final String FORMATO_LINHA = "| %-5s | %-20s | %-22s | %-14s |%n";
    private static final String RECUO = "                    ";

    public void print(List<Usuario> lista, boolean maskCpf, boolean alignRight, String theme) {
        print(lista, OpcoesImpressao.de(maskCpf, alignRight, theme));
    }

    public void print(List<Usuario> lista, OpcoesImpressao opcoes) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("ERRO: Lista de usuários vazia ou nula.");
            return;
        }

        var tabela = montarTabela(lista, opcoes);
        imprimir(tabela, opcoes.comRecuo());
    }

    private String montarTabela(List<Usuario> lista, OpcoesImpressao opcoes) {
        var sb = new StringBuilder();
        var borda = opcoes.tema().caractereBorda();

        appendBorda(sb, borda);
        sb.append(String.format(FORMATO_LINHA, "ID", "NOME", "EMAIL", "CPF"));
        appendBorda(sb, borda);

        for (var usuario : lista) {
            if (usuario != null) {
                appendLinha(sb, usuario, opcoes.mascararCpf());
            }
        }

        appendBorda(sb, borda);
        return sb.toString();
    }

    private void appendLinha(StringBuilder sb, Usuario usuario, boolean mascararCpf) {
        sb.append(String.format(
                FORMATO_LINHA,
                FormatadorUsuario.formatarId(usuario.id()),
                FormatadorUsuario.formatarNome(usuario.nome()),
                FormatadorUsuario.formatarEmail(usuario.email()),
                FormatadorUsuario.formatarCpf(usuario.cpf(), mascararCpf)
        ));
    }

    private void appendBorda(StringBuilder sb, String caractere) {
        sb.repeat(caractere, LARGURA_TABELA).append("\n");
    }

    private void imprimir(String tabela, boolean comRecuo) {
        if (!comRecuo) {
            System.out.print(tabela);
            return;
        }
        for (var linha : tabela.split("\n")) {
            System.out.println(RECUO + linha);
        }
    }

    public static void main(String[] args) {
        var usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
        usuarios.add(new Usuario(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
        usuarios.add(new Usuario(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
        usuarios.add(new Usuario(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
        usuarios.add(new Usuario(105L, "Lucas Mendes", "lucas@email.com", "12345"));
        usuarios.add(new Usuario(106L, "", "beatriz@email.com", "55566677788"));

        var printer = new UsuarioPrinter();
        printer.print(usuarios, true, true, "LIGHT");
    }
}
