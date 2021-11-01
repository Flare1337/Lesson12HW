import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Supplier;

public class Optional <T> {
    private T data;

    private Optional() {

    }

    private Optional(T data) {
        this.data = data;
    }

    public boolean isPresent() {
        boolean result = false;
        if (data != null) {
            result = true;
        }
        return result;
    }

    public T get() {
        if (data != null) {
            return data;
        }
        else {
            throw new NoSuchElementException();
        }
    }

    public static <T> Optional<T> of(T value) {

            if (value == null) {
                throw new NullPointerException();
            }
            return new Optional<T>(value);
    }

    public static <T> Optional<T> ofNullable(T value) {
        return new Optional<T>(value);
    }

    public static <T> Optional<T> empty() {
        return new Optional<T>();
    }

    public T orElse(T other) {
        if (isPresent()) {
            return data;
        }
        return other;
    }

    public boolean equals(Object obj) {
        if (obj == this) {

        }

        if (!(obj instanceof Optional)) {

        }

        Object other = obj;

        return Objects.equals(obj, this);
    }

    public int hashCode() {
        if (isPresent()) {
            return data.hashCode();
        }
        return 0;
    }
}
