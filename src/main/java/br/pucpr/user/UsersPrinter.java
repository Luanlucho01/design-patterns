package br.pucpr.user;

import java.util.ArrayList;

public class UsersPrinter {
  public void print(ArrayList<User> users, boolean maskCpf, boolean alignRight, Theme theme) {
    if (users == null || users.isEmpty()) {
      System.out.println("ERRO: Lista de usuários vazia ou nula.");
      return;
    }
    final var borderChar = theme.getBorderChar();

    // Borda superior e cabeçalho
    final var BORDER_WIDTH = 74;
    var sb = new StringBuilder();
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");
    sb.append(String.format("| %-5s | %-20s | %-22s | %-14s |%n", "ID", "NOME", "EMAIL", "CPF"));
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");
    for (var user : users) {
      if (user == null) {
        continue;
      }
      sb.append(
          String.format(
              "| %-5s | %-20s | %-22s | %-14s |%n",
              formatId(user.id()),
              formatName(user),
              validateAndFormatEmail(user.email()),
              formatCpf(user.cpf(), maskCpf)));
    }
    // Borda inferior
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");

    // Espaçamento
    if (alignRight) {
      var lines = sb.toString().split("\n");
      for (var line : lines) {
        System.out.println("                    " + line);
      }
    } else {
      System.out.print(sb);
    }
  }

  private static String formatId(Long id) {
    return id != null ? id.toString() : "0";
  }

  private static String formatCpf(String cpf, boolean mask) {
    if (cpf == null || cpf.length() != 11) {
      return "CPF INVÁLIDO";
    }
    if (mask) {
      return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }
    return cpf.substring(0, 3)
        + "."
        + cpf.substring(3, 6)
        + "."
        + cpf.substring(6, 9)
        + "-"
        + cpf.substring(9, 11);
  }

  private static String validateAndFormatEmail(String email) {
    return email == null || !email.contains("@") ? "INVÁLIDO" : email;
  }

  private static String formatName(User user) {
    var name = user.name();
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }
    if (name.length() > 20) {
      name = name.substring(0, 17) + "...";
    }
    return name;
  }
}
