package com.nicodev.sale_service.mapper;

import com.nicodev.sale_service.dto.CartDTO;
import com.nicodev.sale_service.dto.SaleDTO;
import com.nicodev.sale_service.exception.BadRequestException;
import com.nicodev.sale_service.model.Sale;

public class SaleMapper {

      public static SaleDTO toDTO(Sale sale, CartDTO cartDTO){
          if (sale == null) {
              throw new BadRequestException("Sale required");
          }

          return SaleDTO.builder()
                  .sale_id(sale.getSale_id())
                  .date(sale.getDate())
                  .cart(cartDTO)
                  .build();
      }

}
