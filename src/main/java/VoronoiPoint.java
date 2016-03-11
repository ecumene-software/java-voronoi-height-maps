import org.joml.Vector2f;

public class VoronoiPoint {
    public float    weight;
    public Vector2f point;

    public VoronoiPoint(VoronoiPoint clone){
        this(clone.getWeight(), new Vector2f(clone.getPoint()));
    }

    public VoronoiPoint(float weight, Vector2f point){
        this.weight = weight;
        this.point  = point;
    }

    public VoronoiPoint(float weight, float x, float y){
        this(weight, new Vector2f(x, y));
    }

    public VoronoiPoint(float ... data){
        this(data[0], data[1], data[2]);
    }

    public float getWeight() {
        return weight;
    }

    public Vector2f getPoint() {
        return point;
    }

    public static VoronoiPoint[] parse(float ... data){
        VoronoiPoint[] out = new VoronoiPoint[data.length / 3];
        for(int pI = 0; pI < (data.length / 3); pI++)
            out[pI] = new VoronoiPoint(data[pI * 3 + 0],
                                       data[pI * 3 + 1],
                                       data[pI * 3 + 2]);
        return out;
    }

}
