package br.pucpr.planet;

import br.pucpr.user.Theme;
import java.util.ArrayList;
import java.util.Locale;

public class PlanetasPrinter {
  private static final Locale PT_BR = Locale.of("pt", "BR");
  private static final double KM_PER_AU = 149_597_870.7;

  public void print(ArrayList<Planet> planets, boolean alignRight, Theme theme) {
    if (planets == null || planets.isEmpty()) {
      System.out.println("ERRO: Lista de planetas vazia ou nula.");
      return;
    }
    final var borderChar = theme.getBorderChar();

    // Borda superior e cabeçalho
    final var BORDER_WIDTH = 81;
    var sb = new StringBuilder();
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");
    sb.append(
        String.format(
            "| %-20s | %-10s | %-14s | %-14s | %-7s |%n",
            "Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo"));
    sb.repeat(borderChar, BORDER_WIDTH).append("\n");
    for (var planet : planets) {
      if (planet == null) {
        continue;
      }
      sb.append(
          String.format(
              "| %-20s | %10s | %14s | %14s | %-7s |%n",
              formatName(planet.name()),
              formatDiameter(planet.diameterKm()),
              formatDistanceKm(planet.sunDistanceKm()),
              formatDistanceAu(planet.sunDistanceKm()),
              formatType(planet.type())));
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

  private static String formatName(String name) {
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }
    if (name.length() > 20) {
      name = name.substring(0, 17) + "...";
    }
    return name;
  }

  private static String formatDiameter(double diameterKm) {
    return String.format(PT_BR, "%,.1f", diameterKm);
  }

  private static String formatDistanceKm(long distanceKm) {
    return String.format(PT_BR, "%,d", distanceKm);
  }

  private static String formatDistanceAu(long distanceKm) {
    return String.format(PT_BR, "%,.2f", distanceKm / KM_PER_AU);
  }

  private static String formatType(PlanetType type) {
    if (type == null) {
      return "?";
    }
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}
