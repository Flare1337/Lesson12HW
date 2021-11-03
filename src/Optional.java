package src;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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
            return true;
        }
        
        if (!(obj instanceof Optional)) {
            return false;
        }

        Optional <T> other = (Optional <T>) obj;

        return Objects.equals(other, this);
    }

    public int hashCode() {
        if (isPresent()) {
            return data.hashCode();
        }
        return 0;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (isPresent() && consumer != null) {
            consumer.accept(data);
        }
        if (isPresent() && consumer == null) {
            throw new NullPointerException();
        }
    }

    public T orElseGet(Supplier<? extends  T> other) {
        if (isPresent()) {
            return data;
        }
        else if (other != null) {
            return other.get();
        }
        else {
            throw new NullPointerException();
        }
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (isPresent()) {
            return data;
        }
        else if (exceptionSupplier != null) {
            throw exceptionSupplier.get();
        }
        else {
            throw new NullPointerException();
        }
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        if (isPresent() && predicate.test(data)) {
            return new Optional<>(data);
        }
        else {
            return new Optional<>();
        }
    }

    public <U> Optional<U> map(Function<? super T,? extends U> mapper) {
        if (mapper == null) {
            throw new NullPointerException();
        }
        if (isPresent()) {
            U result = mapper.apply(data);
            if (result != null) {
                return new <U> Optional<U>(result);
            }
        }
            return new Optional<>();
    }

    public <U> Optional<U> flatMap(Function<? super T,Optional<U>> mapper) {
        if (mapper == null) {
            throw new NullPointerException();
        }
        if (isPresent()) {
            Optional<U> result = mapper.apply(data);
            if (result == null) {
                throw new NullPointerException();
            }
            else {
                return new <U> Optional<U>(result.data);
            }
        }
        else {
            return new Optional<>();
        }
    }
}
