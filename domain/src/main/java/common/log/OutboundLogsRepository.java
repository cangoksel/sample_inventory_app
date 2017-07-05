package common.log;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

/**
 * Created by mersoy on 8.03.2017.
 */
public interface OutboundLogsRepository extends MongoRepository<OutboundLogs, BigInteger> {
}
