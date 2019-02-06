package cabmgmt.persistence.dao.mongo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper=true)
@Document(collection = "users")
public class UserDocument extends BaseDocument {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	//composite user unique identifier
	private String phoneCountryCode;
	//composite user unique identifier
	private String phoneNumber;

	private String firstName;

	private String lastName;

	// user can able to enable/ disable most of the push notifications
	private boolean notify;

	private String userEmail;

	//indicate user is still active
	private boolean active;

	//Firebase Cloud token used for PUSH notification to user's device
	private String fcmToken;

	//will be null or empty when used by createUser API
	private String imageUrl;
}
