package core.basesyntax.service.strategy;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.dao.impl.FruitDaoImpl;
import core.basesyntax.model.Fruit;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.validator.Validator;

public class OperationHandlerOfReturn implements OperationHandler {
    private final Validator validator;
    private final FruitDao storageDao;

    public OperationHandlerOfReturn() {
        this.storageDao = new FruitDaoImpl();
        this.validator = new Validator();
    }

    @Override
    public void update(FruitTransaction fruitTransaction) {
        validator.validateFruitTransaction(fruitTransaction);
        Fruit fruit = storageDao.get(fruitTransaction.getFruit());
        int fruitAmount = fruit.getQuantity();
        fruit.setQuantity(fruitAmount + fruitTransaction.getQuantity());
    }
}
