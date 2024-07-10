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
