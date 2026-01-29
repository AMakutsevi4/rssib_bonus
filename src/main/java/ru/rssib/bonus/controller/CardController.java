package ru.rssib.bonus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.rssib.bonus.dto.*;
import ru.rssib.bonus.service.CardService;

import java.util.List;

@RestController
@RequestMapping("api/bonus/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/addons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addons(@RequestBody AddonsRequest request) {
        cardService.addons(request);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public void withdraw(@RequestBody WithdrawRequest request) {
        cardService.withdraw(request);
    }

    @PostMapping("/refund")
    @ResponseStatus(HttpStatus.CREATED)
    public void refund(@RequestBody RefundRequest request) {
        cardService.refund(request);
    }

    @GetMapping("/getBalance/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public BalanceResponse getApplicationHistory(@PathVariable String cardNumber) {
        return cardService.getBalance(cardNumber);
    }

    @GetMapping("/getHistory/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationResponse> getHistory(@PathVariable String cardNumber) {
        return cardService.getHistory(cardNumber);
    }
}
