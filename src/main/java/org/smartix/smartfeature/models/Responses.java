package org.smartix.smartfeature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responses {
    private Integer responseCode;
    private String responseMessage;
}
