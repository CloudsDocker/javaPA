package todzhang;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindLongTracks {

	// the first version, there are two forEach in the solutions.
	public Set<String> findLongTracks(List<Album> list){
		Set<String> trackNames=new HashSet<>();
		list.stream()
		.forEach(album->album.getTracks()
				.forEach(track->{
					if(track.getLength()>60){
						trackNames.add(track.getName());
					}
				}));
		return null;
	}
	
	public Set<String> findLongTracksByFilter(List<Album> list){
		Set<String> trackNames=new HashSet<>();
	  list.stream().forEach(album->{album.getTracks()
			.filter(track -> track.getLength()>60)
			.map(track->track.getName())
			.forEach(name->trackNames.add(name));
		});
		return trackNames;
	}
	
	// the 3rd version to further enhance the approach
	// to use more streamy way, i.e. flatMap, instead of forEach
	public Set<String> findlongTracksByMoreStreamyWay(List<Album> list){
		Set<String> trackNames=new HashSet<>();
		list.stream()
		.flatMap(album->album.getTracks())
		.filter(track->track.getLength()>60)
		.map(track->track.getName())
		.forEach(name->trackNames.add(name));
		return trackNames;
	}
	
	// the 4th version with further enhancement
	// first, no need to explicitly create a Set to return
	// this is the most concise approach
	public Set<String> findLongTracksWithStreamChain(List<Album> list){
		return list.stream()
				.flatMap(album->album.getTracks())
				.filter(track->track.getLength()>60)
				.map(track->track.getName())
				.collect(Collectors.toSet());
	}
	
	class Album{
		class Track{
			int length;
			String name;
			public int getLength() {
				return length;
			}
			public String getName() {
				return name;
			}
		}
		
		List<Track> tracks=new ArrayList<Track>();
		public Stream<Track> getTracks() {
			return tracks.stream();
		}
	}
}
