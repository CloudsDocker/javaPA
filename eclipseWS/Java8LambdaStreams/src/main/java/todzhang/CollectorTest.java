package todzhang;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CollectorTest {

	@Test
	public Map<Boolean, List<Artist>> testCollector(Stream<Artist> artists){
		return artists.collect(Collectors.partitioningBy(artist->artist.isSolo()));
	}
	
	@Test
	public Map<Boolean, List<Artist>> testCollectorv2(Stream<Artist> artists){
		return artists.collect(Collectors.partitioningBy(Artist::isSolo));
	}
}
