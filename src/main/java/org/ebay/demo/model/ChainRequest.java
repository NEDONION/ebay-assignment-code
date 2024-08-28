package org.ebay.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChainRequest {
	@JsonProperty("initial_value")
	private Number initialValue;

	List<OperationRequest> operations;
}
