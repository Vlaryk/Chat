package server.server.repository;

public interface RepositoryInterface {

    void saveInLog(String text);

    String readLog();


}
