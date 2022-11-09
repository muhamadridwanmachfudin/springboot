package com.basilisk.service;

import com.basilisk.dao.CategoryRepository;
import com.basilisk.dao.OrderDetailRepository;
import com.basilisk.dao.ProductRepository;
import com.basilisk.dao.SupplierRepository;
import com.basilisk.dto.category.UpsertCategoryDTO;
import com.basilisk.dto.product.ProductGridDTO;
import com.basilisk.dto.product.UpsertProductDTO;
import com.basilisk.dto.utility.DropdownDTO;
import com.basilisk.entity.Category;
import com.basilisk.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private final int rowsInPage = 10;

    @Override
    public List<ProductGridDTO> getProductGrid(Integer page, String productName) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        var grid = productRepository.findAll(productName, pagination);
        return grid;
    }

    @Override
    public Long getTotalPages(String productName) {
        double totalData = (double) productRepository.count(productName);
        long totalPage = (long) (Math.ceil(totalData / (double) rowsInPage));
        return totalPage;
    }

    @Override
    public Long saveProduct(UpsertProductDTO dto) {
        Product entity = new Product(dto.getId(),
                dto.getName(),
                dto.getSupplierId(),
                dto.getCategoryId(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getStock(),
                dto.getOnOrder(),
                dto.getDiscontinue());
        Product respond = productRepository.save(entity);
        return respond.getId();
    }

    @Override
    public List<DropdownDTO> getCategoryDropdown() {
        return categoryRepository.findAllOrderByName();
    }

    @Override
    public List<DropdownDTO> getSupplierDropdown() {
        return supplierRepository.findAllOrderByCompanyName();
    }

    @Override
    public UpsertProductDTO getUpdateProduct(Long id) {
        Optional<Product> nullableEntity = productRepository.findById(id);
        Product entity = nullableEntity.get();
        UpsertProductDTO dto = new UpsertProductDTO(entity.getId(),
                entity.getName(),
                entity.getSupplierId(),
                entity.getCategoryId(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock(),
                entity.getOnOrder(),
                entity.getDiscontinue());
        return dto;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Long totalDependentOrderDetail = dependentOrderDetail(id);
        if (totalDependentOrderDetail == 0){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Long dependentOrderDetail(Long id) {
        Long totalDependentOrderDetail = orderDetailRepository.countByProductId(id);
        return totalDependentOrderDetail;
    }

}
