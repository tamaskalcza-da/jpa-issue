package example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WrapperTest {
  private static EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;

  @BeforeAll
  static void beforeAll() {
    entityManagerFactory = Persistence.createEntityManagerFactory(
      "demo",
      Map.of(
        "jakarta.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/demo",
        "jakarta.persistence.jdbc.user", "demoAdmin",
        "jakarta.persistence.jdbc.password", "demoPassword",
        "jakarta.persistence.schema-generation.database.action", "drop-and-create"
      )
    );
  }

  @BeforeEach
  void setUp() {
    entityManager = entityManagerFactory.createEntityManager();
  }

  @AfterEach
  void tearDown() {
    entityManager.close();
  }

  @AfterAll
  static void afterAll() {
    entityManagerFactory.close();
  }

  @Test
  @Order(1)
  void save_wrapper() {
    var wrapper = Wrapper.create(1L, Tuple2.create(42L, "Hello, World!"));
    var transaction = entityManager.getTransaction();
    transaction.begin();
    entityManager.persist(wrapper);
    transaction.commit();
  }

  @Test
  @Order(2)
  void read_wrapper() {
    var wrapper = entityManager.find(Wrapper.class, 1L);
    assertThat(wrapper)
      .usingRecursiveComparison()
      .isEqualTo(Wrapper.create(1L, Tuple2.create(42L, "Hello, World!")));
  }

  @Test
  @Order(3)
  void query_wrapper() {
    var wrapper = entityManager
      .createQuery("select x from Wrapper x where x.value = :value", Wrapper.class)
      .setParameter("value", Tuple2.create(42L, "Hello, World!"))
      .getSingleResult();
    assertThat(wrapper)
      .usingRecursiveComparison()
      .isEqualTo(Wrapper.create(1L, Tuple2.create(42L, "Hello, World!")));
  }
}
