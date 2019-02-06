package cabmgmt.service.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		log.warn("REST call has error: {} - {}", response.getStatusCode(), response.getStatusText());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		HttpStatus.Series statusSeries = response.getStatusCode().series();
		
		//handle 4xx and 5xx error code as error
		return (HttpStatus.Series.CLIENT_ERROR.equals(statusSeries) || 
				HttpStatus.Series.SERVER_ERROR.equals(statusSeries));
	}

}
