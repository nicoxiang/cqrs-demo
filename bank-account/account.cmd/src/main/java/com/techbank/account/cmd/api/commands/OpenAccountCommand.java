package com.techbank.account.cmd.api.commands;

import com.techbank.account.common.dto.AccountType;
import com.techbank.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    /**
     * full name of the account holder
     */
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
