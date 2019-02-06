package cabmgmt.persistence.dao.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String id;

}
