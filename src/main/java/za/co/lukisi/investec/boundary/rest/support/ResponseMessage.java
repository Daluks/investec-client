package za.co.lukisi.investec.boundary.rest.support;

import lombok.Data;

/**
 * ResponseMessage POJO.
 */
@Data
public class ResponseMessage {

  private Long transactionId;
  private String request;
  private String message;
}
