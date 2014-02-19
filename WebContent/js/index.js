$(function() {
	var map = $.getMap();
	$.plotUsersOnMap(map);
});

(function($) {
	$.getMap = function() {
		var mapOptions = {
				zoom: 15,
				mapTypeId: google.maps.MapTypeId.ROADMAP,
				center: new google.maps.LatLng(40.099876, -88.227857)
		};
			
		return new google.maps.Map($("#map").get(0), mapOptions);
	};
	
	$.plotUsersOnMap = function(map) {
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(40.099876, -88.227857),
			map: map,
			title: "ababan2",
			animation: google.maps.Animation.BOUNCE
		});
		
		var content = "<div>" + marker.getTitle() + " needs help! <br /> Current Location: " + 
							marker.getPosition().toString() + 
					  "</div>";
		
		$.attachInfo(map, content, marker);
	};
	
	$.attachInfo = function(map, content, marker) {
		
		var infoWindow = new google.maps.InfoWindow({
			maxWidth: 400,
			disableAutoPan: false,
			content: content
		});
		
		google.maps.event.addListener(marker, "click", function(e) {
			infoWindow.open(map, marker);
		});
	};
})(jQuery);

