package test.core.lifecycle;

public class NetworkClient {

    //접속 url
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
        System.out.println("========================");
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

}
