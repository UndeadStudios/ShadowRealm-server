package io.exilius.sql.eventcalendar.queries;

import java.sql.Connection;
import java.sql.SQLException;

import io.exilius.content.event.eventcalendar.ChallengeParticipant;
import io.exilius.sql.DatabaseManager;
import io.exilius.sql.DatabaseTable;
import io.exilius.sql.SqlQuery;
import io.exilius.sql.eventcalendar.tables.EventCalendarBlacklistTable;

public class AddToBlacklistQuery implements SqlQuery<Boolean> {

    private static final DatabaseTable TABLE = new EventCalendarBlacklistTable();

    private final ChallengeParticipant participant;

    public AddToBlacklistQuery(ChallengeParticipant participant) {
        this.participant = participant;
    }

    @Override
    public Boolean execute(DatabaseManager context, Connection connection) throws SQLException {
        connection.createStatement().execute("insert into " + TABLE.getName()+ " values("
               + "'" + participant.getIpAddress() + "', '" + participant.getMacAddress() + "')");
        return true;
    }

}
