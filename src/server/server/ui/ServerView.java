package server.server.ui;

import server.server.domain.ServerController;

public interface ServerView {
    void showMessage(String text);
    void setServerController(ServerController serverController);
}
