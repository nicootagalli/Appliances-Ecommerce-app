package com.nicodev.sale_service.controller;

import com.nicodev.sale_service.dto.SaleDTO;
import com.nicodev.sale_service.model.Sale;
import com.nicodev.sale_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    //POST
    @PostMapping("/save")
    public ResponseEntity<String> saveSale(@RequestBody Sale sale){
        saleService.saveSale(sale);
        return ResponseEntity.created(URI.create("/sale/save")).body("Sale created successfully");
    }

    // FIND ALL
    @GetMapping("/find")
    public  ResponseEntity<List<SaleDTO>> getSales(){
        return ResponseEntity.ok(saleService.getSales());
    }

    // FIND BY ID
    @GetMapping("/find/{sale_id}")
    public ResponseEntity<SaleDTO> findSale(@PathVariable Long sale_id){
        return ResponseEntity.ok(saleService.findSaleDTO(sale_id));
    }

    // EDIT
    @PutMapping("/edit/{sale_id}")
    public ResponseEntity<SaleDTO> editSale(@PathVariable Long sale_id,
                                            @RequestBody Sale sale){
        return ResponseEntity.ok(saleService.editSale(sale_id,sale));
    }

    // DELETE
    @DeleteMapping("/delete/{sale_id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long sale_id){
        saleService.deleteSale(sale_id);
        return ResponseEntity.noContent().build();
    }

}
