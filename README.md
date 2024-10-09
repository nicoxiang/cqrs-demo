# cqrs-demo

## Project structure

```

|-cqrs
|-bank-account
  |-account-cmd
    |-api  --contain commands, rest controllers, dto
    |-domain  --contain account aggregate, event store repository
    |-infrastructure  --contain infrastructure related code such as the command dispatcher, event producer, event souring handler, event store implementation
  |-account-common
    |-events
    |-dto
  |-account-query
    |-api  --contain queries, rest controllers, dto
    |-domain  --contain account repository, bank account domain entity
    |-infrastructure  --contain infrastructure related code such as the query dispatcher, the consumer implementation, handlers

```

## Messages
Command and event objects are known as message objects, and both requires a unique identifier.

### What is a Command
A command is a combination of expressed intent.
In other words, it describes something that you want to be done.
It also contains the information required to undertake action based on that intent. Commands are named with a verb in the imperative mood. For example, open a card command or deposit funds command.

