package com.nicodev.sale_service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SaleDTO {

    private Long sale_id;
    private LocalDate date;
    private CartDTO cart;

}
