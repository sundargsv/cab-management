package cabmgmt.rest.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DisbaleUserRequest {

	@ApiModelProperty(value="user record id")
	private String id;
	
	@ApiModelProperty(value="user phone country code")
	private String phoneCountryCode;
	
	@ApiModelProperty(value="user phone number")
	private String phoneNumber;
}
