package enums;

public enum ArriveAt {
    SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");

    private final String arriveAt;

    ArriveAt(String arriveAt) {
        this.arriveAt = arriveAt;
    }
    public String getValueArriveAt(){
        return arriveAt;
    }
}
