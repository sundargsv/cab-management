package cabmgmt.persistence.dao.common;

import java.util.UUID;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * spatial UUID Generator
 * @author Sundar Gsv
 *
 */
public class UUIDGenerator {
	private static TimeBasedGenerator uuidGenerator = null;

	private UUIDGenerator(){
	}

	public static UUID getTimebaseUUIDGenerator(){

		if(uuidGenerator == null){
			//take the ethernet address of current host
			EthernetAddress ethernetAddress = EthernetAddress.fromInterface();
			uuidGenerator = Generators.timeBasedGenerator(ethernetAddress);
		}

		return uuidGenerator.generate();
	}
}
