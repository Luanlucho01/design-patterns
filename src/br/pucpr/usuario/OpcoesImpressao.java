package br.pucpr.usuario;

record OpcoesImpressao(boolean mascararCpf, boolean comRecuo, Tema tema) {

    static OpcoesImpressao de(boolean mascararCpf, boolean comRecuo, String tema) {
        return new OpcoesImpressao(mascararCpf, comRecuo, Tema.de(tema));
    }
}
