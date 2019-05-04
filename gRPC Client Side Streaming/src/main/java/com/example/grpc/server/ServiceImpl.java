package com.example.grpc.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.calculator.ComputeAverageRequest;
import com.example.calculator.ComputeAverageResponse;
import com.example.calculator.CalculatorServiceGrpc.CalculatorServiceImplBase;

import io.grpc.stub.StreamObserver;

public class ServiceImpl extends CalculatorServiceImplBase {

	private static final Logger logger = Logger.getLogger(ServiceImpl.class.getName());
	
	@Override
	public StreamObserver<ComputeAverageRequest> computeAverage(
			StreamObserver<ComputeAverageResponse> responseObserver) {
		StreamObserver<ComputeAverageRequest>  requestObserver = new StreamObserver<ComputeAverageRequest>() {
			//running sum and count
			int sum = 0;
			int count = 0 ;
			
			@Override
			public void onNext(ComputeAverageRequest value) {
				//increment the sum
				sum+= value.getNumber();
				//increment the count
				count +=1;
				
			}
			
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCompleted() {
				double average = (double) sum/count;
				// client is done
				//this is when we want to return a response (responseObserver)
				responseObserver.onNext(
						ComputeAverageResponse.newBuilder()
						.setAverage(average).build());
				
				responseObserver.onCompleted();
				
				
			}
		};
		
		
		
		return requestObserver;
	}

}
