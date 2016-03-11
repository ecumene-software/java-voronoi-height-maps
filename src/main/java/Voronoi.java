public class Voronoi {
    public VoronoiPoint[] points;

    public Voronoi(VoronoiPoint ... points){
        this.points = points;
    }

    public VoronoiPoint[] getPoints() {
        return points;
    }
}
