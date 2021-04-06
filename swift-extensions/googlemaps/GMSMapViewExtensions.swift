import Foundation
import GoogleMaps
import GoogleMapsUtils
import TRIKOT_FRAMEWORK_NAME
import Trikot_streams
import Trikot_viewmodels

extension GMSMapView {

    public var mapViewModel: MapViewModel? {
        get { trikotViewModel() }
        set(value) {
            viewModel = value
            bindTo(mapViewModel)
        }
    }

    private func bindTo(_ mapViewModel: MapViewModel?) {
        unsubscribeFromAllPublisher()
        guard let mapViewModel = mapViewModel else { return }

        setMinZoom(mapViewModel.minimumZoomLevel, maxZoom: maxZoom)

        let clusterManager = setupClusterManager()

        observe(mapViewModel.isInteractive) {[weak self] (interactive: Bool) in
            self?.isUserInteractionEnabled = interactive
        }

        observe(mapViewModel.shouldShowUserMarker) {[weak self] (shouldShowUserMarker: Bool) in
            self?.isMyLocationEnabled = shouldShowUserMarker
        }

        observe(mapViewModel.camera) {[weak self] (camera: MapViewModelCamera) in
            guard let self = self, camera != MapViewModelCamera.Companion().none, !camera.targets.isEmpty else { return }

            let coordinates = camera.targets.map { $0.asCoordinate() }

            if coordinates.count == 1 {
                self.focusOnSingleLocation(coordinates.first!, zoomLevel: camera.zoom as? Float)
            } else {
                self.focusOnBoundingBox(coordinates)
            }
        }

        observe(mapViewModel.markers) { (markers: [MapViewModelMarker]) in
            let items = markers.map { marker in
                GMSClusterFactoryManager.shared.createMarker(marker: marker)
            }

            clusterManager.clearItems()
            clusterManager.add(items)
            clusterManager.cluster()
        }
    }

    private func setupClusterManager() -> GMUClusterManager {
        let clusterManager = GMSClusterFactoryManager.shared.createClusterManager(mapView: self)
        clusterManager.setMapDelegate(self)
        return clusterManager
    }

    private func focusOnSingleLocation(_ coordinate: CLLocationCoordinate2D,
                                       duration: CFTimeInterval = 1,
                                       zoomLevel: Float? = nil) {
        animate(duration: duration, timingFunctionName: .easeInEaseOut) {
            animate(to: GMSCameraPosition.camera(withTarget: coordinate,
                                                 zoom: zoomLevel ?? self.camera.zoom))
        }
    }

    private func focusOnBoundingBox(_ coordinates: [CLLocationCoordinate2D],
                                    duration: CFTimeInterval = 1,
                                    padding: CGFloat = 100) {
        let coordinateBounds = calculateItemsBounds(coordinates)

        animate(duration: duration, timingFunctionName: .easeInEaseOut) {
            animate(with: GMSCameraUpdate.fit(coordinateBounds, withPadding: padding))
        }
    }

    private func calculateItemsBounds(_ coordinates: [CLLocationCoordinate2D]) -> GMSCoordinateBounds {
        coordinates.reduce(GMSCoordinateBounds()) { bounds, coordinate in
            bounds.includingCoordinate(coordinate)
        }
    }

    private func animate(duration: CFTimeInterval = 1,
                         timingFunctionName: CAMediaTimingFunctionName = .easeInEaseOut,
                         _ work: () -> Void) {
        CATransaction.begin()
        CATransaction.setAnimationDuration(duration)
        CATransaction.setAnimationTimingFunction(CAMediaTimingFunction(name: timingFunctionName))
        work()
        CATransaction.commit()
    }
}

extension MapViewModelPosition {
    public func asCoordinate() -> CLLocationCoordinate2D {
        CLLocationCoordinate2D(
            latitude: latitude,
            longitude: longitude
        )
    }
}

// MARK: - GMSMapViewDelegate

extension GMSMapView: GMSMapViewDelegate {
    public func mapView(_ mapView: GMSMapView, didTap marker: GMSMarker) -> Bool {
        if let cluster = marker.userData as? GMUCluster {
            focusOnBoundingBox(cluster.items.map { $0.position }, duration: 0.5)
            return true
        } else if let marker = marker.userData as? MapViewModelMarker {
            marker.onSelect()
            return true
        }

        return false
    }

    public func mapView(_ mapView: GMSMapView, didChange position: GMSCameraPosition) {
        let target = position.target
        let position = MapViewModelPosition(latitude: target.latitude, longitude: target.longitude)
        let zoom = KotlinFloat(float: mapView.camera.zoom)

        let camera = MapViewModelCamera(targets: [position], zoom: zoom)
        mapViewModel?.didChangeCamera(camera: camera)
    }
}
