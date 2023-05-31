package com.alibaba.nacos.grpc.example.impl;

import io.grpc.stub.StreamObserver;
import com.alibaba.nacos.grpc.example.proto.GrpcTestServiceGrpc;
import com.alibaba.nacos.grpc.example.proto.GrpcTestService_Request_String;
import com.alibaba.nacos.grpc.example.proto.GrpcTestService_Response_String;

public class GrpcTestServiceImpl extends GrpcTestServiceGrpc.GrpcTestServiceImplBase {

    @Override
    public void reqString(GrpcTestService_Request_String request,
                          StreamObserver<GrpcTestService_Response_String> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder().setResult("success:" + name).build());
        responseObserver.onCompleted();
    }

    @Override
    public void reqStringServerStream(GrpcTestService_Request_String request,
                                      StreamObserver<GrpcTestService_Response_String> responseObserver) {
        String name = request.getName();
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder().setResult("success_1:" + name).build());
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder().setResult("success_2:" + name).build());
        responseObserver.onNext(GrpcTestService_Response_String.newBuilder().setResult("success_3:" + name).build());
        responseObserver.onCompleted();
    }
}