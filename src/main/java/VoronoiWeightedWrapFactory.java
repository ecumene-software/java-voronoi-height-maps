import org.joml.Vector2f;
import org.joml.Vector2i;

public class VoronoiWeightedWrapFactory {
    public static HeightMap wrapHit(int sizex, int sizey, boolean denomalize, VoronoiPoint[] points){
        int sizeWrapX = sizex * 3;
        int sizeWrapY = sizey * 3;

        if(denomalize) {
            for (VoronoiPoint point : points) {
                point.getPoint().mul(sizex);
            }
        }
        VoronoiPoint[] pointsWrapped = new VoronoiPoint[points.length * 3 * 3];
        int pointID = 0;
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++) {
                for (VoronoiPoint point : points) {
                    VoronoiPoint newPoint = new VoronoiPoint(point);
                    newPoint.getPoint().add(new Vector2f(sizex * x, sizex * y));
                    pointsWrapped[pointID] = newPoint;
                    pointID++;
                }
            }
        }
        VoronoiWeighted voronoi = new VoronoiWeighted(new Vector2i(sizeWrapX, sizeWrapY), false, pointsWrapped);
        return voronoi.getHit().crop(sizex, sizey, sizeWrapX - sizex, sizeWrapY - sizey);
    }

    public static HeightMap wrapDistance(int sizex, int sizey, boolean denomalize, VoronoiPoint[] points){
        int sizeWrapX = sizex * 3;
        int sizeWrapY = sizey * 3;

        if(denomalize) {
            for (VoronoiPoint point : points) {
                point.getPoint().mul(sizex);
            }
        }
        VoronoiPoint[] pointsWrapped = new VoronoiPoint[points.length * 3 * 3];
        int pointID = 0;
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++) {
                for (VoronoiPoint point : points) {
                    VoronoiPoint newPoint = new VoronoiPoint(point);
                    newPoint.getPoint().add(new Vector2f(sizex * x, sizex * y));
                    pointsWrapped[pointID] = newPoint;
                    pointID++;
                }
            }
        }
        VoronoiWeighted voronoi = new VoronoiWeighted(new Vector2i(sizeWrapX, sizeWrapY), false, pointsWrapped);
        return voronoi.getDistance().crop(sizex, sizey, sizeWrapX - sizex, sizeWrapY - sizey);
    }
}
