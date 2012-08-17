package episode_11_12_13;

public enum BlockType {

    STONE("res/images/stone.png"), AIR("res/images/air.png"), GRASS("res/images/grass.png"), DIRT("res/images/dirt.png");
    public final String location;

    BlockType(String location) {
        this.location = location;
    }
}