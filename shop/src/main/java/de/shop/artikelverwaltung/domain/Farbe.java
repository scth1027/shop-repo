package de.shop.artikelverwaltung.domain;

public enum Farbe {
	  SCHWARZ("S"),
      ROT("R"),
      WEISS("W"),
      BLAU("B"),
      GELB("G");

      private String internal;
      
      private Farbe(String internal) {
              this.internal = internal;
      }
      
      public String getInternal() {
              return internal;
      }
      
      public static Farbe build(String internal) {
              if (internal == null) {
                      return null;
              }
              
              switch (internal) {
                      case "S":
                              return SCHWARZ;
                      case "R":
                              return ROT;
                      case "W":
                              return WEISS;
                      case "B":
                              return BLAU;
                      case "G":
                              return GELB;
                      default:
                              throw new IllegalArgumentException(internal + " ist kein gueltiger Wert fuer Farbe");
              }
      }
}
