package com.techbank.account.cmd.api.commands;

import com.techbank.account.cmd.domain.AccountAggregate;
import com.techbank.cqrs.core.handlers.EventSouringHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandHandler implements CommandHandler{

    @Autowired
    private EventSouringHandler<AccountAggregate> eventSouringHandler;

    @Override
    public void handle(OpenAccountCommand command) {
        AccountAggregate aggregate = new AccountAggregate(command);
        eventSouringHandler.save(aggregate);
    }

    @Override
    public void handle(DepositFundsCommand command) {
        AccountAggregate aggregate = eventSouringHandler.getById(command.getId());
        aggregate.depositFunds(command.getAmount());
        eventSouringHandler.save(aggregate);
    }

    @Override
    public void handle(WithdrawFundsCommand command) {
        AccountAggregate aggregate = eventSouringHandler.getById(command.getId());
        if(command.getAmount() > aggregate.getBalance()) {
            throw new IllegalStateException("Withdrawal declined, insufficient funds!");
        }
        aggregate.withdrawFunds(command.getAmount());
        eventSouringHandler.save(aggregate);
    }

    @Override
    public void handle(CloseAccountCommand command) {
        AccountAggregate aggregate = eventSouringHandler.getById(command.getId());
        aggregate.closeAccount();
        eventSouringHandler.save(aggregate);
    }

    @Override
    public void handle(RestoreReadDbCommand command) {
        eventSouringHandler.republishEvents();
    }
}
