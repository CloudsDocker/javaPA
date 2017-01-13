package todzhang;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> items=Stream.of("a","n","z").collect(Collectors.toList());
		System.out.println(items);
	}

}
