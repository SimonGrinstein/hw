package ait.cohort49.shop_49.exceptionHandling;

import java.util.List;
import java.util.Objects;

public class ResponseList {

    private List<String> message;

    public ResponseList(List<String> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseList{" +
                "message=" + message +
                '}';
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof ResponseList that)) return false;

        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }
}
