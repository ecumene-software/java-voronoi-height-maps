import ecumene.exo.sim.util.heightmap.channel.HeightChannel;
import org.joml.Vector2f;

public class Voronoi {
    public VoronoiPoint[] points;

    public Voronoi(VoronoiPoint ... points){
        this.points = points;
    }

    public VoronoiPoint[] getPoints() {
        return points;
    }
}
