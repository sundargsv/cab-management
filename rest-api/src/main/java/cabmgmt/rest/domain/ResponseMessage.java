package cabmgmt.rest.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseMessage {
	@ApiModelProperty(value="response message", required=true)
	private String message;
}
