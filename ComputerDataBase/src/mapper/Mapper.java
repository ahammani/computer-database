package mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Mapper {

	public static Timestamp LocalDateTimeToTimestamp(LocalDateTime ldt) {
		return Timestamp.valueOf(ldt);
	}

	public static LocalDateTime TimestampToLocalDateTime(Timestamp ts) {
		return ts.toLocalDateTime();
	}

}
