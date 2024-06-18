package models;

import enums.*;

public class Ticket {
    String departDate;
    Locations departFrom;
    Locations arriveAt;
    SeatType seatType;
    Amount amount;

    public Ticket(String departDate, Locations departFrom, Locations arriveAt, SeatType seatType, Amount amount) {
        this.departDate = departDate;
        this.departFrom = departFrom;
        this.arriveAt = arriveAt;
        this.seatType = seatType;
        this.amount = amount;
    }

    public String getDepartDate() {
        return departDate;
    }

    public Locations getDepartFrom() {
        return departFrom;
    }

    public Locations getArriveAt() {
        return arriveAt;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public Amount getAmount() {
        return amount;
    }
}
