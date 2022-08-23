package com.example.farmingproject.web;

import com.example.farmingproject.domain.Sale;
import com.example.farmingproject.jpql.HighestCustomer;
import com.example.farmingproject.service.SaleService;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class SaleController {

    private SaleService saleService;

    @GetMapping(value = "/sales")
    public String showAllSales(Model model) {
        List<Sale> sales = saleService.findAllSales();
        model.addAttribute("sales", sales);
        return "sale/sales";
    }

    @GetMapping("/sales/new")
    public String showNewSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("pageTitle", "Add New Sale");
        return "sale/sale_form";
    }

    @PostMapping("/sales/save")
    public String saveSale(Sale sale, RedirectAttributes redirectAttributes) {
        saleService.saveSale(sale);
        redirectAttributes.addFlashAttribute("message", "The sale has been saved successfully!");
        return "redirect:/sales";
    }

    @GetMapping("/sales/edit/{id}")
    public String showSaleEditForm(@PathVariable("id") Integer id, Model model) {
        Sale sale = saleService.findSaleById(id);
        model.addAttribute("sale", sale);
        model.addAttribute("pageTitle", "Edit Sale (ID: " + id + ")");
        return "sale/sale_form";
    }

    @GetMapping("/sales/delete/{id}")
    public String deleteSale(@PathVariable("id") Integer id, Model model,
                             RedirectAttributes redirectAttributes) {
        saleService.deleteSale(id);
        redirectAttributes.addFlashAttribute("message", "The sale ID " + id + " has been deleted!");
        return "redirect:/sales";
    }

    @GetMapping("/sales/highestCustomer")
    public String showCustomerSpentTheMost(Model model) {
        Set<HighestCustomer> highestCustomer = saleService.findTheHighestCustomer();
        model.addAttribute("highestCustomer", highestCustomer);
        model.addAttribute("pageTitle", "Customer spent the most on sale");
        return "sale/customer_spent_the_most";
    }
}
