package com.example.grpc.server;

import java.io.IOException;
import java.util.logging.Logger;

import io.grpc.ServerBuilder;
import io.grpc.Server;

public class GrpcServer {

	private static final Logger logger = Logger.getLogger(GrpcServer.class.getName());
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
		Server server = ServerBuilder.forPort(50051)
				.addService(new ServiceImpl())
				.build();
		
		server.start();
		logger.info("Server started, listening on " + 50051);
		
		Runtime.getRuntime().addShutdownHook(new Thread( () -> {
			logger.info("Received shutdown Request");
			server.shutdown();
			logger.info("successfully stopped the server");
		}));
		
		server.awaitTermination();
	}
}
