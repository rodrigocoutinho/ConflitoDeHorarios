package br.ufg.inf.model;

public enum CollisionLevel {
    TOTAL("total"),
    PARCIAL("parcial");

    private String description;

    CollisionLevel(String description) {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
