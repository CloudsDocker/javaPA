package todzhang;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalTest {

	@Test
	public void testOptional(){
		Optional<String> optA=Optional.of("a");
		Assert.assertEquals("a", optA.get());
	}
	
	@Test
	public void testEmpty(){
		Optional emp=Optional.ofNullable(null);
		Assert.assertEquals(Optional.empty(), emp);
		Assert.assertFalse(emp.isPresent());
		Assert.assertEquals("b", emp.orElse("b"));
		Assert.assertEquals("c", emp.orElseGet(()->"c"));
	}
}
