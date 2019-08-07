package com.myfood.dishes.model.dish;

import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by rakov on 06.08.2019.
 */
public enum Status {
    NEWLY_CREATED,
    PROMOTED,
    DEPRECATED,
    APPROVED
}
