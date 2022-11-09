package com.basilisk.controller;

import com.basilisk.dto.product.UpsertProductDTO;
import com.basilisk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String productName,
                        Model model) {
        var grid = service.getProductGrid(page, productName);
        Long totalPages = service.getTotalPages(productName);
        page = (totalPages > 0) ? page : 0;
        model.addAttribute("grid", grid);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("productName", productName);
        return "product/product-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id, Model model) {
        UpsertProductDTO dto = new UpsertProductDTO();
        String actionType = "Insert";
        if (id != null && id != 0) {
            dto = service.getUpdateProduct(id);
            actionType = "Update";
        }
        model.addAttribute("product", dto);
        var supplierDD = service.getSupplierDropdown();
        model.addAttribute("supplierDropdown", supplierDD);
        var categoryDD = service.getCategoryDropdown();
        model.addAttribute("categoryDropdown", categoryDD);
        model.addAttribute("actionType", actionType);
        return "product/product-form";
    }

    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("product") UpsertProductDTO dto,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            String actionType = "Insert";
            actionType = (dto.getId() != null && dto.getId() != 0) ? "Update" : actionType;
            var supplierDD = service.getSupplierDropdown();
            model.addAttribute("supplierDropdown", supplierDD);
            var categoryDD = service.getCategoryDropdown();
            model.addAttribute("categoryDropdown", categoryDD);
            return "product/product-form";
        } else {
            service.saveProduct(dto);
            return "redirect:/product/index";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id, Model model){
        Boolean successDelete = service.deleteProduct(id);
        if (successDelete){
            return "redirect:/product/index";
        }
        Long totalDependentOrderDetails = service.dependentOrderDetail(id);
        model.addAttribute("dependencies",totalDependentOrderDetails);
        return "product/product-delete";
    }
}
