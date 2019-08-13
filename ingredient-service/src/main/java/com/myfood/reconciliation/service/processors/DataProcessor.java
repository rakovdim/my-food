package com.myfood.reconciliation.service.processors;

import com.myfood.reconciliation.model.dto.PlainEntity;

/**
 * Created by rakov on 08.08.2019.
 */

public interface DataProcessor<T> {
    T convert(PlainEntity plainEntity);


}
