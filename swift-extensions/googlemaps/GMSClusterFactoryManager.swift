import GoogleMaps
import GoogleMapsUtils
import TRIKOT_FRAMEWORK_NAME

public protocol GMSClusterFactory {
    func createClusterManager(mapView: GMSMapView) -> GMUClusterManager
    func createMarker(marker: MapViewModelMarker) -> GMSMarker
}

class GMSDefaultClusterFactory: GMSClusterFactory {
    func createClusterManager(mapView: GMSMapView) -> GMUClusterManager {
        let renderer = GMUDefaultClusterRenderer(
            mapView: mapView,
            clusterIconGenerator: GMUDefaultClusterIconGenerator()
        )

        renderer.animatesClusters = true

        return GMUClusterManager(map: mapView, algorithm: GMUNonHierarchicalDistanceBasedAlgorithm(), renderer: renderer)
    }

    func createMarker(marker: MapViewModelMarker) -> GMSMarker {
        let gmsMarker = GMSMarker()
        gmsMarker.title = marker.label
        gmsMarker.userData = marker
        gmsMarker.position = marker.position.asCoordinate()
        gmsMarker.appearAnimation = GMSMarkerAnimation.pop

        return gmsMarker
    }
}

public class GMSClusterFactoryManager {
    public static var shared: GMSClusterFactory = GMSDefaultClusterFactory()
}
