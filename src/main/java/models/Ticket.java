package models;

import enums.Amount;
import enums.ArriveAt;
import enums.DepartFrom;
import enums.SeatType;

public class Ticket {
    String departDate;
    DepartFrom departFrom;
    ArriveAt arriveAt;
    SeatType seatType;
    Amount amount;

    public Ticket(String departDate, DepartFrom departFrom, ArriveAt arriveAt, SeatType seatType, Amount amount) {
        this.departDate = departDate;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.seatType = seatType;
        this.amount = amount;
    }
    public Ticket(DepartFrom departFrom, ArriveAt arriveAt, SeatType seatType, String departDate, Amount amount){
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.seatType = seatType;
        this.departDate = departDate;
        this.amount = amount;
    }


    public String getDepartDate() {
        return departDate;
    }

    public DepartFrom getDepartFrom() {
        return departFrom;
    }

    public ArriveAt getArriveAt() {
        return arriveAt;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public Amount getAmount() {
        return amount;
    }
}
