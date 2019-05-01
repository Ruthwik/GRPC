package com.example.grpc.client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;

import com.example.grpc.CalculatorServiceGrpc;
import com.example.grpc.SumRequest;
import com.example.grpc.SumResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.delegatesTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.stub.StreamObserver;
import io.grpc.testing.GrpcCleanupRule;

public class ClientTest {

	/**
	   * This rule manages automatic graceful shutdown for the registered servers and channels at the
	   * end of test.
	   */
	  @Rule
	  public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

	  private final CalculatorServiceGrpc.CalculatorServiceImplBase serviceImpl =
	      mock(CalculatorServiceGrpc.CalculatorServiceImplBase.class, delegatesTo(new CalculatorServiceGrpc.CalculatorServiceImplBase() {}));
	  
	  private GrpcClient client;

	  @Before
	  public void setUp() throws Exception {
	    // Generate a unique in-process server name.
	    String serverName = InProcessServerBuilder.generateName();

	    // Create a server, add service, start, and register for automatic graceful shutdown.
	    grpcCleanup.register(InProcessServerBuilder
	        .forName(serverName).directExecutor().addService(serviceImpl).build().start());

	    // Create a client channel and register for automatic graceful shutdown.
	    ManagedChannel channel = grpcCleanup.register(
	        InProcessChannelBuilder.forName(serverName).directExecutor().build());

	    // Create a Client using the in-process channel;
	    client = new GrpcClient(channel);
	  }

	  /**
	   * To test the client, call from the client against the fake server, and verify behaviors or state
	   * changes from the server side.
	   */
	  @Test
	  public void greet_messageDeliveredToServer() {
	    ArgumentCaptor<SumRequest> requestCaptor = ArgumentCaptor.forClass(SumRequest.class);

	    client.addNumbers(10, 40);

	    verify(serviceImpl)
	        .sum(requestCaptor.capture(), ArgumentMatchers.<StreamObserver<SumResponse>>any());
	    
	    assertEquals(10, requestCaptor.getValue().getFirstNumber());
	    assertEquals(40, requestCaptor.getValue().getSecondNumber());
	  }

}
