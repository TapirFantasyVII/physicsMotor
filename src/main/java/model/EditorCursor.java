package model;

public class EditorCursor {

    private int worldX;
    private int worldY;

    private boolean visible;

    public EditorCursor() {
        worldX = 0;
        worldY = 0;
        visible = false;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void setPosition(int worldX, int worldY) {
        if (visible) {
            this.worldX = worldX;
            this.worldY = worldY;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void hide() {
        visible = false;
    }

    public void show() {
        visible = true;
    }

}
