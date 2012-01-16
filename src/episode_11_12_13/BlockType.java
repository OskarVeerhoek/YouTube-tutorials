package episode_11_12_13;

public enum BlockType {

    STONE("res/stone.png"), AIR("res/air.png"), GRASS("res/grass.png"), DIRT("res/dirt.png");
    public final String location;

    BlockType(String location) {
        this.location = location;
    }
}