package tech.sneakybuzz.artifact.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
  public int status;
  public String message;
  public Object data;
  public boolean success;

  public static <T> ApiResponse<T> success(int status,T data, String message) {
    return ApiResponse.<T>builder()
        .status(status)
        .success(true)
        .message(message)
        .data(data)
        .build();
  }
}
