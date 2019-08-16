package com.myfood.ingredients.events;

public class Events {
    public abstract static class EventBase {
        private Long ingredientId;

        private EventBase(Long ingredientId) {
            this.ingredientId = ingredientId;
        }

        public Long getIngredientId() {
            return ingredientId;
        }
    }

    public static class Deleted extends EventBase {
        private Deleted(Long id) {
            super(id);
        }
    }

    public static Deleted deleted(Long id) {
        return new Deleted(id);
    }

}
