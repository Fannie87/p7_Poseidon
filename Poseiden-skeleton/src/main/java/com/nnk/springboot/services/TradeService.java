package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

	@Autowired
	public TradeRepository tradeRepository;
	
    public String home(Model model)
    {
    	List<Trade> trade = tradeRepository.findAll();
    	model.addAttribute("trades", trade);
    	return "trade/list";
    }


    public String validate(@Valid Trade trade, BindingResult result, Model model) {

    	if (trade.getAccount().isBlank()) {
    		result.rejectValue("account", null, "Account is mandatory");
		}
    	if (trade.getType().isBlank()) {
    		result.rejectValue("type", null, "Type is mandatory");
		}
    	if (trade.getBuyQuantity()== null) {
    		result.rejectValue("buyQuantity", null, "The quantity bought is mandatory");
		}
    	if (result.hasErrors()) {
            return "trade/add";
		}
    	tradeRepository.save(trade);
    	return "redirect:/trade/list";
    }
    

    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Trade trade = tradeRepository.getReferenceById(id);
    	model.addAttribute("trade", trade);
    	return "trade/update";
    }

    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	if (trade.getTradeId()==null) {
    		result.rejectValue("tradeId", null, "Must not be null");
    	}
    	if (trade.getAccount().isBlank()) {
    		result.rejectValue("account", null, "Account is mandatory");
		}
    	if (trade.getType().isBlank()) {
    		result.rejectValue("type", null, "Type is mandatory");
		}
    	if (trade.getBuyQuantity()== null) {
    		result.rejectValue("buyQuantity", null, "The quantity bought is mandatory");
		}
    	if (result.hasErrors()) {
            return "trade/update";
		}
    	tradeRepository.save(trade);
    	return "redirect:/trade/list";
    }

    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	tradeRepository.deleteById(id);
    	return "redirect:/trade/list";
    }
}
