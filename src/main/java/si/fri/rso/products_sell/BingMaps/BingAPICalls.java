package si.fri.rso.products_sell.BingMaps;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@RequestScoped
public class BingAPICalls {

    @Inject
    BingConfig bingConfig;

    @Inject
    LocationAPI locationAPI;

    @Inject
    DistanceMatrixAPI distanceMatrixAPI;

    public List<Double> getBingLocation(String query) {
        Location l = locationAPI.getLonAndLat(query,
                1,
                bingConfig.getApikey());

        if(l.getResourceSets().get(0).getResources().size() == 0)
            throw new NotFoundException();

        return l.getResourceSets().get(0).getResources().get(0).getPoint().getCoordinates();
    }


    public Double getBingDistance(String origin, String destination) {
        if(origin == null || destination == null
        || origin.isEmpty() || destination.isEmpty())
            throw new BadRequestException();

        List<Double> originCoordinates = getBingLocation(origin);
        List<Double> destinationCoordinates = getBingLocation(destination);

        String origins = originCoordinates.get(0).toString() + "," + originCoordinates.get(1).toString();
        String destinations = destinationCoordinates.get(0).toString() + "," + destinationCoordinates.get(1).toString();

        DistanceMatrix distanceMatrix = distanceMatrixAPI
                    .getDistance(origins, destinations, bingConfig.getApikey(), "driving");

        Double travelDistance = distanceMatrix.getResourceSets().get(0).getResources().get(0).getResults().get(0).getTravelDistance();

        return travelDistance;
    }
}
