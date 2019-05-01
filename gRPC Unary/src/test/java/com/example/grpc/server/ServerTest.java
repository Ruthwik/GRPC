package com.example.grpc.server;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.grpc.CalculatorServiceGrpc;
import com.example.grpc.SumRequest;
import com.example.grpc.SumResponse;
import com.example.grpc.CalculatorServiceGrpc.CalculatorServiceBlockingStub;

import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;


public class ServerTest {

	/**
	   * This rule manages automatic graceful shutdown for the registered servers and channels at the
	   * end of test.
	   */
	  @Rule
	  public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

	  /**
	   * To test the server, make calls with a real stub using the in-process channel, and verify
	   * behaviors or state changes from the client side.
	   */
	  @Test
	  public void greeterImpl_replyMessage() throws Exception {
	    // Generate a unique in-process server name.
	    String serverName = InProcessServerBuilder.generateName();

	    // Create a server, add service, start, and register for automatic graceful shutdown.
	    grpcCleanup.register(InProcessServerBuilder
	        .forName(serverName).directExecutor().addService(new ServiceImpl()).build().start());

	     CalculatorServiceBlockingStub blockingStub = CalculatorServiceGrpc.newBlockingStub(
	        // Create a client channel and register for automatic graceful shutdown.
	        grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build()));

			SumResponse response = blockingStub.sum(SumRequest.newBuilder().setFirstNumber(10).setSecondNumber(25).build());

			assertEquals(35, response.getSumResult());
	  }
	  
}
