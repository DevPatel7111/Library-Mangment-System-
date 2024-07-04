package org.dev.JBDL_Lec14.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class TxnRequest {
    @NotBlank(message = "user detatin fillef ")
    private String userPhoneNumber;
    @NotBlank(message = "user detatin fillef ")
    private String BookNo;
}
