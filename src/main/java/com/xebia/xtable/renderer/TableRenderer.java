package com.xebia.xtable.renderer;

public interface TableRenderer {
    static ConsoleBasedRenderer getConsoleBasedRenderer() {
        return new ConsoleBasedRenderer();
    }
    void render(String table);
}
