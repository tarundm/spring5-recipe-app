package guru.springframework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith test the Spring context loading. It take time. This is basically Integration test
// not unit test
@RunWith(SpringRunner.class)
@SpringBootTest // This searches for SB App-n for running test - i.e. main class
public class Spring5RecipeAppApplicationTests {

	@Test
	public void contextLoads() {
		// This is default test provided by Spring application
	}

}
