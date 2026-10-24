package br.pucpr.planet;

import br.pucpr.table.model.ColumnData;

public enum PlanetaColumns implements ColumnData<Planet> {
  NAME("%-10s".formatted("Nome")) {
    @Override
    public String get(Planet planet) {
      return planet.name() == null || planet.name().isEmpty() ? "NÃO INFORMADO" : planet.name();
    }
  },
  DIAMETER("%10s".formatted("Diâmetro")) {
    @Override
    public String get(Planet planet) {
      return "%,10.1f".formatted(planet.diameterKm());
    }
  },
  SUN_DISTANCE_KM("%15s".formatted("Dist. sol (km)")) {
    @Override
    public String get(Planet planet) {
      return "%,15d".formatted(planet.sunDistanceKm());
    }
  },
  SUN_DISTANCE_AU("%15s".formatted("Dist. sol (ua)")) {
    @Override
    public String get(Planet planet) {
      return "%,15.2f".formatted(Planet.kmToAu(planet.sunDistanceKm()));
    }
  },
  TYPE("%-10s".formatted("Tipo")) {
    @Override
    public String get(Planet planet) {
      return switch (planet.type()) {
        case ROCK -> "Rochoso";
        case GAS -> "Gasoso";
        case ICE -> "Gelado";
        case DWARF -> "Anão";
      };
    }
  };

  private final String header;

  PlanetaColumns(String header) {
    this.header = header;
  }

  @Override
  public String header() {
    return header;
  }
}
