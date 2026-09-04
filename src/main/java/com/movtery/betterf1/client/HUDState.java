package com.movtery.betterf1.client;

public enum HUDState {
    ALL_VISIBLE {
        @Override
        public HUDState next() {
            return NO_HUD;
        }
    }, NO_HUD {
        @Override
        public HUDState next() {
            return ALL_HIDDEN;
        }
    }, ALL_HIDDEN {
        @Override
        public HUDState next() {
            return ALL_VISIBLE;
        }
    };

    public abstract HUDState next();
}
