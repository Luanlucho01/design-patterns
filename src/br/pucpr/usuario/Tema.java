package br.pucpr.usuario;

enum Tema {
    DARK("#"),
    LIGHT("-"),
    PADRAO("=");

    private final String caractereBorda;

    Tema(String caractereBorda) {
        this.caractereBorda = caractereBorda;
    }

    String caractereBorda() {
        return caractereBorda;
    }

    static Tema de(String valor) {
        if (valor == null) {
            return PADRAO;
        }
        return switch (valor.toUpperCase()) {
            case "DARK" -> DARK;
            case "LIGHT" -> LIGHT;
            default -> PADRAO;
        };
    }
}
