package example;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class Wrapper {
  @Id
  private Long id;
  @Type(JsonType.class)
  @Column(columnDefinition = "jsonb")
  private Tuple2<Long, String> value;

  protected Wrapper() {
  }

  private Wrapper(Long id, Tuple2<Long, String> value) {
    this.id = id;
    this.value = value;
  }

  public static Wrapper create(Long id, Tuple2<Long, String> value) {
    return new Wrapper(id, value);
  }
}
