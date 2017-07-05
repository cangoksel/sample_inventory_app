package com.github.cangoksel.common.log;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

/**
 * Created by mersoy on 8.03.2017.
 */
public interface InboundLogsRepository extends MongoRepository<InboundLogs, BigInteger> {
}
