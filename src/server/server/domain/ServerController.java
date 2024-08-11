package server.server.domain;

import server.client.domain.ClientController;
import server.server.repository.Log;
import server.server.ui.ServerView;

import java.util.ArrayList;

public class ServerController {
    private ArrayList<ClientController> clientCList = new ArrayList<>();
    private boolean work = false;
    private ServerView serverView;
    private Log reposytory;

    public ServerController(ServerView serverView, Log reposytory) {
        this.serverView = serverView;
        this.reposytory = reposytory;
        serverView.setServerController(this);


    }

    public boolean connectUser(ClientController clientC){
        if (!work){
            return false;
        }
        clientCList.add(clientC);
        serverView.showMessage(clientC.getName() + " - подключился");
        return true;
    }

    public void disconnectUser(ClientController clientC){
        clientCList.remove(clientC);
        if (clientC != null){
            clientC.disconnectedFromServer();
        }
    }

    public void showMessage(String text){
        if (!work){
            return;
        }
        serverView.showMessage(text);
        answerAll(text);
        reposytory.saveInLog(text);
    }

    private void answerAll(String text){
        for (ClientController clientC: clientCList){
            clientC.answerFromServer(text);
        }
    }

    public String getHistory() {
        return reposytory.readLog();
    }

    public void start() {
        if (work){
            serverView.showMessage("Сервер уже был запущен");
        } else {
            work = true;
            serverView.showMessage(getHistory());
            serverView.showMessage("Сервер запущен!");
        }
    }

    public void stop () {
        if (!work){
            serverView.showMessage("Сервер уже был остановлен");
        } else {
            work = false;
            while (!clientCList.isEmpty()){
                disconnectUser(clientCList.get(clientCList.size()-1));
            }
            serverView.showMessage("Сервер остановлен!");
        }
    }
}
