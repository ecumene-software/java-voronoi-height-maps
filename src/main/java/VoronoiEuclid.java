import org.joml.Vector2f;
import org.joml.Vector2i;

public class VoronoiEuclid extends Voronoi {
    private HeightMap pDistance; // Pixels of the distance to the closest point - the second closest (euclideon distance)
    private HeightMap pHit;      // Pixels of the weight values (mainly for debugging, a e s t h e t i c s, and indexing)

    private Vector2i dimension; // Dimensions

    public VoronoiEuclid(Vector2i size, boolean denormalizePoints, VoronoiPoint ... points){
        super(points);
        this.dimension = size;

        if(denormalizePoints)                 // If points are normalized, multiply them by size
            for(VoronoiPoint point : points){ // Itr and multiply
                point.getPoint().x *= size.x;
                point.getPoint().y *= size.y;
            }

        pDistance = new HeightMap(dimension.x, dimension.y);
        pHit      = new HeightMap(dimension.x, dimension.y);

        float pmax = 0;
        for(int x = 0; x < dimension.x; x++){          // For each x domain
            for(int y = 0; y < dimension.y; y++){      // For each y domain
                float closest       = Float.MAX_VALUE; // For each pixel locate the closest point
                float secondClosest = closest;         // For each pixel locate the second closest point
                float pixelWeight   = 0;               // The pixel's weight

                for(VoronoiPoint point : points) {     // All points are in heightmap / screen space
                    float distance = new Vector2f(x, y).distance(point.getPoint()); // Distance from pixel to point
                    if(distance < closest){                                         // Finding closest distance
                        pixelWeight = (point.getWeight() * 2) - 1;
                        secondClosest = closest;
                        closest       = distance;
                    } else if(distance < secondClosest)                             // Finding second closest distance
                        secondClosest = distance;
                }

                float d1 = (255 - closest);      // Constant - the closest value to the current pixel
                float d2 = (255 - secondClosest);// Constant - the second closest value to the current pixel
                float distance = (((d1 - d2 ) / 255)          // Distance from d1 to d2 divided by constant
                                                    * 2) - 1; // From (0,1) range to (-1,1) range
                if(distance > pmax) pmax = distance;          // Setting the largest pixel value (so far)
                pDistance.elevation[x][y] = distance;
                pHit     .elevation[x][y] = pixelWeight;
            }
        }

        // This gets rid of problems created by the whole "width, height
        // being very large values" thing by making sure that the final
        // value is in the range of (0,1)
        for(int x = 0; x < pDistance.elevation.length; x++){
            for(int y = 0; y < pDistance.elevation.length; y++){
                pDistance.elevation[x][y] /= pmax;
            }
        }
    }

    public HeightMap getDistance(){
        return pDistance;
    }

    public HeightMap getHit() {
        return pHit;
    }

    public Vector2i getDimensions() {
        return dimension;
    }
}
