package org.lafeuille.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestApplication.class)
class ApplicationTest {

  @Test
  void contextLoads() {}
}
