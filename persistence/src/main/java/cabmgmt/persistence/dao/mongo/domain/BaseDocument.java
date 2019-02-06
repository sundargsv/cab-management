package cabmgmt.persistence.dao.mongo.domain;

import java.io.Serializable;

//import com.mongo.client.java.repository.annotation.Id;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class BaseDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

}
