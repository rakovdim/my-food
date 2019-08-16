package com.myfood.ingredients.events;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


@Log4j2
@Component
public class TransactionalEventManagerStub implements EventManager {

    @Override
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = Events.Deleted.class)
    public void publishDeleteEvent(Long ingredientId) {
        log.debug("Ingredient: " + ingredientId + " is marked for deletion");
    }
}
