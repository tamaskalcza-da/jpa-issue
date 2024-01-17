package example;

@SuppressWarnings("unused")
public class Tuple2<T1, T2> {
  private T1 _1;
  private T2 _2;

  public static <T1, T2> Tuple2<T1, T2> create(T1 _1, T2 _2) {
    var instance = new Tuple2<T1, T2>();
    instance._1 = _1;
    instance._2 = _2;
    return instance;
  }

  public T1 get_1() {
    return _1;
  }

  public void set_1(T1 _1) {
    this._1 = _1;
  }

  public T2 get_2() {
    return _2;
  }

  public void set_2(T2 _2) {
    this._2 = _2;
  }
}
