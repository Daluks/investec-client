package za.co.lukisi.investec.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Schema(name = "Client")
@Data
@Builder
@AllArgsConstructor
public class Client implements Serializable {
    @JsonProperty(required = true)
    @Schema(required = true, description = "First name")
    @NotEmpty
    private String firstName;
    @NotEmpty
    @Schema(required = true, description = "Last name")
    @JsonProperty(required = true)

    private String lastName;
    @NotEmpty
    @Schema(required = true, description = "ID Number")
    @JsonProperty(required = true)
    private String idNumber;

    private String physicalAddress;
    private String mobileNumber;

    public Client(){}
}
