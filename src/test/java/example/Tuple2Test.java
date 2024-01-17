package example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Tuple2Test {
  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  void json_conversion() throws Exception {
    var tuple = Tuple2.create(42L, "Hello, World!");

    var json = mapper.writeValueAsString(tuple);
    assertThat(json).isEqualTo("{\"_1\":42,\"_2\":\"Hello, World!\"}");

    var deserializedTuple = mapper.readValue(json, new TypeReference<Tuple2<Long, String>>() {
    });
    assertThat(deserializedTuple).usingRecursiveComparison().isEqualTo(tuple);
  }
}
