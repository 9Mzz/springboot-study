package test.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    //접속 url
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    // getter + setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    // getter + setter

    //서비스 시작 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String messege) {
        System.out.println("call = " + url + ", messege = " + messege);

    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("connect close");
    }


    //의존관계 주입이 끝나면 호출
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    // Bean이 종료되면 호출
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
