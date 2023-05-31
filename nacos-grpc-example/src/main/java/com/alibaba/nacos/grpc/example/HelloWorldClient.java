package com.alibaba.nacos.grpc.example;

import io.grpc.Attributes;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.alibaba.nacos.grpc.internal.NacosNameResolverProvider;
import com.alibaba.nacos.grpc.example.proto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class HelloWorldClient {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ManagedChannel channel;
    private final GrpcTestServiceGrpc.GrpcTestServiceBlockingStub blockingStub;

    public HelloWorldClient(int port, URI uri, String nacosServiceId) {
        this(ManagedChannelBuilder.forTarget("nacos://" + nacosServiceId)
                .nameResolverFactory(new NacosNameResolverProvider(uri, Attributes.newBuilder().build()))
                .usePlaintext(true)
                .build());
    }

    public HelloWorldClient(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = GrpcTestServiceGrpc.newBlockingStub(channel);
    }

    public void reqString (String req) {
        GrpcTestService_Response_String response = blockingStub.reqString(GrpcTestService_Request_String.newBuilder().setName(req).build());
        System.out.println(response);
    }

    public static void main(String[] args) {
        int port = GrpcNacosOptions.getDescriptor().getOptions().getExtension(GrpcNacosProto.grpcNacosPort);
        URI uri = URI.create(GrpcNacosOptions.getDescriptor().getOptions().getExtension(GrpcNacosProto.nacosUri));
        HelloWorldClient client = new HelloWorldClient(port, uri, "com.alibaba.nacos.grpc.example.proto.GrpcTestService");
        client.reqString("AAA");
    }
}