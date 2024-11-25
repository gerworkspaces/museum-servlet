package net.blwsmartware.museum.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorResponse {
    UNKNOWN_EXCEPTION(9001, "Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED_ERROR(9002, "Uncategorized error!", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_KEY(9003, "Uncategorized key!", HttpStatus.BAD_REQUEST),

    MANY_DATA_INVALID(8001, "Many data invalid!", HttpStatus.BAD_REQUEST),
    UNIQUE_EXISTED(8002, "Data of unique field already exists!", HttpStatus.CONFLICT),
    NAME_EXISTED(8003, "Name already exists!", HttpStatus.CONFLICT),
    QUERY_KEY_INVALID(8004, "Your ID in the request has conflicted!", HttpStatus.CONFLICT),

    ROLE_NOT_EXISTED(3200, "Role not found in database!", HttpStatus.NOT_FOUND),
    ROLE_EXISTED(3201, "Name of role already exists!", HttpStatus.CONFLICT),


    PERMISSION_NOT_EXISTED(3210, "Permission not found in database!", HttpStatus.NOT_FOUND),
    PERMISSION_EXISTED(3211, "Name of permission already exists!", HttpStatus.CONFLICT),

    EMAIL_EXISTED(3011, "Email already exists!", HttpStatus.CONFLICT),
    EMAIL_INVALID(3012, "Invalid email!", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_NULL(3011, "Email cannot be null!", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_NULL(3041, "Username cannot be null!", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(3042, "Invalid username!", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(3043, "Username already exists!", HttpStatus.CONFLICT),

    USER_NOT_FOUND(3004, "User not found!", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(3994, "Category not found!", HttpStatus.NOT_FOUND),
    MUSEUM_NOT_FOUND(3984, "Museum not found!", HttpStatus.NOT_FOUND),
    POST_NOT_FOUND(3974, "Post not found!", HttpStatus.NOT_FOUND),
    TYPE_NOT_FOUND(3964, "Type of post not found!", HttpStatus.NOT_FOUND),

    USER_NOT_LOGIN(1001, "Incorrect password!", HttpStatus.BAD_REQUEST),

    PASSWORD_MUST_8_DIGITS(2008, "Password must be at least 8 characters!", HttpStatus.BAD_REQUEST),
    PASSWORD_MUST_6_DIGITS(2006, "Password must be at least 6 characters!", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_NULL(2006, "Password cannot be null!", HttpStatus.BAD_REQUEST),
    NAME_NOT_NULL(3002, "Name cannot be null!", HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT(3003, "Incorrect password!", HttpStatus.BAD_REQUEST),

    JWT_REFRESH_INVALID(7009, "Invalid token. Request requires a refresh token!", HttpStatus.FORBIDDEN),
    JWT_ACCESS_INVALID(7008, "Invalid token. Request requires a access token!", HttpStatus.FORBIDDEN),
    JWT_INVALID(7011, "Token is invalid!", HttpStatus.UNAUTHORIZED),
    JWT_EXPIRED(7012, "Token has expired!", HttpStatus.UNAUTHORIZED),
    JWT_NOT_NULL(7015, "Token cannot be null!", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(7030, "Access denied!", HttpStatus.FORBIDDEN),

    ;

    private final int code;
    private final String message;
    private final HttpStatusCode httpCode;

    ErrorResponse(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.httpCode = statusCode;
    }
}
