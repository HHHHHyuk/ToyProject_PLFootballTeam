package study.jpaProject.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

public class ApiUtils {

    public static <T> ApiResult<T> succes(T response){
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(Throwable throwable, HttpStatus status){
        return new ApiResult<>(false, null, new ApiError(throwable, status));
    }

    public static ApiResult<?> error(String message, HttpStatus status){
        return new ApiResult<>(false, null, new ApiError(message, status));
    }

    @RequiredArgsConstructor
    @Getter @ToString
    public static class ApiError {
        private final String message;
        private final int status;

        ApiError(Throwable throwable, HttpStatus status){
            this(throwable.getMessage(), status);
        }
        ApiError(String message, HttpStatus status){
            this(message, status.value());
        }
    }

    @RequiredArgsConstructor
    @Getter @ToString
    public static class ApiResult<T> {
        private final boolean success;
        private final T response;
        private final ApiError error;
    }


}
