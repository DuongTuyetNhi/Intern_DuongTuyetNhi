package enums;

public enum DepartFrom {
    SAI_GON("Sài Gòn"),
    PHAN_THIET("Phan Thiết"),
    NHA_TRANG("Nha Trang"),
    DA_NANG("Đà Nẵng"),
    HUE("Huế"),
    QUANG_NGAI("Quảng Ngãi");

    private final String departFrom;

    DepartFrom(String departFrom) {
        this.departFrom = departFrom;
    }
    public String getValueDepartFrom(){
        return departFrom;
    }
}
