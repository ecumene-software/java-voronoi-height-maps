public class HeightMap implements IHeightMap{
    public float[][] elevation;

    public HeightMap(){}

    public HeightMap(float[][] floats){
        this.elevation = floats;
    }

    public HeightMap(int width, int height){
        elevation = new float[width][height];
    }

    public HeightMap crop(int x, int y, int width, int height){
        // This is the one part I did not test!
        // Someone more intelligent than I should probably do so!
        // (Also that's the reason this isn't very well documented :/)
        int nWidth  = width  - x + 1;
        int nHeight = height - y + 1;
        float[][] newElevation = new float[nWidth][nHeight];
        for(int xi = x; xi <= height; xi++)
            System.arraycopy(newElevation[xi], y, newElevation[xi-x], 0, height);
        return new HeightMap(newElevation);
    }

    @Override
    public float[][] getElevation() {
        return elevation;
    }
}
