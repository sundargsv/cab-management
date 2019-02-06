package cabmgmt.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import cabmgmt.app.annotation.CommonApi;

import cabmgmt.rest.domain.ResponseMessage;


/**
 *
 * @author Sundar Gsv
 *
 */
@RestController
@Slf4j
@RequestMapping(path="/cabmgmt_core")
@CommonApi
public class CabMgmtCoreApiRESTController extends AbstractRESTController {

	/************************************* Test APIs ************************************/


	/**
	 *
	 * @param
	 * @return
	 */
	@CrossOrigin(origins={"*"},
			methods={RequestMethod.GET},
			allowedHeaders={"origin","content-type","accept","authorization"})
	// Swagger UI
	@ApiOperation(value="Test Core API",
			notes="This is the Cab M test core api.",
			produces= MediaType.APPLICATION_JSON_VALUE,
			tags={"TEST"})
	@ApiResponses(value={
			@ApiResponse(code=200, message="ok", response=ResponseMessage.class),
			@ApiResponse(code=500, message="Unexpected internal error", response=ResponseMessage.class)
	})
	//REST mapping
	@GetMapping(path="/test/get",
			produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus
	@SuppressWarnings("rawtypes")
	public ResponseEntity getTestContent() {
		log.info("testing the core ... ");

		try {
			return ok("Test Successful");
		}
		catch(Exception e) {
			log.error("Error testing the core api ",e);
			return internalServerError(e.getMessage());
		}
	}


}
