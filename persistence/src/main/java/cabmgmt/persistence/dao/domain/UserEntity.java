package cabmgmt.persistence.dao.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class UserEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String phoneCountryCode;

	private String phoneNumber;

	private String firstName;

	private String lastName;

	// user can able to enable/ disable most of the push notifications
	private boolean notify;

	private String userEmail;

	private boolean active;

	//Firebase cloud messaging instanceId only modifiable via special API call
	private String fcmToken;

    //will be null or empty when used by createUser API
    private String imageUrl;
}
