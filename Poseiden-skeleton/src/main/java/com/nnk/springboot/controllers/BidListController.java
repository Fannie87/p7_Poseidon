package com.nnk.springboot.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;


@Controller
public class BidListController {
	@Autowired
	public BidListRepository bidListRepository;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
    	List<BidList> bidList = bidListRepository.findAll();
    	model.addAttribute("bidLists", bidList);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	if (bid.getAccount().isBlank()) {
    		result.rejectValue("account", null, "Please field the account");
		}
    	if (bid.getType().isBlank()) {
    		result.rejectValue("type", null, "Please field the type");
		}
    	if (bid.getBidQuantity()== null) {
    		result.rejectValue("bidQuantity", null, "Please field the bid quantity");
		}
    	if (result.hasErrors()) {
            return "bidList/add";
		}
    	
    	bidListRepository.save(bid);
        return "redirect:list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	BidList bidList = bidListRepository.getOne(id);
    	model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	
    	if (bidList.getAccount().isBlank()) {
    		result.rejectValue("account", null, "Please field the account");
		}
    	if (bidList.getType().isBlank()) {
    		result.rejectValue("type", null, "Please field the type");
		}
    	if (bidList.getBidQuantity()== null) {
    		result.rejectValue("bidQuantity", null, "Please field the bid quantity");
		}
    	if (result.hasErrors()) {
            return "bidList/update";
		} 
    	
    	bidListRepository.save(bidList);
    	
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	bidListRepository.deleteById(id);
        return "redirect:/bidList/list";
    }
}
