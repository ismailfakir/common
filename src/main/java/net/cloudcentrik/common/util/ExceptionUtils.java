package net.cloudcentrik.common.util;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionUtils {
    public static <T, E extends Exception> Consumer<T>
    tryCatchWrapper(Consumer<T> consumer, Class<E> clazz) {

        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception ex) {
                try {
                    E exCast = clazz.cast(ex);
                    System.err.println(
                            "Exception occured : " + exCast.getMessage());
                } catch (ClassCastException ccEx) {
                    throw ex;
                }
            }
        };
    }

    public static <T> Supplier<T> uncheck(Supplier<T> unchecked) {
        return () -> {
            try {
                return unchecked.get();
            } catch (Exception exception) {
                throw exception;
            }
        };
    }

    public static <T> Supplier<T> unchecked(Callable<T> f) {
        return () -> {
            try {
                return f.call();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            catch (Throwable t) {
                throw t;
            }
        };
    }
}
