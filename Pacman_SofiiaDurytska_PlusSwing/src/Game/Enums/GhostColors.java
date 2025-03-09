package Game.Enums;

public enum GhostColors {
    BLUE("blue"),
    ORANGE("orange"),
    PINK("pink"),
    RED("red");

    private final String color;

    GhostColors(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
