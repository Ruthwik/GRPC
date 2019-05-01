package com.example.grpc.client;

import java.util.concurrent.TimeUnit;

import com.example.grpc.CalculatorServiceGrpc;
import com.example.grpc.SumRequest;
import com.example.grpc.SumResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrpcClient {

	private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());

	private final ManagedChannel channel;
	private final CalculatorServiceGrpc.CalculatorServiceBlockingStub blockingStub;

	/** Construct client connecting to server at {@code host:port}. */
	public GrpcClient(String host, int port) {

		this(ManagedChannelBuilder.forAddress(host, port)
				// Channels are secure by default (via SSL/TLS). For the example we disable TLS
				// to avoid
				// needing certificates.
				.usePlaintext().build());
	}

	/** Construct client for accessing server using the existing channel. */
	GrpcClient(ManagedChannel channel) {
		this.channel = channel;
		this.blockingStub = CalculatorServiceGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public void addNumbers(int firstNumber, int secondNumber) {

		SumRequest request = SumRequest.newBuilder().setFirstNumber(firstNumber).setSecondNumber(secondNumber).build();
		SumResponse response =null;
		try {
			response = blockingStub.sum(request);
		} catch (StatusRuntimeException e) {
			logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
			return;
		}

		 logger.info(request.getFirstNumber() + " + " + request.getSecondNumber() + " = " + response.getSumResult());

	}

	public static void main(String[] args) throws Exception {
		GrpcClient client = new GrpcClient("localhost", 50051);
		try {
			/* Access a service running on the local machine on port 50051 */
			client.addNumbers(10,25);
		} finally {
			client.shutdown();
		}
	}

}
