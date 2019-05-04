package com.example.grpc.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.calculator.CalculatorServiceGrpc;
import com.example.calculator.ComputeAverageRequest;
import com.example.calculator.ComputeAverageResponse;

public class GrpcClient {

	private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());

	private final ManagedChannel channel;
	private final CalculatorServiceGrpc.CalculatorServiceStub asyncStub;

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
		this.asyncStub = CalculatorServiceGrpc.newStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public void computeAverage() {

		CountDownLatch latch = new CountDownLatch(1);

		StreamObserver<ComputeAverageRequest> requestObserver = asyncStub
				.computeAverage(new StreamObserver<ComputeAverageResponse>() {

					@Override
					public void onNext(ComputeAverageResponse value) {
						// we get response from the server
						logger.info("Received a response from the server");
						logger.info("Average: "+value.getAverage());

					}

					@Override
					public void onError(Throwable t) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCompleted() {
						// the server is done sending the data
						logger.info("server has completed sending the data");
						// onCompleted will be called after onNext()
						latch.countDown();

					}

				});

		logger.info("Sending message 1");
		requestObserver.onNext(ComputeAverageRequest.newBuilder().setNumber(25).build());

		logger.info("Sending message 2");
		requestObserver.onNext(ComputeAverageRequest.newBuilder().setNumber(45).build());

		logger.info("Sending message 3");
		requestObserver.onNext(ComputeAverageRequest.newBuilder().setNumber(5).build());

		logger.info("Sending message 4");
		requestObserver.onNext(ComputeAverageRequest.newBuilder().setNumber(15).build());

		// we tell the server that the client is done sending the data
		requestObserver.onCompleted();

		try {
			latch.await(3L, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		GrpcClient client = new GrpcClient("localhost", 50051);
		try {
			/* Access a service running on the local machine on port 50051 */
			client.computeAverage();
		} finally {
			client.shutdown();
		}
	}

}
