package tfar.finitewater.config;

public class Config {
    private boolean isWaterWhitelist = true;
    private boolean bottlesRemoveWaterSource = true;

    public Config() {
    }

    public boolean isWaterWhitelist() {
        return isWaterWhitelist;
    }
    public boolean bottlesRemoveWaterSource() { return bottlesRemoveWaterSource; }
}
