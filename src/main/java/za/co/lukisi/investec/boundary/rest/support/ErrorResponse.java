package za.co.lukisi.investec.boundary.rest.support;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import java.util.List;

/**
 * Error response POJO.
 */
@Builder
@Data
public class ErrorResponse {

  private String request;
  @JsonProperty(value = "messages")
  private List<String> errorMessages;


}
