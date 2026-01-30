package ru.rssib.bonus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.rssib.bonus.dto.AddonsRequest;
import ru.rssib.bonus.dto.WithdrawRequest;
import ru.rssib.bonus.dto.RefundRequest;
import ru.rssib.bonus.dto.BalanceResponse;
import ru.rssib.bonus.dto.OperationResponse;
import ru.rssib.bonus.service.CardService;

import java.util.List;

@RestController
@RequestMapping("api/bonus/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PreAuthorize(value = "hasRole('WRITER')")
    @PostMapping("/addons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addons(@RequestBody AddonsRequest request) {
        cardService.addons(request);
    }

    @PreAuthorize(value = "hasRole('WRITER')")
    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public void withdraw(@RequestBody WithdrawRequest request) {
        cardService.withdraw(request);
    }

    @PreAuthorize(value = "hasRole('WRITER')")
    @PostMapping("/refund")
    @ResponseStatus(HttpStatus.CREATED)
    public void refund(@RequestBody RefundRequest request) {
        cardService.refund(request);
    }

    @PreAuthorize(value = "hasRole('READER')")
    @GetMapping("/getBalance/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public BalanceResponse getApplicationHistory(@PathVariable String cardNumber) {
        return cardService.getBalance(cardNumber);
    }

    @PreAuthorize(value = "hasRole('READER')")
    @GetMapping("/getHistory/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<OperationResponse> getHistory(@PathVariable String cardNumber) {
        return cardService.getHistory(cardNumber);
    }
}
