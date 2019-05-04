package com.example.grpc.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.grpc.PrimeNumberDecompositionRequest;
import com.example.grpc.PrimeNumberDecompositionResponse;
import com.example.grpc.CalculatorServiceGrpc.CalculatorServiceImplBase;

import io.grpc.stub.StreamObserver;

public class ServiceImpl extends CalculatorServiceImplBase {

	private static final Logger logger = Logger.getLogger(ServiceImpl.class.getName());
	
	@Override
	public void primeNumberDecomposition(PrimeNumberDecompositionRequest request,
			StreamObserver<PrimeNumberDecompositionResponse> responseObserver) {
		
		Integer number = request.getNumber();
		Integer divisor =2;
		
		while(number>1) {
			if(number % divisor ==0) {
				number = number /divisor;
				logger.info("divisor: "+divisor);
				responseObserver.onNext(PrimeNumberDecompositionResponse.newBuilder()
						.setPrimeFactor(divisor)
						.build());
			}else {
				divisor = divisor +1;
			}
		}
		
		responseObserver.onCompleted();
	}

}
