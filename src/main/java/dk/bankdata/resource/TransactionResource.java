package dk.bankdata.resource;

import dk.bankdata.model.Transaction;
import dk.bankdata.model.TransactionCategory;
import dk.bankdata.service.TransactionService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("transaction")
public class TransactionResource {

  @Inject TransactionService transactionService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{accountId}")
  @Transactional
  public List<Transaction> getTransactionsForAccount(@PathParam("accountId") String accountId) {
    return transactionService.getTransactionsForAccount(accountId);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/fetchByCategory/{category}")
  @Transactional
  public List<Transaction> getTransactionsForCategory(@HeaderParam("accountId") String accountId, @PathParam("category") TransactionCategory category) {
    List<Transaction> trans = transactionService.getTransactionsForAccountByCategory(accountId, category);
    return trans;
    // return trans.stream().filter(t -> t.getTransactionCategory() == category).collect(Collectors.toList());
  }
}
