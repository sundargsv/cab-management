package cabmgmt.rest.domain.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRecord {

	@ApiModelProperty(value="Id of user record")
	private String id;

	@ApiModelProperty(value="Phone country code. Used as identity")
	private String phoneCountryCode;

	@ApiModelProperty(value="Phone number. Used as identity", required= true)
	private String phoneNumber;

	@ApiModelProperty(value="First name")
	private String firstName;

	@ApiModelProperty(value="Last name")
	private String lastName;

	@ApiModelProperty(value="Push Notification indicator. When enabled, the user gets the fcm push notification", required= true)
	private boolean notify;

	@ApiModelProperty(value="User email address")
	private String userEmail;

	@ApiModelProperty(value="user image URL uploaded to cloud storage")
	private String imageUrl;

}
