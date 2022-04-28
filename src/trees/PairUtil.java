package trees;

import java.util.AbstractMap;
import java.util.Map;

public class PairUtil<T,U> {
    public static <T,U> Map.Entry<T,U> Of(T key, U value) {
        return new AbstractMap.SimpleEntry<T,U>(key, value);
    }
}
