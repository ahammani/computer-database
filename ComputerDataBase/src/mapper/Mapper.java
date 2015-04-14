package mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Mapper {

	public static Timestamp LocalDateTimeToTimestamp(LocalDateTime ldt) {
		if (ldt != null)
			return Timestamp.valueOf(ldt);
		else
			return null;
	}

	public static LocalDateTime TimestampToLocalDateTime(Timestamp ts) {
		if (ts != null)
			return ts.toLocalDateTime();
		else
			return null;
	}

}
