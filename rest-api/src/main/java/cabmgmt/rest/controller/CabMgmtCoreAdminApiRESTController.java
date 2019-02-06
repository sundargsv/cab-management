package cabmgmt.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cabmgmt.app.annotation.AdminApi;
import cabmgmt.feature.admin.AdminFeatureService;
import cabmgmt.persistence.dao.domain.UserEntity;
import cabmgmt.rest.domain.ResponseMessage;
import cabmgmt.rest.domain.request.UserRecord;
import cabmgmt.rest.domain.request.DisbaleUserRequest;
import cabmgmt.rest.util.UserRecordDTOMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Cab Mgmt Core Admin API
 * @author Sundar Gsv
 *
 */
@RestController
@Slf4j
@RequestMapping(path="/cabmgmt_core/admin")
@AdminApi
public class CabMgmtCoreAdminApiRESTController extends AbstractRESTController{

	@Autowired
	private AdminFeatureService adminService;

	/**
	 *
	 * @param requestMsg
	 * @return
	 */
	@CrossOrigin(origins={"*"},
		     methods={RequestMethod.POST},
		     allowedHeaders={"origin","content-type","accept","authorization"})
	// Swagger UI
	@ApiOperation(value="Create User",
				  notes="This is the Cab Mgmt User record creation service.",
				  consumes= MediaType.APPLICATION_JSON_VALUE,
				  produces= MediaType.APPLICATION_JSON_VALUE,
				  tags={"User"})
	@ApiResponses(value={
					@ApiResponse(code=200, message="ok", response=UserRecord.class),
					@ApiResponse(code=500, message="Unexpected internal error", response=ResponseMessage.class)
				})
	//REST mapping
	@PostMapping(path="/users",
 				 consumes={MediaType.APPLICATION_JSON_VALUE},
				 produces={MediaType.APPLICATION_JSON_VALUE})

	@ResponseStatus
	@SuppressWarnings("rawtypes")
	public ResponseEntity createUser(@RequestBody UserRecord requestMsg) {
		log.info("creating user record...");

		try {
			//1. convert REST domain DTO to persistence domain DTO
			UserEntity userEntity = UserRecordDTOMapper.toUserEntity(requestMsg);

			//2. invoke feature layer to create
			userEntity = adminService.createUser(userEntity);

			//3. convert persistence domain DTO back to REST domain DTO
			UserRecord result = UserRecordDTOMapper.toUserRecord(userEntity);

			//4. format response
			return ok(result);
		}
		catch(Exception e) {
			log.error("error in creating user" , e);
			return internalServerError(e.getMessage());
		}
	}

	@CrossOrigin(origins={"*"},
		     methods={RequestMethod.PUT},
		     allowedHeaders={"origin","content-type","accept","authorization"})
	// Swagger UI
	@ApiOperation(value="Update User",
				  notes="This is the Cab Mgmt User record update service.",
				  consumes= MediaType.APPLICATION_JSON_VALUE,
				  produces= MediaType.APPLICATION_JSON_VALUE,
				  tags={"User"})
	@ApiResponses(value={
					@ApiResponse(code=200, message="ok", response=ResponseMessage.class),
					@ApiResponse(code=500, message="Unexpected internal error", response=ResponseMessage.class)
				})
	//REST mapping
	@PutMapping(path="/users",
				 consumes={MediaType.APPLICATION_JSON_VALUE},
				 produces={MediaType.APPLICATION_JSON_VALUE})

	@ResponseStatus
	@SuppressWarnings("rawtypes")
	public ResponseEntity updateUser(@RequestBody UserRecord requestMsg) {
		log.info("updating user record...");

		try {
			//0. convert REST domain DTO to persistence domain DTO
			UserEntity userEntity = UserRecordDTOMapper.toUserEntity(requestMsg);

			//1. invoke feature layer to create
			userEntity = adminService.updateUser(userEntity);

			//2. convert persistence domain DTO back to REST domain DTO
			UserRecord result = UserRecordDTOMapper.toUserRecord(userEntity);

			//3. format response
			return ok(result);
		}
		catch(Exception e) {
			log.error("error in updating user" , e);
			return internalServerError(e.getMessage());
		}
	}

	/**
	 *
	 * @param userPhoneNumber
	 * @return
	 */
	@CrossOrigin(origins={"*"},
		     methods={RequestMethod.GET},
		     allowedHeaders={"origin","content-type","accept","authorization"})
	// Swagger UI
	@ApiOperation(value="Get User by Phone Number",
				  notes="This is the Cab Mgmt User record retrieval by phone number service.",
				  produces= MediaType.APPLICATION_JSON_VALUE,
				  tags={"User"})
	@ApiResponses(value={
					@ApiResponse(code=200, message="ok", response=UserRecord.class),
					@ApiResponse(code=500, message="Unexpected internal error", response=ResponseMessage.class)
				})
	//REST mapping
	@GetMapping(path="/users",
				produces={MediaType.APPLICATION_JSON_VALUE})

	@ResponseStatus
	@SuppressWarnings("rawtypes")
	public ResponseEntity getUserByPhoneNumber(@ApiParam(value="user phone country code")
	   										   @RequestParam(required=false)
	   										   String userCountryCode,
											   @ApiParam(value="user phone number", required=true)
	 										   @RequestParam
	 										   String userPhoneNumber) {
		log.info("retrieve user record...");

		try {

			//0. invoke feature layer to find user by phone number
			Optional<UserEntity> userOpt = adminService.getUserByPhoneNumber(userCountryCode,
																			 userPhoneNumber);

			//1. check if present
			if(!userOpt.isPresent()) {
				return notFoundError("User not found");
			}

			//2. convert persistence domain DTO back to REST domain DTO
			UserRecord result = UserRecordDTOMapper.toUserRecord(userOpt.get());

			//3. format response
			return ok(result);
		}
		catch(Exception e) {
			log.error("error in retrieving user" , e);
			return internalServerError(e.getMessage());
		}
	}


	/**
	 *
	 * @param requestMsg
	 * @return
	 */
	@CrossOrigin(origins={"*"},
		     methods={RequestMethod.POST},
		     allowedHeaders={"origin","content-type","accept","authorization"})
	// Swagger UI
	@ApiOperation(value="Disable User",
				  notes="This is the Cab Mgmt User record disable service.",
				  consumes= MediaType.APPLICATION_JSON_VALUE,
				  produces= MediaType.APPLICATION_JSON_VALUE,
				  tags={"User"})
	@ApiResponses(value={
					@ApiResponse(code=200, message="ok", response=ResponseMessage.class),
					@ApiResponse(code=500, message="Unexpected internal error", response=ResponseMessage.class)
				})
	//REST mapping
	@PostMapping(path="/users/disable",
				 consumes={MediaType.APPLICATION_JSON_VALUE},
				 produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus
	@SuppressWarnings("rawtypes")
	public ResponseEntity disableUser(@RequestBody DisbaleUserRequest requestMsg) {
		log.info("Disabling user record ...");

		try {
			//0. check at least 1 of the parameter has value
			if(requestMsg.getId()==null && requestMsg.getPhoneNumber()==null) {
				return badRequestError("Either id or phoneNumber attribute must have value");
			}
			else if(requestMsg.getId()!=null && requestMsg.getPhoneNumber()!=null) {
				return badRequestError("Either id or phoneNumber attribute can have value");
			}
			//1. disable user
			if(requestMsg.getId()!=null) {
				adminService.disableUserById(requestMsg.getId());
			}
			else {
				adminService.disableUserByPhoneNumber(requestMsg.getPhoneCountryCode(),
													  requestMsg.getPhoneNumber());
			}

			//2. format response
			return ok("Disable successful");
		}
		catch(Exception e) {
			log.error("error in disabling user" , e);
			return internalServerError(e.getMessage());
		}
	}
}
