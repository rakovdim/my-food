package com.myfood.ingredient.events;

public class Events {
    public abstract static class EventBase {
        private Long id;

        private EventBase(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
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
