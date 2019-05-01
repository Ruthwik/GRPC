package com.example.grpc.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.grpc.SumRequest;
import com.example.grpc.SumResponse;
import com.example.grpc.CalculatorServiceGrpc.CalculatorServiceImplBase;

import io.grpc.stub.StreamObserver;

public class ServiceImpl extends CalculatorServiceImplBase {

	private static final Logger logger = Logger.getLogger(ServiceImpl.class.getName());
	
	@Override
	public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {

		SumResponse sumResponse = SumResponse.newBuilder()
				.setSumResult(request.getFirstNumber() + request.getSecondNumber()).build();
		
		logger.log(Level.INFO, "Sending sum response {}", sumResponse.getSumResult());
		
		responseObserver.onNext(sumResponse);
		
		responseObserver.onCompleted();

	}

}
