package com.nicodev.sale_service.service;

import com.nicodev.sale_service.dto.SaleDTO;
import com.nicodev.sale_service.model.Sale;

import java.util.List;

public interface ISaleService {

    // Post
    public void saveSale(Sale sale);

    // FIND ALL
    public List<SaleDTO> getSales();

    //FIND BY ID
    public SaleDTO findSaleDTO(Long sale_id);

    // EDIT
    public SaleDTO editSale(Long sale_id, Sale sale);

    // DELETE
    public void deleteSale(Long sale_id);

}
