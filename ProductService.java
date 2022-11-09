package com.basilisk.service;

import com.basilisk.dto.product.ProductGridDTO;
import com.basilisk.dto.product.UpsertProductDTO;
import com.basilisk.dto.utility.DropdownDTO;

import java.util.List;

public interface ProductService {

    public List<ProductGridDTO> getProductGrid(Integer page,String productName);
    public Long getTotalPages(String productName);
    public Long saveProduct(UpsertProductDTO dto);
    public List<DropdownDTO> getCategoryDropdown();
    public List<DropdownDTO> getSupplierDropdown();

    public UpsertProductDTO getUpdateProduct(Long id);
    public Boolean deleteProduct(Long id);
    public Long dependentOrderDetail(Long id);
}
