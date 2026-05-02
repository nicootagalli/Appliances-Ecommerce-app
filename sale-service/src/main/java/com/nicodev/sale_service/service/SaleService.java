package com.nicodev.sale_service.service;

import com.nicodev.sale_service.dto.CartDTO;
import com.nicodev.sale_service.dto.SaleDTO;
import com.nicodev.sale_service.exception.BadRequestException;
import com.nicodev.sale_service.exception.NotFoundException;
import com.nicodev.sale_service.mapper.SaleMapper;
import com.nicodev.sale_service.model.Sale;
import com.nicodev.sale_service.repository.ICartAPI;
import com.nicodev.sale_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleService implements ISaleService{

    private final ISaleRepository saleRepository;

    private final ICartAPI cartAPI;

    private final CartServiceClient cartServiceClient;

    // POST
    @Override
    public void saveSale(Sale sale) {

        // Verify Date data
        if (sale.getDate() == null) {
            throw new BadRequestException("Sale Date required");
        }

        // Verify if the cart exist using its API.
        if (!cartAPI.cartExist(sale.getCart_id())){
            throw new NotFoundException("Cart with ID: " + sale.getCart_id() + " not found");
        }

        saleRepository.save(sale);

    }

    // 1. Metodo interno sin CB
    public Sale findSaleEntity(Long sale_id){
        return saleRepository.findById(sale_id)
                .orElseThrow(()-> new NotFoundException("Sale with ID: " + sale_id + " not found"));
    }


    // 3. Metodo principal (orquestacion)
    public SaleDTO getSaleDTO(Long sale_id){
        Sale sale = findSaleEntity(sale_id);
        CartDTO cartDTO = cartServiceClient.findCartDTO(sale.getCart_id());
        SaleDTO saleDTO = SaleMapper.toDTO(sale,cartDTO);
        return saleDTO;
    }


    // FIND SALE DTO BY ID
    @Override
    @CircuitBreaker(name = "sale-service", fallbackMethod = "fallbackFindSaleDTO")
    public SaleDTO findSaleDTO(Long sale_id) {
        // find the Sale entity or throw exception
        Sale sale = saleRepository.findById(sale_id)
                .orElseThrow(()-> new NotFoundException("Sale with ID: " + sale_id + " not found"));
        // find the CartDTO usig its API and add it to the saleDTO
        CartDTO cartDTO = cartAPI.findCartDTO(sale.getCart_id());

        // convert to dto for a professional response.
        SaleDTO saleDTO = SaleMapper.toDTO(sale,cartDTO);
        return saleDTO;
    }

    public SaleDTO fallbackFindSaleDTO(Long sale_id, Throwable throwable){
        log.error("Cart service unavailable for sale_id {}: {}", sale_id, throwable.getMessage());

        SaleDTO fallbackDTO = new SaleDTO();
        fallbackDTO.setSale_id(null);
        fallbackDTO.setCart(new CartDTO());
        fallbackDTO.setDate(LocalDate.now());
        return fallbackDTO;
    }

    // FIND ALL SALE DTO
    @Override
    public List<SaleDTO> getSales() {
        // Verify if the list of sale is empty
        if (saleRepository.findAll().isEmpty()){
            throw new NotFoundException("The list of Sales is empty");
        }

        SaleDTO saleDTO = new SaleDTO();
        List<SaleDTO> saleDTOList = new ArrayList<>();

        for (Sale s : saleRepository.findAll()){
            saleDTO = this.findSaleDTO(s.getSale_id());
            saleDTOList.add(saleDTO);
        }

        return saleDTOList;
    }

    // EDIT
    @Override
    public SaleDTO editSale(Long sale_id, Sale sale) {
        // Find the Sale to edit
        Sale saleToEdit = saleRepository.findById(sale_id)
                .orElseThrow(()-> new NotFoundException("Sale with ID: " + sale_id + " not found"));

        // Verify i'm getting the values
        if (sale.getDate() == null){
            throw new BadRequestException("Sale date is required");
        }
        if (!cartAPI.cartExist(sale.getCart_id())){
            throw new NotFoundException("Cart with ID: " + sale.getCart_id() + " not found");
        }

        // If everything is ok set the new values
        saleToEdit.setDate(sale.getDate());
        saleToEdit.setCart_id(sale.getCart_id());

        // Save the sale with the new values
        this.saveSale(saleToEdit);

        // Find the sale, convert to DTO and return
        SaleDTO saleDTO = this.findSaleDTO(sale_id);

        return saleDTO;
    }

    // DELETE
    @Override
    public void deleteSale(Long sale_id) {
        // Verify if the sale exist
        if (!saleRepository.existsById(sale_id)){
            throw new NotFoundException("Sale with ID: " + sale_id + " not found");
        }

        saleRepository.deleteById(sale_id);

    }

}
