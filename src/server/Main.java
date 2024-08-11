package server;

import server.client.domain.ClientController;
import server.client.ui.ClientGUI;
import server.server.domain.ServerController;
import server.server.repository.Log;
import server.server.ui.ServerWindow;

public class Main {
    public static void main(String[] args) {

        //создание объектов сервера и создание связи между ними
        ServerController serverController = new ServerController(new ServerWindow(),new Log());

        //создание объектов клиента1 и создание связи между ними
        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClient(clientController1);
        clientController1.setServer(serverController);

        //создание объектов клиента2 и создание связи между ними
        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClient(clientController2);
        clientController2.setServer(serverController);
    }
}
